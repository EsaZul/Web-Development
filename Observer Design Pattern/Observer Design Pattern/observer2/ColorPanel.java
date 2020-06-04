package observer2;

  
import javax.swing.*;
import java.awt.*;


public class ColorPanel extends JPanel {
    private Color color;
    private float hue, saturation, brightness;

    public ColorPanel(Color initialColor) {
        this.color = initialColor;
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
    
    public void changeColor(){
        Color newColor = Color.getHSBColor(this.hue, this.saturation, this.brightness);
        this.color = newColor;
        repaint();
    }
}