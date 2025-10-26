package net.dark.voltforgecore.block;

import net.dark.voltforgecore.VoltForgeCore;
import net.dark.voltforgecore.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VoltForgeCore.MOD_ID);

    public static final DeferredBlock<Block> TIN_BLOCK = register_block("tin_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> TIN_ORE = register_block("tin_ore", () -> new DropExperienceBlock(UniformInt.of(1, 3), BlockBehaviour.Properties.of()
            .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    private static <T extends Block> DeferredBlock<T> register_block(String name, Supplier<T> block) {
        DeferredBlock<T> to_return = BLOCKS.register(name, block);
        register_block_item(name, to_return);
        return to_return;
    }

    private static <T extends Block> void register_block_item (String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
