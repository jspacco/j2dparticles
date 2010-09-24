package com.j2dparticles.domains;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * RingDomain
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class RingDomain implements Domain
{
    private int centerX;
    private int centerY;
    private int internalRadius;
    private int externalRadius;

    /**
     * RingDomain
     *
     * @param centerX int
     * @param centerY int
     * @param internalRadius int
     * @param externalRadius int
     */
    public RingDomain( int centerX, int centerY, int internalRadius, int externalRadius )
    {
        this.centerX = centerX;
        this.centerY = centerY;

        this.internalRadius = Math.min( internalRadius, externalRadius );
        this.externalRadius = Math.max( internalRadius, externalRadius );
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

            double randRadius = getInternalRadius() + RandomValue.getFromRange( getExternalRadius() - getInternalRadius() );

            p.setCurrentPosition( new Position( getCenterX() + randRadius * Math.cos( theta ),
                                                getCenterY() + randRadius * Math.sin( theta ) ) );

            p.setPreviousPosition( new Position( p.getCurrentPosition().x,
                                                 p.getCurrentPosition().y ) );

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
        throw new UnsupportedOperationException( "Not supported yet." );
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
     * @param centerX int
     */
    public void setCenterX( int centerX )
    {
        this.centerX = centerX;
    }

    /**
     * getCenterY
     *
     * @return int
     */
    public int getCenterY()
    {
        return centerY;
    }

    /**
     * setCenterY
     *
     * @param centerY int
     */
    public void setCenterY( int centerY )
    {
        this.centerY = centerY;
    }

    /**
     * getInternalRadius
     *
     * @return float
     */
    public float getInternalRadius()
    {
        return internalRadius;
    }

    /**
     * setInternalRadius
     *
     * @param internalRadius int
     */
    public void setInternalRadius( int internalRadius )
    {
        this.internalRadius = internalRadius;
    }

    /**
     * getExternalRadius
     *
     * @return float
     */
    public float getExternalRadius()
    {
        return externalRadius;
    }

    /**
     * setExternalRadius
     *
     * @param externalRadius int
     */
    public void setExternalRadius( int externalRadius )
    {
        this.externalRadius = externalRadius;
    }
}