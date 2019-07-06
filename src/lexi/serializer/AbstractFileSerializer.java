package lexi.serializer;

import lexi.model.Composition;
import lexi.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class AbstractFileSerializer implements Encoder, Decoder {

    protected Charset originCharset;

    protected Charset charset;

    private String filePath;

    public AbstractFileSerializer(String filePath) {
        if(StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("File path must not be empty.");
        }
        this.filePath = filePath;
        this.originCharset = StandardCharsets.UTF_8;
        this.charset = StandardCharsets.UTF_8;
    }
    @Override
    public void decode(Composition document) {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new IllegalStateException("File not exists!");
        }
        try (FileInputStream in = new FileInputStream(file)) {
            decode(document, in);
        } catch(Exception e) {
            throw new RuntimeException("读取文件发生异常!", e);
        }

    }

    protected abstract void decode(Composition document, InputStream file) throws Exception;

    @Override
    public void encode(Composition document) {
        File file = new File(filePath);
        if(file.exists()) {
            throw new IllegalStateException("文件已经存在!");
        }
        try (FileOutputStream out = new FileOutputStream(file)) {
            encode(document, out);
        } catch(Exception e) {
            throw new RuntimeException("写文件发生异常！", e);
        }

    }

    protected abstract void encode(Composition document, OutputStream out) throws Exception;
}
