package ru.weierstrass.components.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final ObjectMapper _mapper = new ObjectMapper();

    public static String toJson(Exception e, int status) throws JsonProcessingException {
        Map<String, String> data = new HashMap<>();
        data.put("code", String.valueOf(status));
        data.put("type", e.getClass().getName());
        data.put("message", e.getLocalizedMessage());
        return _mapper.writeValueAsString(data);
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<String> handleAnyException(Exception e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<String> handleBadRequest(Exception e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<String> handleNotFound(Exception e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> handleException(Exception e, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            return new ResponseEntity<>(toJson(e, status.value()), headers, status);
        } catch (JsonProcessingException jsonEx) {
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(e.getClass().getName() + ": " + e.getLocalizedMessage(),
                status);
        }
    }

}
