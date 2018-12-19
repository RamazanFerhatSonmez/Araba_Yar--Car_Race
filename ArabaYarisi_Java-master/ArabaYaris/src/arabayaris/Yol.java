package arabayaris;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Yol extends Sprite{
    public int hiz=10,a=0;
    public Board board;
    public int dy;
    Araba car;
    public static final String SND_CarCrash_FilenameWithPath = "sounds/carcrashfinal.wav";
    public Yol(int x,int y)
    {
        super(x,y);
        initYOL();
    }
    private void initYOL()
    {
        loadImage("ArabaYaris/yolstart.png");
        getImageDimensions();      

    }
    public void move(int hizArttir)
    {
        y += hiz + hizArttir;
        a += hiz;
        if(y>=512){
            if(a > 100000){
                loadImage("ArabaYaris/yolfinish.png");
                getImageDimensions(); 
                hiz = 0;
                y=300;
            }else{
                loadImage("ArabaYaris/yol.png");
                getImageDimensions(); 
                y=0; 
            }
        }
            
    }
}

