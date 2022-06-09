package com.gd.captchaserver.service;

import com.gd.captchaserver.dto.CheckRequest;
import com.gd.captchaserver.model.CaptchaCode;
import com.gd.captchaserver.repository.CaptchaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CaptchaServiceImpl implements CaptchaService {


    private final CaptchaRepository captchaRepository;

    public CaptchaServiceImpl(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    @Override
    public CaptchaCode createCaptcha(String code) {
        CaptchaCode captchaCode = CaptchaCode.builder()
                .setCaptchaNumber(code)
                .setExpired(LocalDateTime.now().plusMinutes(30))
                .build();
        return captchaRepository.save(captchaCode);
    }

    @Override
    @Transactional
    public Boolean check(CheckRequest checkRequest) {
        CaptchaCode captchaCode = captchaRepository
                .findCaptcha(UUID.fromString(checkRequest.getIdParam()), LocalDateTime.now())
                .orElse(null);
        if (captchaCode == null) {
            return false;
        }
        captchaCode.setDisabled(true);
        return captchaCode.getCaptchaNumber().equals(checkRequest.getCaptchaParam());
    }
}