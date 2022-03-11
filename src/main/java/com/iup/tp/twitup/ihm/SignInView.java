package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.session.ISessionObserver;
import com.iup.tp.twitup.observer.session.ISignedInObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignInView extends ViewBase implements ISignedInObserver {

	private final List<INavigationObserver> navigationObservers;
	private final List<ISessionObserver> sessionObservers;

	private final JTextField tag;
	private final JPasswordField paswword;
	private final JLabel infos;

	public SignInView() {
		super();

		navigationObservers = new ArrayList<>();
		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Connexion");
		title.setFont(title.getFont().deriveFont(48.0F));

		JLabel tagText = new JLabel("Tag :", JLabel.CENTER);
		tagText.setFont(title.getFont().deriveFont(14.0F));
		tag = new JTextField(20);

		JLabel passwordText = new JLabel("Mot de passe :", JLabel.CENTER);
		passwordText.setFont(title.getFont().deriveFont(14.0F));
		paswword = new JPasswordField(20);

		JButton signIn = new JButton("Se connecter");
		signIn.addActionListener((e) -> doLogin(tag.getText(), String.valueOf(paswword.getPassword())));

		JButton cancel = new JButton("Créer un compte");
		cancel.addActionListener((e) -> doCancel());

		infos = new JLabel();

		add(title, new GridBagConstraints(0, 0, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(tagText, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(tag, new GridBagConstraints(1, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(passwordText, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(paswword, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(signIn, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(cancel, new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(infos, new GridBagConstraints(0, 4, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void doLogin(String tag, String password) {
		sessionObservers.forEach(res -> res.notifySignIn(tag, password));
	}

	private void doCancel() {
		navigationObservers.forEach(INavigationObserver::loadSignUpView);
	}

	@Override
	public void notifySuccess() {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	@Override
	public void notifyError(String tag) {
		infos.setText("Couple login/mot de passe introuvable");
	}

	@Override
	public void notifyWrongInputs() {
		infos.setText("Les champs ne sont pas correctement remplis");
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}
}
