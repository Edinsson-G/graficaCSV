import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
//clase que implementa la interfaz gŕafica
public class graficaCSV{
    //canvas en el que trazará el dibujo
    //panel en el que mostrara en formato de tabla el resumen del csv
    private JPanel tabla;
    //csv actual 
    public static void main(String[] args){
        JFrame ventIinicial=new JFrame("GraficaCSV+");
        ventIinicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventIinicial.setSize(400,300);

        JLabel mensajeBienvenida= new JLabel("<html>Bienvenido a graficaCSV+, para comenzar carga un archivo CSV.</html>",SwingConstants.CENTER);
        JButton carguador=new JButton("carguar archivo");

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
                        JFrame lienzo=new JFrame();
                        csv pinturas=new csv(navegador.getSelectedFile().getAbsolutePath());
                        lienzo.getContentPane().add(BorderLayout.CENTER,pinturas);
                        lienzo.setSize(pinturas.ancho(),pinturas.alto());
                        lienzo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        lienzo.setResizable(false);
                        lienzo.setVisible(true);
                        //si la carga es exitosa cerrar la ventana de bienvenida
                        //ventIinicial.setVisible(false);
                        //ventIinicial.dispose();
                    }catch(Exception i){
                        JOptionPane.showMessageDialog(null,"Nombre del error: "+i.getClass().getSimpleName(),"No se pudo leer el archivo",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );
        //agregar componentes a la ventana y hacer visible
        ventIinicial.getContentPane().add(BorderLayout.LINE_END,mensajeBienvenida);
        ventIinicial.getContentPane().add(BorderLayout.SOUTH,carguador);
        
        ventIinicial.setVisible(true);
            
        
    }
}
