/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		label = new JLabel("name");
		field = new JTextField(20);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		add(label,SOUTH);
		add(field,SOUTH);
		add(graph,SOUTH);
		add(clear,SOUTH);
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
	}
	
	private JLabel label;
	private JTextField field;
	private JButton graph;
	private JButton clear;
}
