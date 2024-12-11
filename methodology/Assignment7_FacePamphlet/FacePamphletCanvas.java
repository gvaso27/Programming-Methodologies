
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

	GLabel M = new GLabel("");
	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		M.setLabel(msg);
		M.setFont(MESSAGE_FONT);
		double X = (getWidth() / 2) - (M.getWidth() / 2);
		double Y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		add(M, X, Y);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		
		updateDisplay();

		// adding profile name
			GLabel prname = new GLabel(profile.getName());
			prname.setFont(PROFILE_NAME_FONT);
			double X = LEFT_MARGIN;
			double Y = TOP_MARGIN + prname.getHeight();
			prname.setColor(Color.BLUE);
			add(prname, X, Y);

		// adding picture
		if (profile.getImage() == null) {
			GRect picrect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			double X1 = LEFT_MARGIN;
			double Y1 = Y + IMAGE_MARGIN;
			add(picrect, X1, Y1);
			GLabel noimg = new GLabel("No Image");
			noimg.setFont(PROFILE_IMAGE_FONT);
			double X2 = LEFT_MARGIN + (IMAGE_WIDTH / 2) - (noimg.getWidth() / 2);
			double Y2 = Y1 + (IMAGE_HEIGHT / 2) + (noimg.getHeight() / 2);
			add(noimg, X2, Y2);
		} else {
			GImage picture = profile.getImage();
			double X1 = LEFT_MARGIN;
			double Y1 = Y + IMAGE_MARGIN;
			picture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(picture, X1, Y1);
		}

		// adding status
		if (profile.getStatus() == null) {
			GLabel nostat = new GLabel("No current status");
			nostat.setFont(PROFILE_STATUS_FONT);
			double X1 = LEFT_MARGIN;
			double Y1 = Y + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + nostat.getHeight();
			add(nostat, X1, Y1);
		} else {
			String stat = profile.getName() + " is " + profile.getStatus();
			GLabel disstat = new GLabel(stat);
			disstat.setFont(PROFILE_STATUS_FONT);
			double X1 = LEFT_MARGIN;
			double Y1 = Y + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + disstat.getHeight();
			add(disstat, X1, Y1);
		}

		// adding friends
		GLabel friends = new GLabel("Friends");
		double X1 = getWidth() / 2;
		double Y1 = Y + IMAGE_MARGIN;
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friends, X1, Y1);
		Iterator<String> friendsIterator = profile.getFriends();
		while (friendsIterator.hasNext()) {
			String lfriend = friendsIterator.next();
			GLabel friendname = new GLabel(lfriend);
			double Y2 = Y1 + friendname.getHeight();
			Y1 = Y2;
			friendname.setFont(PROFILE_FRIEND_FONT);
			add(friendname, X1, Y2);
		}
	}
	
	public void updateDisplay() {
		M.setLabel("");;
		removeAll();
	}

}
