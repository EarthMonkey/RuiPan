package util;

/**
 * Created by lvdechao on 2016/10/4.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

public class FileManager {

    private static Log logger = LogFactory.getLog(FileManager.class);

    private static String encoding="utf-8";

    // 读文件，参数为文件地址
    public static String ReadFile(String path) {
        String result="";
        BufferedReader br = null;
        try {
//            BufferedReader br = new BufferedReader(new FileReader(path));
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path), encoding));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result+=temp;
            }
        } catch (Exception e) {
            logger.error(e);
        } finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return result;
    }

    // 写文件，参数为文件地址和是否覆盖，false为覆盖,true为最后加入
    public static void WriteFile(String string, String uploadUrl,String fileName, boolean overwrite) {

        ensureFileExists(uploadUrl,fileName);

        String path=uploadUrl+fileName;
        File file=new File(path);
        if(!overwrite){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error(e);
            }
        }
        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), encoding));
            fileWriter.write(string);

        } catch (Exception e) {
            logger.error(e);
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
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
                logger.error(e);
            }
        }
    }

    public static void deleteText(String textPath){
        String rootPath=RootPathUtil.getInstance().getRootPath();
        String fullFilePath = rootPath + "upload"+File.separator+"text"+File.separator+textPath;
        deleteFile(fullFilePath);
    }

    public static void deleteImage(String imagePath){
        String rootPath=RootPathUtil.getInstance().getRootPath();
        String fullFilePath = rootPath + "upload"+File.separator+"images"+File.separator+imagePath;
        deleteFile(fullFilePath);
    }

    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                logger.error("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            logger.error("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
