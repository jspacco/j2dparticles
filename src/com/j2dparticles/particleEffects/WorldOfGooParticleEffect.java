package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.BounceRectangleParticleAction;
import com.j2dparticles.particlesActions.GravityParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.particlesActions.SinkParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Point;

/**
 * WorldOfGooParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class WorldOfGooParticleEffect implements ParticleEffect
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

        particleGroup.setMaxParticles( 100 );
        particleGroup.setCreationInterval( 200 );
        particleGroup.setRate( 1 );

        // domain
        particleGroup.setSourceDomain( new CircleDomain( 90, 65, 5 ) );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( 200, -10, 500, 10 ) );
        particleGroup.addSourceAction( new ColorParticleSource( Color.black ) );

        // particle actions
        particleGroup.addParticleAction( new GravityParticleAction( 0, 10 ) );

        particleGroup.addParticleAction( new SinkParticleAction( new RectangleDomain( 0, 650, 100, 50  ) ) );
        particleGroup.addParticleAction( new BounceRectangleParticleAction( 80, 580, 1024, 168, 0.75f ) );
        particleGroup.addParticleAction( new BounceRectangleParticleAction( 1000, 0, 20, 768, 0.75f ) );

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
        g2d.setPaint( new Color( 0xdad7c4 ) );
        g2d.fillRect( 0, 0, w, h );

        g2d.setPaint( new GradientPaint( 0, 600, new Color( 0xdad7c4 ),
                                             0, 768, new Color( 0x000000 ) ) );
        g2d.fillRect( 0, 600, 100, 168 );

        try
        {
            List<ParticleGroup> particleGroups = groups;

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    g2d.setColor( p.getColor() );
                    g2d.fillOval( (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y), 20, 20 );

                    g2d.setColor( Color.white );
                    g2d.fillOval( (int)p.getCurrentPosition().x + 5, (int)(p.getCurrentPosition().y +5 ), 3, 3 );
                    g2d.fillOval( (int)p.getCurrentPosition().x + 15, (int)(p.getCurrentPosition().y +5 ), 3, 3 );
                }
            }

            Area areaRectangle = new Area( new Rectangle( -10, 50, 120, 40 ) );
            Area areaRoundRectangle = new Area( new RoundRectangle2D.Float( 110, 40, 20, 60, 10, 10 ) );
            areaRectangle.add( areaRoundRectangle );

            int shadowSize = 6;

            for ( int i = 0; i < shadowSize; i++ )
            {
                g2d.translate( 1, 1 );
                g2d.setColor( new Color( 49, 49, 49, 255 - 40 * i) );
                g2d.fill( areaRectangle );
            }

            g2d.translate( -shadowSize, -shadowSize );

            g2d.setPaint( new GradientPaint( 0, 70, new Color( 0x664306 ),
                                             0, 100, new Color( 0x3b2704 ) ) );
            
            g2d.fill( areaRectangle);


            g2d.setColor( Color.BLACK );
            g2d.fillRect( 100, 600, 1024, 168 );

            g2d.setColor( Color.black );
            g2d.drawString( "CircleDomain( 90, 65, 5 )", 10, 300 );

            g2d.drawString( "VelocityParticleSource( 200, -10, 500, 10 )", 10, 340 );
            g2d.drawString( "ColorParticleSource( Color.black )", 10, 370 );

            g2d.drawString( "GravityParticleAction( 0, 10 )", 10, 410 );
            g2d.drawString( "SinkParticleAction( new RectangleDomain( 0, 650, 100, 50  )", 10, 440 );
            g2d.drawString( "Bounce(...)", 10, 470 );
            g2d.drawString( "Bounce(...)", 10, 500 );
            g2d.drawString( "KillParticleAction( 10000 )", 10, 530 );


        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}