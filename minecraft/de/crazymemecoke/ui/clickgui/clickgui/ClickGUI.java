package de.crazymemecoke.ui.clickgui.clickgui;

import de.crazymemecoke.ui.clickgui.clickgui.elements.Element;
import de.crazymemecoke.ui.clickgui.clickgui.elements.ModuleButton;
import de.crazymemecoke.ui.clickgui.clickgui.elements.menu.ElementSlider;
import de.crazymemecoke.ui.clickgui.clickgui.util.ColorUtil;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.FileUtils;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class ClickGUI extends GuiScreen {
    public static ArrayList<Panel> panels;
    public static ArrayList<Panel> rpanels;
    private ModuleButton mb = null;
    private File guiFile;

    public ClickGUI() {

        //FontUtil.setupFontUtils();
        panels = new ArrayList<>();
        double pwidth = 80;
        double pheight = 15;
        double px = 10;
        double py = 10;
        double pyplus = pheight + 10;

        try {
            guiFile = new File(Client.getInstance().getClientDir() + "/gui.txt");
            if (guiFile.createNewFile()) {
                System.out.println("File created: " + guiFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<String> lines = FileUtils.loadFile(guiFile);
        HashMap<Category, String> guiLocations = new HashMap<>();

        lines.forEach(line -> {
            // CATEGORY_X_Y

            String[] data = line.split("_");

            Category cat = Category.valueOf(data[0]);
            if (cat != null) {
                guiLocations.put(Category.valueOf(data[0]),
                        data[1] + ":" + data[2] + ":" + (data.length == 4 ? data[3] : "false"));
            } else {
                System.out.println("Error reading gui config (Category not found) line " + lines.indexOf(line));
            }
        });

        for (Category c : Category.values()) {
            String title = Character.toUpperCase(c.name().toLowerCase().charAt(0))
                    + c.name().toLowerCase().substring(1);
            double categoryX = px;
            double categoryY = py;
            boolean expanded = false;
            if (guiLocations.containsKey(c)) {
                String positions = guiLocations.get(c);
                String[] options = positions.split(":");
                categoryX = Double.parseDouble(options[0]);
                categoryY = Double.parseDouble(options[1]);
                if (options.length == 3)
                    expanded = Boolean.parseBoolean(options[2]);

            }
            ClickGUI.panels.add(new Panel(title, categoryX, categoryY, pwidth, pheight, expanded, this) {
                @Override
                public void setup() {
                    for (Module m : Client.getInstance().getModuleManager().getModules()) {
                        if (!m.getCategory().equals(c))
                            continue;
                        this.Elements.add(new ModuleButton(m, this));
                    }
                }
            });
            py += pyplus;
        }

        rpanels = new ArrayList<Panel>();
        for (Panel p : panels) {
            rpanels.add(p);
        }
        Collections.reverse(rpanels);

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Panel p : panels) {
            p.drawScreen(mouseX, mouseY, partialTicks);
        }

        ScaledResolution s = new ScaledResolution(mc);

        mb = null;
        listen:
        for (Panel p : panels) {
            if (p != null && p.visible && p.extended && p.Elements != null && p.Elements.size() > 0) {
                for (ModuleButton e : p.Elements) {
                    if (e.listening) {
                        mb = e;
                        break listen;
                    }
                }
            }
        }

        for (Panel panel : panels) {
            if (panel.extended && panel.visible && panel.Elements != null) {
                for (ModuleButton b : panel.Elements) {
                    if (b.extended && b.menuelements != null && !b.menuelements.isEmpty()) {
                        double off = 0;
                        Color temp = ColorUtil.getClickGUIColor().darker();
                        int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();

                        for (Element e : b.menuelements) {
                            e.offset = off;
                            e.update();
                            if (Client.getInstance().getSetmgr().getSettingByName("Design", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValString()
                                    .equalsIgnoreCase("New")) {
                                RenderUtils.drawRect(e.x, e.y, e.x + e.width + 2, e.y + e.height, outlineColor);
                            }
                            e.drawScreen(mouseX, mouseY, partialTicks);
                            off += e.height;
                        }
                    }
                }
            }

        }

		/*if (mb != null) {
			RenderUtils.drawOldRect(0, 0, this.width, this.height, 0x88101010);
			GL11.glPushMatrix();
			GL11.glTranslatef(s.getScaledWidth() / 2, s.getScaledHeight() / 2, 0.0F);
			GL11.glScalef(4.0F, 4.0F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow("Listening...", 0, -10, 0xffffffff);
			GL11.glScalef(0.5F, 0.5F, 0F);
			FontUtil.drawTotalCenteredStringWithShadow(
					"Press 'ESCAPE' to unbind " + mb.mod.getName()
							+ (mb.mod.getBind() > -1 ? " (" + Keyboard.getKeyName(mb.mod.getBind()) + ")" : ""),
					0, 0, 0xffffffff);
		}*/

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mb != null)
            return;

        for (Panel panel : rpanels) {
            if (panel.extended && panel.visible && panel.Elements != null) {
                for (ModuleButton b : panel.Elements) {
                    if (b.extended) {
                        for (Element e : b.menuelements) {
                            if (e.mouseClicked(mouseX, mouseY, mouseButton))
                                return;
                        }
                    }
                }
            }
        }

        for (Panel p : rpanels) {
            if (p.mouseClicked(mouseX, mouseY, mouseButton))
                return;
        }

        try {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (mb != null)
            return;

        for (Panel panel : rpanels) {
            if (panel.extended && panel.visible && panel.Elements != null) {
                for (ModuleButton b : panel.Elements) {
                    if (b.extended) {
                        for (Element e : b.menuelements) {
                            e.mouseReleased(mouseX, mouseY, state);
                        }
                    }
                }
            }
        }

        for (Panel p : rpanels) {
            p.mouseReleased(mouseX, mouseY, state);
        }

        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        for (Panel p : rpanels) {
            if (p != null && p.visible && p.extended && p.Elements != null && p.Elements.size() > 0) {
                for (ModuleButton e : p.Elements) {
                    try {
                        if (e.keyTyped(typedChar, keyCode))
                            return;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        try {
            super.keyTyped(typedChar, keyCode);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void initGui() {
        if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer) {
            if (mc.entityRenderer.theShaderGroup != null) {
                mc.entityRenderer.theShaderGroup.deleteShaderGroup();
            }
            mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }

    @Override
    public void onGuiClosed() {
        if (mc.entityRenderer.theShaderGroup != null) {
            mc.entityRenderer.theShaderGroup.deleteShaderGroup();
            mc.entityRenderer.theShaderGroup = null;
        }

        List<String> lines = new ArrayList<>();
        for (Panel panel : ClickGUI.rpanels) {
            lines.add(
                    Category.valueOf(panel.title.toUpperCase()) + "_" + panel.x + "_" + panel.y + "_" + panel.extended);
            if (panel.extended && panel.visible && panel.Elements != null) {
                for (ModuleButton b : panel.Elements) {
                    if (b.extended) {
                        for (Element e : b.menuelements) {
                            if (e instanceof ElementSlider) {
                                ((ElementSlider) e).dragging = false;
                            }
                        }
                    }
                }
            }
        }

        FileUtils.saveFile(guiFile, lines);

        Client.getInstance().getModuleManager().getModByName("ClickGUI").setState(false);
    }

    public void closeAllSettings() {
        for (Panel p : rpanels) {
            if (p != null && p.visible && p.extended && p.Elements != null && p.Elements.size() > 0) {
                for (ModuleButton e : p.Elements) {
                    // e.extended = false;
                }
            }
        }
    }
}
