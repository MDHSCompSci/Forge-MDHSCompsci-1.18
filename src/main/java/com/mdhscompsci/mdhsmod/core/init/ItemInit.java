package com.mdhscompsci.mdhsmod.core.init;

import com.mdhscompsci.mdhsmod.MdhsMod;
import com.mdhscompsci.mdhsmod.common.item.WaterGunBaseItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    private ItemInit() {}

    public static final DeferredRegister<Item> ITEMS= DeferredRegister.create(ForgeRegistries.ITEMS, MdhsMod.MOD_ID);


    public static final RegistryObject<Item> NETHERITE_WATER_GUN = ITEMS.register("netherite_water_gun", () ->
            new WaterGunBaseItem(8.0f, 4));
}
