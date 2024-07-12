import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.stream.IntStream;
//clase que implementa la interfaz gŕafica
public class graficaCSV{
    //objeto con la informacion del csv procesada
    public static csv pinturas;
    public static JTable tabla;
    public static void main(String[] args){
        JFrame ventIinicial=new JFrame("GraficaCSV+");
        ventIinicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventIinicial.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
                        
                        //llenar una tabla con los datos de la pintura
                        String[] encabezado={
                            "#",
                            "Tipo",
                            "<html>(x<sub>1</sub>,y<sub>1</sub>)</html>",
                            "<html>(x<sub>2</sub>,y<sub>2</sub>)</html>",
                            "Color"
                        };
                        
                        String [][] contenido=new String[pinturas.figuras().size()][encabezado.length];
                        for(int i=0;i<pinturas.figuras().size();i++){
                            figura figActual=pinturas.figuras().get(i);
                            contenido[i][0]=Integer.toString(i+1);
                            contenido[i][1]=figActual.tipo();
                            contenido[i][2]="("+figActual.x1()+","+figActual.y1()+")";
                            contenido[i][3]="("+figActual.x2()+","+figActual.y2()+")";
                            //la columna de color no tiene texto, solo color de fondo
                            contenido[i][4]="   ";
                        }
                        //crear tabla
                        tabla=new JTable(new DefaultTableModel(contenido,encabezado));
                        //colorear la ultima columna
                        tabla.getColumnModel().getColumn(4).setCellRenderer(
                            new DisenoCelda(
                                pinturas.figuras().get(0).color(),
                                IntStream.rangeClosed(0,pinturas.figuras().size()-1).toArray()
                            )
                        );
                        
                        //agregar la funcion de cambiar color
                        tabla.addMouseListener(
                            new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e){
                                    //validar que se haya hecho exactamente un clic
                                    if(e.getClickCount()==1){
                                        JTable temp=(JTable)e.getSource();
                                        if(temp.getSelectedColumn()==4){
                                            //la seleccion fue en la columna del color (4)

                                            //paleta de colores flotante
                                            JColorChooser paleta=new JColorChooser();
                                            //variable que nos idica si realmente se selecciono un color
                                            int resultado=JOptionPane.showConfirmDialog(
                                                null,
                                                paleta,
                                                "cambiar color de lafigura "+temp.getSelectedRow()+1,
                                                JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.PLAIN_MESSAGE
                                            );
                                            if(resultado==JOptionPane.OK_OPTION){
                                                //el usuario realmente selecciono un color
                                                
                                                pinturas.figuras().get(temp.getSelectedRow()).repintar(paleta.getColor());
                                                //repintar la casilla correspondiente de la tabla
                                                int[]fila={temp.getSelectedRow()};
                                                System.out.println(fila[0]);
                                                tabla.getColumnModel().getColumn(4).setCellRenderer(
                                                    new DisenoCelda(
                                                        paleta.getColor(),
                                                        fila
                                                    )
                                                );
                                            }
                                        }
                                    }
                                }
                            }
                        );

                        //evitar que se edite por medio de mouse o teclado
                        tabla.setDefaultEditor(Object.class, null);
                        JScrollPane scroll=new JScrollPane(tabla);
                        ventIinicial.getContentPane().add(BorderLayout.SOUTH,scroll);
                        ventIinicial.revalidate();
                        ventIinicial.repaint();
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
