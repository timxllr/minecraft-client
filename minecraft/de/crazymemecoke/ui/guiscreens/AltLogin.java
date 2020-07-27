package de.crazymemecoke.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.ui.GuiPasswordField;
import de.crazymemecoke.utils.Util;
import de.crazymemecoke.utils.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
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
        this.parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4, "Einloggen"));
        this.buttonList.add(new GuiButton(3, width / 2 - 100, height / 4 + 22, "Neu einloggen"));
        this.buttonList.add(new GuiButton(4, width / 2 - 100, height / 4 + 44, "Aus Zwischenablage einloggen"));
        this.buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 96 + 200, "Zurück"));
        this.usernameBox = new GuiTextField(3, this.mc.fontRendererObj, width / 2 - 100, 50, 200, 20);
        this.passwordBox = new GuiPasswordField(this.mc.fontRendererObj, width / 2 - 100, 95, 200, 20);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void updateScreen() {
        this.usernameBox.updateCursorCounter();
        this.passwordBox.updateCursorCounter();
    }

    public void mouseClicked(int x, int y, int b) {
        this.usernameBox.mouseClicked(x, y, b);
        this.passwordBox.mouseClicked(x, y, b);
        try {
            super.mouseClicked(x, y, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            if (this.usernameBox.getText().length() > 0) {
                new Thread() {
                    public void run() {
                        AltLogin.lastUsername = AltLogin.this.usernameBox.getText();
                        AltLogin.lastPassword = AltLogin.this.passwordBox.getText();
                        Values.getValues().premium = Util.login(AltLogin.this.usernameBox.getText(),
                                AltLogin.this.passwordBox.getText());
                    }
                }.start();
            }
        } else if (button.id == 2) {
            Minecraft.getMinecraft().displayGuiScreen(new ClientMenu());
        } else if (button.id == 3) {
            new Thread() {
                public void run() {
                    Values.getValues().premium = Util.login(AltLogin.lastUsername, AltLogin.lastPassword);
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
                    AltLogin.lastUsername = args[0];
                    AltLogin.lastPassword = args[1];
                    Values.getValues().premium = Util.login(args[0], args[1]);
                }
            }.start();
        }
    }

    public void keyTyped(char ch, int key) {
        if (key == 1) {
            Minecraft.getMinecraft().displayGuiScreen(this.parent);
        }
        this.usernameBox.textboxKeyTyped(ch, key);
        this.passwordBox.textboxKeyTyped(ch, key);
        if (key == 15) {
            if (this.usernameBox.isFocused()) {
                this.usernameBox.setFocused(false);
                this.passwordBox.setFocused(true);
            } else {
                this.usernameBox.setFocused(true);
                this.passwordBox.setFocused(false);
            }
        }
        if (key == 28) {
            try {
                actionPerformed((GuiButton) this.buttonList.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (key == 13) {
            try {
                actionPerformed((GuiButton) this.buttonList.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((GuiButton) this.buttonList.get(0)).enabled = (this.usernameBox.getText().length() > 3);
    }

    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        drawString(this.mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution s = new ScaledResolution(this.mc);
        Gui.drawRect(0, 0, s.getScaledWidth(), s.getScaledHeight(), new Color(28, 26, 28).getRGB());

        Client.getInstance().getFontManager().comfortaa20.drawString("E-Mail / Username:", s.getScaledWidth() / 2 + 35, s.getScaledHeight() / 4 - 62, 0xFFFFFF);
        Client.getInstance().getFontManager().comfortaa20.drawString("Passwort:", s.getScaledWidth() / 2 + 35, s.getScaledHeight() / 4 - 18, 0xFFFFFF);

        if (Values.getValues().premium)
            Client.getInstance().getFontManager().comfortaa20.drawString("Nutzername: " + this.mc.session.getUsername(), 3, 3, 16777215);
        else
            Client.getInstance().getFontManager().comfortaa20.drawString("Nutzername (Cracked): " + this.mc.session.getUsername(), 3, 3, 16777215);

        try {
            this.usernameBox.drawTextBox();
            this.passwordBox.drawTextBox();
        } catch (Exception err) {
            err.printStackTrace();
        }

        super.drawScreen(x, y, f);
    }
}