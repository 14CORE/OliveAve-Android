package cs246.oliveave;
/*
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginView view;
    @Mock
    private LoginService service;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);
    }

    @Test
    public void showErrorMessageUsernameEmpty() throws Exception {
        when(view.getUsername()).thenReturn("");
        presenter.onLoginClicked();

        verify(view).showUsernameError(R.string.username_error);
    }

    @Test
    public void showErrorMessagePasswordEmpty() throws Exception {
        when(view.getUsername()).thenReturn("test@test.com");
        when(view.getPassword()).thenReturn("");
        presenter.onLoginClicked();

        verify(view).showPasswordError(R.string.password_error);
    }

    @Test
    public void shouldStartMainActivity() throws Exception {
        when(view.getUsername()).thenReturn("brunotest@cs246.com");
        when(view.getPassword()).thenReturn("123456");
        when(service.login("brunotest@cs246.com", "123456")).thenReturn(true);
        presenter.onLoginClicked();

        verify(view).startMainActivity();
    }

    @Test
    public void shouldGiveErrorMainActivity() throws Exception {
        when(view.getUsername()).thenReturn("brunotest@cs246.com");
        when(view.getPassword()).thenReturn("123456");
        when(service.login("brunotest@cs246.com", "123456")).thenReturn(false);
        presenter.onLoginClicked();

        verify(view).showLoginError(R.string.login_error);
    }
}
*/