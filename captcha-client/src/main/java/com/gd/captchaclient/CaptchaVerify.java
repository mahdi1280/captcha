package com.gd.captchaclient;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaptchaVerify {

    String idParam() default "captchaId";

    String captchaParam() default "captchaParam";
}
