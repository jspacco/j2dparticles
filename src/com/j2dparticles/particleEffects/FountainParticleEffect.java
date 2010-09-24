package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import com.j2dparticles.util.ResourceLocator;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * FountainParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class FountainParticleEffect implements ParticleEffect
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
        ParticleGroup pg = new ParticleGroup();

        pg.setMaxParticles( 10000 );
        pg.setRate( 20 );
        pg.setCreationInterval( 10 );

        // domain
        pg.setSourceDomain(  new CircleDomain( 550, 350, 5 ) );

        // source actions
        pg.addSourceAction( new VelocityParticleSource( -50, -200, 50, -250 ) );

        // particle actions
        pg.addParticleAction( new GravityParticleAction( 0, 10 ) );

        pg.addParticleAction( new KillParticleAction( 3000 ) );

        groups.add( pg );

        groupManager.addParticleGroup( pg );

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
        g2d.setPaint( new GradientPaint( 0, 0, new Color(0xebeca3),
                                         0, h/2, Color.white ) );
        g2d.fillRect( 0, 0, w, h );

        try
        {
            List<ParticleGroup> particleGroups = groups;

            ResourceLocator rc = new ResourceLocator();
            Image waterImage = rc.getImage( "water.png" );
            Image fountainImage = rc.getImage( "fountain.png" );

            g2d.drawImage( fountainImage, 430, 340, null );

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    g2d.setColor( Color.blue );
                    g2d.drawImage( waterImage, (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y ), null );
                }
            }

            g2d.setColor( new Color( 0x820e0e ) );

            g2d.drawString( "CircleDomain( 550, 350, 5 )", 10, 300 );
            g2d.drawString( "VelocityParticleSource( -50, -200, 50, -250 )", 10, 340 );
            g2d.drawString( "GravityParticleAction( 0, 10 )", 10, 370 );
            g2d.drawString( "KillParticleAction( 3000 )", 10, 400 );

        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}