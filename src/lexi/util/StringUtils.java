package lexi.util;

import java.util.Objects;

public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException();
    }
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static boolean endsWith(String filePath, String s) {
        return (filePath != null && filePath.endsWith(s));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean equals(String str1, String str2) {
        return Objects.equals(str1, str2);
    }
}
