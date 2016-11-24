package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import client.clientController;

public class UiListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton) e.getSource()).getText().equals("Connexion")){
			clientController.connect();
		}		
	}
}
