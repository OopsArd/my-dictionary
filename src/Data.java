import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Data {
    private TreeMap<String, List<String>> listData = new TreeMap<>();

    private static Data init = new Data();
    private String fileInput = "slang.txt";
    private String fileHistory = "history.txt";
    private int sizeData;

    private Data () {
        try {
            String f = new File(".").getCanonicalPath();
            fileInput = f + "\\" + fileInput;
            fileHistory = f + "\\" + fileHistory;
            System.out.println("src:" + fileInput);
            readFile(fileInput);

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
        String key = null;
        while(scanner.hasNext()){
            List<String> values = new ArrayList<String>();
            key = part[1].trim();
            temp = scanner.next();
            part = temp.split("\n");
            if (listData.containsKey(key)) {
                values = listData.get(key);
            }
            if (part[0].contains("|")) {
                String[] d = (part[0]).split("\\|");
                Collections.addAll(values, d);
                sizeData += d.length - 1;
            } else {
                values.add(part[0]);
            }
            // map.put(slag.trim(), meaning);
            listData.put(key, values);
            i++;
            sizeData++;
        }
        scanner.close();
    }

    public String[][] getData() {
        System.out.println("size: " + sizeData);

        String s[][] = new String[sizeData][3];
        Set<String> listKeySet = listData.keySet();
        Object[] listKey = listKeySet.toArray();


        int index = 0;
        for (int i = 0; i < sizeData; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = (String) listKey[index];
            List<String> values = listData.get(listKey[index]);
            s[i][2] = values.get(0);

            for (int j = 1; j < values.size(); j++) {
                if (i < sizeData) {
                    i++;
                }
                s[i][0] = String.valueOf(i);
                s[i][1] = (String) listKey[index];
                s[i][2] = values.get(j);
            }
            index++;

        }

        return s;
    }

    public static Data getInit() {
        // khởi tạo giá tr init nếu chưa có
        if (init == null)
        {
            // đảm bảo chỉ 1 Data được tạo trong suốt quá trình ứng dụng chạy
            synchronized (Data.class) {
                if (init == null) {
                    init = new Data();
                }
            }
        }
        // nếu có rồi trả về init đã được tạo trước đó
        return init;
    }
}
