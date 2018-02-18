/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschudakov.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Andrey
 */
public class IndicatorLed extends JPanel {
    private Color color;
    
    public IndicatorLed() {
        setRed();
    }
    
    public void setGreen() {
        this.color = Color.green;
    }
    
    public void setRed() {
        this.color = Color.red;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(color);
        g.fillOval(1, 5, 16, 16);
        
        g.setColor(Color.black);
        g.drawOval(1, 5, 16, 16);
    }
}
