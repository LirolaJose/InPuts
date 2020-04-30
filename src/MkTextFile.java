import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MkTextFile {
    public static void mkTxt(Map<String, List<String>> phonesAndEmails) throws IOException {
        File phonesFile = new File("D://Programming//inputs//phones.txt");
        if (!phonesFile.exists()) {
            phonesFile.createNewFile();
        }
        File emailsFile = new File("D://Programming//inputs//emails.txt");
        if (!emailsFile.exists()) {
            emailsFile.createNewFile();
        }
        List<String> phoneList = new ArrayList<>(phonesAndEmails.keySet());
        Collections.sort(phoneList);

        List<List<String>> valuesList = new ArrayList<>(phonesAndEmails.values());
        List<String> emailsList = valuesList.stream()
                .flatMap(Collection::stream).sorted().collect(Collectors.toList());

        FileWriter phone = new FileWriter(phonesFile);
        for (int i = 0; i < phoneList.size(); i++) {
            if(phoneList.get(i).equals("Invalid number")){
                continue;
            }
            phone.write(phoneList.get(i) + "\n");
        }
        phone.close();

        FileWriter emails = new FileWriter(emailsFile);
        for (int i = 0; i<emailsList.size(); i++) {
            if(emailsList.get(i).endsWith(".org")) {
                emails.write(emailsList.get(i) + "\n");
            }
        }
        emails.close();
    }
}
