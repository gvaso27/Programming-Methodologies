
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	private FacePamphletDatabase DB;
	private FacePamphletProfile profile;
	private FacePamphletCanvas canvas;
	private GImage image;

	private JButton Add;
	private JButton Delete;
	private JButton Lookup;
	private JButton ChangeStatus;
	private JButton ChangePicture;
	private JButton AddFriend;
	private JLabel name;
	private JTextField nameField;
	private JTextField status;
	private JTextField picture;
	private JTextField friend;

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	public void init() {
		canvas = new FacePamphletCanvas();
		DB = new FacePamphletDatabase();
		add(canvas);
		addInteractors();
		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {
		String s = nameField.getText();
		String stat = status.getText();
		String pic = picture.getText();
		String fri = friend.getText();

		// clicking Add button
		if (e.getSource() == Add && !s.equals("")) {
			if (!DB.containsProfile(s)) {
				FacePamphletProfile currentpro = new FacePamphletProfile(s);
				profile = currentpro;
				DB.addProfile(profile);
				canvas.displayProfile(profile);
				canvas.showMessage("Add: new profile: " + profile.toString());
			} else {
				FacePamphletProfile currentpro = new FacePamphletProfile(s);
				profile = currentpro;
				canvas.displayProfile(profile);
				canvas.showMessage("This profile already exists: " + profile.toString());
			}
		}
		// clicking Delete button
		if (e.getSource() == Delete && !s.equals("")) {
			if (DB.containsProfile(s)) {
				FacePamphletProfile currentpro = DB.getProfile(s);
				profile = currentpro;
				DB.deleteProfile(s, profile);
				canvas.updateDisplay();
				canvas.showMessage("profile named " + s + " was deleted");
				profile = null;
			} else {
				canvas.showMessage("profile with name: " + s + " can not be found");
			}
		}
		// clicking Lookup button
		if (e.getSource() == Lookup && !s.equals("")) {
			if (DB.containsProfile(s)) {
				FacePamphletProfile currentpro = DB.getProfile(s);
				profile = currentpro;
				canvas.displayProfile(profile);
				canvas.showMessage("Lookup: " + profile.toString());
			} else {
				canvas.showMessage("profile with name: " + s + " can not be found");
				profile = null;
			}
		}
		//clicking Change Status button or "enter" on keyboard
		if (e.getSource() == ChangeStatus && !stat.equals("") || e.getSource() == status) {
			if (profile != null) {
				profile.setStatus(stat);
				canvas.displayProfile(profile);
			} else {
				canvas.showMessage("Please choose profile first");
			}
		}
		//clicking Change Picture button or "enter" on keyboard
		if (e.getSource() == ChangePicture && !pic.equals("") || e.getSource() == picture) {
			if (profile != null) {
				try {
					BufferedReader rd = new BufferedReader(new FileReader(pic));
					image = new GImage("" + pic + "");
				} catch (FileNotFoundException e1) {
					canvas.showMessage("please try another name");
				}
				if (image != null) {
					profile.setImage(image);
					canvas.displayProfile(profile);
					canvas.showMessage("profile picture was updated");
				}
			} else {
				canvas.showMessage("Please choose profile first");
			}
		}
		//clicking Add Friend button or "enter" on keyboard
		if (e.getSource() == AddFriend && !fri.equals("") || e.getSource() == friend) {
			if (profile != null) {
				if (DB.containsProfile(fri)) {
					if (profile.addFriend(fri)) {
						FacePamphletProfile theProfile = DB.getProfile(fri);
						theProfile.addFriend(profile.getName());
						canvas.displayProfile(profile);
						canvas.showMessage(profile.getName() + " and " + theProfile.getName() + " are now friends");
					}
				} else {
					canvas.showMessage("profile with name: " + fri + " can not be found");
				}
			} else {
				canvas.showMessage("Please choose profile first");
			}
		}
	}

	public void addInteractors() {
		Add = new JButton("Add");
		Delete = new JButton("Delete");
		Lookup = new JButton("Lookup");
		ChangeStatus = new JButton("Change Status");
		ChangePicture = new JButton("Change Picture");
		AddFriend = new JButton("Add Friend");
		name = new JLabel("name");
		nameField = new JTextField(TEXT_FIELD_SIZE);
		status = new JTextField(TEXT_FIELD_SIZE);
		status.addActionListener(this);
		picture = new JTextField(TEXT_FIELD_SIZE);
		picture.addActionListener(this);
		friend = new JTextField(TEXT_FIELD_SIZE);
		friend.addActionListener(this);
		add(name, NORTH);
		add(nameField, NORTH);
		add(Add, NORTH);
		add(Delete, NORTH);
		add(Lookup, NORTH);
		add(status, WEST);
		add(ChangeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(picture, WEST);
		add(ChangePicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friend, WEST);
		add(AddFriend, WEST);
	}

}
