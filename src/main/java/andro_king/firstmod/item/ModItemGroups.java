package andro_king.firstmod.item;

import andro_king.firstmod.TutorialMod;
import andro_king.firstmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup SCULK_ADDITIONS = Registry.register(Registries.ITEM_GROUP, Identifier.of(
                    TutorialMod.MOD_ID,
                    "sculk_additions"),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModItems.SCULK_SHELL))
                        .displayName(Text.translatable("itemgroup.tutorialmod.sculk_additions"))
                        .entries((displayContext, entries) -> {
                            entries.add(ModItems.SCULK_SHELL);
                            entries.add(ModItems.SCULK_TOOTH);
                            entries.add(ModBlocks.SCULK_BRICKS);
                            entries.add(ModBlocks.CRACKED_SCULK_BRICKS);
                            entries.add(ModBlocks.SCULK_PILLAR);
                            entries.add(ModBlocks.SILENT_SCULK);
                        })
                        .build()
            );

    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Register Item Groups for " + TutorialMod.MOD_ID);
    }
}
