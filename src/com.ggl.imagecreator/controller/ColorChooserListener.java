package com.ggl.imagecreator.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.ImageCreatorFrame;

public class ColorChooserListener implements ActionListener {

	private final boolean isBackground;
	
	private final ImageCreatorFrame frame;
	
	private final ImageCreatorModel model;
	
	private final String title;
	
	public ColorChooserListener(ImageCreatorFrame frame, ImageCreatorModel model, 
			String title, boolean isBackground) {
		this.frame = frame;
		this.model = model;
		this.title = title;
		this.isBackground = isBackground;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Color initialColor = (isBackground) ? 
				model.getBackgroundColor() : model.getTextColor();
		Color newColor = JColorChooser.showDialog(frame.getFrame(), title, initialColor);
		if (newColor != null) {
			if (isBackground) {
				model.setBackgroundColor(newColor);
			} else {
				model.setTextColor(newColor);
			}
			frame.updateColorPanels();
			frame.repaint();
			frame.getFrame().pack();
		}
	}

}
