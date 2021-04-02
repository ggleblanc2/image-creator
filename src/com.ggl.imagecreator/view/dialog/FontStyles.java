package com.ggl.imagecreator.view.dialog;

import java.awt.Font;

public class FontStyles {
	
	private final FontStyle[] fontStyles;
	
	public FontStyles() {
		this.fontStyles = new FontStyle[4];
		
		fontStyles[0] = new FontStyle(Font.PLAIN, "Plain");
		fontStyles[1] = new FontStyle(Font.BOLD, "Bold");
		fontStyles[2] = new FontStyle(Font.ITALIC, "Italic");
		fontStyles[3] = new FontStyle(Font.BOLD | Font.ITALIC, "Bold & Italic");
	}

	public FontStyle[] getFontStyles() {
		return fontStyles;
	}

	public int getFontInt(String style) {
		for (FontStyle fontStyle : fontStyles) {
			if (fontStyle.getFontStyle().equals(style)) {
				return fontStyle.getFontInt();
			}
		}
		return -1;
	}
	
//	public String getFontStyleString(int fontInt) {
//		for (FontStyle fontStyle : fontStyles) {
//			if (fontStyle.getFontInt() == fontInt) {
//				return fontStyle.getFontStyle();
//			}
//		}
//		return "";
//	}
	
	public FontStyle getFontStyle(int fontInt) {
		for (FontStyle fontStyle : fontStyles) {
			if (fontStyle.getFontInt() == fontInt) {
				return fontStyle;
			}
		}
		return null;
	}
}
