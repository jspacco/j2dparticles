package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.awt.Color;
import java.util.List;

/**
 * ColorToColorParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ColorToColorParticleAction implements ParticleAction
{
    private Color startColor = Color.white;
    private Color endColor = Color.black;

    /**
     * ColorToColorParticleAction
     *
     * @param startColor Color
     * @param endColor Color
     */
    public ColorToColorParticleAction( Color startColor, Color endColor )
    {
        this.startColor = startColor;
        this.endColor = endColor;
    }
    
    /**
     * doAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void doAction( List<Particle> particles )
    {
        for ( Particle p : particles )
        {
            int r1 = startColor.getRed();
            int g1 = startColor.getGreen();
            int b1 = startColor.getBlue();
            int a1 = startColor.getAlpha();

            int r2 = endColor.getRed();
            int g2 = endColor.getGreen();
            int b2 = endColor.getBlue();
            int a2 = endColor.getAlpha();

            double age = System.currentTimeMillis() - p.getStartTime();

            int r = (int) getAverageColor( r1, r2, age, p.getEndTime() );
            int g = (int) getAverageColor( g1, g2, age, p.getEndTime() );
            int b = (int) getAverageColor( b1, b2, age, p.getEndTime() );
            int a = (int) getAverageColor( a1, a2, age, p.getEndTime() );

            p.setColor( new Color( r, g, b, a ) );
        }
    }

    /**
     * getAverageColor
     *
     * @param start int
     * @param end int
     * @param age double
     * @param endTime double
     * @return double
     */
    private double getAverageColor( int start, int end, double age, double endTime )
    {
        int min = start;
        int max = end;

        double result = ((((max - min) / endTime) * age) + min);

        result = result > 255 ? 255 : result;
        result = result < 0   ? 0   : result;

        return result;
    }
}