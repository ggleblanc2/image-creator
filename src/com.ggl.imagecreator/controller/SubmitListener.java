package com.ggl.imagecreator.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.ImageCreatorFrame;

public class SubmitListener implements ActionListener {
	
	private final ImageCreatorFrame frame;
	
	private final ImageCreatorModel model;

	public SubmitListener(ImageCreatorFrame frame, ImageCreatorModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Dimension d = frame.getDrawingPanelDimension();
		Dimension m = model.getDrawingPanelDimension();
		if (!d.equals(m)) {
			model.setDrawingPanelDimension(d);
			frame.setPreferredSize(d);
			frame.getFrame().pack();
		}
		
		model.setText(frame.getText());
		frame.repaint();
	}

}
