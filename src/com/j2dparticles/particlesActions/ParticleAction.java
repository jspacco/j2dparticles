package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.util.List;

/**
 * ParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public interface ParticleAction
{
    public void doAction( List<Particle> particles );
}
