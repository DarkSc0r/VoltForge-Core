package net.dark.voltforgecore.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import net.dark.voltforgecore.questing.QuestingScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyMapping OPEN_GUI_KEY = new KeyMapping(
            "key.voltforgecore.open_gui", // translation key
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,        // Press G to open
            "key.categories.voltforgecore" // key category
    );

    // Register the key binding itself
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_GUI_KEY);
    }

    // Handle key press
    @EventBusSubscriber(value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (OPEN_GUI_KEY.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null && mc.screen == null) {
                    mc.setScreen(new QuestingScreen());
                }
            }
        }
    }
}
