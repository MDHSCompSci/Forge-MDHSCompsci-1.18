package com.mdhscompsci.mdhsmod.core.event;

import com.mdhscompsci.mdhsmod.MdhsMod;
import com.mdhscompsci.mdhsmod.common.entity.Berry;
import com.mdhscompsci.mdhsmod.core.init.EntityInit;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MdhsMod.MOD_ID, bus = Bus.MOD)
public class CommonModEvents {
    
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.BERRY.get(), Berry.createAttributes().build());
    }
}
