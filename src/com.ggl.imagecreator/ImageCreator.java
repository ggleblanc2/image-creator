package com.ggl.imagecreator;

import javax.swing.SwingUtilities;

import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.ImageCreatorFrame;

public class ImageCreator implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ImageCreator());
	}

	@Override
	public void run() {
		new ImageCreatorFrame(new ImageCreatorModel());
	}

}
