package conLSP;

public class VipMember extends Member implements TopMember{
    public VipMember(String nombre) {
        super(nombre);
    }
    @Override
    public void joinTournament() {
        System.out.printf("Miembro %s se une al torneo\n", getNombre());
    }
    @Override
    public void organizeTournament() {
        System.out.printf("Miembro %s organiza el torneo\n", getNombre());
    }
}
