package SelverTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MapToFileExample {

    public static void writeMapToFile(Map<String, Double> map, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Map has been written to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}