package serializer;

import model.Char;
import model.Composition;
import util.StringUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TxtSerializer extends AbstractFileSerializer {

    public TxtSerializer(String filePath) {
        super(filePath);
    }

    @Override
    protected void encode(Composition document, File file) {
        //TODO 待实现
    }

    @Override
    protected void decode(Composition document, File file) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = reader.readLine();
            while(StringUtils.isNotEmpty(line)) {
                for (char c : line.toCharArray()) {
                    Font font =  new Font("宋体", Font.BOLD, 10);
                    Char ch = new Char(c, font);
                    document.getChildren().add(ch);
                }
                line = reader.readLine();
            }
        } catch(Exception e) {
            throw new RuntimeException("读取文件错误.", e);
        }
    }
}
