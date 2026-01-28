package net.zero.tutorialmod.item;



import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zero.tutorialmod.TutorialMod;

public class ModItems {
    public static final Item KeyCard_lvl_1 = registerItem("keycard_lvl_1", new Item(new Item.Settings()
            .maxCount(1)

    ));
    public static final Item KeyCard_lvl_2 = registerItem("keycard_lvl_2", new Item(new Item.Settings()
            .maxCount(1)

    ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for "+ TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(KeyCard_lvl_1);
            entries.add(KeyCard_lvl_2);
        });
    }
}