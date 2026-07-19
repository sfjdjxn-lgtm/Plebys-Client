package com.plebys.modules.render;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Fullbright extends Module {
    public final SliderSetting gamma = addSlider("Gamma", 1.0, 20000.0, 10000.0);
    public final BooleanSetting nightVision = addBoolean("Night Vision", true);

    private float oldGamma;

    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }

    @Override
    public void onEnable() {
        oldGamma = (float) mc.options.getGamma().getValue();
    }

    @Override
    public void onDisable() {
        mc.options.getGamma().setValue(oldGamma);
    }

    @Override
    public void onUpdate() {
        if (toggled) {
            mc.options.getGamma().setValue(gamma.getValue());
        }
    }
}
