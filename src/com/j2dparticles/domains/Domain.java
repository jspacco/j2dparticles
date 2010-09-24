package com.j2dparticles.domains;

import com.j2dparticles.data.Particle;
import java.util.List;

/**
 * Controls the execution of all particles groups included on it.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public interface Domain
{
    public boolean contains( int x, int y );

    public List<Particle> createParticles( int rate );
    
    public void bounce( Particle p, float resilience );
}
