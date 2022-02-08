package com.mdhscompsci.mdhsmod.core.init;

import com.mdhscompsci.mdhsmod.core.MdhsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    private ItemInit() {}

    public static final DeferredRegister<Item> ITEMS= DeferredRegister.create(ForgeRegistries.ITEMS, MdhsMod.MOD_ID);
}
