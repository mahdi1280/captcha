package com.gd.captchaserver;

import com.gd.captchaserver.dto.CheckRequest;
import com.gd.captchaserver.dto.ResponseCaptcha;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
class CaptchaTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public CaptchaTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("test normal create captcha image")
    void normalTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(UriComponentsBuilder.fromUriString("/captcha").build()
                .toUri())).andReturn();
        ResponseCaptcha responseCaptcha = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseCaptcha.class);
        Assertions.assertNotNull(responseCaptcha.getCaptchaImage());
        Assertions.assertNotNull(responseCaptcha.getId());
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("test checkCaptcha by not valid id")
    void checkCaptcha() throws Exception {
        CheckRequest checkRequest = CheckRequest.builder()
                .idParam("adadasdas")
                .captchaParam("asdasdasdas")
                .build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(UriComponentsBuilder
                        .fromUriString("/captcha/check")
                        .build()
                        .toUri()).content(objectMapper.writeValueAsBytes(checkRequest)))
                .andReturn();
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("test by not valid number captcha")
    void tetNumberCaptcha() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(UriComponentsBuilder.fromUriString("/captcha").build()
                .toUri())).andReturn();
        ResponseCaptcha responseCaptcha = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseCaptcha.class);
        CheckRequest checkRequest = CheckRequest.builder()
                .idParam(responseCaptcha.getId())
                .captchaParam("name name name")
                .build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(UriComponentsBuilder
                                .fromUriString("/captcha/check")
                                .build()
                                .toUri())
                        .content(objectMapper.writeValueAsBytes(checkRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertFalse(objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Boolean.class));
    }
}