import java.util.ArrayList;
import java.util.List;

public class SistemaReservas {
    List<Integer> horasReservadas = new ArrayList<>();

    public void reservar(int hora) {
        if(hora >= 8 && hora <= 20){
            if(!horasReservadas.contains(hora)){
                horasReservadas.add(hora);
            }
        }
    }

    public List<Integer> getHorasReservadas() {
        return horasReservadas;
    }
}
