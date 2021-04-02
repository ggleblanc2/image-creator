package com.ggl.imagecreator.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.ggl.imagecreator.view.ImageCreatorFrame;

public class InstructionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
//	private final ImageCreatorFrame frame;

	public InstructionsDialog(ImageCreatorFrame frame, String title) {
		super(frame.getFrame(), true);
		setTitle(title);
		
		this.add(createMainPanel());
		
		this.pack();
		this.setLocationRelativeTo(frame.getFrame());
		this.setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(createTextPanel(), BorderLayout.CENTER);
		panel.add(createButtonPanel(), BorderLayout.AFTER_LAST_LINE);
		return panel;
	}
	
	private JPanel createTextPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(new Dimension(500, 300));
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setMargin(new Insets(5, 5, 5, 5));
		try {
			textPane.setText(getInstructions("instructions.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		textPane.setCaretPosition(0);
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(panel.getFont().deriveFont(16f));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstructionsDialog.this.dispose();
			}
		});
		panel.add(cancelButton);
		
		return panel;
	}
	
	private String getInstructions(String filename) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("<html><body>");
		builder.append(System.lineSeparator());
		
		InputStream is = getClass().getResourceAsStream("/" + filename);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
       
        String line = reader.readLine();
        while (line != null) {
        	builder.append(processLine(line));
        	builder.append(System.lineSeparator());
        	line = reader.readLine();
        }
		reader.close();
		
		builder.append("</body></html>");
		builder.append(System.lineSeparator());

		return builder.toString();
	}
	
	private StringBuilder processLine(String line) {
		StringBuilder builder = new StringBuilder();
		
		if (line.isBlank()) {
			return builder;
		}
		
		if (line.startsWith("###")) {
			builder.append("<h3 style=\"font-family: Arial\">");
			builder.append(line.substring(3).trim());
			builder.append("</h3>");
			return builder;
		}
		
		if (line.startsWith("##")) {
			builder.append("<h2 style=\"font-family: Arial\">");
			builder.append(line.substring(2).trim());
			builder.append("</h2>");
			return builder;
		}
		
		if (line.startsWith("#")) {
			builder.append("<h1 style=\"font-family: Arial\">");
			builder.append(line.substring(1).trim());
			builder.append("</h1>");
			return builder;
		}
		
		builder.append("<p style=\"font-family: Arial; font-size: 16px\">");
		builder.append(line);
		builder.append("</p>");
		return builder;
	}

}
