/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschudakov.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Andrey
 */
public class IndicatorLed extends JPanel {
    private Image redLedImage;
    private Image greenLedImage;
    private Image currentImage;
    
    public IndicatorLed() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            redLedImage = ImageIO.read(new File(classLoader.getResource("images/led_red.png").getFile()));
            greenLedImage = ImageIO.read(new File(classLoader.getResource("images/led_green.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setRed();
    }
    
    public void setGreen() {
        currentImage = greenLedImage;
    }
    
    public void setRed() {
        currentImage = redLedImage;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage,1,6,null);
        }
    }
}
