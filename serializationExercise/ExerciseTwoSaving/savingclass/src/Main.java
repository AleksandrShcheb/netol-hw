import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(100, 2, 80, 30.3);
        GameProgress progress2 = new GameProgress(50, 1, 45, 60.0);
        GameProgress progress3 = new GameProgress(100, 2, 79, 20.3);

        String saveDir = "C:\\Code\\Java_training\\netologyDZ\\serializationExercise\\Games\\savegames\\";

        String save1 = saveDir + "save1.dat";
        String save2 = saveDir + "save2.dat";
        String save3 = saveDir + "save3.dat";

        saveGame(save1, progress1);
        saveGame(save2, progress2);
        saveGame(save3, progress3);

        String zipFile = saveDir + "saves.zip";
        String[] filesToZip = {save1, save2, save3};
        zipFiles(zipFile, filesToZip);

    }

    private static void saveGame(String filePath, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void zipFiles(String zipPath, String[] files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file);
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[1024];

                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String file : files) {
            new File(file).delete();
        }
    }
}