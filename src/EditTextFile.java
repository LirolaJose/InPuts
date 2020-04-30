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
            if(line.equals("")){
                continue;
            }
            String cityCode = line.substring(line.indexOf("("), line.indexOf(")")+1);

            switch (cityCode) {
                case "(101)":
                    line = line.replace("(101)", "(401)");
                    lines.add(line);
                    break;
                case "(202)":
                    line = line.replace("(202)", "(802)");
                    lines.add(line);
                    break;
                case "(301)":
                    line = line.replace("(301)", "(321)");
                    lines.add(line);
                    break;
                default:
                    if (lines.contains(line)) {
                        continue;
                    }
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