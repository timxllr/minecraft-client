package de.crazymemecoke.ui.altmanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import de.crazymemecoke.Client;
import de.crazymemecoke.utils.FileUtils;

public class AltManager {
    public static ArrayList altList = new ArrayList();
    public static ArrayList guiSlotList = new ArrayList();
    public static File altFile;

    static {
        try {
            altFile = new File(Client.getInstance().getClientDir() + "/alts.txt");
            if (altFile.createNewFile()) {
                System.out.println("File created: " + altFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void saveAlts() {
        Iterator var2 = guiSlotList.iterator();

        while (var2.hasNext()) {
            GuiAltSlot slot = (GuiAltSlot) var2.next();
            List<String> formattedAlts = new ArrayList<String>();
            formattedAlts.add(slot.getUsername() + ":" + slot.getPassword());
            FileUtils.saveFile(altFile, formattedAlts);
        }
    }

    public static void loadAlts() {
        guiSlotList.clear();

        FileUtils.loadFile(altFile).forEach(line -> {
            final String[] args = line.split(":");
            if (args.length == 2) {
                String username = args[0];
                String password = args[1];
                guiSlotList.add(new GuiAltSlot(username, password));
            }
        });
    }

    public File getAltFile() {
        return altFile;
    }
}
