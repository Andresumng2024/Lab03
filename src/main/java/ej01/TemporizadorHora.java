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

    
}
