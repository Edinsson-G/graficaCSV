import java.awt.Color;
public class figura {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    private String tipo;
    public figura(int x1,int y1,int x2,int y2,String tipo){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        if(tipo.equals("Rectangle")){
            this.color=new Color(153,153,0);
        }else{
            this.color=Color.blue;
        }
        this.tipo=tipo;
    }
    //métodos que retornan los parametros de la clase
    public int x1(){
        return this.x1;
    }
    public int y1(){
        return this.y1;
    }
    public int x2(){
        return this.x2;
    }
    public int y2(){
        return this.y2;
    }
    public String tipo(){
        return this.tipo;
    }
    public Color color(){return this.color;}
    //cambiar color
    public void repintar(Color color){
        this.color=color;
    }
}
