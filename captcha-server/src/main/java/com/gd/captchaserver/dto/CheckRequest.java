package com.gd.captchaserver.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CheckRequest.Builder.class)
public class CheckRequest {

    private final String idParam;
    private final String captchaParam;

    public CheckRequest(String idParam, String captchaParam) {
        this.idParam = idParam;
        this.captchaParam = captchaParam;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getIdParam() {
        return idParam;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private String idParam;
        private String captchaParam;

        public Builder() {
        }

        public Builder idParam(String idParam) {
            this.idParam = idParam;
            return this;
        }

        public Builder captchaParam(String captchaParam) {
            this.captchaParam = captchaParam;
            return this;
        }

        public CheckRequest build() {
            return new CheckRequest(idParam, captchaParam);
        }
    }
}
