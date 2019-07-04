package lexi.util.i18n;

import java.nio.charset.Charset;

/**
 * 国际化支持
 */
public interface MessageResource {

    /**
     * 获取值
     * @param key
     * @param args
     * @return
     */
    String get(String key, Object... args);

    /**
     * 获取目标编码
     * @return
     */
    Charset getCharset();
}
