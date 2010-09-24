package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import java.util.List;

/**
 * SourceAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public interface SourceAction
{
    public void applyAction( List<Particle> particles );
}
