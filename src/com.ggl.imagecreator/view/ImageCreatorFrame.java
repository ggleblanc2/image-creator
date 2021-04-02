package com.ggl.imagecreator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.ggl.imagecreator.controller.OpenTextListener;
import com.ggl.imagecreator.controller.SaveImageListener;
import com.ggl.imagecreator.controller.SaveTextListener;
import com.ggl.imagecreator.model.ImageCreatorModel;
import com.ggl.imagecreator.view.dialog.AboutDialog;
import com.ggl.imagecreator.view.dialog.InstructionsDialog;

public class ImageCreatorFrame {
	
	private final ControlPanel controlPanel;
	
	private final DrawingPanel drawingPanel;
	
	private final ImageCreatorModel model;
	
	private JFrame frame;

	public ImageCreatorFrame(ImageCreatorModel model) {
		this.model = model;
		this.controlPanel = new ControlPanel(this, model);
		this.drawingPanel = new DrawingPanel(model);
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		frame = new JFrame("Image Creator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(getImage("page.jpg"));
		frame.setJMenuBar(createMenuBar());
		
		frame.add(drawingPanel, BorderLayout.CENTER);
		frame.add(controlPanel.getPanel(), BorderLayout.AFTER_LINE_ENDS);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println(frame.getSize());
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem openTextFileItem = new JMenuItem("Open text file... ");
		openTextFileItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
		openTextFileItem.addActionListener(new OpenTextListener(this, model));
		fileMenu.add(openTextFileItem);
		
		JMenuItem saveTextFileItem = new JMenuItem("Save text file... ");
		saveTextFileItem.addActionListener(new SaveTextListener(this, model));
		fileMenu.add(saveTextFileItem);
		
		fileMenu.addSeparator();
		
		JMenuItem saveImageFileItem = new JMenuItem("Save image file... ");
		saveImageFileItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
		saveImageFileItem.addActionListener(new SaveImageListener(this));
		fileMenu.add(saveImageFileItem);
		
		fileMenu.addSeparator();
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setAccelerator(KeyStroke.getKeyStroke("alt X"));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem instructionsItem = new JMenuItem("Instructions... ");
		instructionsItem.setAccelerator(KeyStroke.getKeyStroke("F1"));
		instructionsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new InstructionsDialog(ImageCreatorFrame.this, "Instructions");
			}
		});
		helpMenu.add(instructionsItem);
		
		JMenuItem aboutItem = new JMenuItem("About... ");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialog(ImageCreatorFrame.this, "About Image Creator");
			}
		});
		helpMenu.add(aboutItem);
		
		return menuBar;
	}
	
	private BufferedImage getImage(String filename) {
		try {
			return ImageIO.read(getClass().getResourceAsStream("/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Dimension getDrawingPanelDimension() {
		return controlPanel.getDrawingPanelDimension();
	}
	
	public void updateColorPanels() {
		controlPanel.updateColorPanels();
	}
	
	public void updateFont() {
		controlPanel.updateFont();
	}
	
	public void updateTextArea() {
		controlPanel.updateTextArea();
	}
	
	public String getText() {
		return controlPanel.getText();
	}
	
	public void setPreferredSize(Dimension preferredSize) {
		drawingPanel.setPreferredSize(preferredSize);
	}
	
	public void repaint() {
		drawingPanel.repaint();
	}
	
	public BufferedImage getDrawingImage() {
		return drawingPanel.getBufferedImage();
	}
}
