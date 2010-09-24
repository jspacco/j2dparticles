package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.domains.PolygonDomain;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.domains.RingDomain;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.EndTimeParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.List;

/**
 * DomainsParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class DomainsParticleEffect implements ParticleEffect
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
        // circle
        ParticleGroup circle = new ParticleGroup();

        circle.setMaxParticles( 4000 );
        circle.setRate( 15 );
        circle.setCreationInterval( 10 );

        circle.setSourceDomain( new CircleDomain( 300, 200, 75 ) );

        circle.addSourceAction( new VelocityParticleSource( -1, 1, 1, -1 ) );
        circle.addSourceAction( new ColorParticleSource( Color.red ) );
        circle.addSourceAction( new EndTimeParticleSource( 3000 ) );

        circle.addParticleAction( new KillParticleAction( 1000 ) );

        groupManager.addParticleGroup( circle );

        // rectangle
        ParticleGroup rectangle = new ParticleGroup();

        rectangle.setMaxParticles( 4000 );
        rectangle.setRate( 15 );
        rectangle.setCreationInterval( 10 );

        rectangle.setSourceDomain( new RectangleDomain( 500, 125, 150, 150 ) );

        rectangle.addSourceAction( new VelocityParticleSource( 20, 0, 1, 0 ) );
        rectangle.addSourceAction( new ColorParticleSource( Color.blue ) );
        rectangle.addSourceAction( new EndTimeParticleSource( 1000 ) );

        rectangle.addParticleAction( new KillParticleAction( 1000 ) );

        groupManager.addParticleGroup( rectangle );

        // rectangle
        ParticleGroup ring = new ParticleGroup();

        ring.setMaxParticles( 4000 );
        ring.setRate( 15 );
        ring.setCreationInterval( 10 );

        ring.setSourceDomain( new RingDomain( 300, 450, 50, 100 ) );

        ring.addSourceAction( new VelocityParticleSource( 20, 0, 1, 0 ) );
        ring.addSourceAction( new ColorParticleSource( Color.green ) );
        ring.addSourceAction( new EndTimeParticleSource( 1000 ) );

        ring.addParticleAction( new KillParticleAction( 1000 ) );

        groupManager.addParticleGroup( ring );


        // polygon
        ParticleGroup polygon = new ParticleGroup();

        polygon.setMaxParticles( 4000 );
        polygon.setRate( 50 );
        polygon.setCreationInterval( 10 );

        Polygon star = new Polygon(
           new int[]{0,22,95,36,59,0,-59,-36,-95,-22},
           new int[]{-100,-31,-31,12,81,38,81,12,-31,-31},10);

        star.translate( 600, 450 );

        polygon.setSourceDomain( new PolygonDomain( star ) );

        polygon.addSourceAction( new VelocityParticleSource( -5, -10, 5, -12 ) );
        polygon.addSourceAction( new ColorParticleSource( Color.orange ) );
        polygon.addSourceAction( new EndTimeParticleSource( 1000 ) );

        polygon.addParticleAction( new KillParticleAction( 500 ) );

        groupManager.addParticleGroup( polygon );

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
            List<ParticleGroup> particleGroups = groupManager.getParticleGroups();

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    g2d.setColor( p.getColor());
                    g2d.fillOval( (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y), 5, 5 );
                }
            }

            g2d.setColor( Color.black );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}