package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import com.sun.prism.paint.Color;

import client.clientController;
import model.Box;

public class UiListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton) e.getSource()).getText().equals("Connexion") && clientController.isGameSet()==true){
			clientController.connect();
		}
	}
}
