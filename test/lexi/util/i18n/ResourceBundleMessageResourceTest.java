package lexi.util.i18n;

import org.junit.Test;
import java.nio.charset.Charset;
import static org.junit.Assert.*;

public class ResourceBundleMessageResourceTest {

    @Test
    public void testGet() {
        ResourceBundleMessageResource message = new ResourceBundleMessageResource(Charset.forName("GBK"));
        String title = message.get("menu.file");
        assertTrue(title != null && title.length() > 0);
        assertEquals(message.getCharset(), Charset.forName("GBK"));

        String args = message.get("args.title", "bar");
        assertEquals( "foo bar", args);

        String args2 = message.get("args.title", "test");
        assertEquals("foo test", args2);
    }

    @Test
    public void testSingleton() {
        assertEquals(ResourceBundleMessageResource.getInstance(), ResourceBundleMessageResource.getInstance());
    }
}