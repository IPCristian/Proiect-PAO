package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFileService {
    private static WriteFileService instance = null;

    private WriteFileService() {}

    public static WriteFileService getInstance()
    {
        if (instance == null)
        {
            instance = new WriteFileService();
        }
        return instance;
    }

    public void writeLines(String outputFile,List<String[]> outputData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        for (String[] output : outputData)
        {
            for (int i = 0; i < output.length-1; ++i)
            {
                writer.write(output[i] + ",");
            }
            writer.write(output[output.length-1]);
            writer.write("\n");
        }

        writer.close();
    }

}
