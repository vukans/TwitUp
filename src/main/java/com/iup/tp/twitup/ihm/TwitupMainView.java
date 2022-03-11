package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.session.IUserStateObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame implements IUserStateObserver {

	private final List<INavigationObserver> navigationObservers;

	private final JMenuBar bar;

	private final JMenu firstMenu;

	private final JMenuItem welcome;
	private final JMenuItem createTwit;
	private final JMenuItem listTwit;
	private final JMenuItem about;
	private final JMenuItem profil;
	private final JMenuItem signIn;
	private final JMenuItem signUp;
	private final JMenuItem logOut;
	private final JMenuItem exit;

	private final JMenu secondMenu;

	private final JMenuItem profiles;

	public TwitupMainView() {
		super("TwitUp");

		navigationObservers = new ArrayList<>();

		bar = new JMenuBar();

		firstMenu = new JMenu("Menu");

		welcome = new JMenuItem("Welcome"); // Welcome
		welcome.addActionListener((e) -> notifyWelcomeViewObservers());

		createTwit = new JMenuItem("Créer un twit"); // Welcome
		createTwit.addActionListener((e) -> notifyCreateTwitViewObservers());

		listTwit = new JMenuItem("Consulter les twits"); // Welcome
		listTwit.addActionListener((e) -> notifyListTwitViewObservers());

		about = new JMenuItem("About"); // About
		about.addActionListener((e) -> notifyAboutViewObservers());

		profil = new JMenuItem("Profil"); // Profil
		profil.addActionListener((e) -> notifyProfilObservers());
		profil.setVisible(false);

		signIn = new JMenuItem("Connexion"); // Connexion
		signIn.addActionListener((e) -> notifySignInObservers());

		signUp = new JMenuItem("Inscription"); // Inscription
		signUp.addActionListener((e) -> notifySignUpObservers());

		logOut = new JMenuItem("Déconnexion"); // Déconnexion
		logOut.addActionListener((e) -> notifyLogOutObservers());
		logOut.setVisible(false);

		exit = new JMenuItem("Quitter"); // Quitter
		exit.addActionListener((e) -> notifyExitObservers());

		firstMenu.add(welcome);
		firstMenu.add(createTwit);
		firstMenu.add(listTwit);
		firstMenu.add(about);
		firstMenu.add(profil);
		firstMenu.add(signIn);
		firstMenu.add(signUp);
		firstMenu.add(logOut);
		firstMenu.add(exit);

		bar.add(firstMenu);

		secondMenu = new JMenu("Menu 2");

		profiles = new JMenuItem("Liste des utilisateurs");
		profiles.addActionListener((e) -> notifyProfilesViewObservers());

		secondMenu.add(profiles);

		bar.add(secondMenu);

		setJMenuBar(bar);

		setSize(950, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	public void showGUI() {
		setVisible(true);
	}

	public String askDirectory() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choisir un dossier");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = chooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			return chooser.getSelectedFile().toString();
		} else {
			notifyExitObservers();
			return null;
		}
	}

	private void notifyWelcomeViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	private void notifyListTwitViewObservers() {navigationObservers.forEach(INavigationObserver::loadListTwitView);}

	private void notifyCreateTwitViewObservers() {navigationObservers.forEach(INavigationObserver::loadCreateTwitView);}

	private void notifyAboutViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadAboutView);
	}

	private void notifySignInObservers() {
		navigationObservers.forEach(INavigationObserver::loadSignInView);
	}

	private void notifyLogOutObservers() {
		navigationObservers.forEach(INavigationObserver::loadDisconnectView);
	}

	private void notifySignUpObservers() {
		navigationObservers.forEach(INavigationObserver::loadSignUpView);
	}

	private void notifyProfilObservers() {
		navigationObservers.forEach(INavigationObserver::loadProfilView);
	}

	private void notifyExitObservers() {
		navigationObservers.forEach(INavigationObserver::exit);
	}

	private void notifyProfilesViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadProfilesView);
	}

	@Override
	public void notifyIsConnected() {
		profil.setVisible(true);
		signIn.setVisible(false);
		signUp.setVisible(false);
		logOut.setVisible(true);
	}

	@Override
	public void notifyIsDisconnected() {
		profil.setVisible(false);
		signIn.setVisible(true);
		signUp.setVisible(true);
		logOut.setVisible(false);
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}
}
