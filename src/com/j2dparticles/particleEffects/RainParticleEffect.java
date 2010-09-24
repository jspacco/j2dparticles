package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.BounceRectangleParticleAction;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Point;

/**
 * RainParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class RainParticleEffect implements ParticleEffect
{    
    private GroupManager groupManager = new GroupManager();

    private List<ParticleGroup> groups = new ArrayList<ParticleGroup>();

    private GravityParticleAction gravity;
    
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

        particleGroup.setMaxParticles( 2000 );
        particleGroup.setCreationInterval( 20 );
        particleGroup.setRate( 15 );

        // domain
        particleGroup.setSourceDomain( new RectangleDomain( -500, -20, 1800, 20  ) );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( 20, 0, 0, 0 ) );
        particleGroup.addSourceAction( new ColorParticleSource( Color.white ) );

        // particle actions
        particleGroup.addParticleAction( gravity = new GravityParticleAction( 0, 8 ) );
        particleGroup.addParticleAction( new BounceRectangleParticleAction( 0, 600, 1024, 50, 0.2f ) );
        particleGroup.addParticleAction( new KillParticleAction( 3000 ) );

        groups.add( particleGroup );

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
        // makes the 'wind' effect
        count++;
        if ( count % compare == 0 )
        {
            gravity.setForceX( (Math.random() * 8) - (Math.random() * 2) );

            compare = (int) (Math.random() * 200);
            compare++;
        }

        g2d.setPaint( new GradientPaint( 0, 0, Color.black,
                                         0, h, new Color( 0x04155c )));
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
                    g2d.drawLine( (int)p.getPreviousPosition().x, (int)(p.getPreviousPosition().y ),
                                  (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y) );
                }
            }

            g2d.setPaint( new GradientPaint( 0, 602, Color.gray,
                                             0, h, Color.black));

            g2d.fillRect(0, 602, 1024, 150 );

            // color for legend
            g2d.setColor( Color.white );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }

}