package de.crazymemecoke.ui.altmanager;

import de.crazymemecoke.ui.UIFlatButton;
import de.crazymemecoke.ui.altmanager.gui.GuiAddAlt;
import de.crazymemecoke.ui.altmanager.gui.GuiEditAlt;
import de.crazymemecoke.utils.Colors;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import de.crazymemecoke.utils.time.TimerUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GuiAltManager extends GuiScreen {
    private final GuiScreen parentScreen;
    private GuiButton loginButton;
    private GuiButton addAltButton;
    private GuiButton editAltButton;
    private GuiButton deleteAltButton;
    private GuiButton importAltsButton;
    private GuiButton cancelButton;
    private int scroll;
    float opacity = 0;
    private float sliderY = 0;
    private int sliderY2;
    private float sliderOpacity;
    private boolean clickedSlider;
    private boolean dragSlider;
    private final JFileChooser fc = new JFileChooser();
    private ArrayList particles;
    private final int MAX_PARTICLES = 5000;
    private final Random random = new Random();
    public static TimerUtil timer = new TimerUtil();
    public static GuiAltSlot selected = null;
    private boolean clicked;

    public GuiAltManager(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    public void updateScreen() {
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        AltManager.loadAlts();
        selected = null;
        this.scroll = 0;
        int c = -15698006;
        this.particles = new ArrayList();
        this.particles.clear();
        this.buttonList.clear();
        this.buttonList.add(this.loginButton = new UIFlatButton(0, this.width - 150 - 140, this.height - 30, 60, 15, "Login", Colors.DARKBLUE.c));
        this.buttonList.add(this.addAltButton = new UIFlatButton(1, this.width - 150 - 70, this.height - 30, 60, 15, "Add Alt", Colors.DARKGREEN.c));
        this.buttonList.add(this.editAltButton = new UIFlatButton(2, this.width - 75, this.height - 30, 65, 15, "Edit Alt", Colors.GREY.c));
        this.buttonList.add(this.deleteAltButton = new UIFlatButton(3, this.width - 145, this.height - 30, 65, 15, "Delete Alt", Colors.DARKRED.c));
        this.buttonList.add(this.cancelButton = new UIFlatButton(4, 15, this.height - 30, 65, 15, "Back", Colors.DARKGREY.c));
        this.buttonList.add(this.importAltsButton = new UIFlatButton(5, this.width - 145, this.height - 50, 135, 15, "Import Alts", Colors.DARKMAGENTA.c));
        this.sliderOpacity = 0.5F;
        this.opacity = 0;
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {

        // Login into selected alt
        if (button.id == 0 && selected != null) {
            AltLogin.login(selected.getUsername(), selected.getPassword());
        }

        // Add new alt
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiAddAlt(this));
        }

        // Edit selected alt
        if (button.id == 2 && selected != null) {
            this.mc.displayGuiScreen(new GuiEditAlt(this, selected));
        }

        // Remove selected alt
        if (button.id == 3 && selected != null) {
            AltManager.guiSlotList.remove(selected);
        }

        // Back with alt-saving
        if (button.id == 4) {
            AltManager.saveAlts();
            this.mc.displayGuiScreen(this.parentScreen);
        }

        // Import alts from file
        if (button.id == 5) {
            Runnable run = this::importAlts;
            (new Thread(run)).start();
        }

    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        Iterator var5 = AltManager.guiSlotList.iterator();

        while (var5.hasNext()) {
            GuiAltSlot slot = (GuiAltSlot) var5.next();
            if (slot.isHovering(mouseX, mouseY) && selected != slot) {
                selected = slot;
                timer.setLastMs(-1900);
            }
        }

        if (timer.isDelayComplete(2000L) && selected != null && selected.isHovering(mouseX, mouseY)) {
            AltLogin.login(selected.getUsername(), selected.getPassword());
            timer.reset();
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution res = new ScaledResolution(Wrapper.mc);
        if (Keyboard.isKeyDown(1)) {
            this.mc.displayGuiScreen(this.parentScreen);
        }

        boolean topHeight = true;
        int darkGray = -15658735;
        int lightGray = -15066598;
        int red = -1023904;
        if (this.opacity < 1) {
            this.opacity += 0.1F;
        }

        if (this.opacity > 1) {
            this.opacity = 1;
        }

        //RenderUtil.drawFullscreenImage(new ResourceLocation("slowly/bg.jpg"));
        byte y = 0;
        Wrapper.mc.fontRendererObj.drawString("AltManager", (res.getScaledWidth() / 2 + 1), 21, RenderUtils.reAlpha(Colors.DARKGREY.c, this.opacity));
        Wrapper.mc.fontRendererObj.drawString("AltManager", (res.getScaledWidth() / 2), 20, RenderUtils.reAlpha(-1, this.opacity));
        Gui.drawRect(10, 50, res.getScaledWidth() - 150, res.getScaledHeight() - 10, RenderUtils.reAlpha(darkGray, this.opacity));
        Gui.drawRect(res.getScaledWidth() - 150, 50, res.getScaledWidth() - 5, res.getScaledHeight() - 10, RenderUtils.reAlpha(-16119286, 0.75F * this.opacity));
        Wrapper.mc.fontRendererObj.drawString("Status:", (res.getScaledWidth() - 145), 55, RenderUtils.reAlpha(Colors.GREEN.c, this.opacity));
        boolean premium = this.mc.session.getProfile().isComplete();
        String strPremium = premium ? "Premium" : "Cracked";
        Wrapper.mc.fontRendererObj.drawString(strPremium, (res.getScaledWidth() - Wrapper.mc.fontRendererObj.getStringWidth(strPremium) - 15), 55, premium ? RenderUtils.reAlpha(Colors.YELLOW.c, this.opacity) : RenderUtils.reAlpha(Colors.DARKRED.c, this.opacity));
        Wrapper.mc.fontRendererObj.drawString("Username:", (res.getScaledWidth() - 145), 70, RenderUtils.reAlpha(Colors.GREEN.c, this.opacity));
        Wrapper.mc.fontRendererObj.drawString(this.mc.session.getUsername(), (res.getScaledWidth() - Wrapper.mc.fontRendererObj.getStringWidth(this.mc.session.getUsername()) - 15), 70, RenderUtils.reAlpha(Colors.MAGENTA.c, this.opacity));
        Wrapper.mc.fontRendererObj.drawString("Alts:", (res.getScaledWidth() - 145), 85, RenderUtils.reAlpha(Colors.GREEN.c, this.opacity));
        Wrapper.mc.fontRendererObj.drawString(String.valueOf(AltManager.guiSlotList.size()), (res.getScaledWidth() - Wrapper.mc.fontRendererObj.getStringWidth(String.valueOf(AltManager.guiSlotList.size())) - 15), 85, RenderUtils.reAlpha(Colors.WHITE.c, this.opacity));
        byte MIN_HEIGHT = 75;
        int MAX_HEIGHT = res.getScaledHeight() - 35;
        float percent = (this.sliderY - MIN_HEIGHT) / (MAX_HEIGHT - MIN_HEIGHT);
        float scrollAmount = (-Mouse.getDWheel()) * 0.07F;
        if (scrollAmount > 0) {
            if (this.sliderY + scrollAmount < MAX_HEIGHT) {
                this.sliderY += scrollAmount;
            } else {
                this.sliderY = MAX_HEIGHT;
            }
        } else if (scrollAmount < 0) {
            if (this.sliderY - scrollAmount > MIN_HEIGHT) {
                this.sliderY += scrollAmount;
            } else {
                this.sliderY = MIN_HEIGHT;
            }
        }

        int all = 0;

        for (Iterator slot = AltManager.guiSlotList.iterator(); slot.hasNext(); all += 25) {
            GuiAltSlot slotY = (GuiAltSlot) slot.next();
        }

        int slotY1 = -((int) (all * percent - (75 + y)));

        for (Iterator var21 = AltManager.guiSlotList.iterator(); var21.hasNext(); slotY1 += 25) {
            GuiAltSlot slot1 = (GuiAltSlot) var21.next();
            slot1.y = slotY1;
            slot1.opacity = this.opacity;
            slot1.WIDTH = res.getScaledWidth() - 160;
            slot1.MIN_HEIGHT = 50;
            slot1.MAX_HEIGHT = res.getScaledHeight() - 10;
            slot1.drawScreen(mouseX, mouseY);
        }

        Gui.drawRect(10, 50, res.getScaledWidth() - 150, 75, RenderUtils.reAlpha(lightGray, this.opacity));
        Wrapper.mc.fontRendererObj.drawString("EMAIL:PASS", ((10 + res.getScaledWidth() - 150) / 2), 57, RenderUtils.reAlpha(-1, this.opacity));
        Gui.drawRect(10, res.getScaledHeight() - 35, res.getScaledWidth() - 150, res.getScaledHeight() - 10, RenderUtils.reAlpha(lightGray, this.opacity));
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawSlider(mouseX, mouseY);
    }

    private void drawSlider(int mouseX, int mouseY) {
        ScaledResolution res = new ScaledResolution(Wrapper.mc);
        boolean MIN_HEIGHT = true;
        int MAX_HEIGHT = res.getScaledHeight() - 35;
        int WIDTH = res.getScaledWidth() - 150;
        byte radius = 2;
        int var10000 = AltManager.guiSlotList.size();
        byte var10001;
        if (AltManager.guiSlotList.size() == 0) {
            var10001 = 0;
        } else {
            GuiAltSlot var17 = (GuiAltSlot) AltManager.guiSlotList.get(0);
            var10001 = 25;
        }

        int allAltsHeight = var10000 * var10001;
        float height;
        if (allAltsHeight <= MAX_HEIGHT - 75) {
            height = (MAX_HEIGHT - 75);
        } else {
            height = (MAX_HEIGHT - 75) / (allAltsHeight + 12) * (MAX_HEIGHT - 75);
        }

        if (height > (MAX_HEIGHT - 75)) {
            height = (MAX_HEIGHT - 75);
        }

        int x = WIDTH - radius / 2;
        int y = (int) this.sliderY;
        int x2 = WIDTH + radius / 2;
        int y2 = (int) (this.sliderY + height - radius);
        boolean yAdd = height < 2;
        boolean hover = mouseX >= x && mouseX <= x2 && mouseY >= y - (yAdd ? 2 : 0) && mouseY <= y2 + (yAdd ? 2 : 0);
        int color = !hover && !this.clickedSlider ? Colors.GREEN.c : Colors.DARKGREEN.c;
        if (Mouse.isButtonDown(0)) {
            if (!this.clickedSlider && hover) {
                this.clickedSlider = true;
                this.sliderY2 = (int) (mouseY - this.sliderY);
            }
        } else {
            this.clickedSlider = false;
        }

        if (this.clickedSlider) {
            this.sliderY = (mouseY - this.sliderY2);
        }

        if (this.sliderY + height > MAX_HEIGHT) {
            this.sliderY = MAX_HEIGHT - height;
        }

        if (this.sliderY < 75) {
            this.sliderY = 75;
        }

        Gui.drawRect(WIDTH - radius / 2, (int) this.sliderY - (yAdd ? 2 : 0), WIDTH + radius / 2, (int) (this.sliderY + height - radius) + (yAdd ? 2 : 0), RenderUtils.reAlpha(color, this.opacity));
        RenderUtils.drawFilledCircle(WIDTH, (int) this.sliderY - (yAdd ? 2 : 0), (radius / 2), RenderUtils.reAlpha(color, this.opacity));
        RenderUtils.drawFilledCircle(WIDTH, (int) this.sliderY + (int) height - radius + (yAdd ? 2 : 0), (radius / 2), RenderUtils.reAlpha(color, this.opacity));
    }

    private void scroll(boolean canScroll) {
        int scroll_ = Mouse.getDWheel() / 12;
        if (canScroll && scroll_ > 0) {
            this.scroll -= scroll_;
        }

        if (this.scroll < 0) {
            this.scroll = 0;
        }

    }

    private void importAlts() {
        File fromFile = null;
        this.fc.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int returnVal = this.fc.showOpenDialog(null);
        this.fc.requestFocus();
        if (returnVal == 0) {
            fromFile = this.fc.getSelectedFile();
            ArrayList altsToImport = new ArrayList();

            try {
                BufferedReader e = new BufferedReader(new FileReader(fromFile));

                String writer;
                while ((writer = e.readLine()) != null) {
                    String[] s = writer.split(":");
                    if (s.length > 0) {
                        altsToImport.add(writer);
                    }
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            }

            try {
                FileWriter e1 = new FileWriter(AltManager.altFile, true);
                PrintWriter writer1 = new PrintWriter(e1);
                Iterator var7 = altsToImport.iterator();

                while (var7.hasNext()) {
                    String s1 = (String) var7.next();
                    writer1.write(s1 + "\n");
                }

                writer1.close();
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            this.mc.displayGuiScreen(this);
        }

    }

    private String getStatus() {
        return this.mc.session == null ? "Cracked as " : "Logged in as " + this.mc.session.getUsername();
    }
}
