package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Velocity;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * VelocityParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class VelocityParticleSource implements SourceAction
{
    private double initialX, initialY, finalX, finalY;

    private Velocity velocity = null;

    /**
     * VelocityParticleSource
     *
     * @param velocity Velocity
     */
    public VelocityParticleSource( Velocity velocity )
    {
        this.velocity = velocity;
    }

    /**
     * VelocityParticleSource
     *
     * @param initialX double
     * @param initialY double
     * @param finalX double
     * @param finalY double
     */
    public VelocityParticleSource( double initialX, double initialY, double finalX, double finalY )
    {
        this.initialX = Math.min( initialX, finalX );
        this.initialY = Math.min( initialY, finalY );
        this.finalX = Math.max( initialX, finalX );
        this.finalY = Math.max( initialY, finalY );
    }
    
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        if ( velocity != null  )
        {
            for ( Particle p : particles )
            {
                p.setCurrentVelocity( velocity );
                p.setPreviousVelocity( velocity );
            }
        }

        else
        {
            for ( Particle p : particles )
            {
                p.setCurrentVelocity( new Velocity( initialX + RandomValue.getFromRange( finalX - initialX ),
                                                    initialY + RandomValue.getFromRange( finalY - initialY )  ) );
            }
        }
    }

    /**
     * getInitialX
     *
     * @return double
     */
    public double getInitialX()
    {
        return initialX;
    }

    /**
     * setInitialX
     *
     * @param initialX double
     */
    public void setInitialX( double initialX )
    {
        this.initialX = initialX;
    }

    /**
     * getInitialY
     *
     * @return double
     */
    public double getInitialY()
    {
        return initialY;
    }

    /**
     * setInitialY
     *
     * @param initialY double
     */
    public void setInitialY( double initialY )
    {
        this.initialY = initialY;
    }

    /**
     * getFinalX
     *
     * @return double
     */
    public double getFinalX()
    {
        return finalX;
    }

    /**
     * setFinalX
     *
     * @param finalX double
     */
    public void setFinalX( double finalX )
    {
        this.finalX = finalX;
    }

    /**
     * getFinalY
     *
     * @return double
     */
    public double getFinalY()
    {
        return finalY;
    }

    /**
     * setFinalY
     *
     * @param finalY double
     */
    public void setFinalY( double finalY )
    {
        this.finalY = finalY;
    }

    /**
     * getVelocity
     *
     * @return Velocity
     */
    public Velocity getVelocity()
    {
        return velocity;
    }

    /**
     * setVelocity
     *
     * @param velocity Velocity
     */
    public void setVelocity( Velocity velocity )
    {
        this.velocity = velocity;
    }
}