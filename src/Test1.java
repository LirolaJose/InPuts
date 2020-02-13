
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Test1 {
    Copy copy = new Copy(); // переменная copy типа Copy
    Zip z = new Zip();
    Print print = new Print(); // переменная print класса Print
    Map<String, List<String>> phonesAndEmails = new HashMap<>(); // создание map c ключём String и значением Список Strings, имя Map phoneAndEmails, тип map - HashMap


    public void fetchChild(File dir) throws Exception { // метод fetchChild с параметром File dir
        //File dir = new File(dirPath);
        if (dir.isDirectory()) { // условный оператор if с параметром если dir это Папка
            for (File item : dir.listFiles()) {
                if (isArchive(item.getName()) == true) {
                    z.unZip(item);
                } else if (item.isDirectory()) { // если объект - это папка вывводит на экран путь и надпись "папка"
                    //System.out.println(item.getAbsolutePath() + "  \t folder");
                    this.fetchChild(item);
                } else {// если объект файл - выводит на экран путь и надпись "файл"
                    String textFile = item.toString();
                    if (textFile.endsWith(".txt")){
                        print.getNumberFromFile(item, phonesAndEmails); // print вызывает метод getNumberFromFile из класса Print и указываем путь файла.
                        phonesAndEmails.forEach((phone, email) -> System.out.println(phone + ":" + email.toString())); // в Map для каждой пары ключ - значение выводим на экран: Ключ: Значение в строку
                        //System.out.println(item.getAbsolutePath() + "\t file");
                    }
                }


            }
        }
    }


    public boolean isArchive(String item) {
        if (item.endsWith(".zip")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception { // метод main
        Copy copy1 = new Copy();
        Test1 example = new Test1(); // переменная example класса Test1

        File dir = new File("D://Programming//inputs//"); // переменная dir класса File. в с кобках указан путь
        //String newAddress = copy1.copyFile(dir);
        example.fetchChild(dir); // переменная example вызывает метод fetchChild

    }

}
