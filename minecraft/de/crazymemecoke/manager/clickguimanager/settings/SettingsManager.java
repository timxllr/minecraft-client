package de.crazymemecoke.manager.clickguimanager.settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.FileUtils;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class SettingsManager {

    private ArrayList<Setting> settings;
    private File settingsFile;

    public SettingsManager() {
        this.settings = new ArrayList<>();

        try {
            settingsFile = new File(Client.getInstance().getClientDir() + "/config.txt");
            if (settingsFile.createNewFile()) {
                System.out.println("File created: " + settingsFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void rSetting(Setting in) {
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<>();
        for (Setting s : getSettings()) {
            if (s.getParentMod() != null) {
                if (s.getParentMod().equals(mod)) {
                    out.add(s);
                }
            } else
                return null;
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    public Setting getSettingByName(String name, Module module) {
        for (Setting set : getSettings()) {
            if (set.getFullName().equalsIgnoreCase((module == null ? "global" : module.getName()) + "_" + name)) {
                return set;
            }
        }
        System.err.println("[" + Client.getInstance().getClientName() + "] Error Setting NOT found: '" + name + "'!");
        return null;
    }


    public void saveSettings() {
        List<String> formattedSettings = new ArrayList<String>();
        for (final Setting set : Client.getInstance().getSetmgr().getSettings()) {
            String yeet = set.getParentMod() != null ? set.getParentMod().getName() : "global";
            if (set.isSlider()) {
                formattedSettings.add(yeet + ":" + set.getName() + ":" + set.getValDouble());
            }
            if (set.isCheck()) {
                formattedSettings.add(yeet + ":" + set.getName() + ":" + set.getValBoolean());
            }
            if (set.isCombo()) {
                formattedSettings.add(yeet + ":" + set.getName() + ":" + set.getValString());
            }
        }
        FileUtils.saveFile(settingsFile, formattedSettings);
    }

    public void loadSettings() {
        FileUtils.loadFile(settingsFile).forEach(line -> {
            final String[] arguments = line.split(":");
            if (arguments.length == 3) {
                Module parent = arguments[0].equalsIgnoreCase("global") ? null : Client.getInstance().getModuleManager().getModByName(arguments[0]);
                Setting set = getSettingByName(arguments[1], parent);

                if (set != null) {
                    if (set.isSlider())
                        set.setValDouble(Double.parseDouble(arguments[2]));
                    if (set.isCheck())
                        set.setValBoolean(Boolean.parseBoolean(arguments[2]));
                    if (set.isCombo())
                        set.setValString(arguments[2]);
                }
            }
        });
    }

    public File getSettingsFile() {
        return settingsFile;
    }

}