package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.RingDomain;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.ExplosionParticleSource;
import com.j2dparticles.sourceActions.ColorParticleSource;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * BigExplosionsParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class BigExplosionsParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();

    private List<ParticleGroup> groups = new ArrayList<ParticleGroup>();
    
    /**
     * initParticles
     *
     * @return GroupManager
     */
    @Override
    public GroupManager initParticles()
    {
        // creates particle group
        ParticleGroup particleGroup = new ParticleGroup();

        particleGroup.setMaxParticles( 500 );
        particleGroup.setRate( 500 );
        particleGroup.setCreationInterval( 2100 );

        // domain
        particleGroup.setSourceDomain( new RingDomain( 1024 / 2, 768 / 2, 5, 5 ) );

        // source actions
        particleGroup.addSourceAction( new ExplosionParticleSource( 200 ) );
        particleGroup.addSourceAction( new ColorParticleSource( 0, 255, 0, 255, 0, 255, true ) );

        // particle actions
        particleGroup.addParticleAction( new KillParticleAction( 2000 ) );

        groups.add( particleGroup );

        groupManager.addParticleGroup( particleGroup );

        return groupManager;
    }
    
    /**
     * printScene
     *
     * @param g2d Graphics2D
     * @param w int
     * @param h int
     * @param mousePoint Point
     */
    @Override
    public void printScene( Graphics2D g2d, int w, int h, Point mousePoint )
    {
        g2d.setColor( Color.white );
        g2d.fillRect( 0, 0, w, h );

        try
        {
            List<ParticleGroup> particleGroups = groups;

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    g2d.setColor( p.getColor());
                    g2d.drawLine( (int)p.getPreviousPosition().x, (int)(p.getPreviousPosition().y),
                                  (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y) );
                }

            }
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}