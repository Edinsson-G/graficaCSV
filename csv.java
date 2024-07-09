import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class csv {
    private String alto;
    private String ancho;
    private List<figura> figuras;
    public csv(String ruta)throws FileNotFoundException,IOException{
        //contenido del archivo csv
        BufferedReader contenido=new BufferedReader(new FileReader(ruta));
        //leer el primer renglon para obtener el alto y ancho de la ventana
        StringTokenizer valores=new StringTokenizer(contenido.readLine(),";");
        valores.nextToken();//se ignora la palabra "Viewport" o cualquiera que aparezca en su luguar
        this.ancho=valores.nextToken();
        this.alto=valores.nextToken();
        //se leen las siguientes lineas
        String linea;
        float x1;
        float y1;
        float x2;
        float y2;
        String tipo;
        this.figuras=new ArrayList<>();
        while((linea=contenido.readLine())!=null){
            valores=new StringTokenizer(linea,";");
            tipo=valores.nextToken();
            x1=Float.parseFloat(valores.nextToken());//se convierte de cadena a flotante
            y1=Float.parseFloat(valores.nextToken());
            x2=Float.parseFloat(valores.nextToken());
            y2=Float.parseFloat(valores.nextToken());
            this.figuras.add(new figura(x1, y1, x2, y2, tipo));
        }
        contenido.close();
    }
}
