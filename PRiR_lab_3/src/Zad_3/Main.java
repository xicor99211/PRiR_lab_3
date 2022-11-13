package Zad_3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Main extends Thread {
    static BufferedImage image;
    static int width;
    static int height;
    int me;

    public Main(int me) throws IOException {
        this.me = me;
    }

    public void run() {

        if (me == 0) {

            for (int i = 0; i < height / 2; i++) {
                for (int j = 0; j < width / 2; j++) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());
                    int final_red, final_green, final_blue;

                    final_red = 255 - red;
                    final_green = 255 - green;
                    final_blue = 255 - blue;
                    Color newColor = new Color(final_red, final_green, final_blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
        } else if (me == 2) {

            for (int i = height / 2; i < height; i++) {
                for (int j = 0; j < width / 2; j++) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());
                    int final_red, final_green, final_blue;

                    final_red = 255 - red;
                    final_green = 255 - green;
                    final_blue = 255 - blue;
                    Color newColor = new Color(final_red, final_green, final_blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
        } else if (me == 1) {

            for (int i = 0; i < height / 2; i++) {
                for (int j = width / 2; j < width; j++) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());
                    int final_red, final_green, final_blue;

                    final_red = 255 - red;
                    final_green = 255 - green;
                    final_blue = 255 - blue;
                    Color newColor = new Color(final_red, final_green, final_blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
        } else if (me == 3) {

            for (int i = height / 2; i < height; i++) {
                for (int j = width / 2; j < width; j++) {


                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());
                    int final_red, final_green, final_blue;

                    final_red = 255 - red;
                    final_green = 255 - green;
                    final_blue = 255 - blue;
                    Color newColor = new Color(final_red, final_green, final_blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
        }

        File output = new File("grayscale.jpg");
        try {
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) throws InterruptedException, IOException {

        try {

            File input = new File("bialystok.png");
            image = ImageIO.read(input);

            width = image.getWidth();
            height = image.getHeight();

        } catch (Exception e) {
        }
        Main g1 = new Main(0);
        Main g2 = new Main(1);
        Main g3 = new Main(2);
        Main g4 = new Main(3);

        g1.start();
        g2.start();
        g3.start();
        g4.start();
    }
}