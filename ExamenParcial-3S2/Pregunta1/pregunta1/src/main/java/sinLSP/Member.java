package sinLSP;

public abstract class Member {
    private final String nombre;

    public Member(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void joinTournament();
    public abstract void organizeTournament();
}

