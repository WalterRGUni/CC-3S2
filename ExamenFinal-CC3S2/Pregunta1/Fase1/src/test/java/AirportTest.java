import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AirportTest {
    @Test
    public void pruebaVueloEconomico() {
        Flight economyFlight = new Flight("1", "Economica");
        Passenger passengerRegular = new Passenger("Renato", false);
        Passenger passengerVip = new Passenger("Victor", true);

        economyFlight.addPassenger(passengerRegular);
        assertThat(economyFlight.getPassengersList()).contains(passengerRegular);

        economyFlight.addPassenger(passengerVip);
        assertThat(economyFlight.getPassengersList()).contains(passengerVip);

        economyFlight.removePassenger(passengerRegular);
        assertThat(economyFlight.getPassengersList()).doesNotContain(passengerRegular);

        economyFlight.removePassenger(passengerVip);
        assertThat(economyFlight.getPassengersList()).contains(passengerVip);
    }

    @Test
    public void pruebaVueloNegocios() {
        Flight businessFlight = new Flight("1", "Negocios");
        Passenger passengerRegular = new Passenger("Renato", false);
        Passenger passengerVip = new Passenger("Victor", true);

        businessFlight.addPassenger(passengerRegular);
        assertThat(businessFlight.getPassengersList()).doesNotContain(passengerRegular);

        businessFlight.addPassenger(passengerVip);
        assertThat(businessFlight.getPassengersList()).contains(passengerVip);

        businessFlight.removePassenger(passengerVip);
        assertThat(businessFlight.getPassengersList()).contains(passengerVip);
    }
}
