package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.data.SymbolProperty;
import com.j2dparticles.domains.CircleDomain;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.j2dparticles.particlesActions.TextMovimentParticleAction;
import com.j2dparticles.sourceActions.ColorParticleSource;
import com.j2dparticles.sourceActions.SizeParticleSource;
import com.j2dparticles.sourceActions.TextCreationParticleSource;
import java.awt.Point;

/**
 * TextParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class TextRotationParticleEffect implements ParticleEffect
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
        String text = "J2DParticles";

        // creates particle group
        ParticleGroup particleGroup = new ParticleGroup();

        particleGroup.setMaxParticles( text.length() );
        particleGroup.setRate( text.length() );

        // domain
        particleGroup.setSourceDomain( new CircleDomain( 100, 100, 5 ) );

        // source actions
        particleGroup.addSourceAction( new TextCreationParticleSource( text, new SymbolProperty() ) );
        particleGroup.addSourceAction( new ColorParticleSource( 0, 0, 0, 255, 255, 255 ) );
        particleGroup.addSourceAction( new SizeParticleSource( 20, 50, true ) );

        // particle actions
        particleGroup.addParticleAction( new TextMovimentParticleAction( 300, -20, 20, 0,
                                                                         200, 600, 700, 0,
                                                                         10000 ) );

        groups.add( particleGroup );

        groupManager.addParticleGroup( particleGroup );

        return groupManager;
    }

    private long count = 0;
    
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
        count+=3;

        g2d.setColor( Color.white );
        g2d.fillRect( 0, 0, w, h );

        try
        {
            List<ParticleGroup> particleGroups = groups;

            g2d.setFont( new Font( "Dialog", Font.PLAIN, 50 ) );

            long rotation = count % 360;

            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                int num = 1;
                for ( Particle p : particles )
                {
                    String s = ((SymbolProperty)p.getData()).getText();

                    g2d.setFont( new Font( "Dialog", Font.PLAIN, p.getSize() ) );

                    
                    g2d.rotate( Math.toRadians( rotation + (num*10) ),(int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y));
                    g2d.setColor( Color.gray );
                    g2d.drawString( s, (int)p.getCurrentPosition().x - 1, (int)(p.getCurrentPosition().y - 1 ) );

                    g2d.setColor( p.getColor());
                    g2d.drawString( s, (int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y) );

                    g2d.rotate( -Math.toRadians( rotation + (num*10) ),(int)p.getCurrentPosition().x, (int)(p.getCurrentPosition().y) );
                    num++;
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