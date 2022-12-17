package com.levente.carelink.viewer.objects;

import java.io.File;
import java.util.Scanner;

public class CareLinkLoginReader {
    static String file_location = "settings.conf";

    public static String GetConfigProperty(Settings setting) throws Exception {
        File configFile = new File(file_location);
        Scanner settingsFileReader = new Scanner(configFile);
        boolean foundSetting = false;
        while (settingsFileReader.hasNextLine() && !foundSetting) {
            String[] data = settingsFileReader.nextLine().split(":");

            if (setting.toString().equals(data[0].strip())) {
                foundSetting = true;
                settingsFileReader.close();
                return data[1].strip();
            }
        }
        settingsFileReader.close();
        throw new Exception("No settings found!");
    }

    public static boolean SettingsFileExists() {
        File configFile = new File(file_location);
        boolean return_value = false;

        if (configFile.isFile()) {
            return_value = true;
        }
        return return_value;
    }

}
