package com.iup.tp.twitup.ihm;


import com.iup.tp.twitup.datamodel.ConnectedUser;
import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.twit.ITwitCreatedObserver;
import com.iup.tp.twitup.observer.twit.ITwitObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTwitView extends ViewBase implements ITwitCreatedObserver {

    private final JTextField twit;
    private final JLabel infos;
    private final ConnectedUser connectedUser;
    private final List<ITwitObserver> twitObserverList;
    private final List<INavigationObserver> navigationObservers;

    public CreateTwitView(ConnectedUser connectedUser) {

        super();
        navigationObservers = new ArrayList<>();
        twitObserverList= new ArrayList<>();
        this.connectedUser=connectedUser;

        infos = new JLabel();
        setLayout(new GridBagLayout());
        JLabel title = new JLabel("créer un twit", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(48.0F));

        JLabel twitText = new JLabel("Contenu du twit :", JLabel.CENTER);
        twitText.setFont(title.getFont().deriveFont(14.0F));
        twit = new JTextField(20);

        JButton create = new JButton("Poster");
        create.addActionListener((e) -> doCreateTwit());


        add(title);
        add(twitText);
        add(twit);
        add(create);

        add(infos, new GridBagConstraints(0, 4, 2, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(15, 15, 15, 15),
                0, 0));

    }

    private void doCreateTwit() {
        twitObserverList.forEach(res -> res.notifyCreateTwit(connectedUser.getConnectedUser(),twit.getText()));
    }

    @Override
    public void notifySuccess() {
        this.navigationObservers.forEach(INavigationObserver::loadListTwitView);
    }

    @Override
    public void notifyTwitTooLong() {
        infos.setText("oups, votre twit est trop long");
    }

    @Override
    public void notifyUnknownUser() {
        infos.setText("Vous ne pouvez pas envoyer de twit tant que vous n'êtes pas connectés");
    }


    public void addNavigationObserver(INavigationObserver navigationObserver) {
        navigationObservers.add(navigationObserver);
    }
    public void addTwitObserver(ITwitObserver twitObserver){
        this.twitObserverList.add(twitObserver);
    }

}
