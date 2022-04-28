package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AuditingService {
    private static AuditingService instance = null;

    private AuditingService() {}

    public static AuditingService getInstance()
    {
        if (instance == null)
        {
            instance = new AuditingService();
        }
        return instance;
    }

    public void logCommand(String command) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/main/java/data/audit.csv",true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            assert writer != null;
            writer.write(command + "," + timestamp + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
