/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jan 14, 2014, 5:28:21 PM (GMT)]
 */
package vazkii.arl.client;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public final class AtlasSpriteHelper {

	public static TextureAtlasSprite forName(TextureMap ir, ResourceLocation res) {
		return ir.registerSprite(res);
	}

	/**
	 * Renders a sprite from the spritesheet with depth, like a "builtin/generated" item model.
	 * Adapted from ItemRenderer.renderItemIn2D, 1.7.10
	 */
	public static void renderIconThicc(Tessellator tess, float maxU, float maxV, float minU, float minV, int width, int height, float thickness) {
		BufferBuilder wr = tess.getBuffer();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
		wr.pos(0.0D, 0.0D, 0.0D).tex(maxU, minV).normal(0, 0, 1).endVertex();
		wr.pos(1.0D, 0.0D, 0.0D).tex(minU, minV).normal(0, 0, 1).endVertex();
		wr.pos(1.0D, 1.0D, 0.0D).tex(minU, maxV).normal(0, 0, 1).endVertex();
		wr.pos(0.0D, 1.0D, 0.0D).tex(maxU, maxV).normal(0, 0, 1).endVertex();
		tess.draw();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
		wr.pos(0.0D, 1.0D, 0.0F - thickness).tex(maxU, maxV).normal(0, 0, -1).endVertex();
		wr.pos(1.0D, 1.0D, 0.0F - thickness).tex(minU, maxV).normal(0, 0, -1).endVertex();
		wr.pos(1.0D, 0.0D, 0.0F - thickness).tex(minU, minV).normal(0, 0, -1).endVertex();
		wr.pos(0.0D, 0.0D, 0.0F - thickness).tex(maxU, minV).normal(0, 0, -1).endVertex();
		tess.draw();
		float f5 = 0.5F * (maxU - minU) / width;
		float f6 = 0.5F * (minV - maxV) / height;
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
		int k;
		float f7;
		float f8;

		for (k = 0; k < width; ++k)
		{
			f7 = (float)k / (float)width;
			f8 = maxU + (minU - maxU) * f7 - f5;
			wr.pos(f7, 0.0D, 0.0F - thickness).tex(f8, minV).normal(-1, 0, 0).endVertex();
			wr.pos(f7, 0.0D, 0.0D).tex(f8, minV).normal(-1, 0, 0).endVertex();
			wr.pos(f7, 1.0D, 0.0D).tex(f8, maxV).normal(-1, 0, 0).endVertex();
			wr.pos(f7, 1.0D, 0.0F - thickness).tex(f8, maxV).normal(-1, 0, 0).endVertex();
		}

		tess.draw();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
		float f9;

		for (k = 0; k < width; ++k)
		{
			f7 = (float)k / (float)width;
			f8 = maxU + (minU - maxU) * f7 - f5;
			f9 = f7 + 1.0F / width;
			wr.pos(f9, 1.0D, 0.0F - thickness).tex(f8, maxV).normal(1, 0, 0).endVertex();
			wr.pos(f9, 1.0D, 0.0D).tex(f8, maxV).normal(1, 0, 0).endVertex();
			wr.pos(f9, 0.0D, 0.0D).tex(f8, minV).normal(1, 0, 0).endVertex();
			wr.pos(f9, 0.0D, 0.0F - thickness).tex(f8, minV).normal(1, 0, 0).endVertex();
		}

		tess.draw();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);

		for (k = 0; k < height; ++k)
		{
			f7 = (float)k / (float)height;
			f8 = minV + (maxV - minV) * f7 - f6;
			f9 = f7 + 1.0F / height;
			wr.pos(0.0D, f9, 0.0D).tex(maxU, f8).normal(0, 1, 0).endVertex();
			wr.pos(1.0D, f9, 0.0D).tex(minU, f8).normal(0, 1, 0).endVertex();
			wr.pos(1.0D, f9, 0.0F - thickness).tex(minU, f8).normal(0, 1, 0).endVertex();
			wr.pos(0.0D, f9, 0.0F - thickness).tex(maxU, f8).normal(0, 1, 0).endVertex();
		}

		tess.draw();
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);

		for (k = 0; k < height; ++k)
		{
			f7 = (float)k / (float)height;
			f8 = minV + (maxV - minV) * f7 - f6;
			wr.pos(1.0D, f7, 0.0D).tex(minU, f8).normal(0, -1, 0).endVertex();
			wr.pos(0.0D, f7, 0.0D).tex(maxU, f8).normal(0, -1, 0).endVertex();
			wr.pos(0.0D, f7, 0.0F - thickness).tex(maxU, f8).normal(0, -1, 0).endVertex();
			wr.pos(1.0D, f7, 0.0F - thickness).tex(minU, f8).normal(0, -1, 0).endVertex();
		}

		tess.draw();

	}

	public static void renderIconThicc(TextureAtlasSprite icon, float thickness) {
		renderIconThicc(Tessellator.getInstance(), icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), thickness);
	}
	
}
