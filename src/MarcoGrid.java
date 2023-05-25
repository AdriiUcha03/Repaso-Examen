import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class crearMarco extends JFrame {
    //Creo una variable estática en marco con la que pasare los colores al listener
    //y al action performed en las diferentes clases.
    public static Color cambiarColor;
    //Creación de marco con lamina.
    crearMarco(int tam_x, int tam_y) {
        this.setSize(tam_x, tam_y);
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridLayout(2,2));
        //Los creamos en el porden que nos combiene pero en el añadido los ordenamos
        laminaDos lamina12 = new laminaDos();
        laminaUno lamina11 = new laminaUno(lamina12);
        laminaCuatro lamina22 = new laminaCuatro();
        laminaTres lamina21 = new laminaTres(lamina22);
        add(lamina11);
        add(lamina12);
        add(lamina21);
        add(lamina22);
        revalidate();
    }
}

class laminaUno extends JPanel{
    //Hacemos la variable privada
    private laminaDos laminaDos;
    laminaUno(laminaDos laminaDos){
        //Creacion de Botones agrupados y agregados a la lamina
        this.laminaDos = laminaDos;
        setLayout(null);
        ButtonGroup colores = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Rojo",true);
        radio1.setBounds(60, 50, 120, 30);
        JRadioButton radio2 = new JRadioButton("Azul");
        radio2.setBounds(60, 100, 120, 30);
        JRadioButton radio3 = new JRadioButton("Amarillo");
        radio3.setBounds(60, 150, 120, 30);
        JRadioButton radio4 = new JRadioButton("Verde");
        radio4.setBounds(60, 200, 120, 30);
        JRadioButton radio5 = new JRadioButton("Naranja");
        radio5.setBounds(60, 250, 120, 30);
        JRadioButton radio6 = new JRadioButton("Marrón");
        radio6.setBounds(60, 300, 120, 30);
        JButton personalizar = new JButton("Personalizar");
        personalizar.setBounds(400, 220, 140, 60);
        add(radio1);
        add(radio2);
        add(radio3);
        add(radio4);
        add(radio5);
        add(radio6);
        add(personalizar);
        colores.add(radio1);
        colores.add(radio2);
        colores.add(radio3);
        colores.add(radio4);
        colores.add(radio5);
        colores.add(radio6);

        //Listener que ejecuta la acción de escucha
        ActionListener escucha = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton radioButton = (JRadioButton) e.getSource();
                crearMarco.cambiarColor = null;
                if (radioButton == radio1){
                    crearMarco.cambiarColor = Color.RED;
                }
                if (radioButton == radio2){
                    crearMarco.cambiarColor = Color.BLUE;
                }
                if (radioButton == radio3){
                    crearMarco.cambiarColor = Color.YELLOW;
                }
                if (radioButton == radio4){
                    crearMarco.cambiarColor = Color.GREEN;
                }
                if (radioButton == radio5){
                    crearMarco.cambiarColor = Color.ORANGE;
                }
                if (radioButton == radio6){
                    Color marron = new Color(128, 64, 0);
                    crearMarco.cambiarColor = marron;
                }
                laminaDos.setColorFondo(crearMarco.cambiarColor);

            }

        };
        //Generaloms el listener que crea el marco suelto con los sliders del RGB
        personalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Establezco una ventana o marco centrado
                int tamx = 300;
                int tamy = 200;
                JFrame venPerso = new JFrame("RGB");
                venPerso.setSize(tamx,tamy);
                Toolkit myScreen = Toolkit.getDefaultToolkit();
                Dimension resolution = myScreen.getScreenSize();
                int resx=resolution.width;
                int resy=resolution.height;
                venPerso.setLocation(resx/2-tamx/2,resy/2-tamy/2);

                //Creo el panel donde estará dividio en 3 celdas y 2 columnas
                JPanel panelPersonalizar = new JPanel();
                panelPersonalizar.setLayout(new GridLayout(3, 2));

                //Ponemos el nombre de Rojo y su jslider
                JLabel lblRojo = new JLabel("Rojo:");
                JSlider sliderRojo = new JSlider(0, 255);
                sliderRojo.setMajorTickSpacing(50);
                sliderRojo.setMinorTickSpacing(10);
                sliderRojo.setPaintTicks(true);
                sliderRojo.setPaintLabels(true);

                //Ponemos el nombre de Verde y su jslider
                JLabel lblVerde = new JLabel("Verde:");
                JSlider sliderVerde = new JSlider(0, 255);
                sliderVerde.setMajorTickSpacing(50);
                sliderVerde.setMinorTickSpacing(10);
                sliderVerde.setPaintTicks(true);
                sliderVerde.setPaintLabels(true);

                //Ponemos el nombre de Azul y su jslider
                JLabel lblAzul = new JLabel("Azul:");
                JSlider sliderAzul = new JSlider(0, 255);
                sliderAzul.setMajorTickSpacing(50);
                sliderAzul.setMinorTickSpacing(10);
                sliderAzul.setPaintTicks(true);
                sliderAzul.setPaintLabels(true);

                //Añadimos al panel los labels y sus sliders.
                panelPersonalizar.add(lblRojo);
                panelPersonalizar.add(sliderRojo);
                panelPersonalizar.add(lblVerde);
                panelPersonalizar.add(sliderVerde);
                panelPersonalizar.add(lblAzul);
                panelPersonalizar.add(sliderAzul);

                //Por ultimo añadimos el panel a nuestra ventana o marco
                venPerso.add(panelPersonalizar);
                venPerso.setVisible(true);

                //Ahora creamos la escucha a los slider y declaramos una variable tipo Color para que se almacena el
                //el valor segun los valores del RGB del slider.
                ChangeListener sliderListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int r = sliderRojo.getValue();
                        int g = sliderVerde.getValue();
                        int b = sliderAzul.getValue();
                        Color colorPersonalizado = new Color(r, g, b);
                        laminaDos.setColorFondo(colorPersonalizado); //Como tenemos hecho referencia a la lamina12,
                        //podemos acceder a sus métodos y guardar el color
                        //para despues cambiarlo.
                    }
                };

                //Añado la escucha a los slider
                sliderRojo.addChangeListener(sliderListener);
                sliderVerde.addChangeListener(sliderListener);
                sliderAzul.addChangeListener(sliderListener);

            }
        });

        //Ahora agregamos el ActionListenes a cada boton para que cuando se presione cambie de color
        radio1.addActionListener(escucha);
        radio2.addActionListener(escucha);
        radio3.addActionListener(escucha);
        radio4.addActionListener(escucha);
        radio5.addActionListener(escucha);
        radio6.addActionListener(escucha);

    }

    public void paintComponent(Graphics g){
        //Decoración de la lamina
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; // Conversion de Graphics en Graphics2D
        g2D.setFont(new Font("Arial", Font.ITALIC, 16));
        g2D.drawString("Selecciona el Color De Fondo",55,20);
        g2D.drawLine(10,15,50,15);
        g2D.drawLine(275,15,950,15);
        g2D.drawLine(10,15,10,500);
        g2D.drawLine(950,15,950,500);
        g2D.drawLine(10,500,950,500);
    }
}

class laminaDos extends JPanel{
    //Inicilaizador Lamina2
    laminaDos(){
        //BORDE Y TITULO
        setLayout(null);
        setBackground(Color.RED); //Dejamos por defecto que sea rojo
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; // Conversion de Graphics en Graphics2D
        g2D.setFont(new Font("Arial", Font.ITALIC, 16));
        g2D.drawString("Comprobar el color",55,20);
        g2D.drawLine(10,15,50,15);
        g2D.drawLine(200,15,950,15);
        g2D.drawLine(10,15,10,500);
        g2D.drawLine(950,15,950,500);
        g2D.drawLine(10,500,950,500);
    }
    //Metodo para asignar el color
    public void setColorFondo(Color color) {
        setBackground(color);
    }
}

class laminaTres extends JPanel{
    private laminaCuatro laminaCuatro;
    
    laminaTres(laminaCuatro laminaCuatro){
        this.laminaCuatro = laminaCuatro;
        setLayout(null);
        ButtonGroup tipografia = new ButtonGroup();
        JRadioButton ComicSans = new JRadioButton("ComicSans",true);
        ComicSans.setBounds(60, 100, 120, 30);
        JRadioButton Serif = new JRadioButton("Serif");
        Serif.setBounds(60, 150, 120, 30);
        JRadioButton Magneto = new JRadioButton("Magneto");
        Magneto.setBounds(60, 200, 120, 30);
        JRadioButton Broadway = new JRadioButton("Broadway");
        Broadway.setBounds(60, 250, 120, 30);
        JButton personalizar = new JButton("Personalizar");
        personalizar.setBounds(700, 255, 140, 60);
        add(personalizar);
        add(ComicSans);
        add(Serif);
        add(Magneto);
        add(Broadway);
        tipografia.add(ComicSans);
        tipografia.add(Serif);
        tipografia.add(Magneto);
        tipografia.add(Broadway);

        ButtonGroup tamaños = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("5");
        radio1.setBounds(500, 100, 120, 30);
        JRadioButton radio2 = new JRadioButton("10");
        radio2.setBounds(500, 150, 120, 30);
        JRadioButton radio3 = new JRadioButton("15");
        radio3.setBounds(500, 200, 120, 30);
        JRadioButton radio4 = new JRadioButton("20");
        radio4.setBounds(500, 250, 120, 30);
        JRadioButton radio5 = new JRadioButton("30");
        radio5.setBounds(500, 300, 120, 30);
        JRadioButton radio6 = new JRadioButton("50", true);
        radio6.setBounds(500, 350, 120, 30);

        add(radio1);
        add(radio2);
        add(radio3);
        add(radio4);
        add(radio5);
        add(radio6);
        tamaños.add(radio1);
        tamaños.add(radio2);
        tamaños.add(radio3);
        tamaños.add(radio4);
        tamaños.add(radio5);
        tamaños.add(radio6);

        ActionListener fuenteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton radioButton = (JRadioButton) e.getSource();

                if (radioButton == ComicSans) {
                    laminaCuatro.setFont("Comic Sans MS");
                } else if (radioButton == Serif) {
                    laminaCuatro.setFont("Serif");
                } else if (radioButton == Magneto) {
                    laminaCuatro.setFont("Magneto");
                } else if (radioButton == Broadway) {
                    laminaCuatro.setFont("Broadway");
                }
            }

        };

        personalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ventana o marco centrado
                int tamx = 400;
                int tamy = 100;
                JFrame venPerso = new JFrame("RGB");
                venPerso.setSize(tamx,tamy);
                Toolkit myScreen = Toolkit.getDefaultToolkit();
                Dimension resolution = myScreen.getScreenSize();
                int resx=resolution.width;
                int resy=resolution.height;
                venPerso.setLocation(resx/2-tamx/2,resy/2-tamy/2);

                //Panel donde distribuimos sobre 1 celda y 2 columnas
                JPanel panelPersonalizar = new JPanel();
                panelPersonalizar.setLayout(new GridLayout(1, 2));

                //Creamos el label del tamaño y su slider
                JLabel lblsize = new JLabel("Tamaño:");
                JSlider slsize = new JSlider(5, 50);
                slsize.setMajorTickSpacing(5);
                slsize.setMinorTickSpacing(1);
                slsize.setPaintTicks(true);
                slsize.setPaintLabels(true);

                //Añadimos al panel el label y el slider
                panelPersonalizar.add(lblsize);
                panelPersonalizar.add(slsize);

                //Añadimos el panel a la ventana o marco centrado
                venPerso.add(panelPersonalizar);
                venPerso.setVisible(true);

                //Para los slider el metodo de escucha es el ChangeListener
                ChangeListener sliderListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int size = slsize.getValue(); //Guardamos los valores del slider en una variable
                        laminaCuatro.setTamayo(size); // accedemos al metodo de la lamina4 y le pasamos el tamaño
                    }
                };

                //Por último añadimos la escucha al slider como siempre
                slsize.addChangeListener(sliderListener);
            }
        });

        ComicSans.addActionListener(fuenteListener);
        Serif.addActionListener(fuenteListener);
        Magneto.addActionListener(fuenteListener);
        Broadway.addActionListener(fuenteListener);

// ActionListener para los botones de tamaño, es lo mismo
        ActionListener tamanoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton radioButton = (JRadioButton) e.getSource();

                if (radioButton == radio1){
                    laminaCuatro.setTamayo(5);
                } else if (radioButton == radio2){
                    laminaCuatro.setTamayo(10);
                } else if (radioButton == radio3){
                    laminaCuatro.setTamayo(15);
                } else if (radioButton == radio4){
                    laminaCuatro.setTamayo(20);
                } else if (radioButton == radio5){
                    laminaCuatro.setTamayo(30);
                } else if (radioButton == radio6){
                    laminaCuatro.setTamayo(50);
                }
            }
        };

        radio1.addActionListener(tamanoListener);
        radio2.addActionListener(tamanoListener);
        radio3.addActionListener(tamanoListener);
        radio4.addActionListener(tamanoListener);
        radio5.addActionListener(tamanoListener);
        radio6.addActionListener(tamanoListener);

    }
    
    


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; // Conversion de Graphics en Graphics2D
        g2D.setFont(new Font("Arial", Font.ITALIC, 16));
        g2D.drawString("Tipografia y Tamaño",55,20);
        g2D.setFont(new Font("Arial", Font.BOLD, 12));
        g2D.drawString("Tipografia:",40,70);
        g2D.drawString("Tamaño:",430,70);
        g2D.drawLine(10,15,50,15);
        g2D.drawLine(210,15,950,15);
        g2D.drawLine(10,15,10,500);
        g2D.drawLine(950,15,950,500);
        g2D.drawLine(10,500,950,500);
    }
}

class laminaCuatro extends JPanel{
    Color colorLetra;
    JLabel test;
    String font;
    int size;

    laminaCuatro() {
        setLayout(null);
        //Yo he creado un label un texto TEST y centrado el texto
        test = new JLabel("TEST");
        test.setBounds(500, 20, 450075, 200);
        test.setForeground(Color.RED);
        add(test);
        setFont("Magneto"); // Por defecto el tipo de fuente será Magneto
        setTamayo(50); //Por defecto el tamaño lo he dejado en 50
    }

    void setTamayo(int i){
        this.size = i;
        repaint();
    }

    void setFont(String fuente){
        this.font = fuente;
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; // Conversion de Graphics en Graphics2D
        g2D.setFont(new Font("Arial", Font.ITALIC, 16));
        g2D.drawString("Comprobar el color",55,20);
        g2D.drawLine(10,15,50,15);
        g2D.drawLine(200,15,950,15);
        g2D.drawLine(10,15,10,500);
        g2D.drawLine(950,15,950,500);
        g2D.drawLine(10,500,950,500);
        test.setFont(new Font(font, Font.BOLD, size));
    }
}

public class MarcoGrid {
    public static void main(String[] args) {
        crearMarco marco = new crearMarco(600,600);
        marco.setVisible(true);
    }

}
