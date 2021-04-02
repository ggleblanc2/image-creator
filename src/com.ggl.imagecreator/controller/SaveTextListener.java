package com.ggl.imagecreator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.ImageCreatorFrame;
import com.ggl.imagecreator.view.component.FileTypeFilter;
import com.ggl.imagecreator.view.component.OSFileChooser;

public class SaveTextListener implements ActionListener {

	private final ImageCreatorFrame frame;
	
	private final ImageCreatorModel model;
	
	public SaveTextListener(ImageCreatorFrame frame, ImageCreatorModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		OSFileChooser fc = new OSFileChooser();
		fc.addChoosableFileFilter(new FileTypeFilter( 
				"Text", "txt"));
		fc.setAcceptAllFileFilterUsed(false);
		
		int returnVal = fc.showSaveDialog(frame.getFrame());
		if (returnVal == OSFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				writeFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeFile(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(model.getText());
		writer.close();
	}

}
