package homeservice;

import util.View;

public class MainHome {

	public static void main(String[] args) throws Exception {
		new MainHome().start();
	}
	
	private LoginView loginview = LoginView.getInstance();
	private HomeView homeview = HomeView.getInstance();

	public int start() throws Exception{
		int view =  View.HOME;
		
		while(true) {
			switch(view) {
			case View.HOME :
				view= homeview.home(); break;
			case View.LOGIN :
				view=loginview.loginview(); break;
			case View.JOIN :
				view=loginview.join();break;
			}
		}
	}
	
	

	
}