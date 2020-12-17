package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class Map implements Serializable {
    public static final int EMPTY = 0;
    public static final int STRONG_WALL = 1;
    public static final int WEAK_WALL = 2;

    public int[][] map = new int[50][35];

    public Map(String filePath) {
        try{
            BufferedImage image = ImageIO.read(new File(filePath));

            for (int x = 0; x < 50; x++) {
                for (int y = 0; y < 35; y++) {
                    switch ((image.getRGB(x,y)  & 0xff0000) >> 16){
                        case 255:
                            map[x][y] = EMPTY;
                            break;
                        case 0:
                            map[x][y] = STRONG_WALL;
                            break;
                        default:
                            map[x][y] = WEAK_WALL;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
