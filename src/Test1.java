
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    static Map<String, List<String>> phonesAndEmails = new HashMap<>(); // создание map c ключём String и значением Список Strings, имя Map phoneAndEmails, тип map - HashMap
    public static void fetchChild(File dir) throws Exception { // метод fetchChild с параметром File dir
        if (dir.isDirectory()) { // условный оператор if с параметром если dir это Папка
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) { // если объект - это папка вывводит на экран путь и надпись "папка"
                    fetchChild(item);
                } else if (isArchive(item.getName())) {
                    String absolutePath = item.getAbsolutePath();
                    String destName;
                    if (absolutePath.endsWith(".zip")) {
                        destName = absolutePath.substring(0, absolutePath.lastIndexOf(".zip"));
                        Unpack.unpackZip(absolutePath, destName);
                        DeleteDir.deleteDirectory(item);
                        File newFolder = new File(destName);
                        fetchChild(newFolder);
                        String zipFile = destName + ".zip";
                        ZipPack.zip(destName, zipFile);
                        System.out.println(newFolder.getPath()+" is archived");
                        DeleteDir.deleteDirectory(newFolder);
                    } else if (absolutePath.endsWith(".gz")) {
                        GzFile.unGZIP(absolutePath);
                        DeleteDir.deleteDirectory(item);
                        destName = GzFile.getDstFileName(absolutePath);
                        File textFile = new File(destName);
                        fetchChild(textFile);
                        String gzFile = destName + ".gz";
                        GzPack.compressGzipFile(destName, gzFile);
                        System.out.println(textFile.getPath()+" is archived");
                        DeleteDir.deleteDirectory(textFile);
                    }
                } else {
                    System.out.println(item.getPath()+" is being processed");
                    Print.getNumberFromFile(item, phonesAndEmails); // print вызывает метод getNumberFromFile из класса Print и указываем путь файла.
                }
            }
        } else {
            System.out.println(dir.getPath()+" is being processed");
            Print.getNumberFromFile(dir, phonesAndEmails);
        }
    }

    public static boolean isArchive(String item) {
        return item.endsWith(".zip") || item.endsWith(".gz");
    }

    public static void main(String[] args) throws Exception { // метод main
        File dir = new File("D://Programming//inputs//");
        if (dir.exists()) {
            DeleteDir.deleteDirectory(dir);
        }
        Unpack.unpackZip("D://Programming//inputs_v2.zip", "D://Programming//");
        //File dir = new File("D://Programming//ttt.txt");
        Test1.fetchChild(dir);// переменная example вызывает метод fetchChild
        phonesAndEmails.forEach((phone, email) -> System.out.println(phone + ":" + email.toString())); // в Map для каждой пары ключ - значение выводим на экран: Ключ: Значение в строку
        MkTextFile.mkTxt(phonesAndEmails);
        ZipPack.zip(dir.getAbsolutePath(), "D://Programming//inputsV2.zip");
    }

}
