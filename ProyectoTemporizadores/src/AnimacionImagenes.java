import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimacionImagenes extends JFrame {
    private JLabel labelImagen;
    private JSlider sliderVelocidad;
    private Timer animacionTimer;
    private int indiceImagen = 0;
    private ImageIcon[] imagenes;

    public AnimacionImagenes() {
        // Cargar imágenes en un arreglo
         imagenes = new ImageIcon[]{
            new ImageIcon(getClass().getResource("/im1.jpg")),
            new ImageIcon(getClass().getResource("/im2.jpg")),
            new ImageIcon(getClass().getResource("/im3.jpg")),
            new ImageIcon(getClass().getResource("/im4.jpg")),
            new ImageIcon(getClass().getResource("/im5.jpg")),
            new ImageIcon(getClass().getResource("/im6.jpg")),
            new ImageIcon(getClass().getResource("/im7.jpg")),
            new ImageIcon(getClass().getResource("/im8.jpg"))
        };

        // Inicializar componentes
        labelImagen = new JLabel(imagenes[0]);
        sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 100, 2000, 1000);
        sliderVelocidad.setMajorTickSpacing(500);
        sliderVelocidad.setPaintTicks(true);
        sliderVelocidad.setPaintLabels(true);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(labelImagen, BorderLayout.CENTER);
        panel.add(sliderVelocidad, BorderLayout.SOUTH);
        add(panel);

        // Temporizador para cambiar la imagen
        animacionTimer = new Timer(sliderVelocidad.getValue(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indiceImagen = (indiceImagen + 1) % imagenes.length;
                labelImagen.setIcon(imagenes[indiceImagen]);
            }
        });
        animacionTimer.start();

        // Escuchar cambios en el control deslizante
        sliderVelocidad.addChangeListener(e -> animacionTimer.setDelay(sliderVelocidad.getValue()));

        setTitle("Animación de Imágenes");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new AnimacionImagenes().setVisible(true);
    }
}

