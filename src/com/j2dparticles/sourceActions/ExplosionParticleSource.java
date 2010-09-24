package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Velocity;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * ExplosionParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ExplosionParticleSource implements SourceAction
{
    private double velocity;

    /**
     * ExplosionParticleSource
     *
     * @param velocity double
     */
    public ExplosionParticleSource( double velocity )
    {
        this.velocity = velocity;
    }
    
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        for ( Particle p : particles )
        {
            double theta = RandomValue.getFromRange( 360 ) * Math.PI / 180;

            p.setCurrentVelocity( new Velocity( velocity * Math.cos( theta ),
                                                velocity * Math.sin( theta ) ) );
        }
    }
}
