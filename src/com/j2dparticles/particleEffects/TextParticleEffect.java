package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.data.SymbolProperty;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.particlesActions.KillParticleAction;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.particlesActions.TextMovimentParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.TextCreationParticleSource;
import java.awt.Point;

/**
 * TextParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class TextParticleEffect implements ParticleEffect
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
        String text = "Texto";

        // creates particle group
        ParticleGroup particleGroup = new ParticleGroup();

        particleGroup.setMaxParticles( text.length() );
        particleGroup.setRate( text.length() );
        particleGroup.setCreationInterval( 8000 );

        // domain
        particleGroup.setSourceDomain( new CircleDomain( 100, 100, 5 ) );

        // source actions
        particleGroup.addSourceAction( new TextCreationParticleSource( text, new SymbolProperty() ) );
        particleGroup.addSourceAction( new ColorParticleSource( Color.black ) );

        // particle actions
        particleGroup.addParticleAction( new TextMovimentParticleAction( 100, 300, 0, 0,
                                                                         100, 300, 400, 0,
                                                                         7500 ) );

        particleGroup.addParticleAction( new KillParticleAction( 7000 ) );

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

            g2d.setColor( Color.black );
            g2d.setFont( new Font( "Dialog", Font.BOLD, 60 ) );

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    String s = ((SymbolProperty)p.getData()).getText();

                    g2d.setColor( p.getColor());
                    g2d.drawString( s, (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y) );
                }
            }

            g2d.setColor( Color.black );
            g2d.setFont( new Font( "Dialog", Font.PLAIN, 14 ) );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}