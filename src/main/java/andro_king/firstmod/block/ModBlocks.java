package andro_king.firstmod.block;


import andro_king.firstmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.stateprovider.PillarBlockStateProvider;

import java.util.function.Function;

public class ModBlocks {
    //registration of unique blocks themselves
    public static final Block WHITE_PUMPKIN = registerPillarBlock(
            "white_pumpkin",
            AbstractBlock.Settings.create()
                .mapColor(MapColor.OFF_WHITE)
                .instrument(NoteBlockInstrument.DIDGERIDOO)
                .strength(1.0F)
                .sounds(BlockSoundGroup.WOOD)
                .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block CARVED_WHITE_PUMPKIN = registerCarvedPumpkin(
            "carved_white_pumpkin",
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .allowsSpawning(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block WHITE_JACK_O_LANTERN = registerCarvedPumpkin(
            "white_jack_o_lantern",
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .strength(1.0F)
                    .luminance(state -> 15)
                    .sounds(BlockSoundGroup.WOOD)
                    .allowsSpawning(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    //TODO: make sculk_pillar drop xp if mined without silktouch
    public static final Block SCULK_PILLAR = registerPillarBlock(
            "sculk_pillar",
            AbstractBlock.Settings.create()
                    .strength(0.75F, 2.5F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SCULK_CATALYST)
                    .mapColor(MapColor.PALE_YELLOW)
    );
    public static final Block SCULK_BRICKS = registerBlock(
            "sculk_bricks",
            AbstractBlock.Settings.create()
                    .strength(1.5F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SCULK_CATALYST)
                    .mapColor(MapColor.PALE_YELLOW)
    );
    public static final Block CRACKED_SCULK_BRICKS = registerBlock(
            "cracked_sculk_bricks",
            AbstractBlock.Settings.create()
                    .strength(1.3F, 2.5F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SCULK_CATALYST)
                    .mapColor(MapColor.PALE_YELLOW)
    );
    public static final Block SILENT_SCULK = registerBlock(
            "silent_sculk",
            AbstractBlock.Settings.create()
                    .strength(0.15F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SCULK)
                    .mapColor(MapColor.BLACK)
    );
    /*
    public static final Block SCULK_SPIKE = registerSpike(
            "sculk_spike",
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_YELLOW)
                    .solid()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SCULK_CATALYST)
                    .strength(1.5F, 3.0F)
                    .dynamicBounds()
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)
    );
    */

    //different kinds of block registration
    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    private static Block registerPillarBlock(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        Block block = new PillarBlock(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    private static Block registerSpike(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        Block block = new PointedDripstoneBlock(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    private static Block registerCarvedPumpkin(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        Block block = new CarvedPumpkinBlock(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    //general registration
    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering ModBlocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.SCULK_PILLAR));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.SCULK_BRICKS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.CRACKED_SCULK_BRICKS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.SILENT_SCULK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.WHITE_PUMPKIN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.CARVED_WHITE_PUMPKIN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(
                fabricItemGroupEntries -> fabricItemGroupEntries.add(ModBlocks.WHITE_JACK_O_LANTERN));
    }
}
