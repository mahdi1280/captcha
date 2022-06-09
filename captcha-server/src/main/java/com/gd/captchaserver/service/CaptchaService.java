package com.gd.captchaserver.service;

import com.gd.captchaserver.dto.CheckRequest;
import com.gd.captchaserver.model.CaptchaCode;

public interface CaptchaService {

    CaptchaCode createCaptcha(String code);

    Boolean check(CheckRequest checkRequest);
}
