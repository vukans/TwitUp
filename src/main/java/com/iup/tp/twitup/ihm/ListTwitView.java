package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.component.ProfilComponent;
import com.iup.tp.twitup.component.TwitComponent;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.ListTwits;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.twit.IGetTwitObserver;
import com.iup.tp.twitup.observer.twit.ITwitObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListTwitView extends ViewBase implements IGetTwitObserver {

    private final List<ITwitObserver> twitObserverList;
    private final JPanel twitsContainer;



    private int x = 1;

    public ListTwitView() {
        super();
        twitObserverList = new ArrayList<>();

        setLayout(new GridBagLayout());
        this.twitsContainer = new JPanel(new GridBagLayout());

        JScrollPane scroller = new JScrollPane(twitsContainer);
        scroller.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scroller, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(15, 15, 15, 15),
                0, 0));
    }

    public void showTwits() {
        twitObserverList.forEach(ITwitObserver::notifyGetTwit);
    }

    @Override
    public void notifyGotTwit(ListTwits listTwits) {
        for (Twit twit : listTwits.getListTwits()) {
            TwitComponent twitComponent = new TwitComponent(twit.getText(), twit.getTwiter(), twit.getEmissionDate());

            this.twitsContainer.add(twitComponent, new GridBagConstraints(0, x, 1, 1, 0, 0,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH,
                    new Insets(15, 15, 15, 15),
                    0, 0));
            x++;
            revalidate();
            repaint();
        }
    }

    public void addTwitObserver(ITwitObserver profilesObserver) {
        twitObserverList.add(profilesObserver);
    }

}
