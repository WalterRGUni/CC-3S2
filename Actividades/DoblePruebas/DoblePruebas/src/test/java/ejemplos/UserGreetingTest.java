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
    @Mock
    private UserProfiles profiles;

    @Test
    void formatsGreetingWithName() {
        when(profiles.fetchNicknameFor(any()))
                .thenReturn("Kapumota");
        var greeting = new UserGreeting(profiles);
        String actual =
                greeting.formatGreeting(new UserId(""));
        assertThat(actual)
                .isEqualTo("Hola y bienvenido, Kapumota");
    }
}



