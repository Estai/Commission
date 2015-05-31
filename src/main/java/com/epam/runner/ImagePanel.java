package com.epam.runner;

import com.epam.runner.FillerBlank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImagePanel extends JPanel {


    private BufferedImage image;

    public ImagePanel() {
        try {
            image = ImageIO.read(new File("D:/blank1.jpg"));
        } catch (IOException ex) {
            System.err.println("Error");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(5,5,2,2);
        g.drawImage(image, 0, 0, null);

    }
    }

