package lexi.model;

import java.util.List;
import lexi.util.*;

public interface ICompositor {	
	List<Row> compose(List<Glyph> glyphs, ViewEventArgs args);
	
}
