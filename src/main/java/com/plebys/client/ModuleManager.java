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
        modules.add(new Sprint());
        modules.add(new Flight());
        modules.add(new Fullbright());
        modules.add(new Aura());
        modules.add(new AutoCrystal());
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesByCategory(Category category) {
        List<Module> list = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == category) list.add(m);
        }
        return list;
    }
}
