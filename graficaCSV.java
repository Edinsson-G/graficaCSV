import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
//clase que implementa la interfaz gŕafica
public class graficaCSV{
    public static csv pinturas;
    public static void main(String[] args){
        JFrame ventIinicial=new JFrame("GraficaCSV+");
        ventIinicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventIinicial.setSize(400,300);

        //texto de bienvenida
        JLabel mensajeBienvenida= new JLabel("<html>Bienvenido a graficaCSV+, para pintar carga un archivo CSV.</html>",SwingConstants.CENTER);

        //panel en el que se guardarán los botones de cargar y pintar
        JPanel botones=new JPanel();
        //boton para cargar archivo
        JButton carguador=new JButton("carguar archivo");
        //boton para generar figura
        JButton pintar=new JButton("pintar");
        //funiconalidad del boton carguador
        carguador.addActionListener(
            e -> {
                //objeto para naveguar entre ficheros
                JFileChooser navegador=new JFileChooser(".");
                //solo permitirá seleccionar archivos CSV
                navegador.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
                //navegar para seleccionar un archivo
                navegador.showOpenDialog(navegador);
                //carguar archivo csv
                if(navegador.getSelectedFile()!=null)
                {
                    try{
                        pinturas=new csv(navegador.getSelectedFile().getAbsolutePath());
                        pintar.setEnabled(true);
                        mensajeBienvenida.setText("se ha cargado "+navegador.getSelectedFile().getAbsolutePath());
                    }catch(Exception i){
                        JOptionPane.showMessageDialog(null,"Nombre del error: "+i.getClass().getSimpleName(),"No se pudo leer el archivo",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );
        pintar.addActionListener(
            e -> {
                if(pinturas!=null){
                    JFrame lienzo=new JFrame();
                    lienzo.getContentPane().add(BorderLayout.CENTER,pinturas);
                    lienzo.setSize(pinturas.ancho(),pinturas.alto());
                    lienzo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    lienzo.setResizable(false);
                    lienzo.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Error inesperado, no se encontró ningún archivo CSV cargado","Error inesperado",JOptionPane.ERROR_MESSAGE);
                }
            }
        );
        pintar.setEnabled(false);

        //agregar los botones al panel
        botones.add(carguador);
        botones.add(pintar);

        //agregar componentes a la ventana y hacer visible
        ventIinicial.getContentPane().add(BorderLayout.NORTH,mensajeBienvenida);
        ventIinicial.getContentPane().add(BorderLayout.CENTER,botones);
        ventIinicial.setVisible(true);
    }
}
