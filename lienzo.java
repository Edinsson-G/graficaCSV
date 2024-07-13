//clase que implementa las ventanas en las que se muestran los dibujos

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class lienzo extends JFrame{
    private int xCliqueada,YCliqueada;
    public lienzo(csv pintura){
        super("pintura");
        setSize(pintura.ancho(),pintura.alto()+22);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);

        //agregar las figuras dibujadas al centro
        getContentPane().add(BorderLayout.CENTER,pintura);

        //crear un panel con los botones cerrar y exportar
        JPanel botones=new JPanel();
        botones.setSize(pintura.ancho(),22);
        botones.setBackground(Color.BLUE);
        
        //boton para cerrar
        JButton cerrar=new JButton("X");
        cerrar.addActionListener(
            e -> {
                setVisible(false);
                //setLocation(200,300);
                dispose();
            }
        );
        cerrar.setSize(new Dimension(pintura.ancho()/2-2,22));
        //evento de raton para cuando se arratre
        addMouseListener(
            new MouseListener() {
                public int xInicial;
                public int yInicial;
                @Override
                public void mouseReleased(MouseEvent e){
                    //calcular la diferencia entre clic inicial y final
                    int dxClic=e.getXOnScreen()-this.xInicial;
                    int dyClic=e.getYOnScreen()-this.yInicial;
                    //sumar la diferencia para hallar la unicacion real
                    int xDestino=getLocationOnScreen().x+dxClic;
                    int yDestino=getLocationOnScreen().y+dyClic;
                    setLocation(xDestino,yDestino);
                }
                @Override
                public void mouseClicked(MouseEvent e){

                }
                @Override
                public void mouseEntered(MouseEvent e){

                }
                @Override
                public void mouseExited(MouseEvent e){

                }
                @Override
                public void mousePressed(MouseEvent e){
                    this.xInicial=e.getXOnScreen();
                    this.yInicial=e.getYOnScreen();
                }
            }
        );

        //agreguar elementos
        botones.add(cerrar);
        getContentPane().add(BorderLayout.CENTER,pintura);
        getContentPane().add(BorderLayout.SOUTH,botones);
        setVisible(true);
    }
}
