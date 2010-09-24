package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Velocity;
import java.util.List;

/**
 * GravityParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class GravityParticleAction implements ParticleAction
{   
    private double forceX;
    private double forceY;

    /**
     * GravityParticleAction
     *
     * @param x double
     * @param y double
     */
    public GravityParticleAction( double x, double y )
    {
        forceX = x;
        forceY = y;
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
            p.setCurrentVelocity( new Velocity( p.getCurrentVelocity().dx + forceX,
                                                p.getCurrentVelocity().dy + forceY ) );
        }
    }
    /**
     * getForceX
     *
     * @return double
     */
    public double getForceX()
    {
        return forceX;
    }

    /**
     * setForceX
     *
     * @param forceX double
     */
    public void setForceX( double forceX )
    {
        this.forceX = forceX;
    }

    /**
     * getForceY
     *
     * @return double
     */
    public double getForceY()
    {
        return forceY;
    }

    /**
     * setForceY
     *
     * @param forceY double
     */
    public void setForceY( double forceY )
    {
        this.forceY = forceY;
    }
}