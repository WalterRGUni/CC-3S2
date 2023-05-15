package sinLSP;

public class PremiumMember extends Member {
    public PremiumMember(String nombre) {
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
