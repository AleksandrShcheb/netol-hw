import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String way = "C:\\Code\\Java_training\\netologyDZ\\serializationExercise\\Games";
        StringBuilder log = new StringBuilder();

        File src = new File(way, "src");
        File res = new File(way, "res");
        File savegames = new File(way, "savegames");
        File temp = new File(way, "temp");

        createDir(src,log);
        createDir(res,log);
        createDir(savegames,log);
        createDir(temp,log);

        File main = new File(src, "main");
        File test = new File(src, "test");

        createDir(main,log);
        createDir(test,log);

        File mainJava = new File(main, "Main.java");
        File utilsJava = new File(main, "Utils.java");

        createFile(mainJava,log);
        createFile(utilsJava,log);

        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        createDir(drawables,log);
        createDir(vectors,log);
        createDir(icons,log);

        File tempTxt = new File(temp, "temp.txt");
        createFile(tempTxt,log);

        FileWriter writer = new FileWriter(tempTxt);
        writer.write(log.toString());
        writer.close();

    }

    private static void createDir(File dir, StringBuilder log) {
        if (!dir.exists()) {
            dir.mkdir();
            log.append("Каталог создан: " + dir.getAbsolutePath() + "\n");
        } else {
            log.append("ОШИБКА создания каталога:");
        }
    }
    private static void createFile(File file, StringBuilder log) {
        try {
            if (file.createNewFile()) {
                log.append("Файл создан: " + file.getAbsolutePath() + "\n");
            } else {
                log.append("Файл уже существует");
            }
        } catch (IOException e) {
            log.append("Ошибка создания");
        }
    }
}