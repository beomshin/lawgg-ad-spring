package com.was.admin.exception;

import com.was.admin.common.dto.GlobalCode;
import com.was.admin.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ResponseDto> handle(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException]", e);
        return exceptionRes(GlobalCode.PARAMETER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> handle(BindException e) {
        log.error("[BindException]", e);
        return exceptionRes(GlobalCode.SYSTEM_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> handle(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]", e);
        return exceptionRes(GlobalCode.SYSTEM_ERROR);
    }


    @ExceptionHandler
    public ResponseEntity<ResponseDto> handle(RuntimeException e) {
        log.error("[RuntimeException]", e);
        return exceptionRes(GlobalCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = LgException.class)
    public ResponseEntity<ResponseDto> handle(LgException e) {
        log.error("[LgException]", e);
        return exceptionRes(e.getGlobalCode());
    }

    private <T> ResponseEntity<ResponseDto> exceptionRes(GlobalCode globalCode) {
        ResponseDto body = new ResponseDto();
        body.setError(globalCode);
        return ResponseEntity.status(globalCode.getHttpStatus()).body(body);
    }
}
