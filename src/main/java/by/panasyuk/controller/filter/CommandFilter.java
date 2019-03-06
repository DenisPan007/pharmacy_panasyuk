package by.panasyuk.controller.filter;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandProvider;
import by.panasyuk.controller.command.redirect.RedirectCommand;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String commandString = request.getParameter("command");
        CommandProvider provider = CommandProvider.getInstance();
        Command command = provider.takeCommand(commandString);
        if (command == null){
            command = provider.takeCommand("doInitialRedirectCommand");
        }

        if (command instanceof RedirectCommand){
            session.setAttribute("initialCommand",command);
        }
        servletRequest.setAttribute("command",command);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
