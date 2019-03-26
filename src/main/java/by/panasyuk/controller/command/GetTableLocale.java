package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GetTableLocale implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String lang = request.getParameter("lang");
        String pathStr;
        switch (lang) {
            case "ru_RU":
                pathStr = "/locale/table_ru.json";
                break;
            case "en_US":
                pathStr = "/locale/table_en.json";
                break;
            default:
                pathStr = "/locale/table_en.json";
                break;
        }
        try {
            URI uri = this.getClass().getResource(pathStr).toURI();
            Path path = Paths.get(uri);
            List<String> result = Files.readAllLines(path);
            String resultStr = String.join("", result);
            return resultStr;
        } catch (IOException | URISyntaxException e) {
            throw new CommandException(e);
        }
    }
}
