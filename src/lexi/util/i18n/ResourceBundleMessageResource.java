package lexi.util.i18n;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceBundleMessageResource implements MessageResource {

    private static final Map<String, String> KEY_VALUE_CACHE = new ConcurrentHashMap<>();

    private ResourceBundle resourceBundle;

    private final Charset charset;

    private final Charset originCharset;

    public ResourceBundleMessageResource(Charset charset) {
        this.charset = charset;
        this.originCharset = StandardCharsets.ISO_8859_1;
        this.resourceBundle = ResourceBundle.getBundle("message");
    }

    @Override
    public String get(String key, Object... args) {
        return KEY_VALUE_CACHE.computeIfAbsent(key, (k)->{
            String value = resourceBundle.getString(k);
            value = charset.decode(originCharset.encode(value)).toString();
            if(args != null && args.length > 0) {
                value = String.format(value, args);
            }
            return value;
        });

    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    public static MessageResource getInstance() {
        return ResourceBundleMessageResourceHolder.INSTANCE;
    }

    private static class ResourceBundleMessageResourceHolder {
        static final MessageResource INSTANCE = new ResourceBundleMessageResource(Charset.forName("GBK"));
    }
}
