import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
//clase que aplica una renderizacion 
public class DisenoCelda extends DefaultTableCellRenderer{
    //lista ordenada con los colores de cada fila
    private List<Color> colores;
    public DisenoCelda(List<Color> colores){
        this.colores=colores;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column==4){
            setBackground(this.colores.get(row));
        }
        return this;
    }
}
