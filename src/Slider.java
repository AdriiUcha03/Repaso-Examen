import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Marco extends JFrame{
    public Marco(int x,int y){
        this.setSize(x,y);
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);;
        laminaPrincipal lamina = new laminaPrincipal();
        add(lamina);

        revalidate();
    }
}

class laminaPrincipal extends JPanel implements ActionListener{
    JSlider slider;

    JButton guardar;
    List<Integer> temperaturas;
    int grados;
    public laminaPrincipal(){
        setLayout(null);
        BoundedRangeModel model = new DefaultBoundedRangeModel(0,0,0,3000);
        slider = new JSlider(model);
        //Que se muestren las lineas de la etiqueta
        slider.setPaintTicks(true);;
        //Que se muestren etiquetas numericas en las grandes
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(200);
        //Espacion entre etiquetas grandes
        slider.setMajorTickSpacing(1000);
        slider.setBounds(50,10,1800,200);
        add(slider);

        JTextField campo = new JTextField();
        // Establece la alineación horizontal al centro
        campo.setHorizontalAlignment(JTextField.CENTER);
        // Cambia la fuente y tamaño del texto si lo deseas
        campo.setFont(new Font("Purisa", Font.PLAIN, 28));
        campo.setBounds(935,600,100,50);
        add(campo);


        //Utilizamos ChangeListener Para ver el valor del slider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                grados = slider.getValue();
                campo.setText(String.valueOf(grados));
                repaint();
            }
        });

        guardar = new JButton("Guardar");
        guardar.setBounds(200, 200, 80, 50);
        add(guardar);
        guardar.addActionListener(this);

        temperaturas = new ArrayList<>();

    }
    public void actionPerformed(ActionEvent e){
        guardarTemperatura();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; // Conversion de Graphics en Graphics2D
        Image myImagen = new ImageIcon("C:\\Users\\adria\\Desktop\\EJERCICIOS JAVA\\JavaSwing\\Eventos\\src\\barcelona.png").getImage();
        //genero una fuente para aplicar en las letras
        Font letra = new Font("Comic Sans",Font.BOLD,50);
        g2D.setFont(letra);
        g2D.setColor(Color.BLACK);
        g2D.drawString("Temperatura del núcleo",700,500);

        if (grados >= 0 && grados <= 1000) {
            g2D.setColor(Color.GREEN);
            g2D.drawString("ESTABLE",875,750);
        } else if (grados > 1000 && grados <= 1500) {
            g2D.setColor(Color.YELLOW);
            g2D.drawString("PRECAUCIÓN",825,750);
        } else {
            g2D.setColor(Color.RED);
            g2D.drawString("RIESGO DE FUSIÓN",775,750);
        }
    }

    public void guardarTemperatura(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("D:\\1rDAW\\Programación\\T.12\\Examen\\fichero.txt"))) {
            for (int temperatura : temperaturas) {
                writer.println(temperatura);
            }
            System.out.println("Temperaturas guardadas en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar las temperaturas: " + e.getMessage());
        }
    }
}

public class Slider {
    public static void main(String[] args) {
        Marco marco1 = new Marco(500,500);
        marco1.setTitle("Temperatura de un reactor Nuclear");
    }
}
