package View;
import Controller.VerifyLogin;
import Model.LogInMenu;

public class MainMenuKTP {
    public void showMenu() {
        new VerifyLogin(new LogInMenu());
    }
}
