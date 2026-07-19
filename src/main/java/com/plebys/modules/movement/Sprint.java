package com.plebys.modules.movement;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Sprint extends Module {
    public final BooleanSetting omniSprint = addBoolean("Omni Sprint", true);
    public final BooleanSetting keepSprint = addBoolean("Keep Sprint", true);
    public final SliderSetting multiplier = addSlider("Speed Multiplier", 0.5, 3.0, 1.0);

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null) return;
        mc.player.setSprinting(true);
    }
}
