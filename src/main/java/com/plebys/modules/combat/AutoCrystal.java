package com.plebys.modules.combat;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class AutoCrystal extends Module {
    public final SliderSetting placeRange = addSlider("Place Range", 3.0, 6.0, 4.5);
    public final SliderSetting breakRange = addSlider("Break Range", 3.0, 6.0, 5.0);

    public AutoCrystal() {
        super("AutoCrystal", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null) return;
        // Versión básica - expandiremos después
    }
}
