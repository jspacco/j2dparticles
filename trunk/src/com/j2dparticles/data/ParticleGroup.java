/*
 * ParticleGroup.java
 *
 * J2DParticles - Particles for Java
 *
 */

package com.j2dparticles.data;

import com.j2dparticles.domains.Domain;
import com.j2dparticles.particlesActions.ParticleAction;
import com.j2dparticles.sourceActions.SourceAction;
import java.util.ArrayList;
import java.util.List;

/**
 * The ParticleGroup which contains and control the particles.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ParticleGroup
{
    private List<SourceAction> sourceActions = new ArrayList<SourceAction>();

    private List<ParticleAction> particleActions = new ArrayList<ParticleAction>();

    private List<Particle> particles = new ArrayList<Particle>();

    private double dt = 0.05;

    private long creationInterval = 100;
    private long previousCreation;

    private int rate = 1;
    private int maxParticles = 1000;

    private Domain sourceDomain;

    /**
     * ParticleGroup
     *
     */
    public ParticleGroup()
    {
    }

    /**
     * getDt
     *
     * @return double
     */
    public double getDt()
    {
        return dt;
    }

    /**
     * setDt
     *
     * @param dt double
     */
    public void setDt( double dt )
    {
        this.dt = dt;
    }

    /**
     * getCreationInterval
     *
     * @return long
     */
    public long getCreationInterval()
    {
        return creationInterval;
    }

    /**
     * setCreationInterval
     *
     * @param creationInterval long
     */
    public void setCreationInterval( long creationInterval )
    {
        this.creationInterval = creationInterval;
    }

    /**
     * getRate
     *
     * @return int
     */
    public int getRate()
    {
        return rate;
    }

    /**
     * setRate
     *
     * @param rate int
     */
    public void setRate( int rate )
    {
        this.rate = rate;
    }

    /**
     * getMaxParticles
     *
     * @return int
     */
    public int getMaxParticles()
    {
        return maxParticles;
    }

    /**
     * setMaxParticles
     *
     * @param maxParticles int
     */
    public void setMaxParticles( int maxParticles )
    {
        this.maxParticles = maxParticles;
    }

    /**
     * getParticles
     *
     * @return List<Particle>
     */
    public List<Particle> getParticles()
    {
        return particles;
    }

    /**
     * setParticles
     *
     * @param particles List<Particle>
     */
    public void setParticles( List<Particle> particles )
    {
        this.particles = particles;
    }

    /**
     * addSourceAction
     *
     * @param sourceAction SourceAction
     */
    public void addSourceAction( SourceAction sourceAction )
    {
        sourceActions.add( sourceAction );
    }

    /**
     * getSourceActions
     *
     * @return List<SourceAction>
     */
    public List<SourceAction> getSourceActions()
    {
        return sourceActions;
    }

    /**
     * setSourceActions
     *
     * @param sourceActions List<SourceAction>
     */
    public void setSourceActions( List<SourceAction> sourceActions )
    {
        this.sourceActions = sourceActions;
    }

    /**
     * getSourceDomain
     *
     * @return Domain
     */
    public Domain getSourceDomain()
    {
        return sourceDomain;
    }

    /**
     * setSourceDomain
     *
     * @param currentDomain Domain
     */
    public void setSourceDomain( Domain currentDomain )
    {
        this.sourceDomain = currentDomain;
    }

    /**
     * addParticleAction
     *
     * @param a ParticleAction
     */
    public void addParticleAction( ParticleAction a )
    {
        particleActions.add( a );
    }

    /**
     * removeParticleAction
     *
     * @param a ParticleAction
     */
    public void removeParticleAction( ParticleAction a )
    {
        particleActions.remove( a );
    }

    /**
     * getParticleActions
     *
     * @return List<ParticleAction>
     */
    public List<ParticleAction> getParticleActions()
    {
        return particleActions;
    }

    /**
     * setParticleActions
     *
     * @param actions List<ParticleAction>
     */
    public void setParticleActions( List<ParticleAction> actions )
    {
        this.particleActions = actions;
    }

    /**
     * createParticles
     *
     * Creates new particles using the current Domain of the group.
     * The number of new particles is defined by the rate.
     * After created, the source actions are apply to all particles.
     * It was determined that it is better to send all particles at once
     * to increase the flexibility of the action since the result of the calculation
     * of one particle can be used to another.
     * Then the new particles are added into group current list of particles.
     */
    public void createParticles()
    {
        if ( previousCreation + creationInterval < System.currentTimeMillis() )
        {
            List<Particle> newParticles = sourceDomain.createParticles( rate );

            for ( SourceAction sa : sourceActions )
            {
                sa.applyAction( newParticles );
            }

            for ( Particle p : newParticles )
            {
                if ( particles.size() < getMaxParticles() )
                {
                    particles.add( p );
                }

                else
                {
                    break;
                }
            }

            previousCreation = System.currentTimeMillis();
        }        
    }

    /**
     * applyActions
     *
     * For each particle of the group is applied all actions contained in the
     * list of actions
     */
    public void applyActions()
    {
        for ( ParticleAction a : particleActions )
        {
            a.doAction( particles );
        }
    }

    /**
     * removeDeadParticles
     *
     * Remove all particles which are not alive
     */
    public void removeDeadParticles()
    {
        for ( int i = 0; i < particles.size(); i++ )
        {
            if ( !particles.get(i).isAlive() )
            {
                particles.remove(i);
            }
        }
    }

    /**
     * move Particles
     *
     * After applied all actions the position of all particles are recalculated.
     */
    public void moveParticles()
    {
        for ( Particle p : particles )
        {
            p.setPreviousPosition( p.getCurrentPosition() );

            p.setCurrentPosition( new Position( p.getCurrentPosition().x + p.getCurrentVelocity().dx * dt,
                                                p.getCurrentPosition().y + p.getCurrentVelocity().dy * dt ) );
        }
    }    
}