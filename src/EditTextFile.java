import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditTextFile {
    public static void editFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(fr);
        List<String> lines = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.contains("(101)")) {
                line = line.replace("(101)", "(401)");
                lines.add(line);
            } else if (line.contains("(202")) {
                line = line.replace("(202)", "(802)");
                lines.add(line);
            } else if (line.contains("(301")) {
                line = line.replace("(301)", "(321)");
                lines.add(line);
            }else{
                lines.add(line);
            }
        }
        fr.close();
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < lines.size(); i++) {
            fw.write(lines.get(i) + "\n");
        }
        fw.close();
    }
}