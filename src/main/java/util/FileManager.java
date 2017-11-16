package util;

/**
 * Created by lvdechao on 2016/10/4.
 */

import java.io.*;

public class FileManager {

    // 读文件，参数为文件地址
    public static String ReadFile(String path) {
        String result="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result+=temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 写文件，参数为文件地址和是否覆盖，false为覆盖,true为最后加入
    public static void WriteFile(String string, String uploadUrl,String fileName, boolean overwrite) {

        ensureFileExists(uploadUrl,fileName);

        String path=uploadUrl+fileName;

        try {
            FileWriter fileWriter = new FileWriter(path, overwrite);
            fileWriter.write(string);
            fileWriter.close();
        } catch (Exception e) {
        }
    }

    public static void ensureFileExists(String uploadUrl,String fileName) {
        File dir = new File(uploadUrl);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(uploadUrl + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
