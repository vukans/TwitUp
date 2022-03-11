package com.iup.tp.twitup.observer.profiles;

import com.iup.tp.twitup.datamodel.Listprofil;
import com.iup.tp.twitup.datamodel.User;

import java.util.Set;

public interface IGetProfilesObserver {

	void notifyGotProfiles(Listprofil users);

}
