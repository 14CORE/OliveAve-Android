package cs246.oliveave;

public interface LoginView {
    String getUsername();
    String getPassword();

    void showUsernameError(int resId);

    void showPasswordError(int pasId);

    void startMainActivity();

    void showLoginError(int logId);
}
