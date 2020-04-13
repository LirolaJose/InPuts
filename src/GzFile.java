import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class GzFile {
    public static void unGZIP(String filename) throws Exception {
        int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];

        try (GZIPInputStream gzipis = new GZIPInputStream(new FileInputStream(filename))) {
            try (FileOutputStream fos = new FileOutputStream(getDstFileName(filename))) {
                int length;
                while ((length = gzipis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GzFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   public static String getDstFileName(final String srcFileName) {
        return srcFileName.substring(0, srcFileName.lastIndexOf("."));
    }

}
