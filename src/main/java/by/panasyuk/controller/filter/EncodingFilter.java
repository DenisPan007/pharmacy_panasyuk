package by.panasyuk.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        filterChain.doFilter(request, response);
    }
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void destroy() {
    }
}