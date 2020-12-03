package de.crazymemecoke;

import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.utils.LoginUtil;
import de.crazymemecoke.utils.MathUtils;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;

public interface Wrapper {
    Minecraft mc = Client.main().getMc();
    Client cl = Client.getInstance();
    RenderUtils renderUtils = new RenderUtils();
    FontManager fontManager = new FontManager();
    LoginUtil loginUtil = new LoginUtil();
    NotifyUtil notify = new NotifyUtil();
    MathUtils mathUtils = new MathUtils();
    Methods methods = new Methods();
}
