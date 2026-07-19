package com.plebys.settings;

public class SliderSetting extends Setting {
    private double value;
    private final double min;
    private final double max;

    public SliderSetting(String name, double min, double max, double defaultValue) {
        super(name);
        this.min = min;
        this.max = max;
        this.value = defaultValue;
    }

    public double getValue() { return value; }
    public void setValue(double value) { 
        this.value = Math.max(min, Math.min(max, value)); 
    }
    public double getMin() { return min; }
    public double getMax() { return max; }
}
