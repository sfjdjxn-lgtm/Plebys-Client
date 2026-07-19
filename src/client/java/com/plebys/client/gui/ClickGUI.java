package com.plebys.client.gui;

import com.plebys.client.modules.Category;
import com.plebys.client.modules.Module;
import com.plebys.client.modules.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.class_332;

public class ClickGUI extends Screen {
    private Category currentCategory = Category.COMBAT;

    public ClickGUI() {
        super(Text.literal("Plebys Client"));
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        // Fondo Azul Marino fuerte
        context.method_28045(0, 0, this.width, this.height, 0xFF000F2B);

        // Panel principal
        context.method_28045(90, 50, this.width - 180, this.height - 100, 0xFF001F3F);

        // Header
        context.method_28045(90, 50, this.width - 180, 70, 0xFF003366);
        context.drawTextWithShadow(textRenderer, "§bPlebys §9Client", 110, 58, 0xFF00CCFF);

        // Categorías
        int x = 110;
        for (Category cat : Category.values()) {
            int color = (cat == currentCategory) ? 0xFF00BFFF : 0xFF6688AA;
            context.drawTextWithShadow(textRenderer, cat.name(), x, 85, color);
            x += 80;
        }

        // Módulos
        int y = 120;
        for (Module m : ModuleManager.INSTANCE.getByCategory(currentCategory)) {
            String status = m.isEnabled() ? "§aON" : "§7OFF";
            context.drawTextWithShadow(textRenderer, m.name + " " + status, 120, y, 0xFFFFFFFF);
            y += 24;
        }
    }
}
