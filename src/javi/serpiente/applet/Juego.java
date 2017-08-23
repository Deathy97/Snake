package javi.serpiente.applet;

import java.applet.Applet;
import java.awt.*;

public class Juego extends Applet implements Runnable{

    private final int ANCHO = 800, ALTO = 600;

    private Thread mainThread;
    private Graphics aux;
    private Image buffer;
    private int ciclos, segundos;
    private int TIMER, SLEEP;
    private Serpiente serpiente;
    private Comida comida;
    private boolean gameLoop;

    @Override
    public void init() {
        setupApplet();
        setupJuego();
    }

    @Override
    public void start() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        while (gameLoop){
            ciclos++;
            if (ciclos % TIMER == 0){
                segundos++;
                System.out.println("Tiempo de juego: " + segundos);
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            serpiente.mover();
            gameLoop = !serpiente.dead();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        aux.setColor(Color.WHITE);
        aux.fillRect(0, 0, getWidth(), getHeight());
        comida.paint(aux);
        serpiente.paint(aux);
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    private void setupApplet() {
        resize(ANCHO, ALTO);
    }

    private void setupJuego() {
        buffer = createImage(ANCHO, ALTO);
        aux = buffer.getGraphics();
        ciclos = 0;
        segundos = 0;
        SLEEP = 100;
        TIMER = 1000 / SLEEP;
        comida = new Comida(this);
        serpiente = new Serpiente(this, comida);
        gameLoop = true;
    }

    @Override
    public boolean keyDown(Event evt, int key) {
        System.out.println(key);
        switch (key){
            case 1006:
                serpiente.girar(Serpiente.Direccion.IZQUIERDA);
                break;
            case 1007:
                serpiente.girar(Serpiente.Direccion.DERECHA);
                break;
            case 1004:
                serpiente.girar(Serpiente.Direccion.ARRIBA);
                break;
            case 1005:
                serpiente.girar(Serpiente.Direccion.ABAJO);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean mouseDown(Event evt, int x, int y) {
        if (comida.contains(x, y)) {
            comida.relocate();
        }
        return  true;
    }
}
