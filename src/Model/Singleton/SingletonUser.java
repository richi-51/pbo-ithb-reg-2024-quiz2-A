package Model.Singleton;

import Model.Users;

public class SingletonUser {
    private static SingletonUser instance;
    private Users loggedInUser;

    private SingletonUser() {}

    public static SingletonUser getInstance() {
        if (instance == null) {
            instance = new SingletonUser();
        }
        return instance;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Users loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
