package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command
 */
public interface Command {

    /**
     * Execute command
     * @return response content
     */
    String execute(HttpServletRequest request) throws CommandException;
}
