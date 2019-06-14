import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage image;

    public Image(String path){
        File img_path = new File(path);
        try{
            image = ImageIO.read(img_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getBlurredImage(){
        BufferedImage copy = new BufferedImage(this.image.getWidth(), this.image.getHeight(), this.image.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(this.image, 0, 0, null);
        g.dispose();


        for(int j = 1; j <= this.image.getHeight() - 2; j++) {
            for(int i = 1; i <= this.image.getWidth() - 2; i++) {
                int alpha = 0;
                int red = 0;
                int green = 0;
                int blue = 0;

                /** 3x3 grid:
                 *
                 *  012
                 *  345
                 *  678
                 *
                 *  -------
                 *  (in loop)
                 *
                 *  0 - alpha
                 *  1 - red
                 *  2 - green
                 *  3 - blue
                 **/
                int[][] positionsRgb = new int[9][4];

                positionsRgb[0] = Color.getARGBFromInt(this.image.getRGB(i - 1, j - 1));
                positionsRgb[1] = Color.getARGBFromInt(this.image.getRGB(i, j - 1));
                positionsRgb[2] = Color.getARGBFromInt(this.image.getRGB(i + 1, j - 1));

                positionsRgb[3] = Color.getARGBFromInt(this.image.getRGB(i - 1, j));
                positionsRgb[4] = Color.getARGBFromInt(this.image.getRGB(i, j));
                positionsRgb[5] = Color.getARGBFromInt(this.image.getRGB(i + 1, j));

                positionsRgb[6] = Color.getARGBFromInt(this.image.getRGB(i - 1, j + 1));
                positionsRgb[7] = Color.getARGBFromInt(this.image.getRGB(i, j + 1));
                positionsRgb[8] = Color.getARGBFromInt(this.image.getRGB(i + 1, j + 1));

                for(int k = 0; k < positionsRgb.length; k++){
                    alpha += positionsRgb[k][0];
                    red += positionsRgb[k][1];
                    green += positionsRgb[k][2];
                    blue += positionsRgb[k][3];
                }

                alpha /= 9;
                red /= 9;
                green /= 9;
                blue /= 9;

                copy.setRGB(i - 1, j - 1, (Color.getIntFromARGB(new int[]{alpha, red, green, blue})));
            }
        }

        return copy;
    }

}
