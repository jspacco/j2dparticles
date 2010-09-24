package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.util.List;

/**
 * KillParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class KillParticleAction implements ParticleAction
{
    long ageToDie;

    /**
     * KillParticleAction
     *
     */
    public KillParticleAction()
    {
    }

    /**
     * KillParticleAction
     *
     * @param ageToDie long
     */
    public KillParticleAction( long ageToDie )
    {
        this.ageToDie = ageToDie;
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
            long age = System.currentTimeMillis() - p.getStartTime();

            if ( age > ageToDie )
            {
                p.setAlive( false );
            }
        }
    }
}
