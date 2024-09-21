package Main;

import ej01.TemporizadorHora;
import ej02.Secuencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public static JFrame frame;
    public static JButton[] botones;

    public static void main(String[] args) {
        // *** AJUSTAR EL TITULO DE LA VENTANA (JFRAME) DEL MENU ***
        String titulo = "Lab01: Introducción al lenguaje Java";

        // *** AJUSTAR LOS MENSAJES QUE HAN DE APARECER EN EL MENU ***
        String opciones[] = {"Cronometro ", "Secuencia Imagenes"};

        final int numop = opciones.length;

        // Crear el marco de la ventana
        frame = new JFrame(titulo);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setSize(400, 50 * numop);
        frame.setLayout(new BorderLayout());

        // Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(numop, 1)); // Disponer los botones en una columna

        // Crear un array de botones
        botones = new JButton[numop];

        // Inicializar los botones y añadir ActionListener
        for (int i = 0; i < numop; i++) {
            final int index = i; // Variable final para usar en el ActionListener
            botones[i] = new JButton(opciones[i]);
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // *** AJUSTAR SEGUN LAS OPCIONES DEL MENU ***
                   switch (index) {
                case 0:
                    new Thread(() -> {
                        TemporizadorHora.main(new String[]{});
                    }).start();
                    frame.dispose();  // Cerrar la ventana del menú
                    break;
                case 1:
                    new Thread(() -> {
                        new Secuencia().setVisible(true);
                    }).start();
                    frame.dispose();  // Cerrar la ventana del menú
                    break;
                        
                    }
                    // *** FINAL AJUSTES ***
                }
            });
            // Añadir el botón al panel
            panelBotones.add(botones[i]);
        }

        // Añadir el panel de botones al marco
        frame.add(panelBotones, BorderLayout.CENTER);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}