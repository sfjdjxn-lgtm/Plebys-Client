package com.plebys.modules;

import com.plebys.settings.*;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected final String name;
    protected final Category category;
    protected boolean toggled = false;
    protected int keyBind = -1;

    protected final List<Setting> settings = new ArrayList<>();
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) onEnable();
        else onDisable();
    }

    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void onUpdate();

    // Settings
    protected BooleanSetting addBoolean(String name, boolean def) {
        BooleanSetting s = new BooleanSetting(name, def);
        settings.add(s);
        return s;
    }

    protected SliderSetting addSlider(String name, double min, double max, double def) {
        SliderSetting s = new SliderSetting(name, min, max, def);
        settings.add(s);
        return s;
    }

    protected ModeSetting addMode(String name, String... modes) {
        ModeSetting s = new ModeSetting(name, modes);
        settings.add(s);
        return s;
    }

    public List<Setting> getSettings() { return settings; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isToggled() { return toggled; }
    public void setToggled(boolean state) { this.toggled = state; }
}
