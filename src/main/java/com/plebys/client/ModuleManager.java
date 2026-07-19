package com.plebys.client;

import com.plebys.modules.*;
import com.plebys.modules.movement.*;
import com.plebys.modules.render.*;
import com.plebys.modules.combat.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // MOVEMENT
        modules.add(new Sprint());
        modules.add(new Flight());

        // RENDER
        modules.add(new Fullbright());

        // COMBAT
        modules.add(new Aura());
        modules.add(new AutoCrystal());
    }

    public List<Module> getModules() { return modules; }

    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .toList();
    }

    public Module getModuleByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
