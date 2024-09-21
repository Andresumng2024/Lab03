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

    
}
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemporizadorHora());
    }
}
