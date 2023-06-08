package ejemplos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WelcomeEmailTest {
    @Mock
    private MailServer mailServer;
    @Test
    public void sendsWelcomeEmail() {
        var notifications
                = new UserNotifications( mailServer );
        notifications.welcomeNewUser("test@example.com");
        verify(mailServer).sendEmail("test@example.com",
                "Bienvenido!",
                "Bienvenido a tu cuenta");
    }
}
