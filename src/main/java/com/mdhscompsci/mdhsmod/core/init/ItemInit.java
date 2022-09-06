package com.mdhscompsci.mdhsmod.core.init;

import com.mdhscompsci.mdhsmod.MdhsMod;
import com.mdhscompsci.mdhsmod.common.item.WaterGunBaseItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    private ItemInit() {}

    public static final DeferredRegister<Item> ITEMS= DeferredRegister.create(ForgeRegistries.ITEMS, MdhsMod.MOD_ID);


    //Adding water gun items
    public static final RegistryObject<Item> WOODEN_WATER_GUN = ITEMS.register("wooden_water_gun", () ->
            new WaterGunBaseItem(1.0f, 1, 4.0));
    public static final RegistryObject<Item> STONE_WATER_GUN = ITEMS.register("stone_water_gun", () ->
            new WaterGunBaseItem(2.0f, 1, 6.0));
    public static final RegistryObject<Item> IRON_WATER_GUN = ITEMS.register("iron_water_gun", () ->
            new WaterGunBaseItem(3.0f, 2, 8.0));
    public static final RegistryObject<Item> GOLD_WATER_GUN = ITEMS.register("gold_water_gun", () ->
            new WaterGunBaseItem(3.0f, 2, 27.0));
    public static final RegistryObject<Item> DIAMOND_WATER_GUN = ITEMS.register("diamond_water_gun", () ->
            new WaterGunBaseItem(5.0f, 3, 12.0));
    public static final RegistryObject<Item> NETHERITE_WATER_GUN = ITEMS.register("netherite_water_gun", () ->
            new WaterGunBaseItem(8.0f, 4, 20.0));
}
