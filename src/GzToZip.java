import java.io.File;

public class GzToZip {
    public static void gzToZip(File item) throws Exception {
        Main test1 = new Main();
        String pathName = item.getAbsolutePath();
        String nName = pathName.substring(0, pathName.indexOf(".gz"));
        item.renameTo(new File(nName + ".zip"));

        File zipFile = new File(nName + ".zip");
        File parFol = new File(zipFile.getParent());
        File part = new File(parFol.getParent());
        test1.fetchChild(part);
    }
}

