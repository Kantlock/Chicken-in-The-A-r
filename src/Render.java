import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Render {
    private BufferedImage image;
    private int width;
    private int height;

    public Render(String imagePath){
        try {
            BufferedImage temp = ImageIO.read(new File(imagePath));
            setWidth(temp.getWidth());
            setHeight(temp.getHeight());
            this.image = temp;
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
