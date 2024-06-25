package wit.projekt.Database;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Database {
    private static HashMap<String, List<String>> tables = new HashMap<>();

    public Database() {
        tables.put("students", new ArrayList<>());
        tables.put("subjects", new ArrayList<>());
        tables.put("groups", new ArrayList<>());

        loadFile();
    }

    public List<String> get(String tableName) {
        return tables.getOrDefault(tableName, new ArrayList<>());
    }

    public static void save(String tableName, List<String> data) {
        tables.put(tableName, data);
    }

    private void loadFile() {
        for (String tableName : tables.keySet()) {
            String path = "src/main/java/resources/" + tableName + ".txt";

            if (Files.notExists(Paths.get(path))) {
                System.out.println("File not found: " + path);
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println("Reading from file: " + path);
                        tables.get(tableName).add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveFile() {
        for (String tableName : tables.keySet()) {
            String path = "src/main/java/resources/" + tableName + ".txt";

            if (Files.notExists(Paths.get(path))) {
                try {
                    System.out.println("Creating file: " + path);
                    Files.createFile(Paths.get(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                for (String line : tables.get(tableName)) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
