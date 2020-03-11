import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unpack {
    public static void unpackZip(String path, String dir_to) throws IOException {
        File dir = new File(dir_to);
        if(!dir.exists()){
            dir.mkdir();
        }
        ZipFile zip = new ZipFile(path);// выдает ошибку
        Enumeration entries = zip.entries();
        LinkedList<ZipEntry> zfiles = new LinkedList<>();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                new File(dir_to+"/"+entry.getName()).mkdir();
            } else {
                zfiles.add(entry);
            }
        }
        for (ZipEntry entry : zfiles) {
            InputStream in = zip.getInputStream(entry);
            OutputStream out = new FileOutputStream(dir_to+"/"+entry.getName());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) >= 0)
                out.write(buffer, 0, len);
            in.close();
            out.close();
        }
        zip.close();
    }
}

