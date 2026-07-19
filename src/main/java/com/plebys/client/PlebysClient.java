package com.plebys.client;

import com.plebys.gui.ClickGUI;
import com.plebys.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "plebysclient", version = "1.0", clientSideOnly = true)
public class PlebysClient {

    public static PlebysClient instance;
    public static ModuleManager moduleManager;
    private boolean guiKeyPressed = false;

    public PlebysClient() {
        instance = this;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager = new ModuleManager();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        // Tecla P para abrir el ClickGUI
        if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
            if (!guiKeyPressed) {
                Minecraft.getMinecraft().displayGuiScreen(new ClickGUI());
                guiKeyPressed = true;
            }
        } else {
            guiKeyPressed = false;
        }

        // Actualizar todos los módulos
        for (com.plebys.modules.Module module : moduleManager.getModules()) {
            if (module.isToggled()) {
                module.onUpdate();
            }
        }
    }
}
