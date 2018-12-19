/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabayaris;

import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import javax.sound.sampled.*;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;


public class Araba extends Sprite{
    private int dx,dy;
    public Clip clip;
    public static final String SND_BackgroundMusic_FilenameWithPath = "sounds/backgroundmusic.wav";//oyuna başlama müzüği
    public static final String SND_CarImpactSound_FilenameWithPath = "sounds/carimpact.wav";//duvara deyme
    public static final String SND_CarCrash_FilenameWithPath = "sounds/carcrashfinal.wav";//çarpışma
    public static final String SND_CarPowerUp_FilenameWithPath = "sounds/motorpowerup.wav";//motor sesi
    private int hiz = 0;
    private Yol yol;
    public Araba(int x,int y)
    {
        super(x,y);
        initAraba();
    }
    private void initAraba()
    {
        loadImage("ArabaYaris/araba.png");
        play(SND_BackgroundMusic_FilenameWithPath);
        getImageDimensions();
    }
    public void move()
    {
        x+=dx;
        y+=dy;
       // System.out.println(" X::"+ x+" **Y::"+y + "  getHizAyarla::"+getHizAyarla()); 
      //  yol.hizArttir = getHizAyarla();
        if (x <= 120) {
            x = 120;
            play(SND_CarImpactSound_FilenameWithPath);
        }
        if(x>=277)
        {
            x=277;
            play(SND_CarImpactSound_FilenameWithPath);
        }

        if (y < 1) {
            y = 1;
        }
        if(y>=383)
        {
            y=383;
        }
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }    
        if (key == KeyEvent.VK_UP) {
            setHizAyarla(5);
            play(SND_CarPowerUp_FilenameWithPath);
        }
        
        if (key == KeyEvent.VK_DOWN) {
            setHizAyarla(-4);
        }    
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            setHizAyarla(0);
        }

        if (key == KeyEvent.VK_DOWN) {
            setHizAyarla(0);
        }
    }
    public static synchronized void play(final String fileName) 
    {
        // Note: use .wav files             
        new Thread(new Runnable() { 
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    URL url = this.getClass().getClassLoader().getResource(fileName);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
                    clip.open(inputStream);
                    clip.start(); 
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();
    } 
}
