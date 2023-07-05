package ejemplos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserGreetingTest {
    private static  final UserId USER_ID  = new UserId(1234);
    @Mock
    private UserProfiles profiles;

    @Test
    void formatsGreetingWithName() {
        when(profiles.fetchNicknameFor(USER_ID))
                .thenReturn("Kapumota");
        var greetings = new UserGreeting(profiles);
        String actual =
                greetings.formatGreeting(USER_ID);
        assertThat(actual)
                .isEqualTo("Hola y bienvenido, Kapumota");
    }
}



