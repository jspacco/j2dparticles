package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

/**
 * SnowParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class SquaresParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();

    
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

        particleGroup.setMaxParticles( 18000 );
        particleGroup.setCreationInterval( 20 );
        particleGroup.setRate( 500 );

        // domain
        particleGroup.setSourceDomain( new RectangleDomain( 0, 0, 1024, 768  ) );

        // source actions
        particleGroup.addSourceAction( new ColorParticleSource( 0, 0, 0, 255, 255, 255, false ) );

        // particle actions
        particleGroup.addParticleAction( new KillParticleAction( 1000 ) );

        groupManager.addParticleGroup( particleGroup );

        return groupManager;
    }

    int count = 0;
    int compare = 1;
    
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
            for ( ParticleGroup pg : groupManager.getParticleGroups() )
            {
                List<Particle> particles = pg.getParticles();

                g2d.setColor( Color.red );
                for ( Particle p : particles )
                {
                    g2d.setColor( p.getColor() );
                    g2d.fillRect( (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y ),4, 4 );
                }
            }

            // color for legend
            g2d.setColor( Color.black );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}