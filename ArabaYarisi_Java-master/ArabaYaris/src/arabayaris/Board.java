/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabayaris;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.nio.file.Files;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener{
    private Yol yol;
    JFrame frame;
    public Clip clip;
    private int yuksekScor;
    private Araba araba;
    private List<Engel> engels;
    private final int B_WIDTH = 512;
    private final int B_HEIGHT = 512;
    private boolean ingame;
    private int Yoly;
    public static final String SND_CarCrash_FilenameWithPath = "sounds/carcrashfinal.wav";//çarpışma
    private final int CAR_X = 190;
    private final int CAR_Y = 400;
    private Engel engel;
    private Timer timer;
    private int count=0;
    private Random rand;
    private int sayac=0;
    private int b_w=512,b_h=512;
    private int Delay=15,q=50;    
    AlphaComposite ac;
    private final int[][] pos = {
        {135, 50},{240, -300}
};  
    public Board()
    {
        initBoard();
    }
    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;
        setPreferredSize(new Dimension(b_w, b_h));
        
        yol = new Yol(getWidth(),getHeight());
        araba = new Araba(CAR_X, CAR_Y);
        engel = new Engel(getWidth(),getHeight());
        initEngels();
        timer = new Timer(Delay, this);
        timer.start();
    }
    public void initEngels() {

        engels = new ArrayList<>();

        for (int[] p : pos) {
            engels.add(new Engel(p[0], p[1]));
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
           araba.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            araba.keyPressed(e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        inGame();
        yol.move(araba.getHizAyarla());
        araba.move();
       updateEngel();
        checkCollisions();
        repaint();
    }
    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(ingame)
          drawObjects(g);
        else
            try {
                drawGameOver(g);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
    public void drawGameOver(Graphics g) throws IOException {
        File file = new File("dosya.txt");
        if (!file.exists()) {
            file.createNewFile();           
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(Integer.toString(yol.a*Delay));
        bWriter.newLine();
        bWriter.close(); 

        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        while ((line = br.readLine()) != null) {
        
            if (yol.a > Integer.parseInt(line)) {
                 yuksekScor= yol.a*Delay;
            }else{
                 yuksekScor= Integer.parseInt(line);
            }
        }
        br.close();
        String msg = "Game Over"+ " Scor:: "+yol.a + " En Yuksek Scor:: "+yuksekScor;
        Font small = new Font("Courier", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.BLACK);
        g.setFont(small);
       // clip.stop();
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);

       // JOptionPane.showMessageDialog(frame, "Skorunuz:: "+yol.a);
    }
    private void updateEngel() {
        if(yol.a>100000){
            ingame=false;
        } 

        if (engels.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < engels.size(); i++) {

            Engel a = engels.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                engels.remove(i);
            }
        }
    }
    public void checkCollisions() {

        Rectangle r3 = araba.getBounds();

            for (Engel engel : engels) {

                Rectangle r2 = engel.getBounds();
    
                if (r3.intersects(r2)) {
                    araba.play(SND_CarCrash_FilenameWithPath);
                    engel.setVisible(false);
                    ingame = false;
                }
            }
    }
    private void drawObjects(Graphics g) {

        g.drawImage(yol.getImage(), yol.getX(), yol.getY(),this);
        for (Engel engel : engels) {
            if (engel.isVisible()) {
                g.drawImage(engel.getImage(), engel.getX(), engel.getY(), this);
            }
        }
        if(yol.getY()<=512)
        {
            g.drawImage(yol.getImage(), yol.getX(), yol.getY()-512,this);
            System.out.println("YOL:GETX:: "+ yol.getX()+"YOL.GETY():: "+yol.getY());
        }
        g.drawImage(araba.getImage(), araba.getX(), araba.getY(),this);
        g.dispose();
        
    }
}
