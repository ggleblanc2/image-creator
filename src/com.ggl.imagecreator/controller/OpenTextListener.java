package com.ggl.imagecreator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.ImageCreatorFrame;
import com.ggl.imagecreator.view.component.FileTypeFilter;
import com.ggl.imagecreator.view.component.OSFileChooser;

public class OpenTextListener implements ActionListener {
	
	private final ImageCreatorFrame frame;
	
	private final ImageCreatorModel model;

	public OpenTextListener(ImageCreatorFrame frame, ImageCreatorModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		OSFileChooser fc = new OSFileChooser();
		fc.addChoosableFileFilter(new FileTypeFilter( 
				"Text", "txt"));
		fc.setAcceptAllFileFilterUsed(true);
		
		int returnVal = fc.showOpenDialog(frame.getFrame());
		if (returnVal == OSFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				model.setText(readFile(file));
				frame.updateTextArea();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String readFile(File file) throws FileNotFoundException, IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while (line != null) {
			line += System.lineSeparator();
			builder.append(line);
			line = reader.readLine();
		}
		reader.close();
		return builder.toString();
	}

}
