package com.plebys.modules.render;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Fullbright extends Module {
    public final SliderSetting gamma = addSlider("Gamma", 0.0, 20000.0, 12000.0);
    public final BooleanSetting nightVision = addBoolean("Night Vision", true);
    public final ModeSetting mode = addMode("Mode", "Gamma", "Potion", "Both");
    public final BooleanSetting dynamic = addBoolean("Dynamic Brightness", false);

    private float oldGamma;

    public Fullbright() { super("Fullbright", Category.RENDER); }

    @Override
    public void onEnable() { oldGamma = mc.options.getGamma().getValue(); }

    @Override
    public void onDisable() { mc.options.getGamma().setValue(oldGamma); }

    @Override
    public void onUpdate() {
        if (!toggled) return;
        if (mode.getValue().equals("Gamma") || mode.getValue().equals("Both")) {
            mc.options.getGamma().setValue(gamma.getValue());
        }
    }
}
