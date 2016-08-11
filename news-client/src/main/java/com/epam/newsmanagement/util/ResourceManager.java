package com.epam.newsmanagement.util;

import java.util.ResourceBundle;

/**
 * Created by Никита on 8/11/2016.
 */
public class ResourceManager {
    private final static ResourceBundle resourceBundle =
                         ResourceBundle.getBundle("project");
    private ResourceManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
