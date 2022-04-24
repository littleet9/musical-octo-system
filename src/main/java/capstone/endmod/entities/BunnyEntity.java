package capstone.endmod.entities;

import capstone.endmod.EndModRoot;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Random;

public class BunnyEntity extends FlyingMob {

    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(EndModRoot.MODID,
            "entities/bunny_entity");

    public BunnyEntity(EntityType<? extends FlyingMob> p_27557_, Level p_27558_)
    {
        super(p_27557_, p_27558_);
        this.moveControl = new BunnyEntity.BunnyMoveControl(this);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GHAST_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_DEATH;
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return LOOT_TABLE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GHAST_HURT;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new BunnyEntity.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static boolean canSpawn(EntityType<BunnyEntity> entity, LevelAccessor levelAccess, MobSpawnType spawnType,
                                   BlockPos pos, Random random) {
        return true;
        //return checkBunnySpawnRules(entity, levelAccess, spawnType, pos, random) && pos.getY() > 2;
    }

    public static boolean checkBunnySpawnRules(EntityType<? extends FlyingMob> p_27578_, LevelAccessor p_27579_, MobSpawnType p_27580_, BlockPos p_27581_, Random p_27582_) {
        return p_27579_.getBlockState(p_27581_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.MOVEMENT_SPEED, 0.24D);
    }

    static class RandomFloatAroundGoal extends Goal {
        private final BunnyEntity bunny;

        public RandomFloatAroundGoal(BunnyEntity p_32783_) {
            this.bunny = p_32783_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.bunny.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.bunny.getX();
                double d1 = movecontrol.getWantedY() - this.bunny.getY();
                double d2 = movecontrol.getWantedZ() - this.bunny.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            Random random = this.bunny.getRandom();
            double d0 = this.bunny.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.bunny.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.bunny.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.bunny.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    static class BunnyMoveControl extends MoveControl {
        private final BunnyEntity bunny;
        private int floatDuration;

        public BunnyMoveControl(BunnyEntity p_32768_) {
            super(p_32768_);
            this.bunny = p_32768_;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.bunny.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.bunny.getX(), this.wantedY - this.bunny.getY(), this.wantedZ - this.bunny.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.bunny.setDeltaMovement(this.bunny.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 p_32771_, int p_32772_) {
            AABB aabb = this.bunny.getBoundingBox();

            for(int i = 1; i < p_32772_; ++i) {
                aabb = aabb.move(p_32771_);
                if (!this.bunny.level.noCollision(this.bunny, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }
}