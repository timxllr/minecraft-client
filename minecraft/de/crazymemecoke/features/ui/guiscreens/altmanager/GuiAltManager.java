package de.crazymemecoke.features.ui.guiscreens.altmanager;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.utils.render.Colors;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import de.crazymemecoke.utils.time.TimerUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GuiAltManager extends GuiScreen {
    public GuiScreen parent;

    public static ArrayList altList = new ArrayList();
    public static ArrayList guiSlotList = new ArrayList();
    public static File altFile;

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
    private boolean clickedSlider;
    private boolean dragSlider;
    private final JFileChooser fc = new JFileChooser();
    private final int MAX_PARTICLES = 5000;
    private final Random random = new Random();
    public static TimerUtil timer = new TimerUtil();
    public static AltSlot selected = null;

    public File getAltFile() {
        return altFile;
    }

    public GuiAltManager(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);

        Thread loadAltsThread = new Thread(AltManager::loadAlts);
        loadAltsThread.start();

        selected = null;
        scroll = 0;
        int c = -15698006;
        buttonList.clear();
        buttonList.add(new GuiButton(0, width - 150 - 130, height - 32, 60, 20, "Login"));
        buttonList.add(new GuiButton(1, width - 150 - 65, height - 32, 60, 20, "Add Alt"));
        buttonList.add(new GuiButton(2, width - 75, height - 32, 65, 20, "Edit Alt"));
        buttonList.add(new GuiButton(3, width - 145, height - 32, 65, 20, "Delete Alt"));
        buttonList.add(new GuiButton(4, 15, height - 32, 65, 20, "Back"));
        buttonList.add(new GuiButton(5, width - 145, height - 55, 135, 20, "Import Alts"));
        buttonList.add(new GuiButton(6, width - 145, height - 78, 135, 20, "Session Stealer"));
        buttonList.add(new GuiButton(7, width - 375, height - 32, 90, 20, "Direct Login"));
        opacity = 0;
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        Iterator slotList = AltManager.slotList.iterator();

        while (slotList.hasNext()) {
            AltSlot slot = (AltSlot) slotList.next();
            if (slot.isHovering(mouseX, mouseY) && selected != slot) {
                selected = slot;
                timer.setLastMs(-1900);
            }
        }
        if (timer.isDelayComplete(2000L) && selected != null && selected.isHovering(mouseX, mouseY)) {
            timer.reset();
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        // Login into selected alt
        if (button.id == 0 && selected != null) {
            Login.login(selected.getUsername(), selected.getPassword());
        }

        // Add new alt
        if (button.id == 1) {
            mc.displayGuiScreen(new GuiAddAlt(this));
        }

        // Edit selected alt
        if (button.id == 2 && selected != null) {
            mc.displayGuiScreen(new GuiEditAlt(this, selected));
        }

        // Remove selected alt
        if (button.id == 3 && selected != null) {
            AltManager.slotList.remove(selected);
            Client.main().getAltManager().saveAlts();
        }

        // Back with save
        if (button.id == 4) {
            Client.main().getAltManager().saveAlts();
            mc.displayGuiScreen(parent);
        }

        // Import alts from file
        if (button.id == 5) {
            Runnable run = this::importAlts;
            (new Thread(run)).start();
        }

        // Open Session Stealer
        if (button.id == 6) {
            mc.displayGuiScreen(new GuiSessionStealer(this));
        }

        // Direct login
        if (button.id == 7) {
            mc.displayGuiScreen(new GuiDirectLogin(this));
        }
    }

    private void drawSlider(int mouseX, int mouseY) {
        ScaledResolution res = new ScaledResolution(Wrapper.mc);
        boolean MIN_HEIGHT = true;
        int MAX_HEIGHT = res.height() - 35;
        int WIDTH = res.width() - 150;
        byte radius = 2;
        int var10000 = AltManager.slotList.size();
        byte var10001;
        if (AltManager.slotList.size() == 0) {
            var10001 = 0;
        } else {
            AltSlot var17 = (AltSlot) AltManager.slotList.get(0);
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
        int y = (int) sliderY;
        int x2 = WIDTH + radius / 2;
        int y2 = (int) (sliderY + height - radius);
        boolean yAdd = height < 2;
        boolean hover = mouseX >= x && mouseX <= x2 && mouseY >= y - (yAdd ? 2 : 0) && mouseY <= y2 + (yAdd ? 2 : 0);
        int color = !hover && !clickedSlider ? Colors.main().getHeroGreenColor() : Colors.main().getHeroGreenColor();
        if (Mouse.isButtonDown(0)) {
            if (!clickedSlider && hover) {
                clickedSlider = true;
                sliderY2 = (int) (mouseY - sliderY);
            }
        } else {
            clickedSlider = false;
        }

        if (clickedSlider) {
            sliderY = (mouseY - sliderY2);
        }

        if (sliderY + height > MAX_HEIGHT) {
            sliderY = MAX_HEIGHT - height;
        }

        if (sliderY < 75) {
            sliderY = 75;
        }

        Gui.drawRect(WIDTH - radius / 2, (int) sliderY - (yAdd ? 2 : 0), WIDTH + radius / 2, (int) (sliderY + height - radius) + (yAdd ? 2 : 0), RenderUtils.reAlpha(color, opacity));
        RenderUtils.drawFilledCircle(WIDTH, (int) sliderY - (yAdd ? 2 : 0), (radius / 2), RenderUtils.reAlpha(color, opacity));
        RenderUtils.drawFilledCircle(WIDTH, (int) sliderY + (int) height - radius + (yAdd ? 2 : 0), (radius / 2), RenderUtils.reAlpha(color, opacity));
    }

    private void scroll(boolean canScroll) {
        int scroll_ = Mouse.getDWheel() / 12;
        if (canScroll && scroll_ > 0) {
            scroll -= scroll_;
        }

        if (scroll < 0) {
            scroll = 0;
        }

    }

    private void importAlts() {
        File fromFile = null;
        fc.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int returnVal = fc.showOpenDialog(null);
        fc.requestFocus();
        if (returnVal == 0) {
            fromFile = fc.getSelectedFile();
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

            mc.displayGuiScreen(this);
        }

    }

    private String getStatus() {
        return mc.session == null ? "Cracked as " : "Logged in as " + mc.session.getUsername();
    }

    public void drawScreen(int posX, int posY, float f) {
        drawString(mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution sr = new ScaledResolution(Wrapper.mc);
        if (Keyboard.isKeyDown(1)) {
            mc.displayGuiScreen(parent);
        }

        boolean topHeight = true;
        int darkGray = -15658735;
        int lightGray = -15066598;
        int red = -1023904;
        if (opacity < 1) {
            opacity += 0.1F;
        }

        if (opacity > 1) {
            opacity = 1;
        }

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.main().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());
        byte y = 0;
        Client.main().fontMgr().comfortaa20.drawString("AltManager", width / 2 - Client.main().fontMgr().comfortaa20.getStringWidth("AltManager") / 2, 21, RenderUtils.reAlpha(Colors.main().getGrey(), opacity));
        Client.main().fontMgr().comfortaa20.drawString("AltManager", width / 2 - Client.main().fontMgr().comfortaa20.getStringWidth("AltManager") / 2, 20, RenderUtils.reAlpha(-1, opacity));
        Gui.drawRect(10, 50, sr.width() - 150, sr.height() - 10, RenderUtils.reAlpha(darkGray, opacity));
        Gui.drawRect(sr.width() - 150, 50, sr.width() - 5, sr.height() - 10, RenderUtils.reAlpha(-16119286, 0.75F * opacity));
        Client.main().fontMgr().comfortaa20.drawString("Status:", (sr.width() - 145), 55, RenderUtils.reAlpha(Colors.main().getHeroGreenColor(), opacity));
        boolean premium = mc.session.getProfile().isComplete();
        String strPremium = premium ? "Premium" : "Cracked";
        Client.main().fontMgr().comfortaa20.drawString(strPremium, (sr.width() - Client.main().fontMgr().comfortaa20.getStringWidth(strPremium) - 8), 55, premium ? RenderUtils.reAlpha(Colors.main().getSaintOrangeColor(), opacity) : RenderUtils.reAlpha(Colors.main().getVortexRedColor(), opacity));
        Client.main().fontMgr().comfortaa20.drawString("Name:", (sr.width() - 145), 70, RenderUtils.reAlpha(Colors.main().getHeroGreenColor(), opacity));
        Client.main().fontMgr().comfortaa20.drawString(mc.session.getUsername(), (sr.width() - Client.main().fontMgr().comfortaa20.getStringWidth(mc.session.getUsername()) - 8), 70, RenderUtils.reAlpha(Colors.main().getNodusPurpleColor(), opacity));
        Client.main().fontMgr().comfortaa20.drawString("Alts:", (sr.width() - 145), 85, RenderUtils.reAlpha(Colors.main().getHeroGreenColor(), opacity));
        Client.main().fontMgr().comfortaa20.drawString(String.valueOf(AltManager.slotList.size()), (sr.width() - Client.main().fontMgr().comfortaa20.getStringWidth(String.valueOf(AltManager.slotList.size())) - 8), 85, RenderUtils.reAlpha(-1, opacity));

        byte MIN_HEIGHT = 75;
        int MAX_HEIGHT = sr.height() - 35;
        float percent = (sliderY - MIN_HEIGHT) / (MAX_HEIGHT - MIN_HEIGHT);
        float scrollAmount = (-Mouse.getDWheel()) * 0.07F;
        int all = 0;
        int altSlotY = -((int) (all * percent - (75 + y)));

        if (scrollAmount > 0) {
            if (sliderY + scrollAmount < MAX_HEIGHT) {
                sliderY += scrollAmount;
            } else {
                sliderY = MAX_HEIGHT;
            }
        } else if (scrollAmount < 0) {
            if (sliderY - scrollAmount > MIN_HEIGHT) {
                sliderY += scrollAmount;
            } else {
                sliderY = MIN_HEIGHT;
            }
        }

        for (Iterator slot = AltManager.slotList.iterator(); slot.hasNext(); all += 25) {
            AltSlot slotY = (AltSlot) slot.next();
        }

        for (Iterator var21 = AltManager.slotList.iterator(); var21.hasNext(); altSlotY += 25) {
            AltSlot altSlot = (AltSlot) var21.next();
            altSlot.y = altSlotY;
            altSlot.opacity = opacity;
            altSlot.WIDTH = sr.width() - 160;
            altSlot.MIN_HEIGHT = 50;
            altSlot.MAX_HEIGHT = sr.height() - 10;
            altSlot.drawScreen(posX, posY);
        }

        drawString(mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);
        Gui.drawRect(10, 50, sr.width() - 150, 75, RenderUtils.reAlpha(lightGray, opacity));
        Client.main().fontMgr().comfortaa20.drawString("EMAIL:PASS", width / 2 - Client.main().fontMgr().comfortaa20.getStringWidth("EMAIL:PASS") / 2, 59, RenderUtils.reAlpha(-1, opacity));
        drawString(mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);
        Gui.drawRect(10, sr.height() - 35, sr.width() - 150, sr.height() - 10, RenderUtils.reAlpha(lightGray, opacity));
        super.drawScreen(posX, posY, f);
        drawSlider(posX, posY);
    }
}