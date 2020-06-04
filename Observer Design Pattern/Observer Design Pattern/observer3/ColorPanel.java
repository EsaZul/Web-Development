package observer3;  
import javax.swing.*;
import java.awt.*;


public class ColorPanel extends JPanel {
    private Color color;
    private float hue, saturation, brightness;

    public ColorPanel(float hue, float saturation, float brightness) {
        this.color = Color.getHSBColor(hue, saturation, brightness);
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }
    public void setColor(Color newColor) {
        this.color = newColor;
        repaint();
    }
    protected void paintComponent(Graphics g) {
        this.setBackground(color);
        super.paintComponent(g);
    }
    public void setHue(float hue){
        this.hue = hue;
    }
    public void setSaturation(float saturation){
        this.saturation = saturation;
    }
    public void setBrightness(float brightness){
        this.brightness = brightness;
    }
    public float getHue(){
        return this.hue;
    }
    public float getSaturation(){
        return this.saturation;
    }
    public float getBrightness(){
        return this.brightness;
    }
    public void changeColor(){
        Color newColor = Color.getHSBColor(this.hue, this.saturation, this.brightness);
        this.color = newColor;
        repaint();
    }
}