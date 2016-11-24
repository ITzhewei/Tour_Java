package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2016/11/23.
 */
public class FileUtils {


    public static void main(String[] args) throws Exception {
        System.out.println("\t"+"adsad");
    }

    public static List<Integer> readintList(String fileName) throws Exception {
        List<Integer> integerList = new ArrayList<>();
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            integerList.add(Integer.parseInt(line));
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return integerList;
    }
    public static List<String> readStringList(String fileName) throws Exception {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringList.add(line);
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return stringList;
    }

    public static List<String> readNames() throws Exception {
        List<String> names = new ArrayList<>();
        File file = new File("names.txt");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            names.add(line);
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return names;
    }

    public static List<String> readV1s() throws Exception {
        List<String> names = new ArrayList<>();
        File file = new File("v1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            names.add(line);
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return names;
    }

    public static List<String> readV2s() throws Exception {
        List<String> names = new ArrayList<>();
        File file = new File("v2.txt");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            names.add(line);
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return names;
    }

    public static List<Integer> readWs() throws Exception {
        List<Integer> wList = new ArrayList<>();
        File file = new File("w.txt");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            wList.add(Integer.parseInt(line));
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return wList;
    }


    public static String readFromFile() throws Exception {
        List<String> names = new ArrayList<>();
        File file = new File("names.txt");
        StringBuffer stringBuffer = new StringBuffer();
        int len = 0;
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (len != 0) {
                stringBuffer.append("\r\n" + line);
            } else {
                stringBuffer.append(line);
            }
            len++;
        }
        inputStream.close();
        reader.close();
        bufferedReader.close();
        return stringBuffer.toString();
    }
}
