package by.panasyuk.controller.filter;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandProvider;
import by.panasyuk.controller.command.RedirectCommand;
import by.panasyuk.controller.command.RoleEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = {"control"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String commandString = req.getParameter("command");
        CommandProvider provider = CommandProvider.getInstance();
        Command command = provider.takeCommand(commandString);
        if (command == null){
            command = provider.takeCommand("doInitialRedirectCommand");
        }
        HttpSession session = req.getSession(true);
        String lang = req.getParameter("lang");
        if (lang!=null) {
            session.setAttribute("lang", lang);
        }
        if (command instanceof RedirectCommand){
            session.setAttribute("initialCommand",command);
        }
        request.setAttribute("command",command);
        if(session.getAttribute("role")==null) {
            session.setAttribute("role", RoleEnum.GUEST);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}