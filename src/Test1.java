
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    Print print = new Print(); // переменная print класса Print
    static Map<String, List<String>> phonesAndEmails = new HashMap<>(); // создание map c ключём String и значением Список Strings, имя Map phoneAndEmails, тип map - HashMap


    public void fetchChild(File dir) throws Exception { // метод fetchChild с параметром File dir
        if (dir.isDirectory()) { // условный оператор if с параметром если dir это Папка
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) { // если объект - это папка вывводит на экран путь и надпись "папка"
                    this.fetchChild(item);
                } else if (isArchive(item.getName())) {
                    String absolutePath = item.getAbsolutePath();
                    String destName = null;
                    if (absolutePath.endsWith(".zip")) {
                        destName = absolutePath.substring(0, absolutePath.lastIndexOf(".zip"));
                        Unpack.unpackZip(absolutePath, destName);
                        assert destName != null;
                        File newFolder = new File(destName);
                        this.fetchChild(newFolder);
                    } else if (absolutePath.endsWith(".gz")) {
                        GzFile.unGZIP(absolutePath);
                    }
                } else {
                    String textFile = item.toString();
                    //if (textFile.endsWith(".txt")) {
                    print.getNumberFromFile(item, phonesAndEmails); // print вызывает метод getNumberFromFile из класса Print и указываем путь файла.
                    //}
                }
            }
        }
    }

    public boolean isArchive(String item) {
        return item.endsWith(".zip") || item.endsWith(".gz");
    }

    public static void main(String[] args) throws Exception { // метод main
        File delDir = new File("D://Programming//inputs//");
        DeleteDir.deleteDirectory(delDir);
        Unpack.unpackZip("D://Programming//inputs_v2.zip", "D://Programming//");
        Test1 example = new Test1(); // переменная example класса Test1
        File dir = new File("D://Programming//inputs//"); // переменная dir класса File. в с кобках указан путь
        example.fetchChild(dir);// переменная example вызывает метод fetchChild
        phonesAndEmails.forEach((phone, email) -> System.out.println(phone + ":" + email.toString())); // в Map для каждой пары ключ - значение выводим на экран: Ключ: Значение в строку


    }

}
