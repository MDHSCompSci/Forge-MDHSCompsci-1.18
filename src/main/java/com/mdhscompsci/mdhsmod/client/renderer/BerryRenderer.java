package com.mdhscompsci.mdhsmod.client.renderer;

import com.mdhscompsci.mdhsmod.MdhsMod;
import com.mdhscompsci.mdhsmod.client.renderer.model.BerryModel;
import com.mdhscompsci.mdhsmod.common.entity.Berry;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class BerryRenderer<Type extends Berry> extends MobRenderer<Type, BerryModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MdhsMod.MOD_ID, "textures/entities/berry.png");

    public BerryRenderer(Context context) {
        //last parameter is shadow size
        super(context, new BerryModel<>(context.bakeLayer(BerryModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
    
}
