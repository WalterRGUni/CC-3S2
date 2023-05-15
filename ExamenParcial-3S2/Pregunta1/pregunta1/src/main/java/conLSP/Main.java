package conLSP;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TopMember> miembrosTop = List.of(
                new PremiumMember("Abejita Azul"),
                new VipMember("Kaperucita Feliz")
        );
        for(TopMember miembro : miembrosTop){
            miembro.organizeTournament();
        }

        List<Member> miembros = List.of(
                new PremiumMember("Abejita Azul"),
                new VipMember("Kaperucita Feliz"),
                new FreeMember("Inspectora Motita")
                );
        for(Member miembro : miembros){
            miembro.joinTournament();
        }

    }
}
