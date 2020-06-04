package observer3;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ComplementaryColorPanel extends ColorPanel implements PropertyChangeListener {
    public ComplementaryColorPanel(float hue, float saturation, float brightness) {
        super(hue, saturation, brightness);
        DisplayColors.originalColorPanel.addPropertyChangeListener(this);
    }
    public void propertyChange(PropertyChangeEvent evt) {
        float newHue = DisplayColors.originalColorPanel.getHue() - (float) .5;
        if (newHue < 0){
            newHue += 1;
        }
        Color newColor = Color.getHSBColor(newHue, DisplayColors.originalColorPanel.getSaturation(), DisplayColors.originalColorPanel.getBrightness());
        this.setColor(newColor);
    }
}