package com.plebys.modules.render;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Fullbright extends Module {
    public final SliderSetting gamma = addSlider("Gamma", 1.0, 20000.0, 10000.0);
    public final BooleanSetting nightVision = addBoolean("Night Vision Effect", true);
    public final ModeSetting mode = addMode("Mode", "Gamma", "Potion", "Both");

    private float oldGamma = 1.0f;

    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }

    @Override
    public void onEnable() {
        oldGamma = mc.gameSettings.gammaSetting;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = oldGamma;
    }

    @Override
    public void onUpdate() {
        if (!isToggled()) return;
        if (mode.getValue().equals("Gamma") || mode.getValue().equals("Both")) {
            mc.gameSettings.gammaSetting = (float) gamma.getValue();
        }
        // Agrega efecto de poción si está activado
    }
}
