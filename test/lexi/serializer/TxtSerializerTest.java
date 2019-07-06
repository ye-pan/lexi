package lexi.serializer;

import lexi.model.Composition;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class TxtSerializerTest {

    @Test
    public void testReaderFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Administrator\\Desktop\\书籍\\test.txt"), Charset.forName("GBK"));
        for (String line : lines) {
            System.out.println(line);
        }
    }

    @Test
    public void testTxtSerializerDecode() {
        TxtSerializer serializer = new TxtSerializer("C:\\Users\\Administrator\\Desktop\\书籍\\test.txt");
        Composition document = new Composition();
        serializer.decode(document);
        assertTrue(document.getChildren().size() > 0);
    }

    @Test
    public void testTxtSerializerEncode() {
        TxtSerializer serializer = new TxtSerializer("C:\\Users\\Administrator\\Desktop\\书籍\\test.txt");
        Composition document = new Composition();
        serializer.decode(document);
        try {
            new TxtSerializer("C:\\Users\\Administrator\\Desktop\\书籍\\1.txt");
        } catch(IllegalStateException ex) {
            ex.printStackTrace();
        }
        String fileId = UUID.randomUUID().toString();
        serializer = new TxtSerializer("C:\\Users\\Administrator\\Desktop\\书籍\\" + fileId + ".txt");
        serializer.encode(document);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFilePath() {
        new TxtSerializer("");
    }
}