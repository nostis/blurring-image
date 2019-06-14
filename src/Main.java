import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Image source = new Image("desert.jpg");

        try{
            BufferedImage bi = source.getBlurredImage();
            File output =  new File("blurred.jpg");
            ImageIO.write(bi, "jpg", output);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
