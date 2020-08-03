package de.crazymemecoke.manager.modulemanager;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.combat.*;
import de.crazymemecoke.features.modules.exploits.Blink;
import de.crazymemecoke.features.modules.exploits.Paralyze;
import de.crazymemecoke.features.modules.exploits.Phase;
import de.crazymemecoke.features.modules.gui.ClickGUI;
import de.crazymemecoke.features.modules.gui.HUD;
import de.crazymemecoke.features.modules.gui.Invis;
import de.crazymemecoke.features.modules.movement.*;
import de.crazymemecoke.features.modules.player.*;
import de.crazymemecoke.features.modules.render.*;
import de.crazymemecoke.features.modules.world.Eagle;
import de.crazymemecoke.features.modules.world.Fucker;
import de.crazymemecoke.features.modules.world.Scaffold;
import de.crazymemecoke.features.modules.world.Tower;
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
        //modules.add(new KillAura());
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

    public File getModulesFile() {
        return modulesFile;
    }

    public File getBindsFile() {
        return bindsFile;
    }
}