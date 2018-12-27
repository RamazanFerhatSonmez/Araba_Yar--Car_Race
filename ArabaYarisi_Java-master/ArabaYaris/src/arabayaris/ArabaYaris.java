
package arabayaris;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ArabaYaris extends JFrame {
    
    public ArabaYaris()
    {
        add(new Board());
        
        setTitle("Araba Yaris");
        
        setResizable(false);
        pack(); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArabaYaris ex = new ArabaYaris();
                ex.setVisible(true);
                System.out.println(".run()");
            }
        });
        
    }
    
}
