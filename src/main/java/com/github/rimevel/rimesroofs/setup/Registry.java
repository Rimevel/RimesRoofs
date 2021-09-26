package com.github.rimevel.rimesroofs.setup;

import com.github.rimevel.rimesroofs.Rimesroofs;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class Registry {
    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Items.ITEMS.register(modEventBus);
        Blocks.BLOCKS.register(modEventBus);
    }

    public static final class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Rimesroofs.MOD_ID);

        /*--Items--*/

        /*--Registration methods--*/

        private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
            return ITEMS.register(name, item);
        }
    }

    public static final class Blocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Rimesroofs.MOD_ID);

        /*--Blocks--*/

        //Oak Roof
        public static final RegistryObject<Block> ROOF_OAK = register("roof_oak", () -> {
            return new Block(AbstractBlock.Properties.of(Material.WOOD).strength(2, 2).harvestLevel(0).sound(SoundType.WOOD));
        });

        /*--Registration methods--*/

        private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
            return BLOCKS.register(name, block);
        }

        private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
            RegistryObject<T> obj = registerNoItem(name, block);
            Items.ITEMS.register(name, () -> {
                return new BlockItem(obj.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
            });
            return obj;
        }
    }

    public static final class Tags {
        public static final class Blocks {
            /*--Tags--*/

            public static final ITag.INamedTag<Block> ROOF_COMPATIBLE = mod("mod_compatible");

            /*--Registration methods--*/

            private static ITag.INamedTag<Block> forge(String path) {
                return BlockTags.bind(new ResourceLocation("forge", path).toString());
            }

            private static ITag.INamedTag<Block> mod(String path) {
                return BlockTags.bind(new ResourceLocation(Rimesroofs.MOD_ID, path).toString());
            }
        }

        public static final class Items {

        }
    }
}
