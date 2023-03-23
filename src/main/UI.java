package main;

import object.ObjectChest;
import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font segoe_32, segoe_60B, comic_80B;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public int commandNumber = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        segoe_32 = new Font("Segoe UI", Font.PLAIN, 32);
        segoe_60B = new Font("Segoe UI", Font.BOLD, 60);
        comic_80B = new Font("Comic Sans MS", Font.BOLD, 80);
        ObjectKey key = new ObjectKey();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        //Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //Play State
        if (gp.gameState == gp.playState) {
            if (gameFinished) {
                g2.setFont(segoe_32);
                g2.setColor(Color.white);

                String text;
                int textLength;
                int x, y;

                text = "You found the treasure!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 - gp.tileSize*3;
                g2.drawString(text, x, y);

                text = "Your time is " + decimalFormat.format(playTime) + "!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 + gp.tileSize*4;
                g2.drawString(text, x, y);

                g2.setFont(segoe_60B);
                g2.setColor(Color.yellow);
                text = "Congratulations!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 + gp.tileSize*2;
                g2.drawString(text, x, y);

                gp.gameThread = null;
            } else {
                g2.setFont(segoe_32);
                g2.setColor(Color.white);
                g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
                g2.drawString("x " + gp.player.hasKey, gp.tileSize/2 + gp.tileSize, gp.tileSize/3 + gp.tileSize);

                //Time counter
                playTime += (double) 1/60;
                g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize* 12, gp.tileSize/3 + gp.tileSize);

                //Message
                if (messageOn) {
                    g2.setFont(g2.getFont().deriveFont(20F));
                    g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                    messageCounter++;

                    if (messageCounter > 120) { //120fr / 60fr = 2s text display time
                        messageCounter = 0;
                        messageOn = false;
                    }
                }
            }
        }
        //Pause state
        if (gp.gameState == gp.pauseState) {
            g2.setColor(new Color(0, 0, 0, 128));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(comic_80B);
            g2.setColor(Color.white);
            drawPauseScreen();
        }
    }

    public void drawTitleScreen() {
        g2.setFont(segoe_60B.deriveFont(Font.BOLD, 90F));
        String text = "Maze Exploration";
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        int y = gp.tileSize*3;

        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        //Main title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Some image
        x = gp.screenWidth/2 - gp.tileSize;
        y = gp.tileSize*6;
//        g2.drawImage(gp.player.right1, x, y , gp.tileSize*2, gp.tileSize*2, null);
        g2.drawImage(new ObjectChest().image, x, y , gp.tileSize*2, gp.tileSize*2, null);

        //Menu
        g2.setFont(segoe_60B.deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.white);

        text = "START GAME";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        y = gp.tileSize*12;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        y = gp.tileSize*13;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
}
