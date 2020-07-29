package de.crazymemecoke.manager.altmanager.gui;

import java.io.IOException;

import de.crazymemecoke.features.ui.UIFlatButton;
import de.crazymemecoke.features.ui.UITextField;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.altmanager.GuiAltSlot;
import de.crazymemecoke.utils.Colors;
import de.crazymemecoke.utils.render.RenderUtils;
import de.crazymemecoke.utils.Wrapper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

public class GuiAddAlt extends GuiScreen {
    private GuiScreen parentScreen;
    private UITextField usernameField;
    private UITextField passwordField;
    private UITextField usernamePasswordField;

    public GuiAddAlt(GuiScreen p_i1033_1_) {
        this.parentScreen = p_i1033_1_;
    }

    public void updateScreen() {
        this.usernameField.updateCursorCounter();
        this.passwordField.updateCursorCounter();
        this.usernamePasswordField.updateCursorCounter();
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new UIFlatButton(0, this.width / 2 - 100, this.height / 4 + 96 + 18, 200, 15, "Add Alt", Colors.DARKBLUE.c));
        this.buttonList.add(new UIFlatButton(1, this.width / 2 - 100, this.height / 4 + 120 + 18, 200, 15, I18n.format("gui.cancel", new Object[0]), Colors.RED.c));
        this.usernameField = new UITextField(0, this.fontRendererObj, this.width / 2 - 100, 66, 200, 20);
        this.usernameField.setFocused(true);
        this.passwordField = new UITextField(1, this.fontRendererObj, this.width / 2 - 100, 106, 200, 20);
        this.passwordField.setMaxStringLength(128);
        this.usernamePasswordField = new UITextField(2, this.fontRendererObj, this.width / 2 - 100, 146, 200, 20);
        this.usernamePasswordField.setMaxStringLength(128);
        ((GuiButton)this.buttonList.get(0)).enabled = this.usernameField.getText().length() > 0 && this.passwordField.getText().length() > 0 || this.usernamePasswordField.getText().split(":").length >= 2;
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.enabled && button.id != 2) {
            if(button.id == 1) {
                this.mc.displayGuiScreen(this.parentScreen);
            } else if(button.id == 0) {
                if(!this.usernameField.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
                    AltManager.guiSlotList.add(new GuiAltSlot(this.usernameField.getText(), this.passwordField.getText()));
                } else if(!this.usernamePasswordField.getText().isEmpty()) {
                    String[] account = this.usernamePasswordField.getText().split(":");
                    AltManager.guiSlotList.add(new GuiAltSlot(account[0], account[1]));
                }

                AltManager.saveAlts();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }

    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.usernameField.textboxKeyTyped(typedChar, keyCode);
        this.passwordField.textboxKeyTyped(typedChar, keyCode);
        this.usernamePasswordField.textboxKeyTyped(typedChar, keyCode);
        if(keyCode == 15) {
            this.usernameField.setFocused(!this.usernameField.isFocused());
            this.passwordField.setFocused(!this.passwordField.isFocused());
            this.usernamePasswordField.setFocused(!this.passwordField.isFocused());
        }

        if(keyCode == 28 || keyCode == 156) {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }

        ((GuiButton)this.buttonList.get(0)).enabled = this.usernameField.getText().length() > 0 && this.passwordField.getText().length() > 0 || this.usernamePasswordField.getText().split(":").length >= 2;
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.passwordField.mouseClicked(mouseX, mouseY, mouseButton);
        this.usernameField.mouseClicked(mouseX, mouseY, mouseButton);
        this.usernamePasswordField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int x = this.width / 2 - 150;
        int darkGray = -15658735;
        int lightGray = -15066598;
        RenderUtils.drawBorderedRect(x, 23, x + 300, this.height / 4 + 170, 1, darkGray, lightGray);
        int var10002 = (this.width / 2);
        Wrapper.mc.fontRendererObj.drawString("Add Alt", var10002, 26, Colors.GREY.c);
        Wrapper.mc.fontRendererObj.drawString("Email", (this.width / 2 - 100), 53, -1);
        Wrapper.mc.fontRendererObj.drawString("Password", (this.width / 2 - 100), 94, -1);
        Wrapper.mc.fontRendererObj.drawString("Email:Password", (this.width / 2 - 100), 135, -1);
        this.usernameField.drawTextBox();
        this.passwordField.drawTextBox();
        this.usernamePasswordField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
