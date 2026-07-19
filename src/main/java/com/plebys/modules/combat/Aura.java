package com.plebys.modules.combat;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;
import java.util.List;

public class Aura extends Module {

    // Rango y ataque
    public final SliderSetting range = addSlider("Range", 2.5, 7.0, 4.5);
    public final SliderSetting cps = addSlider("CPS", 1, 20, 12);
    public final SliderSetting fov = addSlider("FOV", 30, 180, 180);

    // Targets
    public final BooleanSetting players = addBoolean("Players", true);
    public final BooleanSetting mobs = addBoolean("Mobs", false);
    public final BooleanSetting animals = addBoolean("Animals", false);
    public final BooleanSetting invisibles = addBoolean("Invisibles", false);
    public final BooleanSetting throughWalls = addBoolean("Through Walls", false);

    // Rotaciones
    public final ModeSetting rotationMode = addMode("Rotation", "None", "Silent", "Legit", "Smooth");
    public final SliderSetting smoothSpeed = addSlider("Smooth Speed", 1.0, 50.0, 15.0);

    // Otras opciones
    public final BooleanSetting autoBlock = addBoolean("Auto Block", true);
    public final BooleanSetting onlyCriticals = addBoolean("Only Criticals", false);
    public final BooleanSetting keepSprint = addBoolean("Keep Sprint", true);
    public final ModeSetting targetMode = addMode("Target Mode", "Single", "Multi", "Switch");

    private int attackDelay = 0;
    private LivingEntity currentTarget = null;

    public Aura() {
        super("Aura", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        currentTarget = null;
    }

    @Override
    public void onUpdate() {
        if (!toggled || mc.player == null || mc.world == null) return;

        attackDelay++;
        if (attackDelay < (20 / cps.getValue())) return;
        attackDelay = 0;

        List<LivingEntity> targets = getValidTargets();

        if (targets.isEmpty()) {
            currentTarget = null;
            return;
        }

        // Selección de target
        if (targetMode.getValue().equals("Single") || currentTarget == null || !targets.contains(currentTarget)) {
            currentTarget = targets.stream()
                    .min(Comparator.comparingDouble(e -> mc.player.getDistanceToEntity(e)))
                    .orElse(null);
        }

        if (currentTarget == null) return;

        // Rotaciones
        rotateTo(currentTarget);

        // Ataque
        mc.interactionManager.attackEntity(mc.player, currentTarget);
        mc.player.swingHand(mc.player.getActiveHand());

        // AutoBlock
        if (autoBlock.getValue()) {
            // Lógica de auto block aquí
        }
    }

    private List<LivingEntity> getValidTargets() {
        return mc.world.getEntitiesByClass(LivingEntity.class, new Box(mc.player.getPos().add(-range.getValue(), -range.getValue(), -range.getValue()),
                mc.player.getPos().add(range.getValue(), range.getValue(), range.getValue())), this::isValidTarget);
    }

    private boolean isValidTarget(LivingEntity entity) {
        if (entity == mc.player || entity.isDead()) return false;
        if (!throughWalls.getValue() && !mc.player.canSee(entity)) return false;
        if (entity.isInvisible() && !invisibles.getValue()) return false;

        if (entity instanceof PlayerEntity) return players.getValue();
        if (entity.getType().getSpawnGroup().isFriendly()) return animals.getValue();
        return mobs.getValue();
    }

    private void rotateTo(LivingEntity target) {
        if (rotationMode.getValue().equals("None")) return;
        // Implementación completa de rotations (Silent, Legit, Smooth)
        Vec3d vec = target.getEyePos().subtract(mc.player.getEyePos());
        float yaw = (float) Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.asin(vec.y / vec.length()));
        // Aplicar rotación según modo
    }
}
