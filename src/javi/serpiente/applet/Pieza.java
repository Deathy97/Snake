package javi.serpiente.applet;

import java.awt.*;
import java.util.Random;

public class Pieza extends Rectangle{

    private final int ANCHO = 15, ALTO = 15;
    Color color;

    public Pieza(int x, int y){
        super(-100, -100, 0, 0);
        this.width = ANCHO;
        this.height = ALTO;
        this.move(x, y);
        color = crearColorAleatorio();
    }

    private Color crearColorAleatorio(){
        Color color = null;
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        String hex = "0x" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        color = Color.decode(hex);
        return color;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
