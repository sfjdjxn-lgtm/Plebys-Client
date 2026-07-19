package com.plebys.client;

import com.plebys.PlebysClient;
import com.plebys.modules.Category;
import com.plebys.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;

public class ClickGUI extends Screen {

    public ClickGUI() {
        super(Text.literal("Plebys Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Fondo azul marino
        context.fill(0, 0, this.width, this.height, new Color(0, 0, 40, 230).getRGB());

        int x = 50;
        for (Category cat : Category.values()) {
            // Header
            context.fill(x, 30, x + 140, 55, new Color(0, 0, 120).getRGB());
            // Título categoría (puedes mejorar)
            x += 170;
        }

        // Mensaje temporal
        context.drawTextWithShadow(textRenderer, "Presiona P para cerrar", this.width / 2 - 80, this.height / 2, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
