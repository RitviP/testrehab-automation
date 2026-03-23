package com.planetrehab.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.planetrehab.automation.constants.FrameworkConstants;

public class ConfigReader {

    private static Properties prop;

    static {
        try (FileInputStream fis = new FileInputStream(
                FrameworkConstants.CONFIG_FILE_PATH)) {

            prop = new Properties();
            prop.load(fis);

            System.out.println("✅ Config loaded from: " + FrameworkConstants.CONFIG_FILE_PATH);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load config.properties");
        }
    }

    // 🔥 STRICT MODE (recommended)
    public static String getProperty(String key) {

        String value = prop.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("❌ Config key not found or empty: " + key);
        }

        return value.trim(); // 🔥 important fix
    }

    // 🔥 OPTIONAL (fallback support)
    public static String getProperty(String key, String defaultValue) {
        String value = prop.getProperty(key);
        return (value == null || value.trim().isEmpty())
                ? defaultValue
                : value.trim();
    }
}