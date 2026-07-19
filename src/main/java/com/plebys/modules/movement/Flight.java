package com.plebys.modules.movement;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Flight extends Module {
    public final ModeSetting mode = addMode("Mode", "Creative", "Vanilla", "Packet");
    public final SliderSetting speed = addSlider("Speed", 0.1, 10.0, 2.0);
    public final SliderSetting verticalSpeed = addSlider("Vertical Speed", 0.1, 5.0, 0.8);

    public Flight() {
        super("Flight", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        if (mc.player != null) mc.player.getAbilities().flying = false;
    }

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null) return;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed((float) (speed.getValue() / 10f));
    }
}
