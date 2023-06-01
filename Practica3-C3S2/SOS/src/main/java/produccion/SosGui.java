package produccion;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 * Interfaz gráfica del juego SOS
 */
public class SosGui extends JFrame {

  private JuegoSimple juego;
  private PanelCentral panelCentral;
  private PanelInferior panelInferior;
  private final Font fuente = new Font("SansSerif", Font.PLAIN, 16);
  private static final int TAMANIO_CELDA = 30;

  private int filaSeleccionada;
  private int colSeleccionada;

  private JRadioButton btnOAzul;
  private JRadioButton btnSAzul;
  private JRadioButton btnHumanoRojo;
  private JRadioButton btnHumanoAzul;
  private JRadioButton btnComputadoraRojo;
  private JRadioButton btnComputadoraAzul;
  private JRadioButton btnORojo;
  private JRadioButton btnSRojo;
  private JRadioButton btnJuegoSimple;
  private JRadioButton btnJuegoGeneral;
  private JTextField txtTamanioTablero;
  private JCheckBox btnGrabarJuego;
  private JButton btnReproducir;

  /**
   * Crea la interfaz de acuerdo al tipo de juego
   *
   * @param juego tipo de juego
   */
  public SosGui(JuegoSimple juego) {
    this.juego = juego;
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

  /**
   * Panel que contiene toda la interfaz del juego
   */
  public void setPanelDeContenido() {
    panelCentral = new PanelCentral();
    panelCentral
        .setPreferredSize(new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 1,
            juego.getTamanioTablero() * TAMANIO_CELDA + 1));
    panelInferior = new PanelInferior();

    PanelIzquierdo panelIzquierdo = new PanelIzquierdo();
    panelIzquierdo.setPreferredSize(new Dimension(180, juego.getTamanioTablero() * TAMANIO_CELDA));

    PanelDerecho panelDerecho = new PanelDerecho();
    panelDerecho.setPreferredSize(new Dimension(180, juego.getTamanioTablero() * TAMANIO_CELDA));

    PanelSuperior panelSuperior = new PanelSuperior();
    panelSuperior.setPreferredSize(
        new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 300, 50));

    Container panelDeContenido = getContentPane();
    panelDeContenido.setLayout(new BorderLayout());

    panelDeContenido.add(panelCentral, BorderLayout.CENTER);
    panelDeContenido.add(panelSuperior, BorderLayout.NORTH);
    panelDeContenido.add(panelIzquierdo, BorderLayout.WEST);
    panelDeContenido.add(panelDerecho, BorderLayout.EAST);
    panelDeContenido.add(panelInferior, BorderLayout.SOUTH);
  }

  /**
   * Panel que contiene al tablero
   */
  public class PanelCentral extends JPanel {

    public PanelCentral() {
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (juego.getEstadoJuego() == EstadoJuego.JUGANDO) {
            filaSeleccionada = e.getY() / TAMANIO_CELDA;
            colSeleccionada = e.getX() / TAMANIO_CELDA;
            Celda celdaSeleccionada = null;
            if (juego.getTurno() == Turno.AZUL) {
              if (btnOAzul.isSelected()) {
                celdaSeleccionada = Celda.O;
              } else if (btnSAzul.isSelected()) {
                celdaSeleccionada = Celda.S;
              }
            } else if (juego.getTurno() == Turno.ROJO) {
              if (btnORojo.isSelected()) {
                celdaSeleccionada = Celda.O;
              } else if (btnSRojo.isSelected()) {
                celdaSeleccionada = Celda.S;
              }
            }
            juego.realizarMovimiento(filaSeleccionada, colSeleccionada, celdaSeleccionada);
          }
          panelCentral.repaint();
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
      dibujarSos(g);
    }

    /**
     * Dibuja todas las líneas SOS del color del jugador que las realizó
     */
    public void dibujarSos(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(4));

      List<LineaSos> lineasSos = juego.getLineasSos();
      for (LineaSos ls : lineasSos) {
        g2d.setColor(ls.getColorLinea());
        int x1 = ls.getX1() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int x2 = ls.getX2() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int y1 = ls.getY1() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int y2 = ls.getY2() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        g2d.drawLine(x1, y1, x2, y2);
      }
    }

    /**
     * Dibuja las líneas del tablero
     */
    private void dibujarLineas(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);

      for (int fila = 0; fila <= juego.getTamanioTablero(); fila++) {
        g.drawLine(0, fila * TAMANIO_CELDA, juego.getTamanioTablero() * TAMANIO_CELDA,
            fila * TAMANIO_CELDA);
      }

      for (int col = 0; col <= juego.getTamanioTablero(); col++) {
        g.drawLine(col * TAMANIO_CELDA, 0, col * TAMANIO_CELDA,
            juego.getTamanioTablero() * TAMANIO_CELDA);
      }
    }

    /**
     * Dibuja las letras 'S' u 'O' en el tablero
     */
    private void dibujarTablero(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("SansSerif", Font.PLAIN, 5 * TAMANIO_CELDA / 6));
      for (int fila = 0; fila < juego.getFilasTotales(); fila++) {
        for (int col = 0; col < juego.getColumnasTotales(); col++) {
          int x1 = col * TAMANIO_CELDA + TAMANIO_CELDA / 6;
          int y1 = fila * TAMANIO_CELDA + 5 * TAMANIO_CELDA / 6;
          if (juego.getCelda(fila, col) == Celda.S) {
            g2d.drawString("S", x1, y1);
          } else if (juego.getCelda(fila, col) == Celda.O) {
            g2d.drawString("O", x1, y1);
          }
        }
      }
    }
  }

  /**
   * Panel que contiene el tipo de juego y tamaño del tablero
   */
  public class PanelSuperior extends JPanel {

    public PanelSuperior() {
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

      pnlTipoJuego.add(btnJuegoSimple);
      pnlTipoJuego.add(btnJuegoGeneral);

      ButtonGroup btnGrpTipoJuego = new ButtonGroup();
      btnGrpTipoJuego.add(btnJuegoSimple);
      btnGrpTipoJuego.add(btnJuegoGeneral);

      add(pnlTipoJuego, BorderLayout.WEST);

      // Ingresa tamaño del tablero
      JPanel pnlTamanioTablero = new JPanel();
      pnlTamanioTablero.setBackground(Color.WHITE);
      JLabel lblTamanioTablero = new JLabel("Tamaño tablero");
      lblTamanioTablero.setFont(fuente);
      pnlTamanioTablero.add(lblTamanioTablero);
      txtTamanioTablero = new JTextField(3);
      txtTamanioTablero.setFont(fuente);
      txtTamanioTablero.setText(String.valueOf(juego.getTamanioTablero()));

      txtTamanioTablero.setHorizontalAlignment(JTextField.CENTER);
      pnlTamanioTablero.add(txtTamanioTablero);
      add(pnlTamanioTablero, BorderLayout.EAST);
    }

  }

  /**
   * Panel que contiene el turno actual, muestra el resultado y contiene al botón NuevoJuego
   */
  public class PanelInferior extends JPanel {

    private final JLabel lblTurno;
    private final JLabel lblResultado;

    PanelInferior() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setBackground(Color.WHITE);

      // Muestra el turno actual
      String turno = juego.getTurno() == Turno.AZUL ? "Azul" : "Rojo";
      lblTurno = new JLabel("Turno actual: " + turno);
      lblTurno.setFont(fuente);
      add(lblTurno, BorderLayout.WEST);

      //Muestra el resultado
      lblResultado = new JLabel("");
      lblResultado.setFont(fuente);
      add(lblResultado, BorderLayout.CENTER);

      JButton btnReset = new JButton("Reset");
      btnReset.setFont(fuente);
      add(btnReset, BorderLayout.EAST);

      // Resetea el juego al estado inicial
      btnReset.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          juego = new JuegoSimple(8);
          juego.setEstadoJuego(EstadoJuego.INICIO);
          txtTamanioTablero.setText(String.valueOf(juego.getTamanioTablero()));
          panelCentral.repaint();
          btnSAzul.doClick();
          btnSRojo.doClick();
          btnJuegoSimple.doClick();
          btnHumanoAzul.doClick();
          btnHumanoRojo.doClick();
          btnGrabarJuego.setSelected(false);
        }
      });
    }

    /**
     * Actualiza el turno en la interfaz
     */
    void actualizarTurnoActual() {
      String turno = juego.getTurno() == Turno.AZUL ? "Azul" : "Rojo";
      lblTurno.setText("Turno actual: " + turno);
    }

    /**
     * Actualiza el resultado en la interfaz
     */
    void actualizarResultado() {
      String resultado = "";
      if (juego.getEstadoJuego() == EstadoJuego.GANO_AZUL) {
        resultado = "        Ganador: Azul!!!";
      } else if (juego.getEstadoJuego() == EstadoJuego.GANO_ROJO) {
        resultado = "        Ganador: Rojo!!!";
      } else if (juego.getEstadoJuego() == EstadoJuego.EMPATE) {
        resultado = "        Empate";
      }
      lblResultado.setText(resultado);
    }
  }

  /**
   * Muestra la letra seleccionada por el juagdor azul
   */
  private class PanelIzquierdo extends JPanel {

    PanelIzquierdo() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugAzul = new JLabel("Jugador Azul");
      lblJugAzul.setFont(fuente);
      add(lblJugAzul);

      btnHumanoAzul = new JRadioButton("Humano", true);
      btnHumanoAzul.setFont(fuente);
      btnHumanoAzul.setBackground(Color.WHITE);
      btnHumanoAzul.setBorder(BorderFactory.createEmptyBorder());

      btnComputadoraAzul = new JRadioButton("Computadora", false);
      btnComputadoraAzul.setFont(fuente);
      btnComputadoraAzul.setBackground(Color.WHITE);
      btnComputadoraAzul.setBorder(BorderFactory.createEmptyBorder());

      ButtonGroup btnGrpTipoJugadorAzul = new ButtonGroup();
      btnGrpTipoJugadorAzul.add(btnComputadoraAzul);
      btnGrpTipoJugadorAzul.add(btnHumanoAzul);

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

      JPanel panelLetras = new JPanel();
      panelLetras.setLayout(new FlowLayout(FlowLayout.CENTER));
      panelLetras.setBorder(new EmptyBorder(0, 20, 0, 20));
      panelLetras.setBackground(Color.WHITE);

      Box boxLetraAzul = Box.createVerticalBox();
      boxLetraAzul.add(btnSAzul);
      boxLetraAzul.add(btnOAzul);

      panelLetras.add(boxLetraAzul);

      add(btnHumanoAzul);
      add(panelLetras);
      add(btnComputadoraAzul);

      btnGrabarJuego = new JCheckBox("Guardar");
      btnGrabarJuego.setFont(fuente);
      btnGrabarJuego.setBackground(Color.WHITE);
      btnGrabarJuego.setBorder(new EmptyBorder(20, 0, 10, 0));

      add(btnGrabarJuego);

      btnReproducir = new JButton("Reproducir");
      btnReproducir.setFont(fuente);
      btnReproducir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("juegoGuardado.txt"));
            String linea;
            while((linea = bufferedReader.readLine()) != null){
              if(linea.equals("")){
                break;
              }
              System.out.println(linea);
            }

          } catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
        }
      });

      add(btnReproducir);
    }
  }

  /**
   * Muestra la letra seleccionada por el juagdor rojo
   */
  private class PanelDerecho extends JPanel {

    PanelDerecho() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugRojo = new JLabel("Jugador Rojo");
      lblJugRojo.setFont(fuente);
      add(lblJugRojo);

      btnHumanoRojo = new JRadioButton("Humano", true);
      btnHumanoRojo.setFont(fuente);
      btnHumanoRojo.setBackground(Color.WHITE);
      btnHumanoRojo.setBorder(BorderFactory.createEmptyBorder());

      btnComputadoraRojo = new JRadioButton("Computadora", false);
      btnComputadoraRojo.setFont(fuente);
      btnComputadoraRojo.setBackground(Color.WHITE);
      btnComputadoraRojo.setBorder(BorderFactory.createEmptyBorder());

      ButtonGroup btnGrpTipoJugadorRojo = new ButtonGroup();
      btnGrpTipoJugadorRojo.add(btnComputadoraRojo);
      btnGrpTipoJugadorRojo.add(btnHumanoRojo);

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

      JPanel panelLetras = new JPanel();
      panelLetras.setLayout(new FlowLayout(FlowLayout.CENTER));
      panelLetras.setBorder(new EmptyBorder(0, 20, 0, 20));
      panelLetras.setBackground(Color.WHITE);

      Box boxLetraRojo = Box.createVerticalBox();
      boxLetraRojo.add(btnSRojo);
      boxLetraRojo.add(btnORojo);

      JButton btnIniciarJuego = new JButton("Iniciar Juego");
      btnIniciarJuego.setFont(fuente);

      btnIniciarJuego.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          int tamanioTab = Integer.parseInt(txtTamanioTablero.getText());
          TipoJugador jugadorAzul = btnHumanoAzul.isSelected() ? TipoJugador.HUMANO : TipoJugador.COMPUTADORA;
          TipoJugador jugadorRojo = btnHumanoRojo.isSelected() ? TipoJugador.HUMANO : TipoJugador.COMPUTADORA;
          if(btnJuegoSimple.isSelected() && btnHumanoAzul.isSelected() && btnHumanoRojo.isSelected()) {
            juego = new JuegoSimple(tamanioTab);
          }
          if(btnJuegoSimple.isSelected() && (btnComputadoraAzul.isSelected() || btnComputadoraRojo.isSelected())) {
            juego = new AutoJuegoSimple(tamanioTab, jugadorAzul, jugadorRojo);
          }
          if(btnJuegoGeneral.isSelected() && btnHumanoAzul.isSelected() && btnHumanoRojo.isSelected()) {
            juego = new JuegoGeneral(tamanioTab);
          }
          if(btnJuegoGeneral.isSelected() && (btnComputadoraAzul.isSelected() || btnComputadoraRojo.isSelected())) {
            juego = new AutoJuegoGeneral(tamanioTab, jugadorAzul, jugadorRojo);
          }
          if(btnGrabarJuego.isSelected()){
            juego.setJuegoDebeGuardarse(true);
          }
          panelCentral.repaint();
          if (btnComputadoraAzul.isSelected()) {
            Celda celda;
            if (btnSAzul.isSelected()) {
              celda = Celda.S;
            } else {
              celda = Celda.O;
            }
            juego.realizarMovimiento(0, 0, celda);
            panelCentral.repaint();
          }
        }
      });

      panelLetras.add(boxLetraRojo);

      add(btnHumanoRojo);
      add(panelLetras);
      add(btnComputadoraRojo);
      add(btnIniciarJuego);

    }
  }

  public static void main(String[] args) {
    JuegoSimple juego = new JuegoSimple(8);
    juego.setEstadoJuego(EstadoJuego.INICIO);
    SwingUtilities.invokeLater(() -> new SosGui(juego));
  }
}
