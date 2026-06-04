package com.xiaoyue.celestial_core.register;

import com.xiaoyue.celestial_core.CelestialCore;
import com.xiaoyue.celestial_core.content.generic.EntityTagData;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_invoker.content.common.registrar.NeoForgeRegister;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class CCObjects {

    public static final NeoForgeRegister<AttachmentType<?>> TYPE = CelestialCore.EXTRA.neoforgeRegister(NeoForgeRegistries.ATTACHMENT_TYPES);

    public static final Supplier<AttachmentType<EntityTagData>> TAG_DATA = TYPE.object("tag_data", () -> AttachmentType.builder(EntityTagData::new)
            .serialize(new EntityTagData()).build());

    public static final Supplier<AttachmentType<PlayerFlagData>> FLAG_DATA = TYPE.object("flag_data", () -> AttachmentType.builder(PlayerFlagData::new)
            .serialize(new PlayerFlagData()).copyOnDeath().build());

    public static void register() {
    }
}
