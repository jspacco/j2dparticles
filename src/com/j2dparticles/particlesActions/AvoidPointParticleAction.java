package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.awt.Point;
import java.util.List;

/**
 * AvoidPointParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class AvoidPointParticleAction implements ParticleAction
{
    private Point point = new Point();

    /**
     * AvoidPointParticleAction
     *
     * @param point Point
     */
    public AvoidPointParticleAction( Point point )
    {
        this.point = point;
    }
    
    /**
     * doAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void doAction( List<Particle> particles )
    {

    }
}
