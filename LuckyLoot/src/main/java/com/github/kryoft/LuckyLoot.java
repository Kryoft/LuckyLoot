package com.github.kryoft;

import java.util.Random;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.registry.Registry;

public class LuckyLoot implements ModInitializer {
	
	Random random = new Random();

	@Override
	public void onInitialize() {
		
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (Math.random() > .5F) {
		    	FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
		    			.rolls(ConstantLootTableRange.create(1))
		    			.with(ItemEntry.builder(Registry.BLOCK.getRandom(random)));
		    	
		    	supplier.pool(poolBuilder);
			} else {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
    			.rolls(ConstantLootTableRange.create(1))
    			.with(ItemEntry.builder(Registry.ITEM.getRandom(random)));
    	
				supplier.pool(poolBuilder);
			}});
		
	}

}
