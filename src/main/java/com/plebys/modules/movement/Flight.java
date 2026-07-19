package com.plebys.modules.movement;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Flight extends Module {
    public final ModeSetting mode = addMode("Mode", "Vanilla", "Creative", "Packet", "Damage");
    public final SliderSetting horizontalSpeed = addSlider("Horizontal Speed", 0.1, 10.0, 2.0);
    public final SliderSetting verticalSpeed = addSlider("Vertical Speed", 0.1, 5.0, 0.8);
    public final BooleanSetting antiKick = addBoolean("Anti-Kick", true);
    public final BooleanSetting autoJump = addBoolean("Auto Jump", false);
    public final SliderSetting glideSpeed = addSlider("Glide Speed (when off)", -2.0, 0.0, -0.15);

    public Flight() {
        super("Flight", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        if (mc.thePlayer != null && mc.thePlayer.capabilities != null) {
            mc.thePlayer.capabilities.isFlying = false;
        }
    }

    @Override
    public void onUpdate() {
        if (!isToggled() || mc.thePlayer == null) return;

        mc.thePlayer.capabilities.isFlying = true;
        mc.thePlayer.capabilities.setFlySpeed((float) horizontalSpeed.getValue());

        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.thePlayer.motionY = verticalSpeed.getValue();
        } else if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.thePlayer.motionY = -verticalSpeed.getValue();
        } else {
            mc.thePlayer.motionY = 0;
        }
    }
}
