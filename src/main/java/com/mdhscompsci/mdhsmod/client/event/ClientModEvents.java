package com.mdhscompsci.mdhsmod.client.event;

import com.mdhscompsci.mdhsmod.MdhsMod;
import com.mdhscompsci.mdhsmod.client.renderer.BerryRenderer;
import com.mdhscompsci.mdhsmod.client.renderer.model.BerryModel;
import com.mdhscompsci.mdhsmod.core.init.EntityInit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MdhsMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    
    private ClientModEvents() {}

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BerryModel.LAYER_LOCATION, BerryModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.BERRY.get(), BerryRenderer::new);
    }
}
