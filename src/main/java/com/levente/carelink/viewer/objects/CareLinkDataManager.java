package com.levente.carelink.viewer.objects;

import java.io.Console;
import java.text.DecimalFormat;

import info.nightscout.medtronic.carelink.client.CareLinkClient;
import info.nightscout.medtronic.carelink.message.RecentData;

public class CareLinkDataManager {
    static CareLinkDataManager instance = null;

    static CareLinkClient client;
    static RecentData recentData;

    static String username;
    static String password;
    static String countryCode;

    public RecentData getRecentData() {
        return CareLinkDataManager.recentData;
    }

    public static CareLinkDataManager GetInstance() {
        if (CareLinkDataManager.instance == null) {
            CareLinkDataManager.instance = new CareLinkDataManager(CareLinkDataManager.username,
                    CareLinkDataManager.password, CareLinkDataManager.countryCode);
        }
        return CareLinkDataManager.instance;
    }

    public static void setLoginData(String username, String password, String countryCode) {
        CareLinkDataManager.username = username;
        CareLinkDataManager.password = password;
        CareLinkDataManager.countryCode = countryCode;
    }

    public static void UpdateData() {
        CareLinkDataManager.recentData = CareLinkDataManager.client.getRecentData();
    }

    public static float getCurrentSG() {
        UpdateData();
        return recentData.lastSG.sg / 18.0f;
    }

    public static String getCurrentSGString(int decimals) {

        return FormatData(CareLinkDataManager.getCurrentSG(), decimals);
    }

    public static float getSGDelta() {
        UpdateData();
        float prev_sg = recentData.sgs.get(recentData.sgs.size() - 2).sg / 18.0f;
        return getCurrentSG() - prev_sg;
    }

    public static String getSGDeltaString(int decimals) {
        UpdateData();
        return FormatData(CareLinkDataManager.getSGDelta(), decimals);
    }

    private static String FormatData(float value, int decimals) {
        String pattern = "#";
        if (decimals != 0) {
            pattern += ".";
            for (int decimal = 0; decimal < decimals; decimal++) {
                pattern += "#";
            }
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);

    }

    private CareLinkDataManager(String username, String password, String countryCode) {
        client = new CareLinkClient(username, password, countryCode);

        if (client.login()) {
            recentData = client.getRecentData();
            System.out.println("Successfully logged in!");
        }
    }

}
