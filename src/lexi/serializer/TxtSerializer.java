package lexi.serializer;

import lexi.model.Char;
import lexi.model.Composition;
import lexi.util.StringUtils;

import java.awt.*;
import java.io.*;

public class TxtSerializer extends AbstractFileSerializer {

    public TxtSerializer(String filePath) {
        super(filePath);
    }

    @Override
    protected void encode(Composition document, OutputStream out) {
        throw new UnsupportedOperationException("lexi.serializer.TxtSerializer.encode TODO");
    }

    @Override
    protected void decode(Composition document, InputStream in) throws Exception {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
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
