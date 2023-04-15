package produccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SosGui extends JFrame {

  int tamanioTablero = 8; // número de celdas por lado en la cuadrícula
  public static final int TAMANIO_CELDA = 40;

  public SosGui() {
    setPanelDeContenido();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setTitle("Juego SOS");
    setVisible(true);
  }

  private void setPanelDeContenido() {
    PanelCentral panelCentral = new PanelCentral();
    panelCentral
        .setPreferredSize(new Dimension(tamanioTablero * TAMANIO_CELDA,
            tamanioTablero * TAMANIO_CELDA));

    PanelSuperior panelSuperior = new PanelSuperior();
    PanelInferior panelInferior = new PanelInferior();
    PanelIzquierdo panelIzquierdo = new PanelIzquierdo();
    PanelDerecho panelDerecho = new PanelDerecho();

    Container panelDeContenido = getContentPane();
    panelDeContenido.setLayout(new BorderLayout());

    panelDeContenido.add(panelCentral, BorderLayout.CENTER);
  }

  private class PanelCentral extends JPanel {

    PanelCentral() {
      setPreferredSize(
          new Dimension(tamanioTablero * TAMANIO_CELDA, tamanioTablero * TAMANIO_CELDA));
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.WHITE);
      dibujarLineas(g);
    }

    private void dibujarLineas(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);

      for (int fila = 0; fila <= tamanioTablero; fila++) {
        g.drawLine(0, fila * TAMANIO_CELDA, tamanioTablero * TAMANIO_CELDA, fila * TAMANIO_CELDA);
      }

      for (int col = 0; col <= tamanioTablero; col++) {
        g.drawLine(col * TAMANIO_CELDA, 0, col * TAMANIO_CELDA, tamanioTablero * TAMANIO_CELDA);
      }
    }
  }

  private class PanelSuperior extends JPanel {

  }

  private class PanelInferior extends JPanel {

  }

  private class PanelIzquierdo extends JPanel {

  }

  private class PanelDerecho extends JPanel {

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SosGui());
  }
}
