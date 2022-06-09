package com.gd.captchaclient.client;

import com.gd.captchaclient.CheckRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "captchaServer", url = "${captcha.server.uri}")
public interface CaptchaServerClient {

    @PostMapping("/check")
    ResponseEntity<Boolean> checkCaptcha(@RequestBody CheckRequest checkRequest);
}
