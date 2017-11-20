package util;

/**
 * Created by ldchao on 2017/11/20.
 */
public class UrlFormatUtils {

    public static String formatUrl(String url){
        String formatUrl=url;
        if(!formatUrl.startsWith("http://")){
            formatUrl="http://"+formatUrl;
        }
        return formatUrl;
    }

//    public static void main(String[] args) {
//        String url="localhost:8080/#/home";
//        System.out.println(UrlFormatUtils.formatUrl(url));
//    }
}
