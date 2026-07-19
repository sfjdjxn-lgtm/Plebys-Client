package com.plebys.modules.combat;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Aura extends Module {
    public final SliderSetting range = addSlider("Range", 3.0, 6.0, 4.5);
    public final SliderSetting cps = addSlider("CPS", 1, 20, 10);
    public final BooleanSetting players = addBoolean("Players", true);

    public Aura() {
        super("Aura", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null || mc.world == null) return;

        // Básico por ahora - puedes expandir después
        for (var entity : mc.world.getEntities()) {
            if (entity instanceof net.minecraft.entity.player.PlayerEntity && entity != mc.player) {
                if (mc.player.getDistanceToEntity(entity) <= range.getValue()) {
                    mc.interactionManager.attackEntity(mc.player, entity);
                    mc.player.swingHand(mc.player.getActiveHand());
                    break;
                }
            }
        }
    }
}
