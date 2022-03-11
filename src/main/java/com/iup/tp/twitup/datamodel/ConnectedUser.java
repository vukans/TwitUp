package com.iup.tp.twitup.datamodel;

/**
 * Classe du modèle représentant l'utilisateur connecté
 *
 * @author S.Theon
 */
public class ConnectedUser {

    protected final User connectedUser;

    public ConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

}
