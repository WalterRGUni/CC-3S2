package sinLSP;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Member> miembros = List.of(
                new PremiumMember("Abejita Azul"),
                new VipMember("Kaperucita Feliz"),
                new FreeMember("Inspectora Motita")
        );
        for(Member miembro : miembros){
            miembro.joinTournament();
            miembro.organizeTournament(); // No es compatible con LSP
        }
    }
}
