package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    private boolean gameStatus = false;
    private Thread thread;
    private Display display;
    private String title;
    private BufferStrategy bs;
    private Graphics g;
    public int width,height;
     
    public Game(String title,int width,int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init() {
        display = new Display(title, width, height);
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        


        bs.show();
        g.dispose();
    }

    private void tick() {

    }

    public void run() {
        init();
        while(gameStatus) {
            try {
                Thread.sleep(17);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tick();
            render();
        }
        stop();
    }

    public synchronized void start() {
        if (gameStatus) return;
        gameStatus = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!gameStatus) return;
        gameStatus = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}