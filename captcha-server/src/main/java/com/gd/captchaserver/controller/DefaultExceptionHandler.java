package com.gd.captchaserver.controller;

import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER= LoggerFactory.getLogger(DefaultExceptionHandler.class);
    private final MessageSourceAccessor messageSourceAccessor;

    public DefaultExceptionHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<List<ErrorMessage>> exceptionHandler(RuleException e){
        List<ErrorMessage> errorMessages=new ArrayList<>();
        for(ErrorMessage errorMessage:e.getErrorMessages()){
            errorMessages.add(ErrorMessage.error(messageSourceAccessor.getMessage(errorMessage.getMessage()),errorMessage.getCode()));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        LOGGER.error("exception: ",e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
