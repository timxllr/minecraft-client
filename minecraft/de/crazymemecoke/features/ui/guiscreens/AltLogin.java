package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.ui.GuiPasswordField;
import de.crazymemecoke.utils.Util;
import de.crazymemecoke.utils.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class AltLogin extends GuiScreen {
    public GuiScreen parent;
    public GuiTextField usernameBox;
    public GuiPasswordField passwordBox;
    public GuiTextField sessionBox;
    public static String lastUsername = "";
    public static String lastPassword = "";

    public AltLogin(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 4, "Einloggen"));
        buttonList.add(new GuiButton(3, width / 2 - 100, height / 4 + 22, "Neu einloggen"));
        buttonList.add(new GuiButton(4, width / 2 - 100, height / 4 + 44, "Aus Zwischenablage einloggen"));
        buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 96 + 200, "Zurück"));
        usernameBox = new GuiTextField(3, mc.fontRendererObj, width / 2 - 100, 50, 200, 20);
        passwordBox = new GuiPasswordField(mc.fontRendererObj, width / 2 - 100, 95, 200, 20);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void updateScreen() {
        usernameBox.updateCursorCounter();
        passwordBox.updateCursorCounter();
    }

    public void mouseClicked(int x, int y, int b) {
        usernameBox.mouseClicked(x, y, b);
        passwordBox.mouseClicked(x, y, b);
        try {
            super.mouseClicked(x, y, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            if (usernameBox.getText().length() > 0) {
                new Thread() {
                    public void run() {
                        lastUsername = usernameBox.getText();
                        lastPassword = passwordBox.getText();
                        Values.getValues().premium = Util.login(usernameBox.getText(),
                                passwordBox.getText());
                    }
                }.start();
            }
        } else if (button.id == 2) {
            Minecraft.getMinecraft().displayGuiScreen(new ClientMenu());
        } else if (button.id == 3) {
            new Thread() {
                public void run() {
                    Values.getValues().premium = Util.login(lastUsername, lastPassword);
                }
            }.start();
        } else if (button.id == 4) {
            String clipboard = null;
            try {
                clipboard = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            }

            String[] args = clipboard.split(":");

            new Thread() {
                public void run() {
                    lastUsername = args[0];
                    lastPassword = args[1];
                    Values.getValues().premium = Util.login(args[0], args[1]);
                }
            }.start();
        }
    }

    public void keyTyped(char ch, int key) {
        if (key == 1) {
            Minecraft.getMinecraft().displayGuiScreen(parent);
        }
        usernameBox.textboxKeyTyped(ch, key);
        passwordBox.textboxKeyTyped(ch, key);
        if (key == 15) {
            if (usernameBox.isFocused()) {
                usernameBox.setFocused(false);
                passwordBox.setFocused(true);
            } else {
                usernameBox.setFocused(true);
                passwordBox.setFocused(false);
            }
        }
        if (key == 28) {
            try {
                actionPerformed((GuiButton) buttonList.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (key == 13) {
            try {
                actionPerformed((GuiButton) buttonList.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((GuiButton) buttonList.get(0)).enabled = (usernameBox.getText().length() > 3);
    }

    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        drawString(mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/client/background.jpg"));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
                width, height, sr.getScaledWidth(), sr.getScaledHeight());

        Client.getInstance().getFontManager().comfortaa20.drawString("E-Mail / Username:", sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 4 - 90, 0xFFFFFF);
        Client.getInstance().getFontManager().comfortaa20.drawString("Passwort:", sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 4 - 45, 0xFFFFFF);

        if (Values.getValues().premium)
            Client.getInstance().getFontManager().comfortaa20.drawString("Nutzername: " + mc.session.getUsername(), 3, 3, 16777215);
        else
            Client.getInstance().getFontManager().comfortaa20.drawString("Nutzername (Cracked): " + mc.session.getUsername(), 3, 3, 16777215);

        try {
            usernameBox.drawTextBox();
            passwordBox.drawTextBox();
        } catch (Exception err) {
            err.printStackTrace();
        }

        super.drawScreen(x, y, f);
    }
}