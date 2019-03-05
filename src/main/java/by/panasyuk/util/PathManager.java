package by.panasyuk.util;

import java.util.ResourceBundle;

public class PathManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("routes");

    private PathManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
