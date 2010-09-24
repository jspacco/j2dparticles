package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.domains.Domain;
import java.util.List;

/**
 * BounceParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class BounceParticleAction implements ParticleAction
{
    private Domain domain;

    private float resilience = 1.0f;

    /**
     * BounceParticleAction
     *
     * @param domain Domain
     */
    public BounceParticleAction( Domain domain )
    {
        this( domain, 1.0f );
    }

    /**
     * BounceParticleAction
     *
     * @param domain Domain
     * @param resilience float
     */
    public BounceParticleAction( Domain domain, float resilience )
    {
        this.domain = domain;
        this.resilience = resilience;
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
            domain.bounce( p, resilience );
        }
    }

    /**
     * getDomain
     *
     * @return Domain
     */
    public Domain getDomain()
    {
        return domain;
    }
    /**
     * setDomain
     *
     * @param domain Domain
     */
    public void setDomain( Domain domain )
    {
        this.domain = domain;
    }

    /**
     * getResilience
     *
     * @return float
     */
    public float getResilience()
    {
        return resilience;
    }

    public void setResilience( float resilience )
    {
        this.resilience = resilience;
    }
}