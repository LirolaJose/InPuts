import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzPack {
    public static void compressGzipFile(String file, String gzipFile) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(gzipFile);
        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;
        while((len=fis.read(buffer)) != -1){
            gzipOS.write(buffer, 0, len);
        }
        gzipOS.close();
        fos.close();
        fis.close();


    }
}
