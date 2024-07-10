import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class csv extends JPanel{
    private int alto;
    private int ancho;
    private List<figura> figuras;
    private JPanel panel;
    public csv(String ruta)throws FileNotFoundException,IOException{
        //contenido del archivo csv
        BufferedReader contenido=new BufferedReader(new FileReader(ruta));
        //leer el primer renglon para obtener el alto y ancho de la ventana
        StringTokenizer valores=new StringTokenizer(contenido.readLine(),";");
        valores.nextToken();//se ignora la palabra "Viewport" o cualquiera que aparezca en su luguar
        this.ancho=Integer.parseInt(valores.nextToken());
        this.alto=Integer.parseInt(valores.nextToken());
        
        //se leen las siguientes lineas
        String linea;
        int x1;
        int y1;
        int x2;
        int y2;
        String tipo;
        this.figuras=new ArrayList<>();
        while((linea=contenido.readLine())!=null){
            valores=new StringTokenizer(linea,";");
            tipo=valores.nextToken();
            //System.out.println(valores.nextToken());
            x1=Integer.parseInt(valores.nextToken().replaceAll(" ", ""));//se convierte de cadena a entero
            y1=Integer.parseInt(valores.nextToken().replaceAll(" ", ""));
            x2=Integer.parseInt(valores.nextToken().replaceAll(" ", ""));
            y2=Integer.parseInt(valores.nextToken().replaceAll(" ", ""));
            this.figuras.add(new figura(x1, y1, x2, y2, tipo));
        }
        contenido.close();
        //crear la ventana
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(this.ancho,this.alto);
        //setUndecorated(true);
        //setVisible(true);
    }
    public int alto(){
        return this.alto;
    }
    public int ancho(){
        return this.ancho;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //dibujar las figuras
        for(figura poligono:this.figuras){
            dibujar(poligono.x1(), poligono.y1(),poligono.x2(), poligono.y2(),poligono.tipo(), poligono.color(), g);
        }
    }
    private void dibujar(int x1,int y1,int x2,int y2,String tipo,Color color,Graphics g){
        g.setColor(color);
        if(tipo.equals("Rectangle"))
        {
            g.fillRect(x1,y1,x2-x1,y2-y1);
        }else if (tipo.equals("Elipse")) {
            g.fillOval(x1, y1,x2-x1,y2-y1);
        }else{
            JOptionPane.showMessageDialog(null,"No se reconoce la figura "+tipo+"\n tipo de figuras admitidas: Rectangulo y elipse","Forma no conocida",JOptionPane.ERROR_MESSAGE);
        }
    }
}
