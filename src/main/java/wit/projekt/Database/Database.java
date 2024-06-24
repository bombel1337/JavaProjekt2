package wit.projekt.Database;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Database {

    public List<String> get(String tableName) {
        List<String> data = new ArrayList<>();

        if (!Files.exists(Paths.get("src/main/resources" + tableName + ".txt"))) {
            return data;
        }

        try {
            data = Files.readAllLines(Paths.get("src/main/resources" + tableName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void save(String tableName, List<String> data) {

        if (!Files.exists(Paths.get("src/main/resources" + tableName + ".txt"))) {
            try {
                Files.createFile(Paths.get("src/main/resources" + tableName + ".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get("src/main/resources" + tableName + ".txt"), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
