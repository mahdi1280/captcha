package com.gd.captchaclient;

import com.gd.captchaclient.client.CaptchaServerClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class CaptchaVerifyAspect {

    private final ObjectMapper objectMapper;
    private final CaptchaServerClient captchaService;


    public CaptchaVerifyAspect(ObjectMapper objectMapper, CaptchaServerClient captchaService) {
        this.objectMapper = objectMapper;
        this.captchaService = captchaService;
    }

    @Before("@annotation(com.gd.captchaclient.CaptchaVerify)")
    public void verifyCaptcha(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CaptchaVerify annotation = signature.getMethod().getDeclaredAnnotation(CaptchaVerify.class);
        String idParam = annotation.idParam();
        String captchaParam = annotation.captchaParam();
        idParam = objectMapper.convertValue(joinPoint.getArgs()[0], HashMap.class).get(idParam).toString();
        captchaParam = objectMapper.convertValue(joinPoint.getArgs()[0], HashMap.class).get(captchaParam).toString();
        CheckRequest build = CheckRequest.builder()
                .setIdParam(idParam)
                .setCaptchaParam(captchaParam)
                .build();
        ResponseEntity<Boolean> booleanResponseEntity = captchaService.checkCaptcha(build);
        if(Boolean.FALSE.equals(booleanResponseEntity.getBody())){
            throw new RuleException(ErrorMessage.error("captcha.not.write","captcha.not.write"));
        }
    }
}
