package com.ggl.imagecreator.view.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FontTableCellRenderer implements TableCellRenderer {
	
	private final int rowHeight;
	
	private final Color highlightColor;
	private final Color backgroundColor;
	
	private final JLabel label;

	private final JPanel component;
	
	private final JTable displayTable;
	
	private List<Font> fonts;


	public FontTableCellRenderer(JTable displayTable) {
		this.displayTable = displayTable;
		this.rowHeight = displayTable.getRowHeight();
		this.label = new JLabel();
		this.component = new JPanel();
		this.highlightColor = new Color(184, 202, 220);
		this.backgroundColor = Color.WHITE;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, 
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (row >= 0 && row < fonts.size()) {
			label.setFont(fonts.get(row));
		}
		label.setText(value.toString());
		if (isSelected) {
			component.setBackground(highlightColor);
			label.setBackground(highlightColor);
		} else {
			component.setBackground(backgroundColor);
			label.setBackground(backgroundColor);
		}
		int thisRowHeight = Math.max(rowHeight, 
				component.getPreferredSize().height + 6);
		displayTable.setRowHeight(row, thisRowHeight);
		component.add(label);
		
		return component;
	}

}

