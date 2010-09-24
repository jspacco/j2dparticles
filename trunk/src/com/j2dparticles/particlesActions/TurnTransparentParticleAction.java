package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.awt.Color;
import java.util.List;

/**
 * TurnTransparentParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class TurnTransparentParticleAction implements ParticleAction
{    
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
            int r = p.getColor().getRed();
            int g = p.getColor().getGreen();
            int b = p.getColor().getBlue();

            double age = System.currentTimeMillis() - p.getStartTime();

            // total =  255
            //  age    alpha

            int alpha = (int)(age * 255 / p.getEndTime() );

            if ( alpha < 255 )
            {
                p.setColor( new Color( r, g, b, Math.abs( alpha - 255 ) ) );
            }
        }
    }
}
