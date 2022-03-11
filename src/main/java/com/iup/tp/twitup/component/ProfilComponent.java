package com.iup.tp.twitup.component;

import javax.swing.*;
import java.awt.*;

public class ProfilComponent extends JPanel {

	public ProfilComponent(String tag, String name, int follows, String avatar) {
		super();

		ImageIcon imageIcon = new ImageIcon(new ImageIcon(avatar).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel text = new JLabel("@" + tag + " " + name + " followers : " + follows, imageIcon, JLabel.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.setBackground(Color.WHITE);

		add(text);
	}
}
