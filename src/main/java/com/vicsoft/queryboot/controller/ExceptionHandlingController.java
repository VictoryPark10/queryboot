package com.vicsoft.queryboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Controller
public class ExceptionHandlingController implements ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Define ERROR PAGE
    private static final String ERROR_404_PAGE_PATH = "/error/404";
    private static final String ERROR_500_PAGE_PATH = "/error/500";
    private static final String ERROR_ETC_PAGE_PATH = "/error/error";

    @RequestMapping(value = "/error")
    public ResponseEntity<Object> handleError(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();

        logger.info("Status : {}", status);
        logger.info("Request URI : {}", request.getRequestURI());

        if (Objects.equals(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, Object> body = Map.of("error", "Not Found", "timestamp", System.currentTimeMillis());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

}
