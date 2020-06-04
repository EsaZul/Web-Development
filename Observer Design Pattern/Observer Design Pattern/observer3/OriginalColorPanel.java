package observer3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class OriginalColorPanel extends ColorPanel implements ChangeListener {
    public OriginalColorPanel(float hue, float saturation, float brightness) {
        super(hue, saturation, brightness);
        DisplayColors.hueSlider.addChangeListener(this);
        DisplayColors.saturationSlider.addChangeListener(this);
        DisplayColors.brightnessSlider.addChangeListener(this);
    }
    public void stateChanged(ChangeEvent e) {
        if (DisplayColors.hueSlider != null && DisplayColors.saturationSlider != null && DisplayColors.brightnessSlider != null) {
            float newHue = (float) DisplayColors.hueSlider.getValue() / 100;
            float newSaturation = (float) DisplayColors.saturationSlider.getValue() / 100;
            float newBrightness = (float) DisplayColors.brightnessSlider.getValue() / 100;
            Color newColor = Color.getHSBColor(newHue, newSaturation, newBrightness);
            this.setColor(newColor);
            this.setHue(newHue);
            this.setSaturation(newSaturation);
            this.setBrightness(newBrightness);
        }
    }
}