import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<String> emailsList = new ArrayList<>();
        for(int i = 0; i<valuesList.size(); i++) {
            String[] strings = new String[valuesList.size()];
            String str = valuesList.get(i).toString();
            Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(str);
            while (m.find()) {
                String email = m.group();
                if (email.endsWith(".org")) {
                    strings[i] = email;
                    emailsList.add(strings[i]);
                }
            }
        }
        Collections.sort(emailsList);

        FileWriter phone = new FileWriter(phonesFile);
        for (int i = 0; i < phoneList.size(); i++) {
            phone.write(phoneList.get(i) + "\n");
        }
        phone.close();

        FileWriter emails = new FileWriter(emailsFile);
        for (int i = 0; i<emailsList.size(); i++) {
            emails.write(emailsList.get(i) + "\n");
        }
        emails.close();
    }
}
