package produccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class SosGui extends JFrame {

  JuegoSimple juegoSimple;
  PanelCentral panelCentral;
  PanelSuperior panelSuperior;
  PanelInferior panelInferior;
  PanelIzquierdo panelIzquierdo;
  PanelDerecho panelDerecho;
  Container panelDeContenido;
  private Font fuente = new Font("SansSerif", Font.PLAIN, 16);
  private int tamanioTablero; // número de celdas por lado en la cuadrícula
  private static final int TAMANIO_CELDA = 30;

  public SosGui(JuegoSimple juegoSimple) {
    this.juegoSimple = juegoSimple;
    tamanioTablero = juegoSimple.getTamanioTablero();
    setPanelDeContenido();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setTitle("Juego SOS");
    setVisible(true);
  }

  public void setPanelDeContenido() {
    panelCentral = new PanelCentral();
    panelCentral
        .setPreferredSize(new Dimension(tamanioTablero * TAMANIO_CELDA + 1,
            tamanioTablero * TAMANIO_CELDA + 1));
    panelInferior = new PanelInferior();

    panelIzquierdo = new PanelIzquierdo();
    panelIzquierdo.setPreferredSize(new Dimension(150, tamanioTablero * TAMANIO_CELDA));

    panelDerecho = new PanelDerecho();
    panelDerecho.setPreferredSize(new Dimension(150, tamanioTablero * TAMANIO_CELDA));

    panelSuperior = new PanelSuperior();
    panelSuperior.setPreferredSize(new Dimension(tamanioTablero * TAMANIO_CELDA + 300, 50));

    panelDeContenido = getContentPane();
    panelDeContenido.setLayout(new BorderLayout());

    panelDeContenido.add(panelCentral, BorderLayout.CENTER);
    panelDeContenido.add(panelSuperior, BorderLayout.NORTH);
    panelDeContenido.add(panelIzquierdo, BorderLayout.WEST);
    panelDeContenido.add(panelDerecho, BorderLayout.EAST);
    panelDeContenido.add(panelInferior, BorderLayout.SOUTH);
  }

  private class PanelCentral extends JPanel {

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

    PanelSuperior() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setBackground(Color.WHITE);

      JPanel pnlTipoJuego = new JPanel();
      pnlTipoJuego.setBackground(Color.WHITE);

      // Elegir entre juego simple y general
      JLabel lblSos = new JLabel("SOS");
      lblSos.setFont(fuente);
      pnlTipoJuego.add(lblSos);

      JRadioButton btnJuegoSimple = new JRadioButton("Juego Simple", true);
      btnJuegoSimple.setBorder(BorderFactory.createEmptyBorder());
      btnJuegoSimple.setFont(fuente);
      btnJuegoSimple.setBackground(Color.WHITE);

      JRadioButton btnJuegoGeneral = new JRadioButton("Juego General", false);
      btnJuegoGeneral.setBorder(BorderFactory.createEmptyBorder());
      btnJuegoGeneral.setFont(fuente);
      btnJuegoGeneral.setBackground(Color.WHITE);

      pnlTipoJuego.add(btnJuegoSimple);
      pnlTipoJuego.add(btnJuegoGeneral);

      ButtonGroup btnGrpTipoJuego = new ButtonGroup();
      btnGrpTipoJuego.add(btnJuegoSimple);
      btnGrpTipoJuego.add(btnJuegoGeneral);

      add(pnlTipoJuego, BorderLayout.WEST);

      // Elegir tamaño de tablero
      JPanel pnlTamanioTablero = new JPanel();
      pnlTamanioTablero.setBackground(Color.WHITE);
      JLabel lblTamanioTablero = new JLabel("Tamaño tablero");
      lblTamanioTablero.setFont(fuente);
      pnlTamanioTablero.add(lblTamanioTablero);
      JTextField txtTamanioTablero = new JTextField(3);
      txtTamanioTablero.setFont(fuente);
      txtTamanioTablero.setText(String.valueOf(tamanioTablero));
      txtTamanioTablero.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int tamanio = Integer.parseInt(txtTamanioTablero.getText());
          if (juegoSimple.setTamanioTablero(tamanio)) {
            tamanioTablero = tamanio;
            panelDeContenido.repaint();
            panelSuperior.setPreferredSize(new Dimension(tamanioTablero * TAMANIO_CELDA + 301, 50));
            panelIzquierdo.setPreferredSize(new Dimension(150, tamanioTablero * TAMANIO_CELDA + 1));
            SosGui.this.pack();
          } else {
            JOptionPane.showMessageDialog(SosGui.this
                , "El tamaño debe estar entre 3 y 20", "Error de tamaño",
                JOptionPane.ERROR_MESSAGE);
            txtTamanioTablero.setText(String.valueOf(tamanioTablero));
          }
        }
      });
      txtTamanioTablero.setHorizontalAlignment(JTextField.CENTER);
      pnlTamanioTablero.add(txtTamanioTablero);
      add(pnlTamanioTablero, BorderLayout.EAST);
    }

  }

  private class PanelInferior extends JPanel {

    PanelInferior() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setBackground(Color.WHITE);

      JLabel lblTurno = new JLabel("Turno actual: azul");
      lblTurno.setFont(fuente);
      add(lblTurno, BorderLayout.CENTER);

      JButton btnNuevoJuego = new JButton("Nuevo Juego");
      btnNuevoJuego.setFont(fuente);
      add(btnNuevoJuego, BorderLayout.EAST);
    }
  }

  private class PanelIzquierdo extends JPanel {

    PanelIzquierdo() {
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugAzul = new JLabel("Jugador Azul");
      lblJugAzul.setFont(fuente);
      add(lblJugAzul);

      JRadioButton btnSAzul = new JRadioButton("S", true);
      btnSAzul.setFont(fuente);
      btnSAzul.setBackground(Color.WHITE);
      btnSAzul.setBorder(BorderFactory.createEmptyBorder());

      JRadioButton btnOAzul = new JRadioButton("O", false);
      btnOAzul.setFont(fuente);
      btnOAzul.setBackground(Color.WHITE);
      btnOAzul.setBorder(BorderFactory.createEmptyBorder());

      ButtonGroup btnGrpLetraAzul = new ButtonGroup();
      btnGrpLetraAzul.add(btnSAzul);
      btnGrpLetraAzul.add(btnOAzul);

      Box boxLetraAzul = Box.createVerticalBox();
      boxLetraAzul.add(btnSAzul);
      boxLetraAzul.add(btnOAzul);
      add(boxLetraAzul);

    }
  }

  private class PanelDerecho extends JPanel {

    PanelDerecho() {
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugRojo = new JLabel("Jugador Rojo");
      lblJugRojo.setFont(fuente);
      add(lblJugRojo);

      JRadioButton btnSRojo = new JRadioButton("S", true);
      btnSRojo.setFont(fuente);
      btnSRojo.setBackground(Color.WHITE);
      btnSRojo.setBorder(BorderFactory.createEmptyBorder());

      JRadioButton btnORojo = new JRadioButton("O", false);
      btnORojo.setFont(fuente);
      btnORojo.setBackground(Color.WHITE);
      btnORojo.setBorder(BorderFactory.createEmptyBorder());

      ButtonGroup btnGrpLetraRojo = new ButtonGroup();
      btnGrpLetraRojo.add(btnSRojo);
      btnGrpLetraRojo.add(btnORojo);

      Box boxLetraRojo = Box.createVerticalBox();
      boxLetraRojo.add(btnSRojo);
      boxLetraRojo.add(btnORojo);
      add(boxLetraRojo);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SosGui(new JuegoSimple(8)));
  }
}
