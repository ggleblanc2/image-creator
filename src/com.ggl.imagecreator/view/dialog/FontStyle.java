package com.ggl.imagecreator.view.dialog;

public class FontStyle {
	
	private final int fontInt;
	
	private final String fontStyle;

	public FontStyle(int fontInt, String fontStyle) {
		this.fontInt = fontInt;
		this.fontStyle = fontStyle;
	}

	public int getFontInt() {
		return fontInt;
	}

	public String getFontStyle() {
		return fontStyle;
	}
	
	@Override
	public String toString() {
		return getFontStyle();
	}

}
