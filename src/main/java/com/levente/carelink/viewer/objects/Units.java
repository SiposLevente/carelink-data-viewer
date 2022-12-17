package com.levente.carelink.viewer.objects;

public enum Units {
    mmolL("mmol/L"),
    mgdL("mg/dL");

    private String displayString;

    private Units(String displayString) {
        this.displayString = displayString;
    }

    public String toDisplayString() {
        return this.displayString;
    }

}
