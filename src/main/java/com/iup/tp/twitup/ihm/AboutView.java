package com.iup.tp.twitup.ihm;

import javax.swing.*;

public class AboutView extends ViewBase {

	public AboutView(String ExchangeDirectoryPath) {
		super();

		JLabel text = new JLabel("Répertoire base de données: " + ExchangeDirectoryPath, JLabel.CENTER);

		add(text);
	}
}
