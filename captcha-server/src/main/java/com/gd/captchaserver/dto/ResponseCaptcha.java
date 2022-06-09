package com.gd.captchaserver.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResponseCaptcha {

    private final String id;
    private final String captchaImage;

    @JsonCreator
    public ResponseCaptcha(String id, String captchaImage) {
        this.id = id;
        this.captchaImage = captchaImage;
    }

    public String getId() {
        return id;
    }


    public String getCaptchaImage() {
        return captchaImage;
    }

}
