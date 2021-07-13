//T18

package commons;

import org.openqa.selenium.WebDriver;

import actions.homeActions;
import actions.loginActions;


public class ManageDriver {
	
	
	public static loginActions login;
	public static homeActions home;
	public static loginActions GoToLogin (WebDriver driver) {
		if (login == null) {
			login = new loginActions(driver);
		}
		return login;
	}
	
	public static homeActions GoToHomepage (WebDriver driver) {
		if (home == null) {
			home = new homeActions(driver);
		}
		return home;
	}


}
