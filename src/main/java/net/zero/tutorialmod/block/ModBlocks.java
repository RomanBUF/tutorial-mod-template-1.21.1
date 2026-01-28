package net.zero.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.zero.tutorialmod.TutorialMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zero.tutorialmod.block.custom.KeyCardReaderBlock;
import net.zero.tutorialmod.block.custom.LandMineBlock;
import net.zero.tutorialmod.item.ModItems;

import java.util.Set;

public class ModBlocks {
    public static final Block LAND_MINE_BLOCK = registerBlock("land_mine_block",
            new LandMineBlock(
                    AbstractBlock.Settings.create().strength(4f).nonOpaque()
            )
    );

    public static final Block KeyCardReaderBlock_lvl_1 = registerBlock("keycard_reader_block_lvl_1",
            new KeyCardReaderBlock (
                    AbstractBlock.Settings.create().strength(10f),
                    Set.of(
                            ModItems.KeyCard_lvl_1,
                            ModItems.KeyCard_lvl_2
                    )
            )
    );

    public static final Block KeyCardReaderBlock_lvl_2 = registerBlock("keycard_reader_block_lvl_2",
            new KeyCardReaderBlock (
                    AbstractBlock.Settings.create().strength(10f),
                    Set.of(
                            ModItems.KeyCard_lvl_2
                    )
            )
    );


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.LAND_MINE_BLOCK);
            entries.add(ModBlocks.KeyCardReaderBlock_lvl_1);
            entries.add(ModBlocks.KeyCardReaderBlock_lvl_2);
        });
    }
}