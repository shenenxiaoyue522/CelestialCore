package com.xiaoyue.celestial_core.data;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_core.CelestialCore;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2damagetracker.init.data.DamageTypeAndTagsGen;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import static net.minecraft.tags.DamageTypeTags.*;

public class CCDamageTypes extends DamageTypeAndTagsGen {

    public static final ResourceKey<DamageType> MAGIC = createDamage("magic");
    public static final ResourceKey<DamageType> ABYSSAL_MAGIC = createDamage("abyssal_magic");

    public static final TagKey<DamageType> WITHER = TagKey.create(Registries.DAMAGE_TYPE, CelestialCore.loc("wither"));

    public CCDamageTypes(L2Registrate registrate) {
        super(registrate);
        new DamageTypeHolder(MAGIC, new DamageType("magic", DamageScaling.NEVER, 0.1f))
                .add(BYPASSES_ARMOR, BYPASSES_COOLDOWN, AVOIDS_GUARDIAN_THORNS, Tags.DamageTypes.IS_MAGIC, L2DamageTypes.NO_SCALE);
        new DamageTypeHolder(ABYSSAL_MAGIC, new DamageType("magic", DamageScaling.NEVER, 0.1f))
                .add(BYPASSES_ARMOR, BYPASSES_COOLDOWN, AVOIDS_GUARDIAN_THORNS, Tags.DamageTypes.IS_MAGIC, L2DamageTypes.NO_SCALE)
                .add(L2DamageTypes.BYPASS_MAGIC);
    }

    @Override
    protected void addDamageTypeTags(RegistrateTagsProvider.Impl<DamageType> pvd) {
        pvd.addTag(WITHER).add(DamageTypes.WITHER, DamageTypes.WITHER_SKULL);
    }

    private static Holder.Reference<DamageType> getDamageSource(Level level, ResourceKey<DamageType> key) {
        return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key);
    }

    private static ResourceKey<DamageType> createDamage(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, CelestialCore.loc(id));
    }

    public static DamageSource magic(LivingEntity attacker) {
        return new DamageSource(getDamageSource(attacker.level(), MAGIC), attacker);
    }

    public static DamageSource abyss(LivingEntity attacker) {
        return new DamageSource(getDamageSource(attacker.level(), ABYSSAL_MAGIC), attacker);
    }

    public static DamageSource abyss(Level level) {
        return new DamageSource(getDamageSource(level, ABYSSAL_MAGIC));
    }

}
