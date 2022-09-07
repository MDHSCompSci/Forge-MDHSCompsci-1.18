package com.mdhscompsci.mdhsmod.common.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Berry extends Monster {

    public Berry(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        //attributes
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1000.0D).add(Attributes.ATTACK_DAMAGE, 12.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.4D).add(Attributes.FOLLOW_RANGE, 80).add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    public boolean doHurtTarget(Entity pEntity) {
    if (!super.doHurtTarget(pEntity)) {
            return false;
        } else {
            if (pEntity instanceof LivingEntity) {
                ((LivingEntity)pEntity).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
            }
            return true;
        }
    }  
}


