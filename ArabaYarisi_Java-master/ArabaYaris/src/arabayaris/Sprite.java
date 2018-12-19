/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arabayaris;

import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
public class Sprite {
    protected int x;
    protected boolean visible;
    protected int y;
    protected int hiz;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }    
    public int getHizAyarla() {
        return hiz;
    } 
    public void setHizAyarla(int hiz) {
       this.hiz = hiz;
    } 
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}