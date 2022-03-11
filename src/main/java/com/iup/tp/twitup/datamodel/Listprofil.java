package com.iup.tp.twitup.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe du modèle représentant la liste des profils
 *
 * @author S.Theon
 */
public class Listprofil {
    protected List<User> ListUsers;

    public Listprofil() {
        ListUsers = new ArrayList<>();
    }

    public List<User> getListUsers() {
        return ListUsers;
    }

    public void setListUsers(List<User> listUsers) {
        ListUsers = listUsers;
    }
}
