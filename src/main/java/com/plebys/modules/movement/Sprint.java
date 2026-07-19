package com.plebys.modules.movement;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;
import net.minecraft.client.settings.KeyBinding;

public class Sprint extends Module {
    public final BooleanSetting omniSprint = addBoolean("Omni Sprint", true);
    public final BooleanSetting keepSprint = addBoolean("Keep Sprint", true);
    public final SliderSetting speedMultiplier = addSlider("Speed Multiplier", 0.5, 3.0, 1.0);
    public final BooleanSetting disableOnEat = addBoolean("Disable While Eating", false);

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {
        if (!isToggled() || mc.thePlayer == null) return;

        if (disableOnEat.getValue() && mc.thePlayer.isEating()) return;

        if (omniSprint.getValue() || mc.thePlayer.moveForward > 0) {
            mc.thePlayer.setSprinting(true);
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
        }

        // Multiplicador de velocidad (puedes modificarlo según necesites)
    }
}
