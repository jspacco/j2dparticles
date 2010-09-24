package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.BounceRectangleParticleAction;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.particlesActions.SinkParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * MultiColorParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class MultiColorParticleEffect implements ParticleEffect
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

        particleGroup.setMaxParticles( 400 );
        particleGroup.setRate( 3 );
        particleGroup.setCreationInterval( 100 );

        // domain
        particleGroup.setSourceDomain(  new CircleDomain( 550, 680, 5 ) );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( -70, -400, 70, -550 ) );
        particleGroup.addSourceAction( new ColorParticleSource( 0, 255, 0, 255, 0, 255, false ) );

        // particle actions
        particleGroup.addParticleAction( new GravityParticleAction( 0, 10 ) );

        particleGroup.addParticleAction( new SinkParticleAction( new RectangleDomain( 240, 400, 80, 40  ) ) );
        particleGroup.addParticleAction( new BounceRectangleParticleAction( 0, 750, 1024, 30, 0.5f ) );

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
                    g2d.fillOval( (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y), 20, 20 );
                }
            }

            g2d.setColor( new Color( 0x820e0e ) );
            g2d.fillRect( 500, 650, 100, 118 );

            g2d.setPaint( new GradientPaint( 500, 650, new Color( 0x820e0e ),
                                             600, 750, new Color( 0x9a1f1f )) );
            g2d.fillRect( 505, 655, 95, 113 );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}
