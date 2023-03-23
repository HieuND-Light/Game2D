package main;

import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;
import tile.Tile;
import tile.TileManager;

public class ObjectSetter {

    GamePanel gp;

    int mapNum = TileManager.mapNum;

    public ObjectSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        if (mapNum == 1) {
            gp.obj[0] = new ObjectKey();
            gp.obj[0].worldX = 4 * gp.tileSize;
            gp.obj[0].worldY = 6 * gp.tileSize;

            gp.obj[1] = new ObjectKey();
            gp.obj[1].worldX = 28 * gp.tileSize;
            gp.obj[1].worldY = 28 * gp.tileSize;

            gp.obj[2] = new ObjectKey();
            gp.obj[2].worldX = 2 * gp.tileSize;
            gp.obj[2].worldY = 27 * gp.tileSize;

            gp.obj[3] = new ObjectDoor();
            gp.obj[3].worldX = 5 * gp.tileSize;
            gp.obj[3].worldY = 3 * gp.tileSize;

            gp.obj[4] = new ObjectDoor();
            gp.obj[4].worldX = 25 * gp.tileSize;
            gp.obj[4].worldY = 6 * gp.tileSize;

            gp.obj[5] = new ObjectDoor();
            gp.obj[5].worldX = 5 * gp.tileSize;
            gp.obj[5].worldY = 27 * gp.tileSize;

            gp.obj[6] = new ObjectChest();
            gp.obj[6].worldX = 25 * gp.tileSize;
            gp.obj[6].worldY = 3 * gp.tileSize;

            gp.obj[7] = new ObjectBoots();
            gp.obj[7].worldX = 16 * gp.tileSize;
            gp.obj[7].worldY = 16 * gp.tileSize;
        } else if (mapNum == 2) {
            gp.obj[0] = new ObjectKey();
            gp.obj[0].worldX = 2 * gp.tileSize;
            gp.obj[0].worldY = 26 * gp.tileSize;

            gp.obj[1] = new ObjectDoor();
            gp.obj[1].worldX = 24 * gp.tileSize;
            gp.obj[1].worldY = 25 * gp.tileSize;

            gp.obj[2] = new ObjectChest();
            gp.obj[2].worldX = 27 * gp.tileSize;
            gp.obj[2].worldY = 26 * gp.tileSize;
        } else if (mapNum == 3) {
            gp.obj[0] = new ObjectKey();
            gp.obj[0].worldX = 3 * gp.tileSize;
            gp.obj[0].worldY = 13 * gp.tileSize;

            gp.obj[1] = new ObjectDoor();
            gp.obj[1].worldX = 23 * gp.tileSize;
            gp.obj[1].worldY = 26 * gp.tileSize;

            gp.obj[2] = new ObjectChest();
            gp.obj[2].worldX = 27 * gp.tileSize;
            gp.obj[2].worldY = 25 * gp.tileSize;
        } else if (mapNum == 4) {
            gp.obj[0] = new ObjectKey();
            gp.obj[0].worldX = 6 * gp.tileSize;
            gp.obj[0].worldY = 28 * gp.tileSize;

            gp.obj[1] = new ObjectDoor();
            gp.obj[1].worldX = 15 * gp.tileSize;
            gp.obj[1].worldY = 2 * gp.tileSize;

            gp.obj[2] = new ObjectChest();
            gp.obj[2].worldX = 28 * gp.tileSize;
            gp.obj[2].worldY = 28 * gp.tileSize;

            gp.obj[3] = new ObjectBoots();
            gp.obj[3].worldX = 7 * gp.tileSize;
            gp.obj[3].worldY = 2 * gp.tileSize;
        } else if (mapNum == 5) {
            gp.obj[0] = new ObjectKey();
            gp.obj[0].worldX = 1 * gp.tileSize;
            gp.obj[0].worldY = 25 * gp.tileSize;

            gp.obj[1] = new ObjectDoor();
            gp.obj[1].worldX = 26 * gp.tileSize;
            gp.obj[1].worldY = 25 * gp.tileSize;

            gp.obj[2] = new ObjectChest();
            gp.obj[2].worldX = 23 * gp.tileSize;
            gp.obj[2].worldY = 25 * gp.tileSize;
        }

    }
}
