package sinLSP;

public class FreeMember extends Member {
    public FreeMember(String nombre) {
        super(nombre);
    }
    @Override
    public void joinTournament() {
        System.out.printf("Miembro %s se une al torneo\n", getNombre());
    }

    // No es compatible con LSP:
    @Override
    public void organizeTournament() {
        System.out.printf("Miembro %s no puede organizar torneos!\n", getNombre());
    }
}
