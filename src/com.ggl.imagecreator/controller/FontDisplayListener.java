package com.ggl.imagecreator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import com.ggl.imagecreator.view.dialog.FontDialog;

public class FontDisplayListener implements ActionListener {
	
	private final DefaultTableModel tableModel;
	
	private final FontDialog dialog;

	public FontDisplayListener(FontDialog dialog, DefaultTableModel tableModel) {
		this.dialog = dialog;
		this.tableModel = tableModel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		new Thread(new FontDisplayRunnable(dialog, tableModel)).start();
	}

}
