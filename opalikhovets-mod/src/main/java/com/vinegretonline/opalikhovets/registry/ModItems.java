package com.vinegretonline.opalikhovets.registry;

import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OpalikhovetsMod.MOD_ID);

    /** The phone the Opalikhovets holds while taking a photo. Purely decorative for players. */
    public static final DeferredItem<Item> PHONE =
            ITEMS.registerSimpleItem("phone", new Item.Properties().stacksTo(1));

    public static final DeferredItem<DeferredSpawnEggItem> OPALIKHOVETS_SPAWN_EGG =
            ITEMS.register("opalikhovets_spawn_egg", () -> new DeferredSpawnEggItem(
                    ModEntities.OPALIKHOVETS, 0x3A5BA0, 0xF2F2F2, new Item.Properties()));

    private ModItems() {
    }

    public static void addToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(OPALIKHOVETS_SPAWN_EGG.get());
        } else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(PHONE.get());
        }
    }
}
