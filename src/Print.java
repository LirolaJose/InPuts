import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Print {
    public void getNumberFromFile(File fileName, Map<String, List<String>> phonesAndEmails) throws Exception { // метод getNumberFromFile c параметрами: указание файла и map
        FileReader fr = new FileReader(fileName); // чтение файла fileName
        Scanner scan = new Scanner(fr); // сканирование файла

        while (scan.hasNextLine()) { // пока scan имеет следующую строку будет выпоняться сканирование
            String line = scan.nextLine();
            Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line); // метод Matcher выделяет из файла почтовые адреса
            List<String> emails = new ArrayList<>(); // создание списка emails
            while (m.find()) { // пока Matcher будет находить адреса
                String email = m.group(); // они будут добавляться в переменную email которой присваивается группа всех найденных адресов
                emails.add(email); // в emails добавляе все email.

            }
            String firstMatch = emails.get(0); // создаем переменную и присваиваем значение взятое из emails ( первый эмейл)
            int indexOfFirstMatch = line.indexOf(firstMatch); // метод indexOf применяем к переменной line, с параметром firstMatch, то есть с первого эмейла присваиваем значение.
            String telNumberString = line.substring(0, indexOfFirstMatch); // присваиваем значение с начала line до indexOfFirstMatch (первого эмейла)
            //System.out.println(telNumberString);
            String extractedNumber = "+" + telNumberString.replaceAll("\\D+", ""); // приводим все номера к одному виду с помощью замены
            if(phonesAndEmails.containsKey(extractedNumber) && !phonesAndEmails.containsValue(phonesAndEmails.get(extractedNumber))){ // проверяем содержит ли map совпадение по ключу
                    List<String> listEmail = phonesAndEmails.get(extractedNumber); // получаем эмейлы по ключу и добавляем их в listEmail
                    listEmail.addAll(emails); // добавляем в listEmail все emails
                    phonesAndEmails.replace(extractedNumber, listEmail); // обновляем map phoneAndEmails
            } else {
                phonesAndEmails.put(extractedNumber, emails); // если совпадения нет, то кладём в map ключ и значение
            }


        }

        fr.close();
    }
}


