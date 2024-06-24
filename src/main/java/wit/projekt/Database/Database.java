package wit.projekt.Database;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Database {
    public static HashMap<String, List<String>> table;

    public Database() {
        HashMap<String, String> data = getRawData();

        table = new HashMap<>();
        for (String key : data.keySet()) {
            table.put(key, new ArrayList<>());
            for (String line : data.get(key).split("\n")) {
                table.get(key).add(line);
            }
        }
    }

    public static void save(String table, List<String> data) {
        for (String line : data) {
            System.out.println(line);
        }
    }

    public List<String> get(String table) {
        List<String> list = new ArrayList<>();

        if (this.table.containsKey(table)) {
            list = this.table.get(table);
        }

        return list;
    }


    private HashMap<String, String> getRawData() {
        HashMap<String, String> map = new HashMap<>();
        Path path = Paths.get("src/main/resources/database.txt");

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(path);
             Scanner scanner = new Scanner(reader)) {

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("=", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                } else {
                    System.out.println("ignoring line: " + parts[0]);
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return map;
    }

    private static void saveRawData(HashMap<String, String> map) {
        Path path = Paths.get("src/main/resources/database.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
