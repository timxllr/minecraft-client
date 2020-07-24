package de.crazymemecoke.module.modules.render;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Tracers extends Module {

	public Tracers() {
		super("Tracers", Keyboard.KEY_NUMPAD8, Category.RENDER, Rainbow.rainbow(1, 1).hashCode());
	}

	public void onRender() {
		if (!this.getState()) {
			return;
		}
		for (Object theObject : mc.theWorld.loadedEntityList) {
			if (!(theObject instanceof EntityLivingBase))
				continue;
			EntityLivingBase entity = (EntityLivingBase) theObject;
			if (entity instanceof EntityPlayer) {
				if (entity != mc.thePlayer)
					player(entity);
				continue;
			}
		}
		super.onRender();
	}

	public void player(EntityLivingBase entity) {

		float red = 1F;
		float green = 0.5F;
		float blue = 0.5F;

		double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks)
				- mc.getRenderManager().renderPosX;
		double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks)
				- mc.getRenderManager().renderPosY;
		double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks)
				- mc.getRenderManager().renderPosZ;

		render(red, green, blue, xPos, yPos, zPos);
	}

	public void render(float red, float green, float blue, double x, double y, double z) {
		RenderUtils.drawTracerLine(x, y, z, red, green, blue, 0.45F, 3F);
	}
}
