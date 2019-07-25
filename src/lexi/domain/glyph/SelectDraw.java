package lexi.domain.glyph;

import java.awt.Color;
import java.awt.Point;

public class SelectDraw {
    private final Color hightLightColor;
    private final Color fontColor;
    private final Point point;

    public SelectDraw(Point point) {
        this(point, Color.BLACK, Color.WHITE);
    }

    public SelectDraw(Point point, Color hightLightColor, Color fontColor) {
        this.point = point;
        this.hightLightColor = hightLightColor;
        this.fontColor = fontColor;
    }

    public Color getHightLightColor() {
        return hightLightColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Point getPoint() {
        return point;
    }
}
