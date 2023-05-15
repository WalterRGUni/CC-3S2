package conLSP;

public class FreeMember extends Member {
    public FreeMember(String nombre) {
        super(nombre);
    }
    @Override
    public void joinTournament() {
        System.out.printf("Miembro %s se une al torneo\n", getNombre());
    }
}

