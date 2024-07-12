import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
//clase que aplica una renderizacion 
public class DisenoCelda extends DefaultTableCellRenderer{
    //establece el color de la selda
    private Color color;
    //private final int fila;
    private int[] fila;
    public DisenoCelda(Color color,int[] fila){
        this.color=color;
        this.fila=fila;
        //System.out.println("inicializado como"+this.fila);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //aplicar el color personalizado
        //System.out.println("aqui llego como"+this.fila);
        if(Arrays.stream(this.fila).anyMatch(num -> num==row)&&4==column){
            setBackground(this.color);
        }
        // else{
        //     setBackground(table.getBackground());
        // }
        return this;
    }
}
