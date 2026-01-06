package com.example.totototo;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

@SuppressWarnings("null")
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("logicchisel");
    public static final DeferredRegister.Items ITEMS = ModItems.ITEMS; // Use the same Items register for BlockItems

    // Test Block
    public static final DeferredBlock<Block> TEST_BLOCK = BLOCKS.register("test_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f, 6.0f)));
    // BlockItem
    public static final DeferredItem<BlockItem> TEST_BLOCK_ITEM = ITEMS.register("test_block",
            () -> new BlockItem(TEST_BLOCK.get(), new Item.Properties()));

}
