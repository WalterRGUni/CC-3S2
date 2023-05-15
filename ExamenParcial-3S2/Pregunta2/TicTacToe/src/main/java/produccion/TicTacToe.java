package produccion;

public class TicTacToe {
    boolean[][] casillaOcupada = new boolean[3][3];
    public void jugar(int x, int y) {
        verificarJugadaDentroTablero(x,y);
        verificarCasillaOcupada(x, y);
    }

    public void verificarJugadaDentroTablero(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new RuntimeException();
        }
    }

    public void verificarCasillaOcupada(int x, int y) {
        if(casillaOcupada[x-1][y-1]){
            throw new RuntimeException();
        }
        casillaOcupada[x-1][y-1] = true;
    }

    public String proximoJugador() {
        return "X";
    }
}