package ej01;
        
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class TemporizadorHora extends JFrame {
    private JLabel labelHora, labelAlarmaConfigurada;
    private JTextField textMinutos;
    private JButton botonConfigurarAlarma, botonCancelarAlarma;
    private Timer temporizador;
    private LocalTime horaAlarma;
    private TimerTask tareaAlarma;
    private Clip clipAlarma; // Declarar clipAlarma a nivel de clase

    public TemporizadorHora() {
        super("Temporizador y Alarma");
        setLayout(new FlowLayout());

        // Crear los componentes
        labelHora = new JLabel("Hora actual: ");
        labelAlarmaConfigurada = new JLabel("");
        textMinutos = new JTextField(5);
        botonConfigurarAlarma = new JButton("Configurar Alarma");
        botonCancelarAlarma = new JButton("Cancelar Alarma");
        botonCancelarAlarma.setEnabled(false); // Desactivar el botón al inicio

        
        // Agregar componentes a la ventana
        add(labelHora);
        add(new JLabel("Minutos para la alarma:"));
        add(textMinutos);
        add(botonConfigurarAlarma);
        add(botonCancelarAlarma);
        add(labelAlarmaConfigurada);

        // Iniciar el temporizador para actualizar la hora actual cada segundo
        TimerTask tareaActualizarHora = new TimerTask() {
            @Override
            public void run() {
                LocalTime horaActual = LocalTime.now();
                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                labelHora.setText("Hora actual: " + horaActual.format(formatoHora));
            }
        };
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(tareaActualizarHora, 0, 1000);

        // Acción del botón para configurar la alarma
        botonConfigurarAlarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int minutos = Integer.parseInt(textMinutos.getText());
                    horaAlarma = LocalTime.now().plusMinutes(minutos);
                    labelAlarmaConfigurada.setText("Alarma sonará a las " + horaAlarma.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    labelAlarmaConfigurada.setForeground(Color.RED);

                    // Desactivar el campo de minutos y el botón de configurar alarma
                    textMinutos.setEnabled(false);
                    botonConfigurarAlarma.setEnabled(false);
                    botonCancelarAlarma.setEnabled(true);

                    // Iniciar el temporizador para la alarma
                    tareaAlarma = new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> iniciarAlarma());
                            mostrarVentanaAlarma();
                        }
                    };
                    temporizador.schedule(tareaAlarma, minutos * 60 * 1000); // Convertir minutos a milisegundos
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                }
            }
        });

     // Acción del botón para cancelar la alarma
        botonCancelarAlarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarAlarma();
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Mostrar ventana de alarma
    private void mostrarVentanaAlarma() {
        SwingUtilities.invokeLater(() -> {
            JFrame ventanaAlarma = new JFrame("Alarma");
            ventanaAlarma.setLayout(new FlowLayout());

            JLabel mensajeAlarma = new JLabel("¡La alarma suena!");
            JButton botonDetenerAlarma = new JButton("Detener Alarma");

            ventanaAlarma.add(mensajeAlarma);
            ventanaAlarma.add(botonDetenerAlarma);

            botonDetenerAlarma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detenerSonidoAlarma();
                    ventanaAlarma.dispose();
                    resetearVentanaPrincipal();
                }
            });

            ventanaAlarma.setSize(200, 100);
            ventanaAlarma.setLocationRelativeTo(null);
            ventanaAlarma.setVisible(true);
        });
    }
// Método para reproducir el sonido de la alarma
    private void iniciarAlarma() {
        try {
            InputStream audioStream = getClass().getResourceAsStream("/alarma.wav");
            if (audioStream != null) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(audioStream);
                clipAlarma = AudioSystem.getClip();
                clipAlarma.open(ais);
                clipAlarma.loop(Clip.LOOP_CONTINUOUSLY); // Repetir el sonido de la alarma continuamente
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo de sonido.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir el sonido de la alarma.");
        }
    }
     // Método para detener el sonido de la alarma
    private void detenerSonidoAlarma() {
        if (clipAlarma != null && clipAlarma.isRunning()) {
            clipAlarma.stop();  // Detiene el sonido de la alarma
            clipAlarma.close(); // Libera recursos
        }
    }

    // Cancelar la alarma y resetear la ventana principal
    private void cancelarAlarma() {
        if (tareaAlarma != null) {
            tareaAlarma.cancel(); // Cancelar la tarea de la alarma
        }
        detenerSonidoAlarma();
        labelAlarmaConfigurada.setText("");
        textMinutos.setEnabled(true);
        botonConfigurarAlarma.setEnabled(true);
        botonCancelarAlarma.setEnabled(false);
    }


     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemporizadorHora());
    }
}
