package ru.weierstrass.components.authentication;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.weierstrass.components.handlers.ExceptionHandlerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HttpBasicFailure implements AuthenticationEntryPoint {

    private static final String REALM = "e2e4-api-realm";
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;
    private static final int STATUS = HttpServletResponse.SC_UNAUTHORIZED;

    public String getRealm() {
        return REALM;
    }

    @Override
    public void commence(
        HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException, ServletException {
        response.setHeader( "WWW-Authenticate", "Basic realm=\"" + REALM + "\"" );
        response.setContentType( CONTENT_TYPE );
        response.setStatus( STATUS );
        response.getWriter().println( ExceptionHandlerAdvice.toJson( authException, STATUS ) );
    }

}
