package c.c.colors;

import android.graphics.Color;


public class ColorFormater {
    private int red, green, blue, alpha;
    private float[] hsv;

    public ColorFormater(){
        alpha =255;
        hsv = new float[3];
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * zamienia kolor w postaci liczbowej na postać szesnastkową w przedziale #00000000 - #ffffffff
     */
    public String getHex(){
        long rgba=0x00000000 | alpha;
        rgba = rgba<<8 | red;
        rgba = rgba<<8 | green;
        rgba = rgba<<8 | blue;

        return "#"+String.format("%8s",Long.toHexString(rgba)).replace(' ','0');
    }

    /**
     * zwraca wartości HSV z przetypowaniem Hue z float na int
     */
    public Number[] getHsv() {
        Number[] newHSV = new Number[3];
        newHSV[0]=(int)hsv[0];
        newHSV[1]=(Math.ceil(hsv[1])==hsv[1]? (int)hsv[1]:hsv[1]);
        newHSV[2]=(Math.ceil(hsv[2])==hsv[2]? (int)hsv[2]:hsv[2]);
        return newHSV;
    }

    public void setHsv(float[] hsv) {
        this.hsv = hsv;
    }

    /**
     * konwersja z RGB do HSV
     */
    public void convertRGBtoHSV(){
        Color.RGBToHSV(red,green,blue,hsv);
    }

    /**
     * konwersja z HSV do RGB
     */
    public void convertHSVtoRGB(){
        long color = Color.HSVToColor(alpha,hsv);

        // zamiana z liczby na poszczególne składowe przez operacje bitowe
        blue  = (int) color & 0xff;
        green = (int) color >>8  & 0xff;
        red   = (int) color >>16 & 0xff;
        alpha = (int) color >>24 & 0xff;
    }
}
