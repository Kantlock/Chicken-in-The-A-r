import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel{

    private BufferedImage backgroundImg;

    public Menu(){

        setLayout(null);
        try {
            backgroundImg = ImageIO.read(new File("./media/menuBackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backgroundImg, 0, 0, this);
    }
}
