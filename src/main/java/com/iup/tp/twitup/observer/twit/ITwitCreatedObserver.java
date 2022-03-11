package com.iup.tp.twitup.observer.twit;

public interface ITwitCreatedObserver {

    void notifySuccess();

    void notifyTwitTooLong();

    void notifyUnknownUser();
}
