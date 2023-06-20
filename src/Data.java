import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class Data {
    private TreeMap<String, List<String>> listData = new TreeMap<>();

    private static Data init = new Data();
    private String fileInput = "slang2.txt";
    private String fileData = "slang.txt";
    private String fileHistory = "slangHistory.txt";
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

    public static Data getInit() {
        if (init == null)
        {
            synchronized (Data.class) {
                if (init == null) {
                    init = new Data();
                }
            }
        }
        return init;
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
            listData.put(key, values);
            i++;
            sizeData++;
        }
        scanner.close();
    }

    public void readFileAgain() {
        try {
            readFile(fileData);
            this.saveFile(fileInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    void saveFile(String file) {
        try {
            PrintWriter pw = new PrintWriter(new File(file));
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Slag`Meaning\n");
            String s[][] = new String[listData.size()][3];
            Set<String> keySet = listData.keySet();
            Object[] keyArray = keySet.toArray();
            for (int i = 0; i < listData.size(); i++) {
                Integer in = i + 1;
                s[i][0] = in.toString();
                s[i][1] = (String) keyArray[i];
                List<String> meaning = listData.get(keyArray[i]);
                stringBuilder.append(s[i][1] + "`" + meaning.get(0));
                for (int j = 1; j < meaning.size(); j++) {
                    stringBuilder.append("|" + meaning.get(j));
                }
                stringBuilder.append("\n");
            }
            // System.out.println(stringBuilder.toString());
            pw.write(stringBuilder.toString());
            pw.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveHistory(String key, String value) throws Exception {
        File f = new File(fileHistory);
        FileWriter fw = new FileWriter(f, true);
        fw.write(key + "`" + value + "\n");
        fw.close();
    }


    public String[][] readHistory() {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileHistory));
            sc.useDelimiter("`");
            String temp = sc.next();
            System.out.println("first line history: " + temp);
            String[] part = sc.next().split("\n");
            keys.add(temp);
            values.add(part[0]);
            while (sc.hasNext()) {
                temp = part[1];
                part = sc.next().split("\n");
                keys.add(temp);
                values.add(part[0]);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = keys.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[size - i - 1][0] = String.valueOf(size - i);
            s[size - i - 1][1] = keys.get(i);
            s[size - i - 1][2] = values.get(i);
        }
        return s;
    }

    public String[][] getValues(String key) {
        List<String> values = listData.get(key);
        if (values == null){
            return null;
        }
        int size = values.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = key;
            s[i][2] = values.get(i);
        }
        return s;
    }
    public String[][] getKeys(String query) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Entry<String, List<String>> entry : listData.entrySet()) {
            List<String> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                if (value.get(i).toLowerCase().contains(query.toLowerCase())) {
                    keys.add(entry.getKey());
                    values.add(value.get(i));
                }
            }
        }
        int size = keys.size();
        String s[][] = new String[size][3];

        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = keys.get(i);
            s[i][2] = values.get(i);
        }
        return s;
    }



    public void add(String key, String value) {
        List<String> values = new ArrayList<>();
        values.add(value);
        sizeData++;
        listData.put(key, values);
        this.saveFile(fileInput);
    }

    public void addDuplicate(String key, String value) {
        List<String> values = listData.get(key);
        values.add(value);
        sizeData++;
        listData.put(key, values);
        this.saveFile(fileInput);
    }

    public void addOverwrite(String key, String value) {
        List<String> values = listData.get(key);
        values.set(0, value);
        listData.put(key, values);
        this.saveFile(fileInput);
    }
    public void edit(String key, String value, String newValue) {
        System.out.println(value + "\t" + newValue);
        List<String> values = listData.get(key);
        int index = values.indexOf(value);
        values.set(index, newValue);
        this.saveFile(fileInput);
        System.out.println("Size of data: " + sizeData);
    }

    public void delete(String key, String value) {
        List<String> values = listData.get(key);
        int index = values.indexOf(value);
        if (values.size() == 1) {
            listData.remove(key);
        } else {
            values.remove(index);
            listData.put(key, values);
        }
        sizeData--;
        this.saveFile(fileInput);
    }

    public boolean check(String key) {
        for (String isKey : listData.keySet()) {
            if (isKey.equals(key))
                return true;
        }
        return false;
    }


}
