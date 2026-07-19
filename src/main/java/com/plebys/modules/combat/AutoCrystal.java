package com.plebys.modules.combat;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;

public class AutoCrystal extends Module {

    // Rangos
    public final SliderSetting placeRange = addSlider("Place Range", 1.0, 6.0, 4.5);
    public final SliderSetting breakRange = addSlider("Break Range", 1.0, 6.0, 5.0);
    public final SliderSetting targetRange = addSlider("Target Range", 1.0, 15.0, 10.0);

    // Velocidad
    public final SliderSetting cps = addSlider("CPS", 1, 20, 14);
    public final SliderSetting breakDelay = addSlider("Break Delay", 0, 10, 2);

    // Opciones principales
    public final BooleanSetting autoPlace = addBoolean("Auto Place", true);
    public final BooleanSetting autoBreak = addBoolean("Auto Break", true);
    public final BooleanSetting multiPlace = addBoolean("Multi Place", true);

    // Modos
    public final ModeSetting placeMode = addMode("Place Mode", "Normal", "Safe", "Predict", "Surround");
    public final ModeSetting breakMode = addMode("Break Mode", "Normal", "Packet", "Instant");

    // Rotaciones y visuales
    public final BooleanSetting rotate = addBoolean("Rotate", true);
    public final BooleanSetting swing = addBoolean("Swing", true);
    public final BooleanSetting predict = addBoolean("Prediction", true);
    public final BooleanSetting rayTrace = addBoolean("Ray Trace", true);

    // Seguridad
    public final BooleanSetting selfProtect = addBoolean("Self Protect", true);
    public final SliderSetting minDamage = addSlider("Min Damage", 0.0, 36.0, 8.0);
    public final SliderSetting maxSelfDamage = addSlider("Max Self Damage", 0.0, 36.0, 6.0);

    private int delay = 0;
    private EndCrystalEntity bestCrystal = null;

    public AutoCrystal() {
        super("AutoCrystal", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        bestCrystal = null;
    }

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null || mc.world == null) return;

        delay++;
        if (delay < (20 / cps.getValue())) return;
        delay = 0;

        // Break crystals
        if (autoBreak.getValue()) {
            findAndBreakCrystal();
        }

        // Place crystals
        if (autoPlace.getValue() && mc.player.getMainHandStack().getItem() == Items.END_CRYSTAL) {
            findAndPlaceCrystal();
        }
    }

    private void findAndBreakCrystal() {
        bestCrystal = mc.world.getEntitiesByClass(EndCrystalEntity.class, mc.player.getBoundingBox().expand(breakRange.getValue()), e -> true)
                .stream()
                .min(Comparator.comparingDouble(e -> mc.player.getDistanceToEntity(e)))
                .orElse(null);

        if (bestCrystal != null && mc.player.getDistanceToEntity(bestCrystal) <= breakRange.getValue()) {
            if (rotate.getValue()) rotateTo(bestCrystal.getPos());
            mc.interactionManager.attackEntity(mc.player, bestCrystal);
            if (swing.getValue()) mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void findAndPlaceCrystal() {
        // Lógica avanzada de búsqueda de posiciones óptimas para colocar cristales
        // (calcula daño, evita self damage, etc.)
        // Implementación completa recomendada aquí
    }

    private void rotateTo(Vec3d pos) {
        // Rotación silenciosa o legit
    }
}
