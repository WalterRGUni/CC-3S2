package produccion;

/**
 * Representa un juego donde uno de los jugadores puede ser la computadora
 */
public interface AutoJuego {

  /**
   * @param jugador Color del jugador (Azul o Rojo)
   * @return el tipo de jugador (Computadora o Humano)
   */
  TipoJugador getTipoJugador(String jugador);

  /**
   * Realiza un movimiento aleatorio en cualquier celda vacía
   */
  void realizarMovimientoAleatorio();
}
