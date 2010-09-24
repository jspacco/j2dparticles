
package com.j2dparticles.domains;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import com.j2dparticles.data.Velocity;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * RectangleDomain
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class RectangleDomain implements Domain
{
    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleDomain( int x, int y, int width, int height )
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * contains
     *
     * @param X int
     * @param Y int
     * @return boolean
     */
    @Override
    public boolean contains( int X, int Y )
    {
        Rectangle r = new Rectangle(x, y, width, height);

        return r.contains( X, Y );
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
        List<Particle> list = new ArrayList<Particle>();

        for ( int i = 0; i < rate; i++ )
        {
            Particle p = new Particle();
            p.setCurrentPosition( new Position( x + RandomValue.getFromRange( width ), y + RandomValue.getFromRange( height ) ) );
            p.setPreviousPosition( new Position( p.getCurrentPosition().x, p.getCurrentPosition().y ) );

            list.add( p );
        }

        return list;
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
        // find out particle direction

        boolean right = p.getCurrentPosition().x > p.getPreviousPosition().x;
        boolean down = p.getCurrentPosition().y > p.getPreviousPosition().y;

        if ( down && right )
        {
            p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx,
                                                - p.getCurrentVelocity().dy ));
        }

        else if ( down && !right )
        {
            p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx,
                                                - p.getCurrentVelocity().dy ));
        }

        else if ( !down && right )
        {
            p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx,
                                                  p.getCurrentVelocity().dy ));
        }

        else
        {
            p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx,
                                                  p.getCurrentVelocity().dy ));
        }
    }

    /**
     * getX
     *
     * @return int
     */
    public int getX()
    {
        return x;
    }

    /**
     * setX
     *
     * @param x int
     */
    public void setX( int x )
    {
        this.x = x;
    }

    /**
     * getY
     *
     * @return int
     */
    public int getY()
    {
        return y;
    }

    /**
     * setY
     *
     * @param y int
     */
    public void setY( int y )
    {
        this.y = y;
    }

    /**
     * getWidth
     *
     * @return int
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * setWidth
     *
     * @param width int
     */
    public void setWidth( int width )
    {
        this.width = width;
    }

    /**
     * getHeight
     *
     * @return int
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * setHeight
     *
     * @param height int
     */
    public void setHeight( int height )
    {
        this.height = height;
    }
}