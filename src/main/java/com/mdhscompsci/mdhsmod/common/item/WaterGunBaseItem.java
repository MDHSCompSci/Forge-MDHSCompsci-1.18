package com.mdhscompsci.mdhsmod.common.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WaterGunBaseItem extends Item {
    private float damage;
    private byte tier;

    public WaterGunBaseItem (float damage, byte tier) {
        super(new Item.Properties());
        this.damage = damage;
        this.tier = tier;
    }

    @Override
    public InteractionResult interactLivingEntity (ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        return InteractionResult.PASS;
    }

    protected void showWaterLaser (Player player, LivingEntity target, byte laser_density) {
        //Start and end pos of the water laser beam
        Vec3 laser_start_pos = player.getEyePosition().subtract(0.0, 0.4, 0.0);
        Vec3 laser_end_pos = target.getEyePosition();

        //Length of the laser
        double laser_length = laser_start_pos.distanceTo(laser_end_pos);

        //Distance between each water particle in the beam
        Vec3 laser_particle_increment_distance = (laser_end_pos.subtract(laser_start_pos))
                .normalize()
                .multiply(1/laser_density, 1/laser_density, 1/laser_density);

        //lc is a simple position storage, a particle is always displayed at it, and it is incremented by the increment each tick
        Vec3 lc = laser_start_pos;
        Level level = player.level;
        for (int inc_step=0; inc_step<(laser_length*laser_density); inc_step++) {
            level.addParticle(ParticleTypes.DRIPPING_WATER,
                    lc.x(), lc.y(), lc.z(),
                    0.0, 0.0, 0.0);
            lc = lc.add(laser_particle_increment_distance);
        }
    }
}
