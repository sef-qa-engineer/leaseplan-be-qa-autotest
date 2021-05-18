package leaseplan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CurrencyUtility extends BaseTest
{

    public ArrayList<String> itemizeCurrency(String currency)
    {
        ArrayList<String> currencies = new ArrayList<>(Arrays.asList(currency.split(",")));
        return currencies;
    }

    public static void printToTextFile(String stepDefName, String textToWrite)
    {
        String executionDate = new SimpleDateFormat("dd.MM.yyyy_HH-mm-ss-SSS").format(new Date());
        String fileName = stepDefName + "_" + executionDate + ".txt";

        File file = new File(System.getProperty("user.dir") + testlogs_dir + "/" + fileName);

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(textToWrite);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
