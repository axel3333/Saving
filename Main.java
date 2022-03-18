package Files.Saving;

import java.io.File;

import static Files.Saving.GameProgress.*;

public class Main {
    public static void main(String[] args) {

        GameProgress gp1 = new GameProgress(100, 3, 2, 140.5);
        GameProgress gp2 = new GameProgress(75, 4, 6, 428.68);
        GameProgress gp3 = new GameProgress(89, 8, 32, 2865.91);
        File save1 = new File("C:/Java/Games/savegames/save1.dat");
        File save2 = new File("C:/Java/Games/savegames/save2.dat");
        File save3 = new File("C:/Java/Games/savegames/save3.dat");

        gp1.saveGame("C:/Java/Games/savegames/save1.dat", gp1);
        gp2.saveGame("C:/Java/Games/savegames/save2.dat", gp2);
        gp3.saveGame("C:/Java/Games/savegames/save3.dat", gp3);

        String path = "C:/Java/Games/savegames/zip.zip";

        File folder = new File("C:/Java/Games/savegames/");
        File[] files = folder.listFiles();
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
            System.out.println(names[i]);
        }

        zipFiles(path, files);
        save1.delete();
        save2.delete();
        save3.delete();

        openZip(path, "C:/Java/Games/temp/");
        openProgress("C:/Java/Games/temp/save1.dat");
        openProgress("C:/Java/Games/temp/save2.dat");
        openProgress("C:/Java/Games/temp/save3.dat");
    }
}