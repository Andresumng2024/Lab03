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
    }
    
    
    
       public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemporizadorHora());
    }
}