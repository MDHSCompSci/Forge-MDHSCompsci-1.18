package com.mdhscompsci.mdhsmod.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class WaterGunBaseItem extends Item {
    private final float damage;
    private final byte tier;
    private final double range;

    public WaterGunBaseItem (float damage, int tier, double range) {
        super(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).rarity((tier<=3)?  Rarity.COMMON : Rarity.UNCOMMON));
        this.damage = damage;
        this.tier = (byte) tier;
        this.range = range;
    }
    
    @Override
    @NotNull
    @ParametersAreNonnullByDefault
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //Number of points per meter to test for a collision. Higher value = better water gun hit accuracy
        final int TESTS_PER_BLOCK = 4;


        //Grabbing some basic finals
        final Vec3 player_look_direction = Vec3.directionFromRotation(pPlayer.getRotationVector());
        final Vec3 start_pos = pPlayer.getEyePosition();
        Vec3 increment = player_look_direction
                .scale(1.0/TESTS_PER_BLOCK);
        int recursions = (int) Math.ceil(this.range*TESTS_PER_BLOCK);

        //List of entities that were hit by the water gun
        List<Entity> hit_entities = Lists.newArrayList();

        //Current pos being checked for collision
        Vec3 cur_pos = start_pos;
        //Loop to check for collision at every set interval on the water beam.
        for (int ri = 0; ri<recursions; ri++) {
            //All the entities that the point check collided with
            List<Entity> entities = checkForEntitiesAtPoint(pPlayer.getId(), cur_pos, pLevel);
            //Adding those entities to the list of entities to be damaged later
            hit_entities.addAll(entities);

            //When it collides, break the checking loop
            if (!entities.isEmpty() ||
                    checkForBlockAtPoint(cur_pos, pLevel)) {
                break;
            }
            //Appending to the current checking position
            cur_pos = cur_pos.add(increment);
        }
        //The beam ends when it collides with something, the beam's final position is subtracted a little, so it's end
        //isn't in a block
        cur_pos = cur_pos.subtract(increment);

        //Looping through all the entities that were hit by the beam
        for (Entity hit : hit_entities) {
            try {
                //Damaging the entity
                hit.hurt(DamageSource.indirectMobAttack(pPlayer, pPlayer), this.damage);
                //Extinguishing fire on the entity
                hit.setRemainingFireTicks(0);
            } catch (Exception ignored) {}
        }

        //Showing the water beam of particles from the player to just before the collision point
        showWaterLaser(pPlayer, cur_pos, (byte) (Math.pow(3, this.tier)));
        //SOUND
        pLevel.playSound(null, new BlockPos(start_pos), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.PLAYERS, 0.6F, 0.1F);

        //Display the right click animation for the player
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

//    @Override
//    @NotNull
//    @ParametersAreNonnullByDefault
//    public InteractionResult interactLivingEntity (ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
//        try {
//            //Try and hurt the target, invulnerable entities raise an error
//            pInteractionTarget.hurt(DamageSource.indirectMobAttack(pPlayer, pPlayer), this.damage);
//        } catch (Exception e) {
//            //Do not use the right click animation, stop all further execution on error
//            return InteractionResult.FAIL;
//        }
//
//        //Un-ignite mobs
//        pInteractionTarget.setRemainingFireTicks(0);
//
//        //Show the water beam
//        showWaterLaser(pPlayer, pInteractionTarget.getEyePosition(), (byte) (Math.pow(3, this.tier)));
//
//        //Use the right click animation
//        return InteractionResult.SUCCESS;
//    }

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
            //Add the particle to the world. Forcerender means that it will render outside the 16 block range
            level.addParticle(ParticleTypes.DRIPPING_WATER, true,
                    lc.x(), lc.y(), lc.z(),                 //Position to add the particle
                    0.0, 0.0, 0.0); //No speed, stays in the air
            //Incrementing the current particle display position
            lc = lc.add(laser_particle_increment_distance);
        }
    }


    @ParametersAreNonnullByDefault
    protected List<Entity> checkForEntitiesAtPoint (int ignoreID, Vec3 point, Level level) {
        //Asking the level for entities near the point
        List<Entity> all_entities =  level.getEntities(null,     //No entity to exclude from checks
                new AABB(                                           //Creating a new bounding box to check for entities
                        point.subtract(0.1, 0.1, 0.1),   //Back-bottom-left corner
                        point.add(0.1, 0.1, 0.1)         //Front-up-right corner
                )
        );

        //Preparing an empty list of entities colliding with the point
        List<Entity> hit_entities = Lists.newArrayList();
        //Looping through all the points
        for (Entity entity : all_entities) {
            //Checking if the entity collides with the point
            if (entity.getBoundingBox().contains(point) && !(ignoreID==entity.getId())) {
                hit_entities.add(entity);
            }
        }

        return hit_entities;
    }

    protected boolean checkForBlockAtPoint (Vec3 point, Level level) {
        //Basic finalage
        final BlockPos pos = new BlockPos(point);
        final BlockState block = level.getBlockState(pos);

        //Checks now
        if (block.isAir()) { //If the current block is air, instaquit the check. Most common case, saves processing time
            return false;
        }
        if (block.isCollisionShapeFullBlock(level, pos)) { //If it's a full block, instantly stop checking
            return true;
        }
        if (block.getCollisionShape(level, pos).isEmpty()) { //Needed to prevent sugar cane from causing a crash
            return false;
        } else {
            return block.getCollisionShape(level, pos).bounds().contains(point); //Checking against the block's bounds
        }
    }
}