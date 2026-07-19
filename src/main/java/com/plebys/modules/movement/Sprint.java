package com.plebys.modules.movement;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Sprint extends Module {
    public final BooleanSetting omni = addBoolean("Omni Sprint", true);
    public final BooleanSetting keepSprint = addBoolean("Keep Sprint", true);
    public final SliderSetting multiplier = addSlider("Speed Multiplier", 0.5, 5.0, 1.0);
    public final BooleanSetting disableOnEat = addBoolean("Disable While Eating", true);
    public final BooleanSetting onlyWhenMoving = addBoolean("Only When Moving", false);
    public final ModeSetting mode = addMode("Mode", "Normal", "Legit", "Packet");

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null) return;
        if (disableOnEat.getValue() && mc.player.isUsingItem()) return;
        if (onlyWhenMoving.getValue() && mc.player.getVelocity().horizontalLength() < 0.01) return;

        mc.player.setSprinting(true);
    }
}
