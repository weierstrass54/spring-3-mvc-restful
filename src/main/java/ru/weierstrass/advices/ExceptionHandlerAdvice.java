package ru.weierstrass.advices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final ObjectMapper _mapper = new ObjectMapper();

    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ExceptionHandler( value = { IllegalArgumentException.class, IllegalStateException.class } )
    public ResponseEntity<String> handleBadRequest( Exception e ) {
        return handleException( e, HttpStatus.BAD_REQUEST );
    }

    private ResponseEntity<String> handleException( Exception e, HttpStatus status ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentType( MediaType.APPLICATION_JSON_UTF8 );
            String body = _exceptionWithStatusToJson( e, status );
            return new ResponseEntity<>( body, headers, status );
        }
        catch( JsonProcessingException jsonEx ) {
            headers.setContentType( MediaType.TEXT_PLAIN );
            return new ResponseEntity<>( e.getClass().getName() + ": " + e.getLocalizedMessage(), status );
        }
    }

    private String _exceptionWithStatusToJson( Exception e, HttpStatus status ) throws JsonProcessingException {
        Map<String, String> data = new HashMap<>();
        data.put( "code", String.valueOf( status.value() ) );
        data.put( "type", e.getClass().getName() );
        data.put( "message", e.getLocalizedMessage() );
        return _mapper.writeValueAsString( data );
    }

}
