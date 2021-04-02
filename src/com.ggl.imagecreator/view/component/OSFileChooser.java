package com.ggl.imagecreator.view.component;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OSFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	@Override
	public void approveSelection() {
		File f = getSelectedFile();
		
		if (f.exists() && getDialogType() == SAVE_DIALOG) {
			int result = JOptionPane.showConfirmDialog(this, f.getName() + 
					" exists, overwrite?",
					"Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				super.approveSelection();
				return;
			case JOptionPane.NO_OPTION:
				return;
			case JOptionPane.CLOSED_OPTION:
				return;
			case JOptionPane.CANCEL_OPTION:
				cancelSelection();
				return;
			}
		}
		
		super.approveSelection();
	}
	
	@Override
	public File getSelectedFile() {
		File file = super.getSelectedFile();
		
		if (file != null && getDialogType() == SAVE_DIALOG) {
			String extension = getExtension(file);
			if (extension.isEmpty()) {
				FileTypeFilter filter = (FileTypeFilter) getFileFilter();
				if (filter != null) {
					extension = filter.getExtension();
					String fileName = file.getPath();
					fileName += "." + extension;
					file = new File(fileName);
				}
			}
		}
		
		return file;
	}
	
	public String getExtension(File file) {
		String extension = "";
		String s = file.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < (s.length() - 1)) {
			extension = s.substring(i + 1).toLowerCase();
		}

		return extension;
	}
	
}
