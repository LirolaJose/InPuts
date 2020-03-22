import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GzToZip {
    public static void gzToZip(File item) throws Exception {
        Test1 test1 = new Test1();
        String pathName= item.getAbsolutePath();
        String nName = pathName.substring(0, pathName.indexOf(".gz"));
        item.renameTo(new File(nName + ".zip"));

        File zipFile = new File(nName + ".zip");
        File parFol = new File(zipFile.getParent());
        File part = new File(parFol.getParent());
        test1.fetchChild(part);
    }
}

