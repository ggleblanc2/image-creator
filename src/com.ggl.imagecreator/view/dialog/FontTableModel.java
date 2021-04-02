package com.ggl.imagecreator.view.dialog;

import javax.swing.table.DefaultTableModel;

public class FontTableModel extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
