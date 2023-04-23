package produccion;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
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
import produccion.JuegoSimple.EstadoJuego;
import produccion.JuegoSimple.Turno;

public class SosGui extends JFrame {

  JuegoSimple juego;
  PanelCentral panelCentral;
  PanelSuperior panelSuperior;
  PanelInferior panelInferior;
  PanelIzquierdo panelIzquierdo;
  PanelDerecho panelDerecho;
  Container panelDeContenido;
  private Font fuente = new Font("SansSerif", Font.PLAIN, 16);
  private int tamanioTablero; // número de celdas por lado en la cuadrícula
  private static final int TAMANIO_CELDA = 30;

  int filaSeleccionada;
  int colSeleccionada;

  JRadioButton btnOAzul;
  JRadioButton btnSAzul;
  JRadioButton btnORojo;
  JRadioButton btnSRojo;
  JRadioButton btnJuegoSimple;
  JRadioButton btnJuegoGeneral;

  public SosGui(JuegoSimple juego) {
    this.juego = juego;
    tamanioTablero = juego.getTamanioTablero();
    setPanelDeContenido();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setTitle("Juego SOS");
    setVisible(true);
  }

  public void setTipoJuego(JuegoSimple juego) {
    this.juego = juego;
  }

  public JuegoSimple getTipoJuego() {
    return this.juego;
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

    PanelCentral() {
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (juego.getEstadoJuego() == JuegoSimple.EstadoJuego.JUGANDO) {
            filaSeleccionada = e.getY() / TAMANIO_CELDA;
            colSeleccionada = e.getX() / TAMANIO_CELDA;
            JuegoSimple.Celda celdaSeleccionada = null;
            if (juego.getTurno() == JuegoSimple.Turno.AZUL) {
              if (btnOAzul.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.O;
              } else if (btnSAzul.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.S;
              }
            } else if (juego.getTurno() == JuegoSimple.Turno.ROJO) {
              if (btnORojo.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.O;
              } else if (btnSRojo.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.S;
              }
            }
            juego.realizarMovimiento(filaSeleccionada, colSeleccionada, celdaSeleccionada);
            System.out.println(juego.getEstadoJuego());
          } else {
            juego.resetearJuego();
          }
          repaint();
        }
      });
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.WHITE);
      dibujarLineas(g);
      dibujarTablero(g);
      panelInferior.actualizarTurnoActual();
      panelInferior.actualizarResultado();
      if (juego.getClass() == JuegoGeneral.class) {
        dibujarSos(g);

      } else if (juego.getEstadoJuego() == JuegoSimple.EstadoJuego.GANO_ROJO
          || juego.getEstadoJuego() == JuegoSimple.EstadoJuego.GANO_AZUL) {
        dibujarGanador(g);

      }
    }

    private void dibujarSos(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(4));

      List<LineaSos> lineasSos = juego.getLineasSos();
      for(LineaSos ls : lineasSos) {
        g2d.setColor(ls.getColorLinea());
        int x1 = ls.getX1()*TAMANIO_CELDA + TAMANIO_CELDA/2;
        int x2 = ls.getX2()*TAMANIO_CELDA+ TAMANIO_CELDA/2;
        int y1 = ls.getY1()*TAMANIO_CELDA+ TAMANIO_CELDA/2;
        int y2 = ls.getY2()*TAMANIO_CELDA+ TAMANIO_CELDA/2;
        g2d.drawLine(x1, y1, x2, y2);
      }
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

    private void dibujarTablero(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("SansSerif", Font.PLAIN, 5 * TAMANIO_CELDA / 6));
      for (int fila = 0; fila < juego.getFilasTotales(); fila++) {
        for (int col = 0; col < juego.getColumnasTotales(); col++) {
          int x1 = col * TAMANIO_CELDA + 1 * TAMANIO_CELDA / 6;
          int y1 = fila * TAMANIO_CELDA + 5 * TAMANIO_CELDA / 6;
          if (juego.getCelda(fila, col) == JuegoSimple.Celda.S) {
            g2d.drawString("S", x1, y1);
          } else if (juego.getCelda(fila, col) == JuegoSimple.Celda.O) {
            g2d.drawString("O", x1, y1);
          }
        }
      }
    }

    private void dibujarGanador(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(4));
      g2d.setColor(juego.getTurno() == JuegoSimple.Turno.AZUL ? Color.BLUE : Color.RED);
      if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.HOR_IZQ) {
        int x1 = colSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA;
        int y1 = filaSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int x2 = x1 - 3 * TAMANIO_CELDA;
        int y2 = y1;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.HOR_DER) {
        int x1 = colSeleccionada * TAMANIO_CELDA;
        int y1 = filaSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int x2 = x1 + 3 * TAMANIO_CELDA;
        int y2 = y1;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.VERT_ARR) {
        int x1 = colSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int y1 = filaSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA;
        int x2 = x1;
        int y2 = y1 - 3 * TAMANIO_CELDA;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.VERT_ABJ) {
        int x1 = colSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int y1 = filaSeleccionada * TAMANIO_CELDA;
        int x2 = x1;
        int y2 = y1 + 3 * TAMANIO_CELDA;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.DIAG_IZQ_ARR) {
        int x1 = colSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA;
        int y1 = filaSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA;
        int x2 = x1 - 3 * TAMANIO_CELDA;
        int y2 = y1 - 3 * TAMANIO_CELDA;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.DIAG_IZQ_ABJ) {
        int x1 = colSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA;
        int y1 = filaSeleccionada * TAMANIO_CELDA;
        int x2 = x1 - 3 * TAMANIO_CELDA;
        int y2 = y1 + 3 * TAMANIO_CELDA;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.DIAG_DER_ARR) {
        int x1 = colSeleccionada * TAMANIO_CELDA;
        int y1 = filaSeleccionada * TAMANIO_CELDA + TAMANIO_CELDA;
        int x2 = x1 + 3 * TAMANIO_CELDA;
        int y2 = y1 - 3 * TAMANIO_CELDA;
        g2d.drawLine(x1, y1, x2, y2);
      } else if (juego.getLineaGanadora() == JuegoSimple.LineaGanadora.DIAG_DER_ABJ) {
        int x1 = colSeleccionada * TAMANIO_CELDA;
        int y1 = filaSeleccionada * TAMANIO_CELDA;
        int x2 = x1 + 3 * TAMANIO_CELDA;
        int y2 = y1 + 3 * TAMANIO_CELDA;
        g2d.drawLine(x1, y1, x2, y2);
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

      btnJuegoSimple = new JRadioButton("Juego Simple", true);
      btnJuegoSimple.setBorder(BorderFactory.createEmptyBorder());
      btnJuegoSimple.setFont(fuente);
      btnJuegoSimple.setBackground(Color.WHITE);

      btnJuegoGeneral = new JRadioButton("Juego General", false);
      btnJuegoGeneral.setBorder(BorderFactory.createEmptyBorder());
      btnJuegoGeneral.setFont(fuente);
      btnJuegoGeneral.setBackground(Color.WHITE);

      btnJuegoSimple.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (getTipoJuego().getClass() == JuegoGeneral.class) {
            setTipoJuego(new JuegoSimple(juego.getTamanioTablero()));
          }
        }
      });

      btnJuegoGeneral.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (getTipoJuego().getClass() == JuegoSimple.class) {
            setTipoJuego(new JuegoGeneral(juego.getTamanioTablero()));
          }
        }
      });

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
          if (juego.setTamanioTablero(tamanio)) {
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

    JLabel lblTurno;
    JLabel lblResultado;
    PanelInferior() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setBackground(Color.WHITE);

      String turno = juego.getTurno() == Turno.AZUL ? "Azul" : "Rojo";
      lblTurno = new JLabel("Turno actual: " + turno);
      lblTurno.setFont(fuente);
      add(lblTurno, BorderLayout.WEST);


      lblResultado = new JLabel("");
      lblResultado.setFont(fuente);
      add(lblResultado, BorderLayout.CENTER);

      JButton btnNuevoJuego = new JButton("Nuevo Juego");
      btnNuevoJuego.setFont(fuente);
      add(btnNuevoJuego, BorderLayout.EAST);
    }

    void actualizarTurnoActual() {
      String turno = juego.getTurno() == Turno.AZUL ? "Azul" : "Rojo";
      lblTurno.setText("Turno actual: " + turno);
    }

    void actualizarResultado() {
      String resultado = "";
      if(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL) {
        resultado = "        Ganador: Azul!!!";
      } else if(juego.getEstadoJuego() == EstadoJuego.GANO_ROJO) {
        resultado = "        Ganador: Rojo!!!";
      } else if(juego.getEstadoJuego() == EstadoJuego.EMPATE) {
        resultado = "        Empate";
      }
      lblResultado.setText(resultado);
    }
  }

  private class PanelIzquierdo extends JPanel {

    PanelIzquierdo() {
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugAzul = new JLabel("Jugador Azul");
      lblJugAzul.setFont(fuente);
      add(lblJugAzul);

      btnSAzul = new JRadioButton("S", true);
      btnSAzul.setFont(fuente);
      btnSAzul.setBackground(Color.WHITE);
      btnSAzul.setBorder(BorderFactory.createEmptyBorder());

      btnOAzul = new JRadioButton("O", false);
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

      btnSRojo = new JRadioButton("S", true);
      btnSRojo.setFont(fuente);
      btnSRojo.setBackground(Color.WHITE);
      btnSRojo.setBorder(BorderFactory.createEmptyBorder());

      btnORojo = new JRadioButton("O", false);
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
