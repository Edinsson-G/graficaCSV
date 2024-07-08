import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
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
                try{
                    f
                }catch(Exception i){
                    System.out.println("hola mundo");
                }
            }
        );

        //agregar componentes a la ventana y hacer visible
        ventIinicial.getContentPane().add(BorderLayout.CENTER,mensajeBienvenida);
        ventIinicial.getContentPane().add(BorderLayout.SOUTH,carguador);
        ventIinicial.setVisible(true);
    }
}
