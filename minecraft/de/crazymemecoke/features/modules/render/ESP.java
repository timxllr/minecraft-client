package de.crazymemecoke.features.modules.render;

import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ESP extends Module {

	public ESP() {
		super("ESP", Keyboard.KEY_NUMPAD2, Category.RENDER, Rainbow.rainbow(1, 1).hashCode());
	}

	@Override
	public void onRender() {
		if (getState()) {
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
	}

	public void player(EntityLivingBase entity) {
		float red = 0.5F;
		float green = 0.5F;
		float blue = 1F;

		double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks)
				- mc.getRenderManager().renderPosX;
		double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks)
				- mc.getRenderManager().renderPosY;
		double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks)
				- mc.getRenderManager().renderPosZ;

		render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
	}

	public void render(float red, float green, float blue, double x, double y, double z, float width, float height) {
		RenderUtils.drawEntityESP(x, y, z, width, height, red, green, blue, 0.45F, 0F, 0F, 0F, 1F, 1F);
	}

}
