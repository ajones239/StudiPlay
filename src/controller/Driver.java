package controller;

import view.LoginFrame;
import controller.Manager;

public class Driver {

	// Main method to execute.
	public static void main(String[] args) {

		if (args.length > 0) {
			String host = "127.0.0.1";
			String user = "root";
			String pass = "1179215aA";
			for (String arg: args) {
				if (arg.substring(0, 4).equals("hostname:"))
					host = arg.split(":")[1];
				else if (arg.substring(0, 4).equals("user"))
					user = arg.split(":")[1];
				else if (arg.substring(0,4).equals("password"))
					pass = arg.split(":")[1];
				else
					continue;
			}
			Manager manager = Manager.getInstance();
			manager.setDatabaseCredentials(host, user, pass);
		}
		new LoginFrame().setVisible(true);
		
	}

}
