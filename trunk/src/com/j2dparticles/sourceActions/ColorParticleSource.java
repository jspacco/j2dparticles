package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import java.awt.Color;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * ColorParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ColorParticleSource implements SourceAction
{
    private Color currentColor = null;
    private Color singleColor = null;
    
    int r1, r2, g1, g2, b1, b2, a1, a2;

    private boolean applyToAll = true;

    /**
     * ColorParticleSource
     *
     * @param color Color
     */
    public ColorParticleSource( Color color )
    {
        this.singleColor = color;
    }

    /**
     * ColorParticleSource
     *
     * @param r1 int
     * @param g1 int
     * @param b1 int
     * @param r2 int
     * @param g2 int
     * @param b2 int
     */
    public ColorParticleSource( int r1, int g1, int b1,
                                int r2, int g2, int b2 )
    {
        this( r1, g1, b1, 255, r2, g2, b2, 255, true );
    }

    /**
     * ColorParticleSource
     *
     * @param r1 int
     * @param g1 int
     * @param b1 int
     * @param r2 int
     * @param g2 int
     * @param b2 int
     * @param applyToAll boolean
     */
    public ColorParticleSource( int r1, int g1, int b1,
                                int r2, int g2, int b2, boolean applyToAll )
    {
        this( r1, g1, b1, 255, r2, g2, b2, 255, applyToAll );
    }

    /**
     * ColorParticleSource
     *
     * @param r1 int
     * @param g1 int
     * @param b1 int
     * @param a1 int
     * @param r2 int
     * @param g2 int
     * @param b2 int
     * @param a2 int
     */
    public ColorParticleSource( int r1, int g1, int b1, int a1,
                                int r2, int g2, int b2, int a2 )
    {
        this( r1, g1, b1, 255, r2, g2, b2, 255, true );
    }

    /**
     * ColorParticleSource
     *
     * @param r1 int
     * @param g1 int
     * @param b1 int
     * @param a1 int
     * @param r2 int
     * @param g2 int
     * @param b2 int
     * @param a2 int
     * @param applyToAll boolean
     */
    public ColorParticleSource( int r1, int g1, int b1, int a1,
                                int r2, int g2, int b2, int a2,
                                boolean applyToAll )
    {
        this.r1 = r1;
        this.b1 = b1;
        this.g1 = g1;
        this.a1 = a1;

        this.r2 = r2;
        this.g2 = g2;
        this.b2 = b2;
        this.a2 = a2;

        this.applyToAll = applyToAll;
    }
    
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        if ( singleColor != null )
        {
            for ( Particle p : particles )
            {
                p.setColor( singleColor );
            }
        }

        else
        {
            if ( applyToAll )
            {
                for ( Particle p : particles )
                {
                    createColor();
                    p.setColor( currentColor );
                }
            }

            else
            {
                createColor();

                for ( Particle p : particles )
                {
                    p.setColor( currentColor );
                }
            }
        }
    }

    /**
     * createColor
     *
     */
    private void createColor()
    {
        currentColor = new Color( RandomValue.getRangeForColor( r1, r2 ),
                                  RandomValue.getRangeForColor( g1, g2 ),
                                  RandomValue.getRangeForColor( b1, b2 ),
                                  RandomValue.getRangeForColor( a1, a2 ) );

    }
}
