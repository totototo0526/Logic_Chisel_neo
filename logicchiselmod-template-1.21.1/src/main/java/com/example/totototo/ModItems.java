package com.example.totototo;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.core.registries.Registries;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("logicchisel");

    
    // Test Sword
    public static final DeferredItem<SwordItem> TEST_SWORD = ITEMS.register("test_sword",
        () -> new SwordItem(Tiers.NETHERITE, 
            new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 10, -2.4f)))
    );
    
}
