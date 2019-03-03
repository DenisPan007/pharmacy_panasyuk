package by.panasyuk.controller.filter;

import by.panasyuk.controller.command.RoleEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/start")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        HttpSession session = httpServletRequest.getSession(true);
        if(session.getAttribute("role")==null) {
            session.setAttribute("role", RoleEnum.GUEST);
        }
        String command = req.getParameter("command");
        if (command.charAt(0)=='t' &&command.charAt(1)=='o'){
            session.setAttribute("initialCommand",command);
        }
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}