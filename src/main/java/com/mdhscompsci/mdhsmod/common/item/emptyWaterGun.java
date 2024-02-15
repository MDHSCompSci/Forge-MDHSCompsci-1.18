package com.mdhscompsci.mdhsmod.common.item;


import com.mdhscompsci.mdhsmod.MdhsMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class emptyWaterGun {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MdhsMod.MOD_ID);

    public static final RegistryObject<Item> EMPTY_WOODEN_WATER_GUN = ITEMS.register("empty_wooden_water_gun", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> EMPTY_Stone_WATER_GUN = ITEMS.register("empty_stone_water_gun", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> EMPTY_IRON_WATER_GUN = ITEMS.register("empty_iron_water_gun", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> EMPTY_GOLD_WATER_GUN = ITEMS.register("empty_gold_water_gun", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> EMPTY_DIAMOND_WATER_GUN = ITEMS.register("empty_diamond_water_gun", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> EMPTY_NETHERITE_WATER_GUN = ITEMS.register("empty_netherite_water_gun", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
