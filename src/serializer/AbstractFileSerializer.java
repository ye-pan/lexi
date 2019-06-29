package serializer;

import model.Composition;
import util.StringUtils;

import java.io.File;

public abstract class AbstractFileSerializer implements Encoder, Decoder {
    protected String filePath;
    public AbstractFileSerializer(String filePath) {
        if(StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("File path must not be empty.");
        }
        this.filePath = filePath;
    }
    @Override
    public void decode(Composition document) {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new IllegalStateException("File not exists!");
        }
        decode(document, file);
    }

    protected abstract void decode(Composition document, File file);

    @Override
    public void encode(Composition document) {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new IllegalStateException("File not exists!");
        }
        encode(document, file);
    }

    protected abstract void encode(Composition document, File file);
}
