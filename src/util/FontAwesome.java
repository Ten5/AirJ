package util;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontAwesome {
	public Font lg;
	public Font sm;
	
	public FontAwesome(){
		
		try (InputStream is = TestFontAwsome.class.getResourceAsStream("fontawesome-webfont.ttf")) {
        	lg = Font.createFont(Font.TRUETYPE_FONT, is);
            lg = lg.deriveFont(Font.PLAIN, 48f);

            sm = lg.deriveFont(Font.PLAIN, 24f);
            
        } catch (IOException | FontFormatException exp) {
            exp.printStackTrace();
        }
	}
	
}
