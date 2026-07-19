package com.plebys.modules;

import com.plebys.settings.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected final String name;
    protected final Category category;
    protected boolean toggled = false;
    protected int keyBind = Keyboard.KEY_NONE;
    protected Minecraft mc = Minecraft.getMinecraft();

    protected final List<Setting> settings = new ArrayList<>();

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

    protected BooleanSetting addBoolean(String name, boolean defaultValue) {
        BooleanSetting s = new BooleanSetting(name, defaultValue);
        settings.add(s);
        return s;
    }

    protected SliderSetting addSlider(String name, double min, double max, double defaultValue) {
        SliderSetting s = new SliderSetting(name, min, max, defaultValue);
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
    public void setToggled(boolean toggled) { this.toggled = toggled; }
    public int getKeyBind() { return keyBind; }
    public void setKeyBind(int keyBind) { this.keyBind = keyBind; }
}
