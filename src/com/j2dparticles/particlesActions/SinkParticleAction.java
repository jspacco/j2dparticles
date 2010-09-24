package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.domains.Domain;
import java.util.List;


/**
 * SinkParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class SinkParticleAction implements ParticleAction
{
    private Domain domain;

    /**
     * SinkParticleAction
     *
     */
    public SinkParticleAction()
    {    
    }
    
    /**
     * SinkParticleAction
     *
     * @param domain Domain
     */
    public SinkParticleAction( Domain domain )
    {            
        this.domain = domain;
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
            boolean contains = domain.contains( (int)p.getCurrentPosition().x,
                                                (int)p.getCurrentPosition().y );

            if ( contains )
            {
                p.setAlive( false );
            }
        }
    }
}