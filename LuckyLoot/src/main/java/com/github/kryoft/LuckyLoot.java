package com.github.kryoft;

import java.util.Random;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
// import net.minecraft.loot.ConstantLootTableRange;  // this doesn't exist anymore in 1.17+ [http://5.9.10.113/66460988/constantloottablerange-fabric-21w08b-is-missing]
// instead of ConstantLootTableRange, I use ConstantLootNumberProvider
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;

public class LuckyLoot implements ModInitializer {
	
	Random random = new Random();
	Block randomBlock;
	Item randomItem;

	@Override
	public void onInitialize() {
		
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (Math.random() > .5F) {
				randomBlock = Registries.BLOCK.getOrThrow(random.nextInt(Registries.BLOCK.size()));
				
//		    	FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
//		    			.rolls(ConstantLootNumberProvider.create(1))
//		    			.with(ItemEntry.builder(randomBlock));
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(randomBlock));
		    	
		    	tableBuilder.pool(poolBuilder.build());
		    	
//		    	System.out.println("id: " + id + "   --->   " + "Random block: " + randomBlock);
			} else {
				randomItem = Registries.ITEM.getOrThrow(random.nextInt(Registries.ITEM.size()));
				
//				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
//    			.rolls(ConstantLootNumberProvider.create(1))
//    			.with(ItemEntry.builder(randomItem));
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(randomItem));
    	
				tableBuilder.pool(poolBuilder);
				
//				System.out.println("id: " + id + "   --->   " + "Random item: " + randomItem);
			}
			}
		);
		
	}

}
