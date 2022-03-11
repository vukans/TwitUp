package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.session.ISessionObserver;
import com.iup.tp.twitup.observer.session.ISignedUpObserver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SignUpView extends ViewBase implements ISignedUpObserver {

	private final List<ISessionObserver> sessionObservers;

	private File avatar;

	private final JTextField tag;
	private final JTextField username;
	private final JPasswordField password;
	private final JLabel info;

	public SignUpView() {
		super();

		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Inscription");
		title.setFont(title.getFont().deriveFont(48.0F));

		JLabel TagText = new JLabel("Tag :", JLabel.CENTER);
		TagText.setFont(title.getFont().deriveFont(14.0F));
		tag = new JTextField(20);

		JLabel usernameText = new JLabel("nom d'utilisateur :", JLabel.CENTER);
		usernameText.setFont(title.getFont().deriveFont(14.0F));
		username = new JTextField(20);

		JLabel passwordText = new JLabel("Mot de passe :", JLabel.CENTER);
		passwordText.setFont(title.getFont().deriveFont(14.0F));
		password = new JPasswordField(20);

		JButton create = new JButton("S'inscrire");
		create.addActionListener((e) -> doRegisterUser(tag.getText(), username.getText(), String.valueOf(password.getPassword()), avatar));

		JButton avatar = new JButton("Sélectionner un avatar");
		avatar.addActionListener((e) -> openFileChooser());

		info = new JLabel();

		add(title, new GridBagConstraints(0, 0, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(TagText, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(tag, new GridBagConstraints(1, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(usernameText, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(username, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(passwordText, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(password, new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(avatar, new GridBagConstraints(0, 4, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(create, new GridBagConstraints(0, 5, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(info, new GridBagConstraints(0, 6, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void openFileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choisir avatar");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			avatar = chooser.getSelectedFile();
		}
	}

	private void doRegisterUser(String tag, String username, String password, File avatar) {
		sessionObservers.forEach(res -> res.notifySignUp(tag, username, password, avatar));
	}

	@Override
	public void notifyUserCreated(String tag) {
		info.setText("L'utilisateur @" + tag + " a bien été crée");
	}

	@Override
	public void notifyUserAlreadyExists(String tag) {
		info.setText("L'utilisateur @" + tag + " existe déjà");
	}

	@Override
	public void notifyWrongInputs() {
		info.setText("Les champs ne sont pas correctement remplis");
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}
}
