import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

    public Boolean running;
    public Boolean levelUP;
    public Game game;
    public App app;

    private Thread thread;
    public Level level;

    public BufferedImage backgroundImg;
    public BufferedImage levelUp;
    public BufferedImage hp;

    public GamePanel(App app){
        this.app = app;
        level = Level.LEVEL1;
        running = false;
        levelUP = false;
        addKeyBinding();

        try {
            backgroundImg = ImageIO.read(new File("./media/sky.jpg"));
            levelUp = ImageIO.read(new File("./media/LevelUP.png"));
            hp = ImageIO.read(new File("./media/hp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        game.update();
        repaint();

        if(game.gameOver)
            app.changeToGameover();

        if(game.score >= 60) {
            if(level.equals(Level.LEVEL1))
                level = Level.LEVEL2;

            else if(level.equals(Level.LEVEL2))
                level = Level.LEVEL3;

            app.changeToLevelUP();
        }
    }

    public synchronized void start(){
        SoundEffect.SONG.loop();
        game = new Game(level);

        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(backgroundImg, 0, 0, 1200, 800, this);

        if (game.leftHP == 3) {
            g.drawImage(hp, 100, 730, this);
            g.drawImage(hp, 120, 730, this);
            g.drawImage(hp, 140, 730, this);
        } else if (game.leftHP == 2) {
            g.drawImage(hp, 100, 730, this);
            g.drawImage(hp, 120, 730, this);
        } else if (game.leftHP == 1) {
            g.drawImage(hp, 100, 730, this);
        }

        for(Cloud c: game.clouds)
            g.drawImage(c.render.getImage(), c.x, c.y, this);

        g.drawImage(game.chicken.render.getImage(), game.chicken.x, game.chicken.y, this);

        for (Egg e : game.chicken.eggs) {

            if (e.direction.equals("Left"))
                g.drawImage(e.renderLeft.getImage(), e.x, e.y, this);

            else if (e.direction.equals("Right"))
                g.drawImage(e.renderRight.getImage(), e.x, e.y, this);
        }

        for (Target t : game.targets)
            g.drawImage(t.render.getImage(), t.x, t.y, this);

        for(Enemy e: game.enemies)
            g.drawImage(e.render.getImage(), e.x, e.y, this);

        g.drawString("Score: " + game.score, 450, 750);

        if(level.equals(level.LEVEL1))
            g.drawString("Level = LEVEL1", 550, 750);

        else if(level.equals(level.LEVEL2))
            g.drawString("Level = LEVEL2", 550, 750);

        else if(level.equals(level.LEVEL3))
            g.drawString("Level = LEVEL3", 550, 750);
    }



    public void run() {
        start();

        int fps = 0;
        int frames = 0;
        long totalTime = 0;
        long curTime = System.currentTimeMillis();
        long lastTime = curTime;

        try {
            while (true) {
                lastTime = curTime;
                curTime = System.currentTimeMillis();
                totalTime += curTime - lastTime;

                if (totalTime > 1000) {
                    totalTime -= 1000;
                    fps = frames;
                    System.out.println("Fps: " + fps);
                    frames = 0;
                }

                frames++;

                update();
                Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        stop();
    }

    private void addKeyBinding(){
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"),
                "MOVE_LEFT");
        this.getActionMap().put("MOVE_LEFT",
                new MoveAction(-6, 0));

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"),
                "MOVE_RIGHT");
        this.getActionMap().put("MOVE_RIGHT",
                new MoveAction(6, 0));

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"),
                "MOVE_UP");
        this.getActionMap().put("MOVE_UP",
                new MoveAction(0, -5));

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"),
                "MOVE_DOWN");
        this.getActionMap().put("MOVE_DOWN",
                new MoveAction(0, 6));

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"),
                "EGG_LEFT");
        this.getActionMap().put("EGG_LEFT",
                new EggAction("LEFT"));

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"),
                "EGG_RIGHT");
        this.getActionMap().put("EGG_RIGHT",
                new EggAction("RIGHT"));
    }

    private class MoveAction extends AbstractAction{
        private int velocityX;
        private int velocityY;

        public MoveAction(int x, int y) {
            velocityX = x;
            velocityY = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.chicken.velX = velocityX;
            game.chicken.velY = velocityY;
        }
    }

    private class EggAction extends AbstractAction{
        private String direction;

        public EggAction(String s){
            direction = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(direction.equals("LEFT")) game.chicken.produceLeftEgg();
            else if(direction.equals("RIGHT")) game.chicken.produceRightEgg();
        }
    }


}
