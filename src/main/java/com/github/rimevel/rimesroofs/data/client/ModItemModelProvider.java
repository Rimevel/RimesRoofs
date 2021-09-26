package com.github.rimevel.rimesroofs.data.client;

import com.github.rimevel.rimesroofs.Rimesroofs;
import com.github.rimevel.rimesroofs.setup.Registry;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Rimesroofs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        Registry.Items.ITEMS.getEntries().forEach((item) -> {
            String id = item.getId().getPath();
            //withExistingParent(id, modLoc("block/" + id));
            builder(itemGenerated, id);
        });
    }

    private ItemModelBuilder builder(ModelFile modelFile, String name) {
        return getBuilder(name).parent(modelFile).texture("layer0", "item/" + name);
    }
}
