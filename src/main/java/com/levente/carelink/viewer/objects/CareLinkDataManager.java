package com.levente.carelink.viewer.objects;

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
    static Units unit;

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

    public static void setUnit(String unit) {
        if (unit.toLowerCase().equals(Units.mmolL.toString().toLowerCase())) {
            CareLinkDataManager.unit = Units.mmolL;
        } else {
            CareLinkDataManager.unit = Units.mgdL;
        }
    }

    public static String getUnit() {
        return unit.toDisplayString();
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
        float currentData = recentData.lastSG.sg;
        if (unit == Units.mmolL) {
            currentData /= 18.0f;
        }
        return currentData;
    }

    public static float getCurrentIOB() {
        return recentData.activeInsulin.amount.floatValue();
    }

    public static float getInsulinInPump() {
        return recentData.reservoirRemainingUnits;
    }

    public static float getSGDelta() {
        UpdateData();
        float prev_sg = recentData.sgs.get(recentData.sgs.size() - 2).sg;
        if (unit == Units.mmolL) {
            prev_sg /= 18.0f;
        }
        return getCurrentSG() - prev_sg;
    }

    public static String FormatData(float value, int decimals) {
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
