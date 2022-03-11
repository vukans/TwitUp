package com.iup.tp.twitup.ihm;

import javax.swing.*;

public class WelcomeView extends ViewBase {

	public WelcomeView() {
		super();

		JLabel title = new JLabel("Bienvenue sur twitUp!", JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(48.0F));
		add(title);
	}
}
