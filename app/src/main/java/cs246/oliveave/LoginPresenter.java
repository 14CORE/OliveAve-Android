package cs246.oliveave;

public class LoginPresenter {
    private LoginView view;
    private LoginService service;

    public LoginPresenter(LoginView view, LoginService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginClicked() {
        String username = view.getUsername();
        if (username.isEmpty()) {
            return;
        }

        String password = view.getPassword();
        if (password.isEmpty()) {
           return;
        }

        boolean loginSucceed = service.login(username, password);
        if (loginSucceed) {
            view.startMainActivity();
            return;
        }
    }
}
