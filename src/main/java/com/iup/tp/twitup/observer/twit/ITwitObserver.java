package com.iup.tp.twitup.observer.twit;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ListTwitView;

public interface ITwitObserver {

    void notifyCreateTwit(User user, String twit);
    
    void notifyGetTwit();

    void addGetTwitsObserver(ListTwitView listTwitView);

}
