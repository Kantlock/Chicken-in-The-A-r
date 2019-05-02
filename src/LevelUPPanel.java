import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelUPPanel extends JPanel {
    public BufferedImage img;

    public LevelUPPanel(){
        setLayout(null);

        try {
            img = ImageIO.read(new File("./media/LevelUP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(img, 350, 150, this);
    }
}
