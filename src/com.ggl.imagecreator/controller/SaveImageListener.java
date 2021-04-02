package com.ggl.imagecreator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ggl.imagecreator.view.ImageCreatorFrame;
import com.ggl.imagecreator.view.component.FileTypeFilter;
import com.ggl.imagecreator.view.component.OSFileChooser;

public class SaveImageListener implements ActionListener {

	private final ImageCreatorFrame frame;
	
	public SaveImageListener(ImageCreatorFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		OSFileChooser fc = new OSFileChooser();
		fc.addChoosableFileFilter(new FileTypeFilter( 
				"Portable Network Graphics", "png"));
		fc.addChoosableFileFilter(new FileTypeFilter(
				"Joint Photographic Experts Group", "jpg"));
		fc.addChoosableFileFilter(new FileTypeFilter(
				"Graphics Interchange Format", "gif"));
		fc.setAcceptAllFileFilterUsed(false);
		
		int returnVal = fc.showSaveDialog(frame.getFrame());
		if (returnVal == OSFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String extension = fc.getExtension(file);
			BufferedImage image = frame.getDrawingImage();
			writeImage(file, extension, image);
		}
	}

	private void writeImage(File file, String extension, BufferedImage image) {
		try {
			ImageIO.write(image, extension, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
