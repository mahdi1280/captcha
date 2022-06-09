package com.gd.captchaclient;

public class CheckRequest {

    private String idParam;
    private String captchaParam;

    public static Builder builder(){
        return new Builder();
    }
    public CheckRequest(String idParam, String captchaParam) {
        this.idParam = idParam;
        this.captchaParam = captchaParam;
    }

    public String getIdParam() {
        return idParam;
    }

    public void setIdParam(String idParam) {
        this.idParam = idParam;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public static class Builder{

        private String idParam;
        private String captchaParam;

        public Builder() {
        }

        public Builder setIdParam(String idParam) {
            this.idParam = idParam;
            return this;
        }

        public Builder setCaptchaParam(String captchaParam) {
            this.captchaParam = captchaParam;
            return this;
        }

        public CheckRequest build(){
            return new CheckRequest(idParam,captchaParam);
        }
    }
}
