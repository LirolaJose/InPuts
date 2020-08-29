import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Print {

    public static void getNumberFromFile(File fileName, Map<String, List<String>> phonesAndEmails) throws Exception {
        EditTextFile.editFile(fileName);
        FileReader fr = new FileReader(fileName);
        Scanner scan = new Scanner(fr);


        while (scan.hasNextLine()) { // пока scan имеет следующую строку будет выпоняться сканирование
            String line = scan.nextLine();
            Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line);
            List<String> emails = new ArrayList<>();// создание списка emails
            Matcher match = Pattern.compile("(\\+*)(.+)(\\(\\d+\\))(.+)").matcher(line);
            if (line.equals("")) {
                continue;
            }
            if (match.find()) {
                while (m.find()) {
                    String email = m.group();
                    if (!emails.contains(email)) {
                        emails.add(email);
                    }
                }
            } else {
                continue;
            }
            String firstMatch = emails.get(0);
            int indexOfFirstMatch = line.indexOf(firstMatch);
            String telNumberString = line.substring(0, indexOfFirstMatch);

            TelNumber phone = new TelNumber();
            phone.setFirstNumber(telNumberString.substring(0, telNumberString.indexOf("(")));
            phone.setCityCode(telNumberString.substring(telNumberString.indexOf("("), telNumberString.indexOf(")") + 1));
            int charsCityCode = phone.getCityCode().length();
            phone.setNumber(telNumberString.substring(telNumberString.indexOf(")") + 1));
            String modNumber = phone.getNumber().replaceAll("\\D+", "");
            String changedPhone = phone.getFirstNumber() + phone.getCityCode() + modNumber;
            if (charsCityCode > 5) {
                changedPhone = "Invalid number";
            }
            if (phonesAndEmails.containsKey(changedPhone)) {
                if (!phonesAndEmails.get(changedPhone).containsAll(emails)) {
                    List<String> listEmail = phonesAndEmails.get(changedPhone);
                    listEmail.addAll(emails);
                    phonesAndEmails.replace(changedPhone, listEmail);
                }
            } else {
                phonesAndEmails.put(changedPhone, emails);
            }

        }


        fr.close();
    }
}


