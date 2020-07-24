package de.crazymemecoke.module.modules.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class ProphuntESP extends Module {

	public ProphuntESP() {
		super("ProphuntESP", Keyboard.KEY_NONE, Category.RENDER, Rainbow.rainbow(1, 1).hashCode());
	}

	@Override
	public void onRender() {
		for (Object entity : mc.theWorld.loadedEntityList)
			if (entity instanceof EntityLiving && ((Entity) entity).isInvisible()) {
				double x = ((Entity) entity).posX;
				double y = ((Entity) entity).posY;
				double z = ((Entity) entity).posZ;
				Color color;
				if (mc.thePlayer.getDistanceToEntity((Entity) entity) >= 0.5)
					color = new Color(1F, 0F, 0F,
							0.5F - MathHelper.abs(
									MathHelper.sin(Minecraft.getSystemTime() % 1000L / 1000.0F * (float) Math.PI * 1.0F)
											* 0.3F));
				else
					color = new Color(0, 0, 0, 0);
				RenderUtils.box(x - 0.5, y - 0.1, z - 0.5, x + 0.5, y + 0.9, z + 0.5, color);
			}
	}

}
