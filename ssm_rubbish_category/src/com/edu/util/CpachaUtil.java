// 
// 
// 

package com.edu.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImageOp;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Random;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class CpachaUtil
{
    private final char[] code;
    private final String[] fontNames;
    private final int[] fontStyles;
    private int vcodeLen;
    private int fontsize;
    private int width;
    private int height;
    private int disturbline;
    
    public CpachaUtil() {
        this.code = new char[] { '2', '3', '4', '5', '6', '7', '8', '9' };
        this.fontNames = new String[] { "\u9ed1\u4f53", "\u5b8b\u4f53", "Courier", "Arial", "Verdana", "Times", "Tahoma", "Georgia" };
        this.fontStyles = new int[] { 1, 3 };
        this.vcodeLen = 4;
        this.fontsize = 21;
        this.width = (this.fontsize + 1) * this.vcodeLen + 10;
        this.height = this.fontsize + 12;
        this.disturbline = 3;
    }
    
    public CpachaUtil(final int vcodeLen) {
        this.code = new char[] { '2', '3', '4', '5', '6', '7', '8', '9' };
        this.fontNames = new String[] { "\u9ed1\u4f53", "\u5b8b\u4f53", "Courier", "Arial", "Verdana", "Times", "Tahoma", "Georgia" };
        this.fontStyles = new int[] { 1, 3 };
        this.vcodeLen = 4;
        this.fontsize = 21;
        this.width = (this.fontsize + 1) * this.vcodeLen + 10;
        this.height = this.fontsize + 12;
        this.disturbline = 3;
        this.vcodeLen = vcodeLen;
        this.width = (this.fontsize + 1) * vcodeLen + 10;
    }
    
    public CpachaUtil(final int vcodeLen, final int width, final int height) {
        this.code = new char[] { '2', '3', '4', '5', '6', '7', '8', '9' };
        this.fontNames = new String[] { "\u9ed1\u4f53", "\u5b8b\u4f53", "Courier", "Arial", "Verdana", "Times", "Tahoma", "Georgia" };
        this.fontStyles = new int[] { 1, 3 };
        this.vcodeLen = 4;
        this.fontsize = 21;
        this.width = (this.fontsize + 1) * this.vcodeLen + 10;
        this.height = this.fontsize + 12;
        this.disturbline = 3;
        this.vcodeLen = vcodeLen;
        this.width = width;
        this.height = height;
    }
    
    public BufferedImage generatorVCodeImage(final String vcode, final boolean drawline) {
        final BufferedImage vcodeImage = new BufferedImage(this.width, this.height, 1);
        final Graphics g = vcodeImage.getGraphics();
        g.setColor(new Color(246, 240, 250));
        g.fillRect(0, 0, this.width, this.height);
        if (drawline) {
            this.drawDisturbLine(g);
        }
        final Random ran = new Random();
        for (int i = 0; i < vcode.length(); ++i) {
            g.setFont(new Font(this.fontNames[ran.nextInt(this.fontNames.length)], this.fontStyles[ran.nextInt(this.fontStyles.length)], this.fontsize));
            g.setColor(this.getRandomColor());
            g.drawString(new StringBuilder(String.valueOf(vcode.charAt(i))).toString(), i * this.fontsize + 10, this.fontsize + 5);
        }
        g.dispose();
        return vcodeImage;
    }
    
    public BufferedImage generatorRotateVCodeImage(final String vcode, final boolean drawline) {
        final BufferedImage rotateVcodeImage = new BufferedImage(this.width, this.height, 1);
        final Graphics2D g2d = rotateVcodeImage.createGraphics();
        g2d.setColor(new Color(246, 240, 250));
        g2d.fillRect(0, 0, this.width, this.height);
        if (drawline) {
            this.drawDisturbLine(g2d);
        }
        for (int i = 0; i < vcode.length(); ++i) {
            final BufferedImage rotateImage = this.getRotateImage(vcode.charAt(i));
            g2d.drawImage(rotateImage, null, (int)(this.height * 0.7) * i, 0);
        }
        g2d.dispose();
        return rotateVcodeImage;
    }
    
    public String generatorVCode() {
        final int len = this.code.length;
        final Random ran = new Random();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.vcodeLen; ++i) {
            final int index = ran.nextInt(len);
            sb.append(this.code[index]);
        }
        return sb.toString();
    }
    
    private void drawDisturbLine(final Graphics g) {
        final Random ran = new Random();
        for (int i = 0; i < this.disturbline; ++i) {
            final int x1 = ran.nextInt(this.width);
            final int y1 = ran.nextInt(this.height);
            final int x2 = ran.nextInt(this.width);
            final int y2 = ran.nextInt(this.height);
            g.setColor(this.getRandomColor());
            g.drawLine(x1, y1, x2, y2);
        }
    }
    
    private BufferedImage getRotateImage(final char c) {
        final BufferedImage rotateImage = new BufferedImage(this.height, this.height, 2);
        final Graphics2D g2d = rotateImage.createGraphics();
        g2d.setColor(new Color(255, 255, 255, 0));
        g2d.fillRect(0, 0, this.height, this.height);
        final Random ran = new Random();
        g2d.setFont(new Font(this.fontNames[ran.nextInt(this.fontNames.length)], this.fontStyles[ran.nextInt(this.fontStyles.length)], this.fontsize));
        g2d.setColor(this.getRandomColor());
        final double theta = this.getTheta();
        g2d.rotate(theta, this.height / 2, this.height / 2);
        g2d.drawString(Character.toString(c), (this.height - this.fontsize) / 2, this.fontsize + 5);
        g2d.dispose();
        return rotateImage;
    }
    
    private Color getRandomColor() {
        final Random ran = new Random();
        return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
    }
    
    private double getTheta() {
        return (((int)(Math.random() * 1000.0) % 2 == 0) ? -1 : 1) * Math.random();
    }
    
    public int getVcodeLen() {
        return this.vcodeLen;
    }
    
    public void setVcodeLen(final int vcodeLen) {
        this.width = (this.fontsize + 3) * vcodeLen + 10;
        this.vcodeLen = vcodeLen;
    }
    
    public int getFontsize() {
        return this.fontsize;
    }
    
    public void setFontsize(final int fontsize) {
        this.width = (fontsize + 3) * this.vcodeLen + 10;
        this.height = fontsize + 15;
        this.fontsize = fontsize;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public int getDisturbline() {
        return this.disturbline;
    }
    
    public void setDisturbline(final int disturbline) {
        this.disturbline = disturbline;
    }
}
