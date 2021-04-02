package com.ggl.imagecreator.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ggl.imagecreator.controller.ColorChooserListener;
import com.ggl.imagecreator.controller.SubmitListener;
import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.dialog.FontDialog;
import com.ggl.imagecreator.view.dialog.FontStyles;

public class ControlPanel {
	
	private final FontStyles fontStyles;
	
	private final ImageCreatorFrame frame;
	
	private final ImageCreatorModel model;
	
	private JLabel backgroundLabel;
	private JLabel foregroundLabel;
	private JLabel fontLabel;
	
	private final JPanel panel;
	
	private JPanel backgroundLabelPanel;
	private JPanel foregroundLabelPanel;
	
	private JTextArea textArea;
	
	private JTextField widthField;
	private JTextField heightField;

	public ControlPanel(ImageCreatorFrame frame, ImageCreatorModel model) {
		this.frame = frame;
		this.model = model;
		this.fontStyles = new FontStyles();
		this.panel = createControlPanel();
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font font = panel.getFont().deriveFont(16f);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1.0;
		
		createDimensionControls(panel, gbc, font);
		createBackgroundColorControls(panel, gbc, font);
		createTextColorControls(panel, gbc, font);
		createFontControls(panel, gbc, font);
		createTextArea(panel, gbc, font);
		createMasterButtons(panel, gbc, font);
		
		return panel;
	}

	private void createDimensionControls(JPanel panel, GridBagConstraints gbc, Font font) {
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Dimension:");
		label.setFont(font);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("Width:");
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.TRAILING);
		panel.add(label, gbc);
		
		Dimension d = model.getDrawingPanelDimension();
		
		gbc.gridx++;
		widthField = new JTextField(4);
		widthField.setFont(font);
		widthField.setHorizontalAlignment(JTextField.TRAILING);
		widthField.setText(Integer.toString(d.width));
		panel.add(widthField, gbc);
		
		gbc.gridx++;
		label = new JLabel("Height:");
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.TRAILING);
		panel.add(label, gbc);
		
		gbc.gridx++;
		heightField = new JTextField(4);
		heightField.setFont(font);
		heightField.setHorizontalAlignment(JTextField.TRAILING);
		heightField.setText(Integer.toString(d.height));
		panel.add(heightField, gbc);
	}

	private void createBackgroundColorControls(JPanel panel, GridBagConstraints gbc, Font font) {
		JLabel label;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Background Color:");
		label.setFont(font);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx += 2;
		backgroundLabelPanel = new JPanel();
		backgroundLabelPanel.setBackground(model.getTextColor());
		backgroundLabel = new JLabel("Sample Text");
		backgroundLabel.setBackground(model.getTextColor());
		backgroundLabel.setFont(font);
		backgroundLabel.setForeground(model.getBackgroundColor());
		backgroundLabelPanel.add(backgroundLabel);
		panel.add(backgroundLabelPanel, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx++;
		JButton button = new JButton("Choose Color");
		button.addActionListener(new ColorChooserListener(frame, model, 
				"Select Background Color", true));
		button.setFont(font);
		panel.add(button, gbc);
	}

	private void createTextColorControls(JPanel panel, GridBagConstraints gbc, Font font) {
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;
		JLabel label = new JLabel("Text Color:");
		label.setFont(font);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx += 2;
		foregroundLabelPanel = new JPanel();
		foregroundLabelPanel.setBackground(model.getBackgroundColor());
		foregroundLabel = new JLabel("Sample Text");
		foregroundLabel.setBackground(model.getBackgroundColor());
		foregroundLabel.setFont(font);
		foregroundLabel.setForeground(model.getTextColor());
		foregroundLabelPanel.add(foregroundLabel);
		panel.add(foregroundLabelPanel, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx++;
		JButton button = new JButton("Choose Color");
		button.addActionListener(new ColorChooserListener(frame, model, 
				"Select Text Color", false));
		button.setFont(font);
		panel.add(button, gbc);
	}
	
	public void updateColorPanels() {
		backgroundLabelPanel.setBackground(model.getTextColor());
		backgroundLabel.setBackground(model.getTextColor());
		backgroundLabel.setForeground(model.getBackgroundColor());
		
		foregroundLabelPanel.setBackground(model.getBackgroundColor());
		foregroundLabel.setBackground(model.getBackgroundColor());
		foregroundLabel.setForeground(model.getTextColor());
	}


	private void createFontControls(JPanel panel, GridBagConstraints gbc, Font font) {
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy++;
		JLabel label = new JLabel("Font:");
		label.setFont(font);
		panel.add(label, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx++;
		fontLabel = new JLabel();
		updateFont();
		panel.add(fontLabel, gbc);
		
		gbc.gridx += 2;
		JButton button = new JButton("Choose Font");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FontDialog dialog = new FontDialog(frame, model, "Select a Font");
				Font font = dialog.getTextFont();
				model.setTextFont(font);
				updateFont();
				frame.repaint();
			}
		});
		button.setFont(font);
		panel.add(button, gbc);
	}
	
	public void updateFont() {
		Font modelFont = model.getTextFont();
		Font textFont = modelFont.deriveFont(16f);
		String text = textFont.getName() + " " +
				fontStyles.getFontStyle(textFont.getStyle()) + " " +
				Integer.toString(modelFont.getSize()) + " pt";
		fontLabel.setFont(textFont);
		fontLabel.setText(text);
	}

	private void createTextArea(JPanel panel, GridBagConstraints gbc, Font font) {
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy++;
		JLabel label = new JLabel("Text:");
		label.setFont(font);
		panel.add(label, gbc);
		
		gbc.gridwidth = 4;
		gbc.gridx++;
		textArea = new JTextArea(10, 30);
		textArea.setFont(font);
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		updateTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, gbc);
	}
	
	public void updateTextArea() {
		textArea.setText(model.getText());
	}

	private void createMasterButtons(JPanel panel, GridBagConstraints gbc, Font font) {
		gbc.gridwidth = 5;
		gbc.gridx = 0;
		gbc.gridy++;
		JButton button = new JButton("Submit");
		button.addActionListener(new SubmitListener(frame, model));
		button.setFont(font);
		panel.add(button, gbc);
	}

	public JPanel getPanel() {
		return panel;
	}

	public String getText() {
		return textArea.getText().trim();
	}
	
	public Dimension getDrawingPanelDimension() {
		Dimension d = model.getDrawingPanelDimension();
		int width = valueOf(widthField.getText().trim());
		int height = valueOf(heightField.getText().trim());
		width = (width < 0) ? d.width : width;
		height = (height < 0) ? d.height : height;
		return new Dimension(width, height);
	}
	
	private int valueOf(String number) {
		try {
			return Integer.valueOf(number);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
}
