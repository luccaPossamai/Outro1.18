package com.lucca.mohard.data.advancementes;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public ModAdvancementProvider(DataGenerator p_123966_) {
        super(p_123966_);
        generator = p_123966_;
    }

    @Override
    public void run(HashCache p_123969_) {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (p_123977_) -> {
            if (!set.add(p_123977_.getId())) {
                throw new IllegalStateException("Duplicate advancement " + p_123977_.getId());
            } else {
                Path path1 = createPath(path, p_123977_);

                try {
                    DataProvider.save(GSON, p_123969_, p_123977_.deconstruct().serializeToJson(), path1);
                } catch (IOException ioexception) {
                    LOGGER.error("Couldn't save advancement {}", path1, ioexception);
                }

            }
        };

        new EssenceAdvancementes().accept(consumer);
    }

    private static Path createPath(Path p_123971_, Advancement p_123972_) {
        return p_123971_.resolve("data/" + p_123972_.getId().getNamespace() + "/advancements/" + p_123972_.getId().getPath() + ".json");
    }
}
