package com.ggl.imagecreator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ImageCreatorModel {
	
	private Color backgroundColor;
	private Color textColor;
	
	private Dimension drawingPanelDimension;
	
	private Font textFont;
	
	private String text;
	
	public ImageCreatorModel() {
		setBackgroundColor(Color.BLACK);
		setTextColor(Color.WHITE);
		setDrawingPanelDimension(425, 550);
		setTextFont(new Font("Arial", Font.BOLD, 24));
		setText("");
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Dimension getDrawingPanelDimension() {
		return drawingPanelDimension;
	}

	public void setDrawingPanelDimension(int width, int height) {
		this.drawingPanelDimension = new Dimension(width, height);
	}

	public void setDrawingPanelDimension(Dimension drawingPanelDimension) {
		this.drawingPanelDimension = drawingPanelDimension;
	}

	public Font getTextFont() {
		return textFont;
	}

	public void setTextFont(Font textFont) {
		this.textFont = textFont;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
