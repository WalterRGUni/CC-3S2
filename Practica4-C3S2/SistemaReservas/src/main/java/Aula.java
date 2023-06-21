import java.util.List;

public class Aula {
    String ID;
    int capacidad;
    List<Equipo> equipos;

    public Aula(String ID, int capacidad, List<Equipo> equipos) {
        this.ID = ID;
        this.capacidad = capacidad;
        this.equipos = equipos;
    }

    public String getID() {
        return ID;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
}