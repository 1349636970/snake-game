package app;
public class Game implements Runnable{
    private boolean gameStatus = false;
    private Thread thread;
    public Game(String title,int width,int height) {
        new Display(title, width, height);
    }

    private void render() {

    }

    private void tick() {

    }

    public void run() {
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