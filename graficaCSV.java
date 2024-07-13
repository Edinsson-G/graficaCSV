import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
//clase que implementa la interfaz gŕafica
public class graficaCSV{
    //objeto con la informacion del csv procesada
    private static csv pinturas;
    private static JTable tabla;
    private static JScrollPane scroll;
    private static JPanel superior;
    private static JPanel panelTabla;
    public static void main(String[] args){
        JFrame ventIinicial=new JFrame("GraficaCSV+");
        ventIinicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventIinicial.setSize(800,500);
        //texto de bienvenida
        JLabel mensajeBienvenida= new JLabel("<html>Bienvenido a graficaCSV+, para pintar carga un archivo CSV.</html>",SwingConstants.CENTER);
        //panel en el que se guardarán los botones de cargar y pintar
        JPanel botones=new JPanel();
        //boton para cargar archivo
        JButton carguador=new JButton("Cargar archivo");
        //boton para generar figura
        JButton pintar=new JButton("Pintar");
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
                        tabla.getColumnModel().getColumn(4).setCellRenderer(
                            new DisenoCelda(colores())
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
                                                tabla.getColumnModel().getColumn(4).setCellRenderer(
                                                    new DisenoCelda(colores())
                                                );
                                            }
                                        }
                                    }
                                }
                            }
                        );

                        //evitar que se edite por medio de mouse o teclado
                        tabla.setDefaultEditor(Object.class, null);
                        //eliminar la tabla actual si ya se habia cargado un archivo
                        if(panelTabla!=null){
                            ventIinicial.getContentPane().remove(panelTabla);
                        }
                        scroll=new JScrollPane(tabla);
                        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
                        //agregar scroll y un mensaje

                        panelTabla=new JPanel();
                        panelTabla.setLayout(new BoxLayout(panelTabla,BoxLayout.Y_AXIS));
                        JLabel instrucciones=new JLabel("Puedes seleccionar el color una figura para cambiarlo.");
                        instrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
                        panelTabla.add(instrucciones);
                        panelTabla.add(scroll);
                        panelTabla.setBorder(new EmptyBorder(8,8,8,8));

                        ventIinicial.getContentPane().add(BorderLayout.CENTER,panelTabla);
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
                    new lienzo(pinturas);
                }else{
                    JOptionPane.showMessageDialog(null,"Error inesperado, no se encontró ningún archivo CSV cargado","Error inesperado",JOptionPane.ERROR_MESSAGE);
                }
            }
        );
        pintar.setEnabled(false);

        //agregar los botones al panel
        botones.add(carguador);
        botones.add(pintar);

        //agreguar mensaje de bienvenida y botones a un panel
        superior=new JPanel();
        superior.setLayout(new BoxLayout(superior,BoxLayout.Y_AXIS));
        mensajeBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        botones.setAlignmentX(Component.CENTER_ALIGNMENT);
        superior.add(mensajeBienvenida);
        superior.add(botones);
        superior.setBorder(new EmptyBorder(8,8,8,8));
        

        
        
        //agregar componentes a la ventana y hacer visible
        ventIinicial.getContentPane().add(BorderLayout.NORTH,superior);
        //ventIinicial.getContentPane().add(BorderLayout.CENTER,panelTabla);
        ventIinicial.setVisible(true);
    }
    private static List<Color> colores(){
        List<Color> colores=new ArrayList<>();
        for(figura figActual:pinturas.figuras()){
            colores.add(figActual.color());
        }
        return colores;
    }
}