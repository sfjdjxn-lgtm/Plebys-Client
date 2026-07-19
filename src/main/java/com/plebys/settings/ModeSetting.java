package com.plebys.settings;

public class ModeSetting extends Setting {
    private String value;
    private final String[] modes;

    public ModeSetting(String name, String... modes) {
        super(name);
        this.modes = modes;
        this.value = modes[0];
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String[] getModes() { return modes; }

    public void cycle() {
        int index = 0;
        for (int i = 0; i < modes.length; i++) {
            if (modes[i].equals(value)) {
                index = i;
                break;
            }
        }
        value = modes[(index + 1) % modes.length];
    }
}
