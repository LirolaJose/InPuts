import java.io.File;

public class DeleteDir {
    public static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children==null){
                return;
            }
            for (String child : children) {
                File f = new File(dir, child);
                deleteDirectory(f);
            }
        }
        dir.delete();
    }
}
