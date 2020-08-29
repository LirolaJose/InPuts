import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<String, List<String>> phonesAndEmails = new HashMap<>(); // created Map with key and value types String

    public static void fetchChild(File dir) throws Exception { // method fetchChild with parameter File
        if (dir.isDirectory()) { // check if dir is directory
            for (File item : dir.listFiles()) { // do for each file in dir
                if (item.isDirectory()) { // check if item is directory
                    fetchChild(item); // call method fetchChild for item
                } else if (isArchive(item.getName())) { // call method isArchive with String parameter
                    String absolutePath = item.getAbsolutePath(); //
                    String destName;
                    if (absolutePath.endsWith(".zip")) {
                        destName = absolutePath.substring(0, absolutePath.lastIndexOf(".zip"));
                        Unpack.unpackZip(absolutePath, destName);
                        DeleteDir.deleteDirectory(item);
                        File newFolder = new File(destName);
                        fetchChild(newFolder);
                        String zipFile = destName + ".zip";
                        ZipPack.zip(destName, zipFile);
                        System.out.println(Main.getDateTime() + " " + newFolder.getPath() + " is archived");
                        DeleteDir.deleteDirectory(newFolder);
                    } else if (absolutePath.endsWith(".gz")) {
                        GzFile.unGZIP(absolutePath);
                        DeleteDir.deleteDirectory(item);
                        destName = GzFile.getDstFileName(absolutePath);
                        File textFile = new File(destName);
                        fetchChild(textFile);
                        String gzFile = destName + ".gz";
                        GzPack.compressGzipFile(destName, gzFile);
                        System.out.println(Main.getDateTime() + " " + textFile.getPath() + " is archived");
                        DeleteDir.deleteDirectory(textFile);
                    }
                } else {
                    System.out.println(Main.getDateTime() + " " + item.getPath() + " is being processed");
                    Print.getNumberFromFile(item, phonesAndEmails);
                }
            }
        } else {
            System.out.println(Main.getDateTime() + " " + dir.getPath() + " is being processed");
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
        Main.fetchChild(dir);// переменная example вызывает метод fetchChild
        phonesAndEmails.forEach((phone, email) -> System.out.println(phone + ":" + email.toString()));
        CreateTextFile.mkTxt(phonesAndEmails);
        ZipPack.zip(dir.getAbsolutePath(), "D://Programming//inputsV2.zip");
    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);

    }
}
