package com.ggl.imagecreator.controller;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.ggl.imagecreator.view.dialog.FontDialog;

public class FontDisplayRunnable implements Runnable {
	
	private final DefaultTableModel tableModel;
	
	private final FontDialog dialog;
	
	private final String[] allFonts;

	public FontDisplayRunnable(FontDialog dialog, DefaultTableModel tableModel) {
		this.dialog = dialog;
		this.tableModel = tableModel;
		this.allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
	}

	@Override
	public void run() {
		int count = tableModel.getRowCount();
		for (int i = count - 1; i >= 0; i--) {
			removeRow(i);
		}

		int fontSize = dialog.getFontSize();
		int fontStyleInt = dialog.getFontStyleInt();
		String sampleText = dialog.getSampleText();
		List<Font> usableFonts = new ArrayList<>();
		for (String fontName : allFonts) {
			Font sizedFont = new Font(fontName, fontStyleInt, fontSize);
			if (sizedFont.canDisplayUpTo(sampleText) == -1) {
				usableFonts.add(sizedFont);
			}
		}
		
		usefulField(usableFonts.size());
		setFonts(usableFonts);

		for (Font font : usableFonts) {
			Object[] row = new Object[2];
			row[0] = font.getName();
			row[1] = sampleText;
			addRow(row);
		}
		
		scrollToTop();
	}
	
	private void usefulField(int count) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialog.setUsefulField(count);
			}
		});
	}
	
	private void setFonts(List<Font> usableFonts) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialog.setFonts(usableFonts);;
			}
		});
	}
	
	private void removeRow(int row) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				tableModel.removeRow(row);
			}
		});
	}
	
	private void addRow(Object[] row) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				tableModel.addRow(row);
			}
		});
	}

	
	private void scrollToTop() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialog.scrollToTop();
			}
		});
	}
	
}
