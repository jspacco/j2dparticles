package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.particlesActions.BounceRectangleParticleAction;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Point;

/**
 * ResilienceParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ResilienceParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();

    private List<ParticleGroup> groups = new ArrayList<ParticleGroup>();

    private BounceRectangleParticleAction bounce1 = new BounceRectangleParticleAction();
    private BounceRectangleParticleAction bounce2 = new BounceRectangleParticleAction();

    private CircleDomain circle = new CircleDomain();
    
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

        particleGroup.setMaxParticles( 1000 );
        particleGroup.setCreationInterval( 100 );
        particleGroup.setRate( 2 );
        particleGroup.setDt( 0.015 );

        // domain
        particleGroup.setSourceDomain( circle = new CircleDomain( 550, 600, 5 ) );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( 350, -400, 400, -500 ) );
        particleGroup.addSourceAction( new ColorParticleSource( Color.black ) );

        // particle actions
        particleGroup.addParticleAction( new GravityParticleAction( 0, 10 ) );

        particleGroup.addParticleAction( bounce1 = new BounceRectangleParticleAction( 400, 250, 330, 180, 0.5f ) );
        particleGroup.addParticleAction( bounce2 = new BounceRectangleParticleAction( 1200, 250, 330, 180, 1.0f ) );

        particleGroup.addParticleAction( new KillParticleAction( 3000 ) );

        groups.add( particleGroup );

        groupManager.addParticleGroup( particleGroup );

        return groupManager;
    }

    private int x1 = 0;
    private int x2 = 800;

    private int count  = 0;
    
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
        count++;
        
        if ( count % 5 == 0 )
        {
            bounce1.setX( x1 );
            bounce2.setX( x2 );
            x1 += 2;
            x2 -= 2;
        }

        circle.setCenterX( mousePoint.x );
        circle.setCenterY( mousePoint.y - 20 );

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

            
            g2d.setPaint( new GradientPaint( bounce1.getX(), bounce1.getY(), new Color( 0x8a5801 ),
                                             bounce1.getX(), bounce1.getY() + bounce1.getHeight(), new Color( 0x5e3c01 ) ) );
            g2d.fillRect( bounce1.getX(), bounce1.getY(), bounce1.getWidth(), bounce1.getHeight() );
            g2d.setColor( new Color( 0x5e3c01 ) );
            g2d.drawRect( bounce1.getX(), bounce1.getY(), bounce1.getWidth(), bounce1.getHeight() );
            g2d.setColor( Color.yellow );
            g2d.drawString( "Resilence 50%", bounce1.getX() + 40, bounce1.getY() + 40);


            g2d.setPaint( new GradientPaint( bounce2.getX(), bounce2.getY(), new Color( 0x8a5801 ),
                                             bounce2.getX(), bounce2.getY() + bounce2.getHeight(), new Color( 0x5e3c01 ) ) );
            g2d.fillRect( bounce2.getX(), bounce2.getY(), bounce2.getWidth(), bounce2.getHeight() );
            g2d.setColor( new Color( 0x5e3c01 ) );
            g2d.drawRect( bounce2.getX(), bounce2.getY(), bounce2.getWidth(), bounce2.getHeight() );
            g2d.setColor( Color.yellow );
            g2d.drawString( "Resilence 100%", bounce2.getX() + 40, bounce2.getY() + 40 );

            g2d.setColor( Color.black );
            g2d.drawOval( 500 - 10, 600 -10, 10, 10);
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}