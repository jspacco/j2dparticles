package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import com.j2dparticles.data.Velocity;
import java.util.List;

/**
 * AttractToTarget
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class AttractToTarget implements ParticleAction
{
    Position position = new Position();

    /**
     * AttractToTarget
     *
     * @param position Position
     */
    public AttractToTarget( Position position )
    {
        this.position = position;
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
            double hypo = Math.hypot( (position.x - p.getCurrentPosition().x),
                                      (position.y - p.getCurrentPosition().y) );

            double dx = (position.x - p.getCurrentPosition().x) / hypo;
            double dy = (position.y - p.getCurrentPosition().y) / hypo;

            p.setCurrentVelocity( new Velocity( p.getCurrentVelocity().dx + dx, p.getCurrentVelocity().dy + dy) );
        }
    }
}
