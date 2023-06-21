import java.util.List;

public class SistemaReservaAulas {
    List<Aula> aulasExistentes;
    List<Reserva> reservas;

    public SistemaReservaAulas(List<Aula> aulasExistentes) {
        this.aulasExistentes = aulasExistentes;
    }

    public String enumerarAulasExistentes() {
        StringBuilder resp = new StringBuilder();
        for (Aula aula : aulasExistentes) {
            resp.append(aula.getID()).append("-");
        }
        return resp.toString();
    }

    public String enumerarAulasDisponibles(String dia, int hora) {
        StringBuilder resp = new StringBuilder();
        for (Aula aula : aulasExistentes) {
            if (!reservas.contains(new Reserva(aula, dia, hora))) {
                resp.append(aula.getID()).append("-");
            }
        }
        return resp.toString();
    }


}
