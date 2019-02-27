package by.panasyuk.controller.command;

import by.panasyuk.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

/**
 * Example of the command implementation
 */
public class Redirect implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String path = request.getParameter("path");
        String resultPath ="/WEB-INF/views/"+path;
        request.setAttribute(path,resultPath);
        return resultPath;
    }
}
