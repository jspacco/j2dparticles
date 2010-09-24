package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import com.j2dparticles.data.Velocity;
import java.util.List;

/**
 * BounceRectangleParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class BounceRectangleParticleAction implements ParticleAction
{
    private static final int NW = 0;
    private static final int N  = 1;
    private static final int NE = 2;
    private static final int W  = 3;
    private static final int E  = 4;
    private static final int SW = 5;
    private static final int S  = 6;
    private static final int SE = 7;
    private static final int NULL = -1;

    private int x;
    private int y;
    private int width;
    private int height;

    private float resilience = 1.0f;

    /**
     * BounceRectangleParticleAction
     *
     */
    public BounceRectangleParticleAction()
    {
    }

    /**
     * BounceRectangleParticleAction
     *
     * @param x int
     * @param y int
     * @param width int
     * @param height int
     * @param resilience float
     */
    public BounceRectangleParticleAction( int x, int y, int width, int height, float resilience )
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if ( resilience < 0 )
        {
            this.resilience = 0;
        }

        else if ( resilience > 1 )
        {
            this.resilience = 1;
        }

        else
        {
            this.resilience = resilience;
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
     * @param value int
     */
    public void setX( int value )
    {
        this.x = value;
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
     * @param value int
     */
    public void setY( int value )
    {
        this.y = value;
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
     * @param value int
     */
    public void setWidth( int value )
    {
        this.width = value;
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
     * @param value int
     */
    public void setHeight( int value )
    {
        this.height = value;
    }

    /*
     * The purpose of this method is to find out the direction
     * of the particles and from which side it enters.
     * So it change the velocity of the particle based on the
     * direction angle and multiplied by the resilience of the
     * object here declared.
     *
     *
     *   NW  N  NE
     *   W   X  E
     *   SW  S  SE
     */
    @Override
    public void doAction( List<Particle> particles )
    {
        for ( Particle p : particles )
        {
            int cX = (int)p.getCurrentPosition().x;
            int cY = (int)p.getCurrentPosition().y;

            if ( cX > x && cX < x + width &&
                 cY > y && cY < y + height )
            {
               // get previous position

                int pX = (int)p.getPreviousPosition().x;
                int pY = (int)p.getPreviousPosition().y;

                int pos = NULL;

                if ( pY <= y ) // NW N NE
                {
                    if ( pX > x && pX < x + width )  // N
                    {
                       pos = N;
                    }
                    else if ( pX < x )   // NW
                    {
                        pos = NW;
                    }
                    else // NE
                    {
                        pos = NE;
                    }
                }

                else if ( pY > y && pY < y + height ) // W  X  E
                {
                    if ( pX <= x ) // W
                    {
                        pos = W;
                    }

                    else if ( pX >= x + width ) // E
                    {
                        pos = E;
                    }
                }

                else // SW  S  SE
                {
                    if ( pX >= x && pX <= x + width )  // S
                    {
                       pos = S;
                    }
                    else if ( pX <= x )   // SW
                    {
                        pos = SW;
                    }
                    else // SE
                    {
                        pos = SE;
                    }
                }

                /*
                 *   | \   c
                 * b |   .                 b / a =   e / d
                 *   | e |  d
                     |___|______\
                 *          a
                 */
                if ( pos == NW )
                {
                    int b = cY - pY;
                    int a = cX - pX;

                    int d = cX - x;

                    int mul = a * d;

                    if ( mul == 0 ) // it avoids division by zero
                    {
                        mul = 1;
                    }

                    int e = b / mul;

                    if ( cY - e < y )  // top
                    {
                        p.setCurrentPosition( new Position( p.getCurrentPosition().x,
                                                            y + 1 ) );

                        p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx * resilience,
                                                            - p.getCurrentVelocity().dy * resilience ) );
                    }
                    else // left
                    {
                        p.setCurrentPosition( new Position( x + 1,
                                                            p.getCurrentPosition().y ) );

                        p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx * resilience,
                                                              p.getCurrentVelocity().dy * resilience ) );

                    }
                }

                if ( pos == N )
                {
                    p.setCurrentPosition( new Position( p.getCurrentPosition().x,
                                                        y + 1 ) );

                    p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx * resilience,
                                                        - p.getCurrentVelocity().dy * resilience ) );
                }

                if ( pos == NE )
                {
                    int b = cY - pY;
                    int a = pX - cX;

                    int d = x + width - cX;

                    int e = b /a * d;

                    if ( cY - e < y )  // top
                    {
                        p.setCurrentPosition( new Position( p.getCurrentPosition().x,
                                                            y ) );

                        p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx * resilience,
                                                            - p.getCurrentVelocity().dy * resilience ) );
                    }
                    else // right
                    {
                        p.setCurrentPosition( new Position( x + width,
                                                            p.getCurrentPosition().y ) );

                        p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx * resilience,
                                                              p.getCurrentVelocity().dy * resilience ) );

                    }
                }

                if ( pos == W )
                {
                    p.setCurrentPosition( new Position( x,
                                                        p.getCurrentPosition().y ) );

                    p.setCurrentVelocity( new Velocity( -  p.getCurrentVelocity().dx * resilience,
                                                           p.getCurrentVelocity().dy * resilience ) );

                }

                if ( pos == E )
                {
                    p.setCurrentPosition( new Position( x + width,
                                                        p.getCurrentPosition().y ) );

                    p.setCurrentVelocity( new Velocity(  - p.getCurrentVelocity().dx * resilience,
                                                           p.getCurrentVelocity().dy * resilience ) );
                }

                if ( pos == SW )
                {
                    int b = cY - pY;
                    int a = cX - pX;

                    int d = cX - x;

                    int mul = a * d;

                    if ( mul == 0 ) // it avoids division by zero
                    {
                        mul = 1;
                    }

                    int e = b / mul;

                    if ( cY - e < y + height )  // bottom
                    {
                        p.setCurrentPosition( new Position( p.getCurrentPosition().x,
                                                            y + height ) );

                        p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx * resilience,
                                                            - p.getCurrentVelocity().dy * resilience ) );
                    }
                    else // left
                    {
                        p.setCurrentPosition( new Position( x,
                                                            p.getCurrentPosition().y ) );

                        p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx * resilience,
                                                              p.getCurrentVelocity().dy * resilience ) );

                    }

                }

                if ( pos == S )
                {
                    p.setCurrentPosition( new Position( p.getCurrentPosition().x,
                                                        y + height ) );

                    p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx * resilience,
                                                        - p.getCurrentVelocity().dy * resilience ) );

                }

                if ( pos == SE )
                {
                    int b = cY - pY;
                    int a = pX - cX;

                    int d = x + width - cX;

                    int e = b /a * d;

                    if ( cY - e < y + height )  // top
                    {
                        p.setCurrentPosition( new Position( p.getCurrentPosition().x,
                                                            y ) );

                        p.setCurrentVelocity( new Velocity(   p.getCurrentVelocity().dx * resilience,
                                                            - p.getCurrentVelocity().dy * resilience ) );
                    }
                    else // right
                    {
                        p.setCurrentPosition( new Position( x + width,
                                                            p.getCurrentPosition().y ) );

                        p.setCurrentVelocity( new Velocity( - p.getCurrentVelocity().dx * resilience,
                                                              p.getCurrentVelocity().dy * resilience ) );

                    }
                }
            }
        }        
    }
}