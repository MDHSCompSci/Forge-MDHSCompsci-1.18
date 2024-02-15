package com.mdhscompsci.mdhsmod.core.init;

import com.mdhscompsci.mdhsmod.MdhsMod;
import com.mdhscompsci.mdhsmod.common.entity.Berry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    private EntityInit () {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MdhsMod.MOD_ID);

    //adding new entity
    public static final RegistryObject<EntityType<Berry>> BERRY = ENTITIES.register("berry", () ->
        EntityType.Builder.of(Berry::new, MobCategory.MONSTER).sized(1.0f, 2.125f).fireImmune()
            .build(new ResourceLocation(MdhsMod.MOD_ID, "berry").toString()));

}
