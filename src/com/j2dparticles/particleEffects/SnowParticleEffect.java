package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import com.j2dparticles.util.ResourceLocator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

/**
 * Controls the execution of all particles groups included on it.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class SnowParticleEffect implements ParticleEffect
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

        particleGroup.setMaxParticles( 6000 );
        particleGroup.setCreationInterval( 20 );
        particleGroup.setRate( 20 );

        // domain
        particleGroup.setSourceDomain( new RectangleDomain( -200, -20, 1200, 20 ) );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( 20, 0, 0, 0 ) );

        // particle actions
        particleGroup.addParticleAction( new GravityParticleAction( 0, 0.5 ) );

        particleGroup.addParticleAction( new KillParticleAction( 8000 ) );

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
        g2d.setColor( Color.black );
        g2d.fillRect( 0, 0, w, h );

        try
        {

            ResourceLocator rc = new ResourceLocator();
            Image waterImage = rc.getImage( "snow2.png" );

            for ( ParticleGroup pg : groupManager.getParticleGroups() )
            {
                List<Particle> particles = pg.getParticles();

                g2d.setColor( Color.white );
                for ( Particle p : particles )
                {
                    g2d.drawImage( waterImage, (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y ), null );
                }
            }

            // color for legend
            g2d.setColor( Color.white );

            g2d.drawString( "RectangleDomain( -200, -20, 1200, 20 )", 10, 300 );

            g2d.drawString( "VelocityParticleSource( 20, 0, 0, 0 )", 10, 340 );

            g2d.drawString( "GravityParticleAction( 0, 0.5 )", 10, 380 );
            g2d.drawString( "KillParticleAction( 800 )", 10, 410 );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}
