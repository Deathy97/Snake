package javi.serpiente.applet;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class Comida extends Rectangle {

    private final Random rand = new Random();
    private final int ANCHO = 15, ALTO = 15;
    private final Applet applet;
    private Color color;

    public Comida(Applet applet){
        super(-100, -100, 0, 0);
        this.applet = applet;
        this.width = ANCHO;
        this.height = ALTO;
        relocate();
    }

    public void relocate() {
        int nuevaX = rand.nextInt(applet.getWidth() - width);
        int nuevaY = rand.nextInt(applet.getHeight() - height);
        move(nuevaX, nuevaY);
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
        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
    }
}
