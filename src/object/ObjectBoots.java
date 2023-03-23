package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectBoots extends SuperObject {

    public ObjectBoots() {
        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
