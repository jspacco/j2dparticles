package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.PolygonDomain;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.BounceParticleAction;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 * CollisionParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class CollisionParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();

    private List<ParticleGroup> groups = new ArrayList<ParticleGroup>();

    private RectangleDomain rectangle = new RectangleDomain( 360, 100, 10, 10 );
    
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
        particleGroup.setRate( 1 );
        particleGroup.setCreationInterval( 100 );

        // domain
        Polygon p = new Polygon();

        p.addPoint( 400, 400 );
        p.addPoint( 440, 380 );
        p.addPoint( 480, 400 );
        p.addPoint( 520, 430 );
        p.addPoint( 480, 450 );
        p.addPoint( 430, 430 );
        p.addPoint( 400, 450 );        
        
        PolygonDomain pd = new PolygonDomain( p );

        particleGroup.setSourceDomain( rectangle );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( -50, -200, -40, -220 ) );
        particleGroup.addSourceAction( new ColorParticleSource( Color.black ) );

        // particle actions
        particleGroup.addParticleAction( new GravityParticleAction( 0, 10 ) );

        particleGroup.addParticleAction( new BounceParticleAction( pd, 0.8f ) );

        particleGroup.addParticleAction( new KillParticleAction( 10000 ) );

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
        rectangle.setX( mousePoint.x );
        rectangle.setY( mousePoint.y );

        g2d.setColor( new Color( 0xfff4c8 ) );
        g2d.fillRect( 0, 0, w, h );

        g2d.setFont( new Font( "Dialog", Font.PLAIN, 20 ) );

        try
        {
            List<ParticleGroup> particleGroups = groups;

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    g2d.setColor( new Color( 0x044410 ));
                    g2d.fillOval( (int)p.getCurrentPosition().x - 5, (int)(p.getCurrentPosition().y - 5), 5, 5 );
                }
            }

            Polygon p = new Polygon();

            p.addPoint( 400, 400 );
            p.addPoint( 400, 450 );
            p.addPoint( 430, 430 );
            p.addPoint( 480, 450 );
            p.addPoint( 520, 430 );
            p.addPoint( 480, 400 );
            p.addPoint( 440, 380 );
            
            g2d.setColor( Color.black );
            g2d.draw( p );

            g2d.setColor( Color.black );
            g2d.drawOval( 500 - 10, 600 -10, 10, 10);
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}