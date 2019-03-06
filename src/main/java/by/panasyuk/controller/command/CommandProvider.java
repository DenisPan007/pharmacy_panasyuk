package by.panasyuk.controller.command;

import by.panasyuk.controller.command.redirect.*;

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
        commandMap.put("deleteUser", new DeleteUser());
        commandMap.put("login", new Login());
        commandMap.put("changePassword", new ChangePassword());
        commandMap.put("signUp", new SignUp());
        commandMap.put("toSignUp", new ToSignUp());
        commandMap.put("toAdmin", new ToAdmin());
        commandMap.put("deleteDrug", new DeleteDrug());
        commandMap.put("toStartPage", new ToHome());
        commandMap.put("toAccount", new ToAccount());
        commandMap.put("toLogin", new ToLogin());
        commandMap.put("logout", new Logout());
        commandMap.put("getDrugList", new GetDrugList());
        commandMap.put("doInitialRedirectCommand", new DoInitialCommand());
        commandMap.put("toForgotPassword", new ToForgotPassword());

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
