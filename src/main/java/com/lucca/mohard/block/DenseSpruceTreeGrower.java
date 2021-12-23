package com.lucca.mohard.block;

import com.lucca.mohard.biome.denseTaiga.DenseTaigaBiomeMaker;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class DenseSpruceTreeGrower extends AbstractMegaTreeGrower {



    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random p_60044_, boolean p_60045_) {
        if(DenseTaigaBiomeMaker.DENSE_SPRUCE == null){
            return null;
        }
        return DenseTaigaBiomeMaker.DENSE_SPRUCE;
    }

    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredMegaFeature(Random p_60042_) {

        if(DenseTaigaBiomeMaker.MEGA_DENSE_PINE == null || DenseTaigaBiomeMaker.MEGA_DENSE_SPRUCE == null){
            return null;
        }
        return p_60042_.nextBoolean() ? DenseTaigaBiomeMaker.MEGA_DENSE_SPRUCE : DenseTaigaBiomeMaker.MEGA_DENSE_PINE;


    }




}
