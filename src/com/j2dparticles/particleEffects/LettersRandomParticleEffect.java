package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.data.SymbolProperty;
import com.j2dparticles.domains.RectangleDomain;
import com.j2dparticles.particlesActions.KillParticleAction;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.RandomCharacterParticleSource;
import com.j2dparticles.sourceActions.SizeParticleSource;
import com.j2dparticles.sourceActions.VelocityParticleSource;
import java.awt.Point;

/**
 * LettersRandomParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class LettersRandomParticleEffect implements ParticleEffect
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

        particleGroup.setMaxParticles( 1000 );
        particleGroup.setRate( 2 );
        particleGroup.setCreationInterval( 50 );

        // domain
        particleGroup.setSourceDomain( new RectangleDomain( 0, -20, 1024, 20 ) );

        // source actions
        particleGroup.addSourceAction( new VelocityParticleSource( 0, 30, 0, 90 ) );
        particleGroup.addSourceAction( new ColorParticleSource( Color.green ) );
        particleGroup.addSourceAction( new SizeParticleSource( 10, 35, true ) );
        particleGroup.addSourceAction( new RandomCharacterParticleSource() );
        particleGroup.addSourceAction( new ColorParticleSource( 0, 100, 0, 0, 200, 0, true ) );

        // particle actions

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
        g2d.setColor( Color.black );
        g2d.fillRect( 0, 0, w, h );

        try
        {
            List<ParticleGroup> particleGroups = groups;

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();


                for ( Particle p : particles )
                {
                    SymbolProperty sp = (SymbolProperty) p.getData();

                    String s = sp.getText();

                    g2d.setFont( new Font( "Dialog", Font.PLAIN, p.getSize() ) );
                    g2d.setColor( p.getColor());
                    g2d.drawString( s, (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y) );

                }
            }

            g2d.setColor( Color.white );
            g2d.setFont( new Font( "Dialog", Font.PLAIN, 14 ) );
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}