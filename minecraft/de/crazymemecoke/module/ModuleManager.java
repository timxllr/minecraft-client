package de.crazymemecoke.module;

import de.crazymemecoke.Client;
import de.crazymemecoke.module.modules.combat.*;
import de.crazymemecoke.module.modules.exploits.Blink;
import de.crazymemecoke.module.modules.exploits.Paralyze;
import de.crazymemecoke.module.modules.exploits.Phase;
import de.crazymemecoke.module.modules.gui.ClickGUI;
import de.crazymemecoke.module.modules.gui.HUD;
import de.crazymemecoke.module.modules.gui.Invis;
import de.crazymemecoke.module.modules.movement.*;
import de.crazymemecoke.module.modules.player.*;
import de.crazymemecoke.module.modules.render.*;
import de.crazymemecoke.module.modules.world.Eagle;
import de.crazymemecoke.module.modules.world.Fucker;
import de.crazymemecoke.module.modules.world.Scaffold;
import de.crazymemecoke.module.modules.world.Tower;
import de.crazymemecoke.utils.FileUtils;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public ArrayList<Module> modules = new ArrayList<Module>();
    private File modulesFile;
    private File bindsFile;

    public ModuleManager() {

        try {
            modulesFile = new File(Client.getInstance().getClientDir() + "/modules.txt");
            if (modulesFile.createNewFile()) {
                System.out.println("File created: " + modulesFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            bindsFile = new File(Client.getInstance().getClientDir() + "/binds.txt");
            if (bindsFile.createNewFile()) {
                System.out.println("File created: " + bindsFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        modules.add(new InventoryMove());
        modules.add(new ChestStealer());
        modules.add(new ProphuntESP());
        modules.add(new Fullbright());
        modules.add(new OutlineESP());
        modules.add(new FastLadder());
        modules.add(new NoSlowDown());
        modules.add(new NoRotation());
        modules.add(new InvCleaner());
        modules.add(new FastPlace());
        modules.add(new AutoArmor());
        modules.add(new DeathDerp());
        modules.add(new Criticals());
        modules.add(new AutoClimb());
        modules.add(new FastFall());
        modules.add(new Velocity());
        modules.add(new IceSpeed());
        modules.add(new AutoSoup());
        modules.add(new Scaffold());
        modules.add(new SafeWalk());
        modules.add(new Paralyze());
        modules.add(new ClickGUI());
        modules.add(new FastUse());
        modules.add(new AntiWeb());
        modules.add(new Trigger());
        modules.add(new AirJump());
        modules.add(new FastBow());
        modules.add(new Parkour());
        modules.add(new Sprint());
        modules.add(new Aimbot());
        modules.add(new Strafe());
        modules.add(new NoFall());
        modules.add(new Fucker());
        modules.add(new Speed());
        modules.add(new Jesus());
        modules.add(new Tower());
        modules.add(new Blink());
        modules.add(new Glide());
        modules.add(new NoBob());
        modules.add(new Invis());
        modules.add(new Phase());
        modules.add(new Eagle());
        modules.add(new Step());
        modules.add(new Zoot());
        modules.add(new Aura());
        modules.add(new ESP());
        modules.add(new HUD());
        modules.add(new Fly());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModByName(String name) {
        for (Module mod : modules) {
            if ((mod.getName().trim().equalsIgnoreCase(name.trim()))
                    || (mod.toString().trim().equalsIgnoreCase(name.trim()))) {
                return mod;
            }
        }

        return null;
    }

    public Module getModule(Class<? extends Module> clazz) {
        for (Module mod : getModules()) {
            if (mod.getClass() == clazz) {
                return mod;
            }
        }
        return null;
    }

    public void saveModules() {
        List<String> formattedModules = new ArrayList<String>();
        modules.forEach(module -> {
            formattedModules.add(module.getName() + ":" + module.getState());
        });
        FileUtils.saveFile(modulesFile, formattedModules);
    }

    public void loadModules() {
        FileUtils.loadFile(modulesFile).forEach(line -> {
            final String[] args = line.split(":");
            if (args.length == 2) {
                Module module = getModByName(args[0]);
                boolean state = Boolean.valueOf(args[1]);

                if (state)
                    module.setState(true);
            }
        });
    }

    public void saveBinds() {
        List<String> formattedBinds = new ArrayList<String>();
        modules.forEach(module -> {
            formattedBinds.add(module.getName() + ":" + module.getBind());
        });
        FileUtils.saveFile(bindsFile, formattedBinds);
    }

    public void loadBinds() {
        FileUtils.loadFile(bindsFile).forEach(line -> {
            final String[] args = line.split(":");
            if (args.length == 2) {
                Module module = getModByName(args[0]);
                int bind = Integer.valueOf(args[1]);

                module.setBind(bind);
            }
        });
    }

    public int convertKeyCodeToInt(String keyCode) {
        if (keyCode.equalsIgnoreCase("a"))
            return Keyboard.KEY_A;
        if (keyCode.equalsIgnoreCase("b"))
            return Keyboard.KEY_B;
        if (keyCode.equalsIgnoreCase("c"))
            return Keyboard.KEY_C;
        if (keyCode.equalsIgnoreCase("d"))
            return Keyboard.KEY_D;
        if (keyCode.equalsIgnoreCase("e"))
            return Keyboard.KEY_E;
        if (keyCode.equalsIgnoreCase("f"))
            return Keyboard.KEY_F;
        if (keyCode.equalsIgnoreCase("g"))
            return Keyboard.KEY_G;
        if (keyCode.equalsIgnoreCase("h"))
            return Keyboard.KEY_H;
        if (keyCode.equalsIgnoreCase("i"))
            return Keyboard.KEY_I;
        if (keyCode.equalsIgnoreCase("j"))
            return Keyboard.KEY_J;
        if (keyCode.equalsIgnoreCase("k"))
            return Keyboard.KEY_K;
        if (keyCode.equalsIgnoreCase("l"))
            return Keyboard.KEY_L;
        if (keyCode.equalsIgnoreCase("m"))
            return Keyboard.KEY_M;
        if (keyCode.equalsIgnoreCase("n"))
            return Keyboard.KEY_N;
        if (keyCode.equalsIgnoreCase("o"))
            return Keyboard.KEY_O;
        if (keyCode.equalsIgnoreCase("p"))
            return Keyboard.KEY_P;
        if (keyCode.equalsIgnoreCase("q"))
            return Keyboard.KEY_Q;
        if (keyCode.equalsIgnoreCase("r"))
            return Keyboard.KEY_R;
        if (keyCode.equalsIgnoreCase("s"))
            return Keyboard.KEY_S;
        if (keyCode.equalsIgnoreCase("t"))
            return Keyboard.KEY_T;
        if (keyCode.equalsIgnoreCase("u"))
            return Keyboard.KEY_U;
        if (keyCode.equalsIgnoreCase("v"))
            return Keyboard.KEY_V;
        if (keyCode.equalsIgnoreCase("w"))
            return Keyboard.KEY_W;
        if (keyCode.equalsIgnoreCase("x"))
            return Keyboard.KEY_X;
        if (keyCode.equalsIgnoreCase("y"))
            return Keyboard.KEY_Y;
        if (keyCode.equalsIgnoreCase("z"))
            return Keyboard.KEY_Z;
        if (keyCode.equalsIgnoreCase("right"))
            return Keyboard.KEY_RIGHT;
        if (keyCode.equalsIgnoreCase("left"))
            return Keyboard.KEY_LEFT;
        if (keyCode.equalsIgnoreCase("up"))
            return Keyboard.KEY_UP;
        if (keyCode.equalsIgnoreCase("down"))
            return Keyboard.KEY_DOWN;
        if (keyCode.equalsIgnoreCase("rshift"))
            return Keyboard.KEY_RSHIFT;
        if (keyCode.equalsIgnoreCase("rcontrol"))
            return Keyboard.KEY_RCONTROL;
        if (keyCode.equalsIgnoreCase("none"))
            return Keyboard.KEY_NONE;
        return 0;
    }

    public File getModulesFile() {
        return modulesFile;
    }

    public File getBindsFile() {
        return bindsFile;
    }
}