package com.mrbysco.itemframes;

import com.hypixel.hytale.component.ComponentRegistryProxy;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.mrbysco.itemframes.component.BoundEntityComponent;
import com.mrbysco.itemframes.component.ItemFrameComponent;
import com.mrbysco.itemframes.interaction.ItemFrameEntityInteraction;
import com.mrbysco.itemframes.interaction.ItemFrameInteraction;
import com.mrbysco.itemframes.system.ItemFrameSystems;

import javax.annotation.Nonnull;

public class ItemFramePlugin{
	private static ComponentType<EntityStore, ItemFrameComponent> itemFrameComponent;
	private static ComponentType<ChunkStore, BoundEntityComponent> boundEntityComponent;

	public ItemFramePlugin() {}

	public static void register(JavaPlugin plugin) {

		itemFrameComponent = plugin.getEntityStoreRegistry().registerComponent(ItemFrameComponent.class, "ItemFrames_ItemFrame", ItemFrameComponent.CODEC);
		boundEntityComponent = plugin.getChunkStoreRegistry().registerComponent(BoundEntityComponent.class, "ItemFrames_BoundEntity", BoundEntityComponent.CODEC);

		plugin.getCodecRegistry(Interaction.CODEC).register("ItemFrameInteraction", ItemFrameInteraction.class, ItemFrameInteraction.CODEC);
		plugin.getCodecRegistry(Interaction.CODEC).register("ItemFrameEntityInteraction", ItemFrameEntityInteraction.class, ItemFrameEntityInteraction.CODEC);

		ComponentRegistryProxy<EntityStore> componentregistryproxy = plugin.getEntityStoreRegistry();
		componentregistryproxy.registerSystem(new ItemFrameSystems.PlaceSystem());
		componentregistryproxy.registerSystem(new ItemFrameSystems.BreakSystem());
		componentregistryproxy.registerSystem(new ItemFrameSystems.ItemFrameTick());
	}

	public static ComponentType<EntityStore, ItemFrameComponent> getItemFrameComponent() {
		return itemFrameComponent;
	}

	public static ComponentType<ChunkStore, BoundEntityComponent> getBoundEntityComponent() {
		return boundEntityComponent;
	}
}