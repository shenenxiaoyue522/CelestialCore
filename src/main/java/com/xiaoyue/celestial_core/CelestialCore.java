package com.xiaoyue.celestial_core;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import com.xiaoyue.celestial_core.content.generic.EntityTagDataSyncPacket;
import com.xiaoyue.celestial_core.data.*;
import com.xiaoyue.celestial_core.events.CCAttackListener;
import com.xiaoyue.celestial_core.register.*;
import com.xiaoyue.celestial_invoker.content.common.registrar.RegistrateExtra;
import com.xiaoyue.celestial_invoker.content.generic.generator.CelestialProviders;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2core.serial.config.PacketHandlerWithConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2damagetracker.contents.materials.vanilla.GenItemVanillaType;
import dev.xkmc.l2serial.network.PacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import org.slf4j.Logger;

@Mod(CelestialCore.MODID)
@EventBusSubscriber(modid = CelestialCore.MODID)
public class CelestialCore {

    public static final String MODID = "celestial_core";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
    public static final RegistrateExtra<L2Registrate> EXTRA = new RegistrateExtra<>(REGISTRATE);
    public static final GenItemVanillaType MATS = new GenItemVanillaType(MODID, REGISTRATE);
    public static final PacketHandlerWithConfig HANDLER = new PacketHandlerWithConfig(MODID, 1,
            p -> p.create(EntityTagDataSyncPacket.class, PacketHandler.NetDir.PLAY_TO_CLIENT));

    public static final SimpleEntry<CreativeModeTab> TAB =
            REGISTRATE.buildModCreativeTab("main", "Celestial Core Misc",
                    e -> e.icon(CCItems.SAKURA_STEEL::asStack));

    public static final SimpleEntry<CreativeModeTab> TOOL_TAB =
            REGISTRATE.buildModCreativeTab("tools", "Celestial Core Tools",
                    e -> e.icon(() -> CCMaterials.VIRTUAL_GOLD.getArmor(EquipmentSlot.CHEST).getDefaultInstance()));

    public CelestialCore() {
        CCItems.register();
        CCEffects.register();
        CCLootModifier.register();
        CCAttributes.register();
        CCRecipes.register();
        CCModConfig.init();
        CCObjects.register();
        AttackEventHandler.register(3450, new CCAttackListener());
    }

    @SubscribeEvent
    public static void modifyAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, CCAttributes.REGEN_RATE.holder(), 1);
        event.add(EntityType.PLAYER, CCAttributes.ARROW_SPEED.holder(), 1);
        event.add(EntityType.PLAYER, CCAttributes.ARROW_KNOCK.holder(), 0);
        event.add(EntityType.PLAYER, CCAttributes.ARMOR_PENETRATION.holder(), 0);
        event.add(EntityType.PLAYER, CCAttributes.TOUGHNESS_PENETRATION.holder(), 0);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void gatherData(GatherDataEvent event) {
        new CCDamageTypes(REGISTRATE).generate();
        REGISTRATE.addDataGenerator(ProviderType.DATA_MAP, CCDataMapGen::onDataMapGen);
        REGISTRATE.addDataGenerator(ProviderType.LANG, CCLangData::addLang);
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, CCRecipeGen::onRecipeGen);
        REGISTRATE.addDataGenerator(ProviderType.LOOT, CCLootTableGen::onLootGen);
        REGISTRATE.addDataGenerator(CelestialProviders.LOOT_MODIFIER, CCGLMProvider::onModifierGen);
    }

    public static ResourceLocation loc(String id) {
        return ResourceLocation.fromNamespaceAndPath(MODID, id);
    }

}
