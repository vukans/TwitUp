package com.iup.tp.twitup.core;


import com.iup.tp.twitup.datamodel.*;
import com.iup.tp.twitup.ihm.ListTwitView;
import com.iup.tp.twitup.observer.session.ISignedInObserver;
import com.iup.tp.twitup.observer.twit.IGetTwitObserver;
import com.iup.tp.twitup.observer.twit.ITwitCreatedObserver;
import com.iup.tp.twitup.observer.twit.ITwitObserver;

import java.util.ArrayList;
import java.util.List;

public class TwitController implements ITwitObserver {
    private final EntityManager entityManager;
    private final IDatabase database;
    private final List<ITwitCreatedObserver> TwitCreatedObserver;
    private final List<IGetTwitObserver> getTwitObserver;


    public TwitController(EntityManager entityManager, IDatabase database) {
        this.entityManager = entityManager;
        this.database = database;
        this.getTwitObserver = new ArrayList<>();
        this.TwitCreatedObserver= new ArrayList<>();
    }
    @Override
    public void notifyCreateTwit(User user, String twit) {
        if (user==null){
            TwitCreatedObserver.forEach(ITwitCreatedObserver::notifyUnknownUser);
        }else{
            if (twit.length()<=250){
                Twit myTwit= new Twit(user,twit);
                entityManager.sendTwit(myTwit);
                TwitCreatedObserver.forEach(ITwitCreatedObserver::notifySuccess);
            }else{
                TwitCreatedObserver.forEach(ITwitCreatedObserver::notifyTwitTooLong);
            }
        }
    }
    public void addTwitCreatedObserver(ITwitCreatedObserver TwitCreatedObserver) {
        this.TwitCreatedObserver.add(TwitCreatedObserver);
    }


    @Override
    public void notifyGetTwit() {
        ListTwits listTwits = new ListTwits();
        listTwits.setListTwits(new ArrayList<>(database.getTwits()));
        getTwitObserver.forEach(res -> res.notifyGotTwit(listTwits));

    }
    @Override
    public void addGetTwitsObserver(ListTwitView getTwitObserver) {
        this.getTwitObserver.add(getTwitObserver);
    }
}
