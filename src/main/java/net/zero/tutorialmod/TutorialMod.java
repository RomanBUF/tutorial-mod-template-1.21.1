package net.zero.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.zero.tutorialmod.block.ModBlocks;
import net.zero.tutorialmod.item.ModItemGroups;
import net.zero.tutorialmod.item.ModItems;
import net.zero.tutorialmod.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
	}
}