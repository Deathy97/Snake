package javi.serpiente.applet;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class Serpiente {

    public enum Direccion {
        ARRIBA, ABAJO, IZQUIERDA, DERECHA
    };

    private ArrayList<Pieza> snake;
    private final Applet applet;
    private final Comida comida;
    private Direccion direccion = null;
    private Pieza cabeza = null;
    private Pieza cola = null;

    public Serpiente(Applet applet, Comida comida){
        snake = new ArrayList<>();
        this.applet = applet;
        this.comida = comida;
        snake.add(new Pieza(20, 20));
        cabeza = snake.get(0);
        cola = snake.get(0);
        direccion = Direccion.DERECHA;
    }

    public void paint(Graphics g){
        for (Pieza pieza : snake) pieza.paint(g);
    }

    public void mover(){
        int x = 0, y = 0;
        switch (direccion){
            case DERECHA:
                x = cabeza.x + cabeza.width;
                y = cabeza.y;
                break;
            case IZQUIERDA:
                x = cabeza.x - cabeza.width;
                y = cabeza.y;
                break;
            case ARRIBA:
                x = cabeza.x;
                y = cabeza.y - cabeza.height;
                break;
            case ABAJO:
                x = cabeza.x;
                y = cabeza.y + cabeza.height;
                break;
        }
        Pieza nueva = new Pieza(x, y);
        cabeza = nueva;
        snake.add(cabeza);
        if (!cabeza.intersects(comida)){
            snake.remove(cola);
        } else {
            comida.relocate();
        }
        cola = snake.get(0);
        System.out.printf("La cabeza tiene índice %d y la cola %d%n", snake.indexOf(cabeza), snake.indexOf(cola));
    }

    private boolean permitirGiro(Direccion nuevaDireccion){
        switch (nuevaDireccion){
            case IZQUIERDA:
                if (direccion == Direccion.DERECHA) return false;
                break;
            case ABAJO:
                if (direccion == Direccion.ARRIBA) return  false;
                break;
            case DERECHA:
                if (direccion == Direccion.IZQUIERDA) return false;
                break;
            case ARRIBA:
                if (direccion == Direccion.ABAJO) return  false;
                break;
        }
        return  true;
    }

    public void girar(Direccion direccion){
        if (permitirGiro(direccion)) this.direccion = direccion;
    }

    public boolean dead(){
        if (cabeza.x < 0) return true;
        if (cabeza.x > 800 - cabeza.width) return true;
        if (cabeza.y < 0) return true;
        if (cabeza.y > 600 - cabeza.width) return true;
        Pieza piezaIterada = snake.get(0); // La cola
        while (piezaIterada != cabeza){
            if (cabeza.intersects(piezaIterada)) return true;
            piezaIterada = snake.get(snake.indexOf(piezaIterada) + 1);
        }
        return false;
    }
}
