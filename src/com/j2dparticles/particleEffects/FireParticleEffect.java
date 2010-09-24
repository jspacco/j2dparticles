package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.particlesActions.ColorToColorParticleAction;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.particlesActions.TurnTransparentParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.EndTimeParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

/**
 * FireParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class FireParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();

    private CircleDomain circle = new CircleDomain( 400, 400, 15 );
    
    /**
     * initParticles
     *
     * @return GroupManager
     */
    @Override
    public GroupManager initParticles()
    {
        // Group 1
        ParticleGroup particleGroup = new ParticleGroup();

        particleGroup.setMaxParticles( 15000 );
        particleGroup.setRate( 200 );
        particleGroup.setCreationInterval( 1 );

        particleGroup.setSourceDomain( circle );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( -5, -105, 5, -120 ) );
        particleGroup.addSourceAction( new ColorParticleSource( new Color( 200, 200, 200, 10 ) ) );
        particleGroup.addSourceAction( new EndTimeParticleSource( 600 ) );

        // particle actions
        particleGroup.addParticleAction( new ColorToColorParticleAction( Color.red, Color.yellow  ) );
        particleGroup.addParticleAction( new TurnTransparentParticleAction() );

        particleGroup.addParticleAction( new KillParticleAction( 400 ) );

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
        circle.setCenterY( mousePoint.y - 20 );

        try
        {
            if (groupManager.getParticleGroups().size() > 0 )
            {
                List<Particle> particles1 = groupManager.getParticleGroups().get( 0 ).getParticles();

                for ( Particle p : particles1 )
                {
                    g2d.setColor( p.getColor());
                    g2d.fillRect( (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y), 2, 2 );
                }
            }

            g2d.setColor( Color.white );

            g2d.drawString( "CircleDomain( 400, 400, 15 )", 10, 300 );

            g2d.drawString( "VelocityParticleSource( -5, -105, 5, -120 )", 10, 340 );
            g2d.drawString( "ColorParticleSource( new Color( 200, 200, 200, 10 ) )", 10, 370 );
            g2d.drawString( "EndTimeParticleSource( 600 )", 10, 400 );

            g2d.drawString( "ColorToColorParticleAction( Color.red, Color.yellow  )", 10, 440 );
            g2d.drawString( "TurnTransparentParticleAction()", 10, 470 );
            g2d.drawString( "KillParticleAction( 400 )", 10, 500 );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}