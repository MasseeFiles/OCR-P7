//package com.nnk.springboot.configuration;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//
//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
//        response.sendRedirect("/error");
//
//    }
//}
//TODO : a effacer dans version definitive