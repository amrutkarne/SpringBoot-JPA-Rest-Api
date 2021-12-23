package com.neosoft.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
@Component
@ControllerAdvice
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
    	ObjectMapper mapper = new ObjectMapper();
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("timestamp", System.currentTimeMillis());
		jsonObject.put("status", 403);
		jsonObject.put("message", "Access denied");
    	res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write(mapper.writeValueAsString(jsonObject));
        		
                
    }
}