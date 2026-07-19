package com.plebys.modules.movement;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Flight extends Module {
    public final ModeSetting mode = addMode("Mode", "Creative", "Vanilla", "Packet", "Smooth");
    public final SliderSetting hSpeed = addSlider("Horizontal Speed", 0.1, 15.0, 3.0);
    public final SliderSetting vSpeed = addSlider("Vertical Speed", 0.1, 10.0, 1.0);
    public final BooleanSetting antiKick = addBoolean("Anti-Kick", true);
    public final BooleanSetting autoJump = addBoolean("Auto Jump", false);
    public final SliderSetting glide = addSlider("Glide Speed", -5.0, 0.0, -0.2);
    public final BooleanSetting groundBoost = addBoolean("Ground Boost", true);

    public Flight() { super("Flight", Category.MOVEMENT); }

    @Override public void onEnable() {}
    @Override public void onDisable() { if (mc.player != null) mc.player.getAbilities().flying = false; }

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null) return;
        var abilities = mc.player.getAbilities();
        abilities.flying = true;
        abilities.setFlySpeed((float) hSpeed.getValue() / 10f);

        // Controles verticales y lógicas avanzadas
    }
}
