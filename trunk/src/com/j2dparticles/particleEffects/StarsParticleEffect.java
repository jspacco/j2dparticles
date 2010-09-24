package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.EndTimeParticleSource;
import com.j2dparticles.sourceActions.SizeParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import com.j2dparticles.util.ResourceLocator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.util.List;

/**
 * StarsParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class StarsParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();


    CircleDomain circle = new CircleDomain( 400, 400, 5 );
    
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

        particleGroup.setMaxParticles( 10000 );
        particleGroup.setRate( 1 );
        particleGroup.setCreationInterval( 30 );

        // domain
        particleGroup.setSourceDomain( circle );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( -40, -40, 40, 40 ) );
        particleGroup.addSourceAction( new EndTimeParticleSource( 3000 ) );
        particleGroup.addSourceAction( new SizeParticleSource( 5, 50 ) );

        // particle actions
        particleGroup.addParticleAction( new KillParticleAction( 1500 ) );

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
        g2d.setColor( Color.black );
        g2d.fillRect( 0, 0, w, h );

        circle.setCenterX( mousePoint.x );
        circle.setCenterY( mousePoint.y - 30 );

        try
        {
            List<ParticleGroup> particleGroups = groupManager.getParticleGroups();

            ResourceLocator rc = new ResourceLocator();
            Image image1 = rc.getImage( "sun_light.png" );
            Image image2 = rc.getImage( "sun_light_2.png" );


            Polygon polygon = new Polygon();
            polygon.addPoint( 0, 0 );
            polygon.addPoint( 5, 5 );
            polygon.addPoint( 10, 0 );
            polygon.addPoint( 5, -5 );

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    g2d.setColor( p.getColor());

                    g2d.drawImage( image1, (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y), p.getSize(), p.getSize(), null );
                    
                }
            }

            // color for legend
            g2d.setColor( Color.white );

            g2d.drawString( "CircleDomain( 400, 400, 5 )", 10, 300 );

            g2d.drawString( "VelocityParticleSource( -40, -40, 40, 40 )", 10, 340 );
            g2d.drawString( "EndTimeParticleSource( 3000 )", 10, 370 );
            g2d.drawString( "SizeParticleSource( 5, 50 )", 10, 400 );

            g2d.drawString( "KillParticleAction( 1500 )", 10, 440 );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}