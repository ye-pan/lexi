package lexi.serializer;

import lexi.model.Char;
import lexi.model.Composition;
import lexi.model.Glyph;
import lexi.util.StringUtils;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;

public class TxtSerializer extends AbstractFileSerializer {

    public TxtSerializer(String filePath) {
        super(filePath);
        originCharset = Charset.forName("GBK");
    }

    @Override
    protected void encode(Composition document, OutputStream out) throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, charset))) {
            for (Glyph child : document.getChildren()) {
                if(child instanceof Char) {
                    writer.write(((Char)child).getChar());
                }
            }
        }
    }

    @Override
    protected void decode(Composition document, InputStream in) throws Exception {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in, originCharset))) {
            String line = reader.readLine();
            while(StringUtils.isNotEmpty(line)) {
                for (char c : line.toCharArray()) {
                    Font font =  new Font("宋体", Font.BOLD, 10);
                    Char ch = new Char(c, font);
                    document.getChildren().add(ch);
                }
                line = reader.readLine();
            }
        }
    }
}
