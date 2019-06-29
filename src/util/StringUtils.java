package util;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static boolean endsWith(String filePath, String s) {
        return (filePath != null && filePath.endsWith(s));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
