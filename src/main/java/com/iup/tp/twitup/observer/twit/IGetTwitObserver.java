package com.iup.tp.twitup.observer.twit;

import com.iup.tp.twitup.datamodel.ListTwits;
import com.iup.tp.twitup.datamodel.Twit;

public interface IGetTwitObserver {

    void notifyGotTwit(ListTwits listTwits);

}
