import java.io.*;
import java.util.Scanner;

public class ChangeFile {
    public static File editText(File file) throws IOException {
        File fileWr = new File(file.getParent()+"\\copy"+file.getName());
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner scan = new Scanner(br);
        FileWriter fw = new FileWriter(fileWr, true);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.equals("")) {
                continue;
            }
            String cityCode = line.substring(line.indexOf("("), line.indexOf(")") + 1);
            switch (cityCode) {
                case "(101)":
                    line = line.replace("(101)", "(401)");
                    fw.write(line + "\n");
                    break;
                case "(202)":
                    line = line.replace("(202)", "(802)");
                    fw.write(line + "\n");
                    break;
                case "(301)":
                    line = line.replace("(301)", "(321)");
                    fw.write(line + "\n");
                    break;
                default:
                    fw.write(line + "\n");
            }
        }
        br.close();
        fw.close();
        file.delete();
        fileWr.renameTo(file.getAbsoluteFile());
        return fileWr;
    }
}
