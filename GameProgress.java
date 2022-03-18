package Files.Saving;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public void saveGame(String path, GameProgress object) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void zipFiles(String path, File[] files) {
        File f = new File(path);
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(f))) {
            for (int i = 0; i < files.length; i++) {
                FileInputStream fis = new FileInputStream(files[i]);
                ZipEntry entry = new ZipEntry((files[i]).getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openZip (String pathZip, String pathUnZip) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathUnZip + name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fout.write(c);
                }
                fout.flush();
                zis.closeEntry();
                fout.close();
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgress = (GameProgress) ois.readObject();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

}