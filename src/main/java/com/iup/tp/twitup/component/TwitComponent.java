package com.iup.tp.twitup.component;

import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TwitComponent extends JPanel {

    public TwitComponent(String text, User twiter, long emissionDate) {
        super(new GridBagLayout());

        JLabel myTwitText = new JLabel("contenu: " + text, JLabel.CENTER);
        JLabel myTwiter = new JLabel("posté par: "+ twiter.getUserTag(), JLabel.CENTER);
        JLabel myTwitDate = new JLabel("le: " + new Date(emissionDate), JLabel.CENTER);

        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(myTwitText ,new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));
        add(myTwiter, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTHWEST,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));
        add(myTwitDate, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTHEAST,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),
                0, 0));

    }
}
