package com.mdhscompsci.mdhsmod.common.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WaterGunBaseItem extends Item {
    private float damage;
    private byte tier;

    public WaterGunBaseItem (float damage, int tier) {
        super(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).rarity((tier<=3)?  Rarity.COMMON : Rarity.UNCOMMON));
        this.damage = damage;
        this.tier = (byte) tier;
    }

    @Override
    public InteractionResult interactLivingEntity (ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        try {
            pInteractionTarget.hurt(DamageSource.indirectMobAttack(pPlayer, pPlayer), this.damage);
        } catch (Exception e) {
            return InteractionResult.FAIL;
        }

        showWaterLaser(pPlayer, pInteractionTarget.getEyePosition(), (byte) (3^this.tier));
        return InteractionResult.SUCCESS;
    }

    protected void showWaterLaser (Player player, Vec3 target, byte laser_density) {
        //Start and end pos of the water laser beam
        Vec3 laser_start_pos = player.getEyePosition().subtract(0.0, 0.4, 0.0);

        //Length of the laser
        double laser_length = laser_start_pos.distanceTo(target);

        //Distance between each water particle in the beam
        Vec3 laser_particle_increment_distance = (target.subtract(laser_start_pos))
                .normalize()
                .multiply(1.0/laser_density, 1.0/laser_density, 1.0/laser_density);

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
