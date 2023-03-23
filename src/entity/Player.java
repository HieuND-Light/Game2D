package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    int mapNum = TileManager.mapNum;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth/2 - (gp.tileSize / 2);
        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        solidArea = new Rectangle(); // On scale 48x48 not image dimension
        solidArea.x = 14;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 26;

        setDefaultValues();
        try {
            getPlayerImage();
        } catch (Exception e) {
            System.out.println("getPlayerImage() not working!");
        }
    }

    public void setDefaultValues() {
        //Starting point
        if (mapNum == 1) {
            worldX = 14 * gp.tileSize;
            worldY = 14 * gp.tileSize;
        } else if (mapNum == 2) {
            worldX = 1 * gp.tileSize;
            worldY = 2 * gp.tileSize;
        } else if (mapNum == 3) {
            worldX = 1 * gp.tileSize;
            worldY = 1 * gp.tileSize;
        } else if (mapNum == 4) {
            worldX = 2 * gp.tileSize;
            worldY = 2 * gp.tileSize;
        } else if (mapNum == 5) {
            worldX = 1 * gp.tileSize;
            worldY = 1 * gp.tileSize;
        }

        speed = 4;
        direction = "DOWN";
    }

    public void getPlayerImage() {
        try {
            System.out.println("Image loading start");
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            System.out.println("Image loading end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direction = "UP";
            }
            if (keyHandler.downPressed) {
                direction = "DOWN";
            }
            if (keyHandler.leftPressed) {
                direction = "LEFT";
            }
            if (keyHandler.rightPressed) {
                direction = "RIGHT";
            }

            //Tile Collision check
            collisionOn = false;
            gp.collisionHandler.checkTile(this);

            //Object Collision check
            int objIndex = gp.collisionHandler.checkObject(this, true);
            pickUpObject(objIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "UP":
                        worldY -= speed;
                        break;
                    case "DOWN":
                        worldY += speed;
                        break;
                    case "LEFT":
                        worldX -= speed;
                        break;
                    case "RIGHT":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 20) { //change image every 20 frames for the update() method is called every frame
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gp.playSoundEffect(2);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed increased!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction){ //simple sprite changer
            case "UP":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "DOWN":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "LEFT":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "RIGHT":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        //Check collision box
        g2.setColor(Color.black);
        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
}
