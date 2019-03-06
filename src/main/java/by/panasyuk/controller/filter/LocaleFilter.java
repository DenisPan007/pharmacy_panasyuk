package by.panasyuk.controller.filter;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.Router;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        ServletRequestWrapper wrapper = new ServletRequestWrapper(request);
        if (lang!=null) {
            Cookie langCookie = new Cookie("lang",lang);
            response.addCookie(langCookie);
            Command initialCommand =(Command) session.getAttribute("initialCommand");
            request.setAttribute("command",initialCommand);
            request.getRequestDispatcher("/").forward(request,response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
