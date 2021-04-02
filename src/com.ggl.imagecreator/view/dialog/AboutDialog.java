package com.ggl.imagecreator.view.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.imagecreator.view.ImageCreatorFrame;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public AboutDialog(ImageCreatorFrame frame, String title) {
		super(frame.getFrame(), true);
		setTitle(title);
		
		this.add(createMainPanel());
		
		this.pack();
		this.setLocationRelativeTo(frame.getFrame());
		this.setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		Font font = panel.getFont().deriveFont(16f);
		
		JLabel titleLabel = new JLabel("Image Creator");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(panel.getFont().deriveFont(24f));
		titleLabel.setForeground(Color.WHITE);
		panel.add(titleLabel);
		
		panel.add(Box.createVerticalStrut(20));
		
		JLabel label = new JLabel("Written by Gilbert G. Le Blanc");
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		panel.add(label);
		
		label = new JLabel("Created on April 1, 2021");
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		panel.add(label);
		
		label = new JLabel("Version 1.0");
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		panel.add(label);
		
		panel.add(Box.createVerticalStrut(30));
		
		JButton button = new JButton("Close");
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		button.setFont(font);
		panel.add(button);
		
		return panel;
	}
	

}
