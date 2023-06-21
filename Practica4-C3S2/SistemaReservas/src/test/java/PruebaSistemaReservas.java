import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PruebaSistemaReservas {
    @Test
    public void reservaValida() {
        SistemaReservas sistemaReservas = new SistemaReservas();
        sistemaReservas.reservar(10);

        List<Integer> horasReservadasEsperadas = new ArrayList<>();
        horasReservadasEsperadas.add(10);

        List<Integer> horasReservadas = sistemaReservas.getHorasReservadas();

        assertThat(horasReservadas).isEqualTo(horasReservadasEsperadas);
    }
    @Test
    public void reservaValoresFueraRango() {
        SistemaReservas sistemaReservas = new SistemaReservas();
        sistemaReservas.reservar(-5);

        List<Integer> horasReservadasEsperadas = new ArrayList<>();

        List<Integer> horasReservadas = sistemaReservas.getHorasReservadas();

        assertThat(horasReservadas).isEqualTo(horasReservadasEsperadas);
    }
    @Test
    public void reservaValoresRepetidos() {
        SistemaReservas sistemaReservas = new SistemaReservas();
        sistemaReservas.reservar(10);
        sistemaReservas.reservar(10);
        sistemaReservas.reservar(10);

        List<Integer> horasReservadasEsperadas = new ArrayList<>();
        horasReservadasEsperadas.add(10);

        List<Integer> horasReservadas = sistemaReservas.getHorasReservadas();

        assertThat(horasReservadas).isEqualTo(horasReservadasEsperadas);
    }
}