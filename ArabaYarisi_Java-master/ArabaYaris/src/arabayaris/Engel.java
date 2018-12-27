/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabayaris;

import java.util.Random;

public class Engel extends Sprite{
    private final int INITIAL_Y = 500; 
    Random rand = new Random();
    public Engel(int x,int y)
    {
        super(x,y);
        initEngel();
    }
    private void initEngel()
    {
        loadImage("ArabaYaris/engel.png");
        getImageDimensions();
    }
    public void move()
    {  
        if (y > 500) {
            y = 0;
        }

        y += 1;
  
    }

    
}
