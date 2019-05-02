import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameoverPanel extends JPanel{

    private BufferedImage backgroundImg;

    public GameoverPanel(){

        try {
            backgroundImg = ImageIO.read(new File("./media/gameOver.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backgroundImg, 0, 0, 1200, 800, this);
    }
}
