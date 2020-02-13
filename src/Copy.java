import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;

public class Copy {

    public String copyFile(File item) throws IOException  {
        File source = new File(item.getAbsolutePath());
        File dest = new File("D://Downloads//New//"+source.getName());

        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel destChannel = new FileOutputStream(dest).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getAbsolutePath();

    }
}
