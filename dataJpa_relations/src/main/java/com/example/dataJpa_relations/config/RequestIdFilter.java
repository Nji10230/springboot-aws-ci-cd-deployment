package com.example.dataJpa_relations.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class RequestIdFilter implements Filter {

    private static final String REQUEST_ID_HEADER = "X-Request-Id";
    private static final String MDC_REQUEST_ID_KEY = "requestId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Get request ID from headers, or generate one if missing
        String requestId = request.getHeader(REQUEST_ID_HEADER);
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }

        // Store Request ID in MDC (for logging)
        MDC.put(MDC_REQUEST_ID_KEY, requestId);

        // Add request ID to response headers (useful for tracking)
        response.setHeader(REQUEST_ID_HEADER, requestId);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear(); // Clear MDC after request processing to prevent memory leaks
        }
    }
}
