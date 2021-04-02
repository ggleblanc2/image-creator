package com.ggl.imagecreator.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ggl.imagecreator.model.ImageCreatorModel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final ImageCreatorModel model;
	
	public DrawingPanel(ImageCreatorModel model) {
		this.model = model;
		setPreferredSize();
	}
	
	public void setPreferredSize() {
		this.setPreferredSize(model.getDrawingPanelDimension());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(model.getBackgroundColor());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		String[] words = model.getText().split("\\s+");
		Font font = model.getTextFont();
		g2d.setFont(font);
		g2d.setColor(model.getTextColor());
		
		FontRenderContext context = g2d.getFontRenderContext();
		StringBuilder builder = new StringBuilder();
		StringBuilder output = new StringBuilder();
		String line;
		int margin = 25;
		double y = margin;
		
		for (String word : words) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			if (word.equals("\\p")) {
				line = output.toString();
				y = drawLine(g2d, context, font, line, margin, y);
				line = " ";
				y = drawLine(g2d, context, font, line, margin, y);
				output.delete(0, output.length());
				builder.delete(0, builder.length());
			} else {
				builder.append(word);
				y = createLine(g2d, context, font, word, builder, output, margin, y);
			}
		}
		
		y = createLine(g2d, context, font, "", builder, output, margin, y);
		line = output.toString();
		y = drawLine(g2d, context, font, line, margin, y);
	}

	private double createLine(Graphics2D g2d, FontRenderContext context, Font font, 
			String word, StringBuilder builder, StringBuilder output, int margin, 
			double y) {
		Rectangle2D bounds = font.getStringBounds(builder.toString(), context);
		int width = getWidth() - margin - margin;
		double x = (width - bounds.getWidth()) / 2 + margin;
		
		if (x < margin) {
			String line = output.toString();
			y = drawLine(g2d, context, font, line, margin, y);
			output.delete(0, output.length());
			builder.delete(0, builder.length());
			builder.append(word);
			output.append(word);
		} else {
			output.append(" ");
			output.append(word);
		}
		
		return y;
	}

	private double drawLine(Graphics2D g2d, FontRenderContext context, 
			Font font, String line, int margin, double y) {
		Rectangle2D bounds = font.getStringBounds(line, context);
		int width = getWidth() - margin - margin;
		double x = (width - bounds.getWidth()) / 2 + margin;
		y += bounds.getHeight() * 5.0 / 4.0;
		g2d.drawString(line, (int) Math.round(x), (int) Math.round(y));
		return y;
	}
	
	public BufferedImage getBufferedImage() {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), 
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		paintAll(g);
		g.dispose();

		return image;
	}

}
