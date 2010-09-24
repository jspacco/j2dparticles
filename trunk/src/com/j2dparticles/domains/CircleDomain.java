package com.j2dparticles.domains;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * CircleDomain
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class CircleDomain implements Domain
{
    private int centerX;
    private int centerY;
    private int radius;

    /**
     * CircleDomain
     *
     */
    public CircleDomain()
    {
    }

    /**
     * CircleDomain
     *
     * @param centerX int
     * @param centerY int
     * @param radius int
     */
    public CircleDomain( int centerX, int centerY, int radius )
    {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }
    
    /**
     * getCenterX
     *
     * @return float
     */
    public float getCenterX()
    {
        return centerX;
    }

    /**
     * setCenterX
     *
     * @param value int
     */
    public void setCenterX( int value )
    {
        this.centerX = value;
    }

    /**
     * getCenterY
     *
     * @return float
     */
    public float getCenterY()
    {
        return centerY;
    }

    /**
     * setCenterY
     *
     * @param value int
     */
    public void setCenterY( int value )
    {
        this.centerY = value;
    }

    /**
     * getRadius
     *
     * @return float
     */
    public float getRadius()
    {
        return radius;
    }

    /**
     * setRadius
     *
     * @param value int
     */
    public void setRadius( int value )
    {
        this.radius = value;
    }
    
    /**
     * contains
     *
     * @param x int
     * @param y int
     * @return boolean
     */
    @Override
    public boolean contains( int x, int y )
    {
        return false;
    }
    
    /**
     * createParticles
     *
     * @param rate int
     * @return List<Particle>
     */
    @Override
    public List<Particle> createParticles( int rate )
    {
        List<Particle> particles = new ArrayList<Particle>();

        for ( int i = 0; i < rate; i++ )
        {
            /*
            theta = angle * pi / 180
            x = centerX + radius * (cos(theta))
            y = centerY - radius * (sin(theta))
            */

            Particle p = new Particle();

            double theta = RandomValue.getFromRange( 360 ) * Math.PI / 180;

            double randRadius = RandomValue.getFromRange( radius );

            p.setCurrentPosition( new Position( centerX + randRadius * Math.cos( theta ),
                                                centerY + randRadius * Math.sin( theta ) ) );
            p.setPreviousPosition( p.getCurrentPosition() );

            particles.add( p );
        }

        return particles;
    }
    
    /**
     * bounce
     *
     * @param p Particle
     * @param resilience float
     */
    @Override
    public void bounce( Particle p, float resilience )
    {
    }
}