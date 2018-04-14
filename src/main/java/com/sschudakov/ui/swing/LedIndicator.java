/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschudakov.ui.swing;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Andrey
 */
public class LedIndicator extends JPanel {
    private Image yellowLedImage;
    private Image orangeLedImage;
    private Image violetLedImage;
    private Image greenLedImage;
    private Image cyanLedImage;
    private Image blueLedImage;
    private Image redLedImage;

    private Image currentImage;

    public enum LedColor {
        YELLOW,
        ORANGE,
        VIOLET,
        GREEN,
        CYAN,
        BLUE,
        RED
    }

    public LedIndicator() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            yellowLedImage = ImageIO.read(new File(classLoader.getResource("images/led_yellow.png").getFile()));
            orangeLedImage = ImageIO.read(new File(classLoader.getResource("images/led_orange.png").getFile()));
            violetLedImage = ImageIO.read(new File(classLoader.getResource("images/led_violet.png").getFile()));
            greenLedImage = ImageIO.read(new File(classLoader.getResource("images/led_green.png").getFile()));
            cyanLedImage = ImageIO.read(new File(classLoader.getResource("images/led_cyan.png").getFile()));
            blueLedImage = ImageIO.read(new File(classLoader.getResource("images/led_blue.png").getFile()));
            redLedImage = ImageIO.read(new File(classLoader.getResource("images/led_red.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setColor(LedColor.RED);
    }

    public void setColor(LedColor color) {
        switch (color) {
            case YELLOW:
                currentImage = yellowLedImage; break;
            case ORANGE:
                currentImage = orangeLedImage; break;
            case VIOLET:
                currentImage = violetLedImage; break;
            case GREEN:
                currentImage = greenLedImage; break;
            case CYAN:
                currentImage = cyanLedImage; break;
            case BLUE:
                currentImage = blueLedImage; break;
            case RED:
                currentImage = redLedImage; break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 1, 6, null);
        }
    }
}
