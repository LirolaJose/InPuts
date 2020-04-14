import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MkTextFile {
    public static void mkTxt(Map<String, List<String>> phonesAndEmails) throws IOException {
        File phonesFile = new File("D://Programming//inputs//phones.txt");
        File emailsFile = new File("D://Programming//inputs//emails.txt");
        phonesFile.createNewFile();
        emailsFile.createNewFile();
        FileWriter phone = new FileWriter("D://Programming//inputs//phones.txt");
        for (Map.Entry<String, List<String>> entry : phonesAndEmails.entrySet()) {
            phone.write(entry.getKey() + "\n");
        }
        phone.close();
        FileWriter emails = new FileWriter("D://Programming//inputs//emails.txt");
        for (Map.Entry<String, List<String>> entry : phonesAndEmails.entrySet()) {
            emails.write(entry.getValue() + "\n");
        }
        emails.close();
    }
}
