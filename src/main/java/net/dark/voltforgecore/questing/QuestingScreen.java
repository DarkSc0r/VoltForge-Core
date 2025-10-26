package net.dark.voltforgecore.questing;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class QuestingScreen extends Screen {

    public QuestingScreen() {
        super(Component.literal("VoltForge Test GUI"));
    }

//    @Override
//    protected void init() {
//        int x = this.width / 2 - 50;
//        int y = this.height / 2 - 10;
//
//        Button testButton = Button.builder(Component.literal("Click Me!"), button -> {
//            if (this.minecraft != null && this.minecraft.player != null) {
//                this.minecraft.player.sendSystemMessage(Component.literal("You clicked the button!"));
//            }
//        }).bounds(x, y, 100, 20).build();
//
//        addRenderableWidget(testButton);
//    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Draw a safe translucent background so we don't depend on any particular renderBackground signature
        guiGraphics.fill(0, 0, this.width, this.height, 0xC0101010); // alpha + dark color

        // Render widgets (buttons, etc.)
        //super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Title
        guiGraphics.drawCenteredString(this.font, "VoltForge Questing", this.width / 2, 5, 0xFFFFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}