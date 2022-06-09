package com.gd.captchaserver.repository;

import com.gd.captchaserver.model.CaptchaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface CaptchaRepository extends JpaRepository<CaptchaCode, UUID> {

    @Query("select c from CaptchaCode c where c.id=:id and c.expired>:expired and c.disabled=false")
    Optional<CaptchaCode> findCaptcha(UUID id, LocalDateTime expired);
}
