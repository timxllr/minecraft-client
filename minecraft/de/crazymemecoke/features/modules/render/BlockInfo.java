package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.Objects;

public class BlockInfo extends Module {
    public BlockInfo() {
        super("BlockInfo", Keyboard.KEY_NONE, Category.RENDER);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.twoD) {
                if (!(Objects.isNull(mc.objectMouseOver.getBlockPos()))) {
                    BlockPos blockPos = mc.objectMouseOver.getBlockPos();
                    Block block = getBlockAtPos(blockPos);

                    if(block instanceof BlockAir){
                        return;
                    }

                    ScaledResolution s = new ScaledResolution(mc);

                    UnicodeFontRenderer font = Client.main().fontMgr().font("BigNoodleTitling", 20, Font.PLAIN);

                    String text = block.getLocalizedName() + " ID: " + Block.getIdFromBlock(block);

                    RenderUtils.drawRect(s.width() / 2 + 10, s.height() / 2 - 8, s.width() / 2 + font.getStringWidth(text) + 15, s.height() / 2 + 8, new Color(0, 0, 0).getRGB());

                    font.drawStringWithShadow(text, s.width() / 2 + 12, s.height() / 2 - 4, -1);
                }
            }
        }
    }

    public static Block getBlockAtPos(BlockPos inBlockPos) {
        BlockPos currentPos = inBlockPos;
        IBlockState s = mc.theWorld.getBlockState(currentPos);
        return s.getBlock();
    }
}
