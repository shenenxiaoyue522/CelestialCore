package com.xiaoyue.celestial_core.events;

import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.CCUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class CCAttackListener implements AttackListener {

    private Optional<AttributeInstance> getAttr(LivingEntity entity, Holder<Attribute> attr) {
        return Optional.ofNullable(entity.getAttribute(attr));
    }

    private void attrOptional(LivingEntity entity, Holder<Attribute> attr, LivingEntity attacker, Holder<Attribute> penetration, ResourceLocation name) {
        this.getAttr(entity, attr).ifPresent(ins -> {
            AttributeInstance attribute = attacker.getAttribute(penetration);
            if (attribute == null) return;
            double ap = attribute.getValue();
            ins.removeModifier(name);
            var modifier = new AttributeModifier(name, -ap, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            ins.addTransientModifier(modifier);
        });
    }

    @Override
    public void onCreateSource(CreateSourceEvent event) {
        if (event.getResult() != null &&
                event.getResult().toRoot().validState(DefaultDamageState.BYPASS_ARMOR) &&
                event.getAttacker().hasEffect(CCEffects.VIOLENT.holder())) {
            event.enable(DefaultDamageState.BYPASS_ARMOR);
        }
    }

    @Override
    public boolean onAttack(DamageData.Attack cache) {
        LivingEntity target = cache.getTarget();
        LivingEntity attacker = cache.getAttacker();
        if (attacker == null) {
            return false;
        }
        this.attrOptional(target, Attributes.ARMOR, attacker, CCAttributes.ARMOR_PENETRATION.holder(), CCUtils.BYPASS_ARMOR_NAME);
        this.attrOptional(target, Attributes.ARMOR_TOUGHNESS, attacker, CCAttributes.TOUGHNESS_PENETRATION.holder(), CCUtils.BYPASS_TOUGHNESS_NAME);
        return false;
    }

    @Override
    public void onHurt(DamageData.Offence data) {
        LivingEntity attacker = data.getAttacker();
        if (attacker != null && attacker.hasEffect(CCEffects.FEAR_CURSE.holder())) {
            int level = EntityUtils.getEffectLevel(attacker, CCEffects.FEAR_CURSE.holder());
            EntityUtils.hurtByPlayerOrMob(attacker, attacker.getLastHurtByMob(), level * data.getDamageOriginal() * 0.2f);
        }
    }

    @Override
    public void onHurtMaximized(DamageData.OffenceMax data) {
        LivingEntity target = data.getTarget();
        if (target.hasEffect(CCEffects.UNYIELDING.holder()) && data.getDamageIncoming() >= target.getHealth()) {
            data.getContainer().setNewDamage(0);
        }
        LivingEntity attacker = data.getAttacker();
        if (attacker != null && attacker.hasEffect(CCEffects.HIDDEN.holder())) {
            attacker.removeEffect(CCEffects.HIDDEN.holder());
        }
        this.getAttr(target, Attributes.ARMOR).ifPresent(ins -> ins.removeModifier(CCUtils.BYPASS_ARMOR_NAME));
        this.getAttr(target, Attributes.ARMOR_TOUGHNESS).ifPresent(ins -> ins.removeModifier(CCUtils.BYPASS_TOUGHNESS_NAME));
    }
}
