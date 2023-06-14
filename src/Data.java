import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Data {
    private TreeMap<String, List<String>> listData = new TreeMap<>();
    private String fileInput = "slang.txt";
    private String fileHistory = "history.txt";
    private int sizeData;

    private Data () {
        try {
            String f = new File(".").getCanonicalPath();
            System.out.println("src:" + f);
            fileInput = f + "\\" + fileInput;
            fileHistory = f + "\\" + fileHistory;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readFile(String fileName) throws FileNotFoundException {
        listData.clear();
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter("`");
        scanner.next();
        String temp = scanner.next();
        String[] part = temp.split("\n");
        int i = 0;
        int flag = 0;
        sizeData = 0;
        String slag = null;
        while(scanner.hasNext()){
            List<String> meaning = new ArrayList<String>();
            slag = part[1].trim();
            temp = scanner.next();
            part = temp.split("\n");
            if (listData.containsKey(slag)) {
                meaning = listData.get(slag);
            }
            if (part[0].contains("|")) {
                System.out.println(part[0]);
                String[] d = (part[0]).split("\\|");
                for (int ii = 0; ii < d.length; ii++)
                    System.out.println(d[ii]);
                Collections.addAll(meaning, d);
                sizeData += d.length - 1;
            } else {
                meaning.add(part[0]);
            }
            // map.put(slag.trim(), meaning);
            listData.put(slag, meaning);
            i++;
            sizeData++;
        }
        scanner.close();
    }
}
