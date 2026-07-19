package com.plebys.modules.combat;

import com.plebys.modules.Category;
import com.plebys.modules.Module;
import com.plebys.settings.*;

public class Aura extends Module {
    public final SliderSetting range = addSlider("Range", 2.5, 6.5, 4.5);
    public final SliderSetting cps = addSlider("CPS", 1, 20, 12);
    public final BooleanSetting players = addBoolean("Players", true);
    public final BooleanSetting mobs = addBoolean("Mobs", false);
    public final BooleanSetting animals = addBoolean("Animals", false);
    public final BooleanSetting throughWalls = addBoolean("Through Walls", false);
    public final ModeSetting rotationMode = addMode("Rotation", "None", "Silent", "Legit");
    public final BooleanSetting autoBlock = addBoolean("Auto Block", true);
    public final BooleanSetting onlyCriticals = addBoolean("Only Criticals", false);

    private int attackDelay = 0;

    public Aura() {
        super("Aura", Category.COMBAT);
    }

    @Override
    public void onUpdate() {
        if (!isToggled() || mc.thePlayer == null) return;
        // Implementación completa de target selection, rotations y ataque aquí
    }
}
