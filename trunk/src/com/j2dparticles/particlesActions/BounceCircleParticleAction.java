package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.util.List;

/**
 * BounceCircleParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class BounceCircleParticleAction implements ParticleAction
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

    private int centerX;
    private int centerY;
    private int radius;

    private float resilience;

    public BounceCircleParticleAction()
    {
    }

    public BounceCircleParticleAction( int centerX, int centerY, int radius, float resilience )
    {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

        this.resilience = resilience;
    }

    public boolean contains( int x, int y )
    {
        return false;
    }

    @Override
    public void doAction( List<Particle> particles )
    {
        for ( Particle p : particles )
        {
            int cX = (int)p.getCurrentPosition().x;
            int cY = (int)p.getCurrentPosition().y;

            int pX = (int)p.getPreviousPosition().x;
            int pY = (int)p.getPreviousPosition().y;

            int distanceX = Math.abs( pX - cX );
            int distanceY = Math.abs( pY - cY );

            int hypot = (int) Math.hypot( distanceX, distanceY );

            if ( radius <= hypot )
            {
                // finding out direction of the particle

                boolean up    = false;
                boolean down  = false;
                boolean left  = false;
                boolean right = false;

                if ( pY <= cY )
                {
                    down = true;
                }
                else
                {
                    up = true;
                }

                if ( pX <= cX )
                {
                    right = true;
                }
                else
                {
                    left = true;
                }

                if ( down && left )
                {

                }

                else if ( down && right )
                {

                }

                else if ( up && left )
                {

                }

                else if ( up && right )
                {

                }


                int pos = NULL;

    //            if ( pY <= y ) // NW N NE
    //            {
    //                if ( pX > x && pX < x + width )  // N
    //                {
    //                   pos = N;
    //                }
    //                else if ( pX < x )   // NW
    //                {
    //                    pos = NW;
    //                }
    //                else // NE
    //                {
    //                    pos = NE;
    //                }
    //            }
    //
    //            else if ( pY > y && pY < y + height ) // W  X  E
    //            {
    //                if ( pX <= x ) // W
    //                {
    //                    pos = W;
    //                }
    //
    //                else if ( pX >= x + width ) // E
    //                {
    //                    pos = E;
    //                }
    //            }
    //
    //            else // SW  S  SE
    //            {
    //                if ( pX >= x && pX <= x + width )  // S
    //                {
    //                   pos = S;
    //                }
    //                else if ( pX <= x )   // SW
    //                {
    //                    pos = SW;
    //                }
    //                else // SE
    //                {
    //                    pos = SE;
    //                }
    //            }
            }
        }
        }
        

}
