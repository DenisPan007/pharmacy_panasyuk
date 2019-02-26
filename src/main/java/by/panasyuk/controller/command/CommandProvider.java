package by.panasyuk.controller.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<String, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put("redirect", new Redirect());
        commandMap.put("getUserList", new GetUserList());
        commandMap.put("deleteUser", new DeleteUser());
        commandMap.put("login", new Login());
    }

    /**
     * Return command by name
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(String command) {
       return commandMap.get(command);
    }
}
