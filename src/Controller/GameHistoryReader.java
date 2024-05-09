package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryReader {
    private static List<String> plantPicturePaths;

    public static String[] readGameHistory() {
        List<String> plantDataList = new ArrayList<>();
        plantPicturePaths = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("gameHistory.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Remove "Plant picture" part and add the line to plantDataList
                line = line.replace("Plant picture;", "");
                plantDataList.add(line);

                String[] parts = line.split("\\|"); // Split the line by '|'
                for (String part : parts) {
                    if (part.trim().startsWith("Plant picture")) {
                        // Extract the filepath from the part
                        String[] filepathPart = part.split(";");
                        if (filepathPart.length > 1) {
                            String filepath = filepathPart[1].trim(); // Extract the filepath
                            plantPicturePaths.add(filepath);
                        }
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to an array
        return plantDataList.toArray(new String[0]);
    }

    public static List<String> getPlantPicturePaths() {
        return plantPicturePaths;
    }
}
