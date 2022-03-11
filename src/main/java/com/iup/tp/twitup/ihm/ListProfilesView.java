package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.component.ProfilComponent;
import com.iup.tp.twitup.datamodel.Listprofil;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.profiles.IGetProfilesObserver;
import com.iup.tp.twitup.observer.profiles.IProfilesObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListProfilesView extends ViewBase implements IGetProfilesObserver {

	private final List<IProfilesObserver> profilesObservers;
	private final JPanel ProfilesContainer;

	private int x = 1;

	public ListProfilesView() {
		super();

		profilesObservers = new ArrayList<>();

		setLayout(new GridBagLayout());
		this.ProfilesContainer = new JPanel(new GridBagLayout());

		JScrollPane scroller = new JScrollPane(ProfilesContainer);
		scroller.getVerticalScrollBar().setUnitIncrement(10);
		this.add(scroller, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	public void showProfiles() {
		profilesObservers.forEach(IProfilesObserver::notifyGetProfiles);
	}

	@Override
	public void notifyGotProfiles(Listprofil users) {
		for (User user:users.getListUsers()){
			ProfilComponent profilComponent = new ProfilComponent(user.getUserTag(), user.getName(), user.getFollows().size(), user.getAvatarPath());

			this.ProfilesContainer.add(profilComponent, new GridBagConstraints(0, x++, 1, 1, 1, 1,
					GridBagConstraints.CENTER,
					GridBagConstraints.BOTH,
					new Insets(15, 15, 15, 15),
					0, 0));
		}
		revalidate();
		repaint();
	}

	public void addProfilesObserver(IProfilesObserver profilesObserver) {
		profilesObservers.add(profilesObserver);
	}
}
