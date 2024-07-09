public class figura {
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private String color;
    private String tipo;
    public figura(float x1,float y1,float x2,float y2,String tipo){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.color="blue";
        this.tipo=tipo;
    }
    //métodos que retornan los parametros de la clase
    public float x1(){
        return this.x1;
    }
    public float y1(){
        return this.y1;
    }
    public float x2(){
        return this.x2;
    }
    public float y2(){
        return this.y2;
    }
    //cambiar color
    public void repintar(String color){
        this.color=color;
    }
}
