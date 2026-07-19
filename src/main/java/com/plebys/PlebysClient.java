package com.plebys;

import com.plebys.client.ModuleManager;
import com.plebys.client.ClickGUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class PlebysClient implements ClientModInitializer {

    public static PlebysClient INSTANCE;
    public static ModuleManager moduleManager;

    private static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        moduleManager = new ModuleManager();

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.plebysclient.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "category.plebysclient"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);
    }

    private void onClientTick(MinecraftClient client) {
        if (openGuiKey.wasPressed()) {
            if (!(client.currentScreen instanceof ClickGUI)) {
                client.setScreen(new ClickGUI());
            }
        }

        moduleManager.getModules().stream()
                .filter(Module::isToggled)
                .forEach(Module::onUpdate);
    }
}
