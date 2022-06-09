package com.gd.captchaserver.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "captcha")
public class CaptchaCode {

    private UUID id;
    private String captchaNumber;
    private LocalDateTime expired;
    private boolean disabled = false;
    private int version;

    public CaptchaCode(String captchaNumber, LocalDateTime expired) {
        this.captchaNumber = captchaNumber;
        this.expired = expired;
    }

    public CaptchaCode() {
    }

    public static Builder builder(){
        return new Builder();
    }
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCaptchaNumber() {
        return captchaNumber;
    }

    public void setCaptchaNumber(String captchaNumber) {
        this.captchaNumber = captchaNumber;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    @Column(columnDefinition = "boolean default false")
    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static class Builder{

        private String captchaNumber;
        private LocalDateTime expired;

        private Builder() {
        }

        public Builder setCaptchaNumber(String captchaNumber) {
            this.captchaNumber = captchaNumber;
            return this;
        }

        public Builder setExpired(LocalDateTime expired) {
            this.expired = expired;
            return this;
        }

        public CaptchaCode build(){
            return new CaptchaCode(captchaNumber,expired);
        }
    }
}
