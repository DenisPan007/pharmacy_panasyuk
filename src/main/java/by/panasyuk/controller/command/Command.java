package by.panasyuk.controller.command;

import by.panasyuk.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

/**
 * Command
 */
public interface Command {

    /**
     * Execute command
     * @param request is used for extracting request parameters
     * @return response content
     */
    String execute(HttpServletRequest request) throws CommandException;
}
