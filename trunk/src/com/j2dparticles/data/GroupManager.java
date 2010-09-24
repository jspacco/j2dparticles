/*
 * GroupManager.java
 * 
 * J2DParticles - Particles for Java
 *
 */

package com.j2dparticles.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the execution of all particles groups included on it.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class GroupManager
{
    private List<ParticleGroup> particleGroups = new ArrayList<ParticleGroup>();


    /**
     * GroupManager
     *
     */
    public GroupManager()
    {
    }

    /**
     * addParticleGroup
     *
     * @param pg ParticleGroup
     */
    public void addParticleGroup( ParticleGroup pg )
    {
        particleGroups.add( pg );
    }

    /**
     * getParticleGroups
     *
     * @return List<ParticleGroup>
     */
    public List<ParticleGroup> getParticleGroups()
    {
        return particleGroups;
    }
 
    /**
     * setParticleGroups
     *
     * @param particleGroups List<ParticleGroup>
     */
    public void setParticleGroups( List<ParticleGroup> particleGroups )
    {
        this.particleGroups = particleGroups;
    }

    /**
     * getParticlesCount
     *
     * @return int
     */
    public int getParticlesCount()
    {
        int count = 0;
        for ( ParticleGroup pg : particleGroups )
        {
            count += pg.getParticles().size();
        }

        return count;
    }

    /**
     * update
     *
     * Apply all functions for each particle group.
     * First, creates new particles and apply the source actions on them.
     * Then apply the actions for each particle contained in the group.
     * The particles that are not alive are then removed.
     * Finally, the position of the particles are calculated again based on
     * the current position and current velocity.
     */
    public void update()
    {
        for ( ParticleGroup pg : particleGroups )
        {
            pg.moveParticles();
            pg.createParticles();
            pg.applyActions();
            pg.removeDeadParticles();            
        }
    }
}