package lexi.util.i18n;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class ResourceBundleMessageResource implements MessageResource {
    private ResourceBundle resourceBundle;

    public ResourceBundleMessageResource() {
        resourceBundle = ResourceBundle.getBundle("message");
    }
    @Override
    public String get(String key, Object... args) {
        String value = resourceBundle.getString(key);
        try {
            value = new String(value.getBytes(StandardCharsets.ISO_8859_1), "GBK");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(args != null && args.length > 0) {
            value = String.format(value, args);
        }
        return value;
    }
    public static MessageResource getInstance() {
        return ResourceBundleMessageResourceHolder.INSTANCE;
    }

    private static class ResourceBundleMessageResourceHolder {
        static final MessageResource INSTANCE = new ResourceBundleMessageResource();
    }
}
