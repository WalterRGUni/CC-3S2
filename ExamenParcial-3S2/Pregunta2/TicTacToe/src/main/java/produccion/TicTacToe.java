package produccion;

public class TicTacToe {
    private String proximoJugador = "X";
    private String jugadorActual = "";
    private String ganador = "No hay ganador";
    boolean[][] casillaOcupada = new boolean[3][3];
    private String[][] tablero = new String[3][3];

    public void jugar(int x, int y) {
        jugadorActual = proximoJugador();
        verificarJugadaDentroTablero(x, y);
        verificarCasillaOcupada(x, y);
        realizarJugada(x, y);
        if(!esGanador(x, y) && !esEmpate()){
            cambiarTurno();
        }
    }

    public boolean esGanador(int x, int y) {
        // Verifica si gana con línea horizontal:
        if ((tablero[x - 1][0] == tablero[x - 1][1]) && (tablero[x - 1][1] == tablero[x - 1][2])) {
            ganador = jugadorActual;
            return true;
        }
        // Verifica si gana con línea vertical:
        if ((tablero[0][y - 1] == tablero[1][y - 1]) && (tablero[1][y - 1] == tablero[2][y - 1])) {
            ganador = jugadorActual;
            return true;
        }
        // Verifica si gana con línea diagonal:
        // Diagonal superior izquierda a inferior derecha:
        if ((tablero[0][0] == tablero[1][1]) && (tablero[1][1] == tablero[2][2])
                && tablero[1][1] != null) {
            ganador = jugadorActual;
            return true;
        }
        // Diagonal inferior izquierda a superior derecha
        if ((tablero[0][2] == tablero[1][1]) && (tablero[1][1] == tablero[2][0])
                && tablero[1][1] != null) {
            ganador = jugadorActual;
            return true;
        }
        return false;
    }

    public void realizarJugada(int x, int y) {
        tablero[x - 1][y - 1] = jugadorActual;
    }

    public void cambiarTurno() {
        if (proximoJugador.equals("X")) {
            proximoJugador = "O";
        } else {
            proximoJugador = "X";
        }
    }

    public String getGanador() {
        return ganador;
    }

    public void verificarJugadaDentroTablero(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new RuntimeException("Jugada fuera del tablero");
        }
    }

    public void verificarCasillaOcupada(int x, int y) {
        if (casillaOcupada[x - 1][y - 1]) {
            throw new RuntimeException("Casilla está ocupada");
        }
        casillaOcupada[x - 1][y - 1] = true;
    }

    public String proximoJugador() {
        return proximoJugador;
    }

    public boolean esEmpate() {
        if(!hayCasillasVacias() && ganador == "No hay ganador") {
            return true;
        }
        return false;
    }

    boolean hayCasillasVacias() {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(tablero[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }
}