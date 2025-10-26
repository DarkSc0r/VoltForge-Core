package net.dark.voltforgecore.item;

import net.dark.voltforgecore.VoltForgeCore;
import net.dark.voltforgecore.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VoltForgeCore.MOD_ID);

    public static final Supplier<CreativeModeTab> VOLT_FORGE_CORE_TAB = CREATIVE_MODE_TAB.register("volt_forge_core_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIN.get()))
                    .title(Component.translatable("creativetab.voltforgecore.volt_forge_core_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_TIN);
                        output.accept(ModItems.TIN);
                        output.accept(ModBlocks.TIN_BLOCK);
                        output.accept(ModBlocks.TIN_ORE);
                    }).build());

//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Voltforgecore.MOD_ID, "INSERT TAB NAME IN PLACE HERE")
    
    public static void register(IEventBus event) {
        CREATIVE_MODE_TAB.register(event);
    }

}
