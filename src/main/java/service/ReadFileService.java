package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileService {
    private static ReadFileService instance = null;

    private ReadFileService() {}

    public static ReadFileService getInstance()
    {
        if (instance == null)
        {
            instance = new ReadFileService();
        }
        return instance;
    }

    public List<String[]> readLines(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<String[]> inputData = new ArrayList<>();

        String dataLine = reader.readLine();
        while (dataLine != null)
        {
            String[] listData = dataLine.split(",");
            inputData.add(listData);
            // System.out.println(Arrays.toString(listData));
            dataLine = reader.readLine();
        }

        reader.close();
        return inputData;
    }
}
