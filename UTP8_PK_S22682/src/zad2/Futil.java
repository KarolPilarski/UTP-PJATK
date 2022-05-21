package zad2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            File file = new File(resultFileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            Predicate<Path> isFile=Files::isRegularFile;
            Predicate<Path> isTxt=p->p.toString().endsWith(".txt");
            Files.walk(Paths.get(dirName)).
                    filter(isFile.and(isTxt)).
                    //map(Path::getFileName).
                    forEach((a)->{
                        try (BufferedReader br = new BufferedReader(new FileReader(a.toString()))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                writer.write(line);
                                writer.flush();
                            }
                            writer.write("\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
