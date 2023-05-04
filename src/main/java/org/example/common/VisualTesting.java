package org.example.common;

import java.awt.image.BufferedImage;

public class VisualTesting {

    public static float calculateImageMatchPercentage(BufferedImage a, BufferedImage b, float allowedColorDrift) {

        if ( (a.getWidth() != b.getWidth()) ||
                (a.getHeight() != b.getHeight()) ) {
            throw new RuntimeException("");
        }

        int pixelsXAxis = a.getWidth();
        int pixelsYAxis = a.getHeight();
        int pixelsTotal = pixelsXAxis * pixelsYAxis;

        int pixelsMismatched = 0;
        for (int x = 0; x < pixelsXAxis; x++) {
            for (int y = 0; y < pixelsYAxis; y++) {
                int actualPixel = a.getRGB(x, y);
                int expectedPixel = b.getRGB(x, y);
                float actualExpectedDifferencePercentage = (float) (expectedPixel - actualPixel) / expectedPixel;
                if (actualExpectedDifferencePercentage >= (1.0f - allowedColorDrift)) {
                    pixelsMismatched++;
                }
            }
        }

        int pixelsMatched = pixelsTotal - pixelsMismatched;

        return (float) pixelsMatched / pixelsTotal;
    }

}
