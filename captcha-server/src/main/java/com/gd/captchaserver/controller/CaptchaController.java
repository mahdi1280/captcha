package com.gd.captchaserver.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.gd.captchaclient.CaptchaVerify;
import com.gd.captchaserver.dto.CheckRequest;
import com.gd.captchaserver.dto.Request;
import com.gd.captchaserver.dto.ResponseCaptcha;
import com.gd.captchaserver.model.CaptchaCode;
import com.gd.captchaserver.service.CaptchaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/captcha")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping
    public ResponseEntity<ResponseCaptcha> createCaptcha() {
        List<String> captchaImage = createCaptchaImage();
        CaptchaCode captchaCode = captchaService.createCaptcha(captchaImage.get(1));
        return ResponseEntity.ok(setCaptchaResponse(captchaCode, captchaImage.get(0)));
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> check(@RequestBody CheckRequest checkRequest) {
        return ResponseEntity.ok(captchaService.check(checkRequest));
    }

    @PostMapping("/test")
    @CaptchaVerify
    public ResponseEntity<String> test(@RequestBody Request request) {
        return ResponseEntity.ok("shod");
    }

    private ResponseCaptcha setCaptchaResponse(CaptchaCode captchaCode, String s) {
        return new ResponseCaptcha(String.valueOf(captchaCode.getId()), s);
    }

    private List<String> createCaptchaImage() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(111, 36);
        List<String> cap = new ArrayList<>();
        cap.add("data:realCaptcha/jpg;base64," + captcha.getImageBase64());
        cap.add(captcha.getCode());
        return cap;
    }

}
