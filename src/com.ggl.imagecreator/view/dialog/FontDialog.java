package com.ggl.imagecreator.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import com.ggl.imagecreator.controller.FontDisplayListener;
import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.ImageCreatorFrame;

public class FontDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final FontTableModel tableModel;
	
	private Font font;
	
	private final FontStyles fontStyles;

	private FontTableCellRenderer renderer;
	
//	private final ImageCreatorFrame frame;
	
	private final ImageCreatorModel model;

	private JComboBox<FontStyle> fontStyleComboBox;
	private JComboBox<Integer> fontSizeComboBox;
	
	private JTable displayTable;

	private JTextField sampleTextField;
	private JTextField usefulField;

	public FontDialog(ImageCreatorFrame frame, ImageCreatorModel model, String title) {
		super(frame.getFrame(), true);
		setTitle(title);
//		this.frame = frame;
		this.model = model;
		this.fontStyles = new FontStyles();
		this.font = model.getTextFont();
		this.tableModel = new FontTableModel();
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createEntryPanel(), BorderLayout.BEFORE_FIRST_LINE);
		panel.add(createDisplayPanel(), BorderLayout.CENTER);
		panel.add(createButtonPanel(), BorderLayout.AFTER_LAST_LINE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(frame.getFrame());
		this.setVisible(true);
	}
	
	private JPanel createEntryPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		JLabel sampleLabel = new JLabel("Sample Text: ");
		panel.add(sampleLabel);

		sampleTextField = new JTextField(20);
		String text = model.getText();
		text = (text.isEmpty()) ? "This is a test" : text;
		text = (text.length() > 20) ? text.substring(0, 20) : text;
		sampleTextField.setEditable(false);
		sampleTextField.setText(text);
		panel.add(sampleTextField);

		panel.add(Box.createHorizontalStrut(10));

		JLabel fontStyleLabel = new JLabel("Font Style: ");
		panel.add(fontStyleLabel);
		
		FontStyle selectedStyle = fontStyles.getFontStyle(font.getStyle());
		fontStyleComboBox = new JComboBox<>(fontStyles.getFontStyles());
		fontStyleComboBox.setSelectedItem(selectedStyle);
		panel.add(fontStyleComboBox);

		panel.add(Box.createHorizontalStrut(10));
		
		JLabel fontSizeLabel = new JLabel("Font Size: ");
		panel.add(fontSizeLabel);

		Integer[] fontSizes = { 10, 12, 14, 16, 18, 24, 36, 48, 72 };
		fontSizeComboBox = new JComboBox<>(fontSizes);
		fontSizeComboBox.setSelectedItem(font.getSize());
		panel.add(fontSizeComboBox);

		panel.add(Box.createHorizontalStrut(10));
		
		JLabel usefulLabel = new JLabel("Number of Useful Fonts: ");
		panel.add(usefulLabel);
		
		usefulField = new JTextField(4);
		usefulField.setEditable(false);
		usefulField.setHorizontalAlignment(JTextField.TRAILING);
		panel.add(usefulField);
		
		panel.add(Box.createHorizontalStrut(10));

		JButton button = new JButton("Display Fonts");
		button.addActionListener(new FontDisplayListener(this, tableModel));
		panel.add(button);

		return panel;
	}

	private JPanel createDisplayPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		String[] headers = { "Font Name", "Sample Text" };
		for (String header : headers) {
			this.tableModel.addColumn(header);
		}

		
		displayTable = new JTable(tableModel);
		displayTable.setRowSelectionAllowed(true);
		ListSelectionModel cellSelectionModel = displayTable.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		renderer = new FontTableCellRenderer(displayTable);
		TableColumn column = displayTable.getColumnModel().getColumn(0);
		column.setCellRenderer(renderer);
		column = displayTable.getColumnModel().getColumn(1);
		column.setCellRenderer(renderer);

		panel.add(new JScrollPane(displayTable), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int row = displayTable.getSelectedRow();
				if (row >= 0) {
					String fontName = displayTable.getValueAt(row, 0).toString();
					FontStyle fontStyle = (FontStyle) fontStyleComboBox.getSelectedItem();
					int fontSize = (int) fontSizeComboBox.getSelectedItem();
					font = new Font(fontName, fontStyle.getFontInt(), fontSize);
					
					FontDialog.this.setVisible(false);
					FontDialog.this.dispose();
				}
			}
		});
		panel.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FontDialog.this.setVisible(false);
				FontDialog.this.dispose();
			}
		});
		panel.add(cancelButton);
		
		okButton.setPreferredSize(cancelButton.getPreferredSize());
		return panel;
	}
	
	public void setUsefulField(int count) {
		usefulField.setText(Integer.toString(count));
	}
	
	public int getFontSize() {
		return (int) fontSizeComboBox.getSelectedItem();
	}
	
	public int getFontStyleInt() {
		FontStyle fontStyle = (FontStyle) fontStyleComboBox.getSelectedItem();
		return fontStyle.getFontInt();
	}
	
	public String getSampleText() {
		return sampleTextField.getText().trim();
	}

	public Font getTextFont() {
		return font;
	}
	
	public void setFonts(List<Font> usableFonts) {
		renderer.setFonts(usableFonts);
	}
	
	public void scrollToTop() {
		Rectangle cellRect = displayTable.getCellRect(0, 0, true);
		displayTable.scrollRectToVisible(cellRect);
	}

}
