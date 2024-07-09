import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileReader;
//clase que implementa la interfaz gŕafica
public class graficaCSV {
    public static void main(String[] args){
        Bienvenida();
    }
    //metodo que implementa la ventana que le da la bienvenida al usuario y le permite seleccionar el primer archivo CSV. Después que esto ocurre se cierra y abre la ventana completa del programa
    private static void Bienvenida(){
        JFrame ventIinicial=new JFrame("GraficaCSV+");
        ventIinicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventIinicial.setSize(400,300);

        JLabel mensajeBienvenida= new JLabel("<html>Bienvenido a graficaCSV+, para comenzar carga un archivo CSV.</html>",SwingConstants.CENTER);
        JButton carguador=new JButton("carguar archivo");

        //funiconalidad del boton carguador
        carguador.addActionListener(
            e -> {
                //objeto para naveguar entre ficheros
                JFileChooser navegador=new JFileChooser();
                //solo permitirá seleccionar archivos CSV
                navegador.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
                //navegar para seleccionar un archivo
                navegador.showOpenDialog(navegador);
                //carguar archivo csv
                if(navegador.getSelectedFile()!=null)
                {
                    try{
                        csv canvas=new csv(navegador.getSelectedFile().getAbsolutePath());
                        //si la carga es exitosa cerrar la ventana de bienvenida
                        ventIinicial.setVisible(false);
                        ventIinicial.dispose();
                        //abrir la ventana final
                        System.out.println("hola mundo");
                    }catch(Exception i){
                        JOptionPane.showMessageDialog(null,"Nombre del error: "+i.getClass().getSimpleName(),"No se pudo leer el archivo",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );

        //agregar componentes a la ventana y hacer visible
        ventIinicial.getContentPane().add(BorderLayout.CENTER,mensajeBienvenida);
        ventIinicial.getContentPane().add(BorderLayout.SOUTH,carguador);
        ventIinicial.setVisible(true);
    }
    private static void VentFinal(csv primerCSV){
        
    }
}
