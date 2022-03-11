package com.iup.tp.twitup.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe du modèle représentant la liste des Twits
 *
 * @author S.Theon
 */
public class ListTwits {
    protected List<Twit> ListTwits;

    public ListTwits() {
        this.ListTwits= new ArrayList<>();
    }

    public List<Twit> getListTwits() {
        return ListTwits;
    }

    public void setListTwits(List<Twit> listTwits) {
        ListTwits = listTwits;
    }
}
