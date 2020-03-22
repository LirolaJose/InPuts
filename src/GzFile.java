import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class GzFile {

    public static void unGZIP(final String filename) {
        int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        try (GZIPInputStream gzipis = new GZIPInputStream(new FileInputStream(filename));
             FileOutputStream fos = new FileOutputStream(getDstFileName(filename))) {
            int length = 0;
            while ((length = gzipis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            Logger.getLogger(GzFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String getDstFileName(final String srcFileName) {
        return srcFileName.substring(0, srcFileName.lastIndexOf("."));
    }
}
