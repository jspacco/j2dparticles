package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import com.j2dparticles.data.Particle;
import com.j2dparticles.data.ParticleGroup;
import com.j2dparticles.data.Position;
import com.j2dparticles.domains.CircleDomain;
import com.j2dparticles.particlesActions.AttractToTarget;
import com.j2dparticles.particlesActions.KillParticleAction;
import com.j2dparticles.sourceActions.ExplosionParticleSource;
import com.j2dparticles.sourceActions.SizeParticleSource;
import com.j2dparticles.util.ResourceLocator;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * AttractToTargetParticleEffect
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class AttractToTargetParticleEffect implements ParticleEffect
{
    private GroupManager groupManager = new GroupManager();

    private List<ParticleGroup> groups = new ArrayList<ParticleGroup>();

    int x = 1024 / 2;
    int y = 768 / 2;

    CircleDomain circle = new CircleDomain( x, y, 5 );

    @Override
    public GroupManager initParticles()
    {
        // creates particle group
        ParticleGroup particleGroup = new ParticleGroup();

        particleGroup.setMaxParticles( 400 );
        particleGroup.setRate( 20 );
        particleGroup.setCreationInterval( 125 );

        // domain
        particleGroup.setSourceDomain( circle );

        // source actions
        particleGroup.addSourceAction( new ExplosionParticleSource( 125 ) );
        particleGroup.addSourceAction( new SizeParticleSource( 10, 50 ) );

        // particle actions
        particleGroup.addParticleAction( new AttractToTarget( new Position( x, y ) ) );
        particleGroup.addParticleAction( new KillParticleAction( 20000 ) );

        groups.add( particleGroup );

        groupManager.addParticleGroup( particleGroup );

        return groupManager;
    }

    @Override
    public void printScene( Graphics2D g2d, int w, int h, Point mousePoint )
    {
        ResourceLocator rc = new ResourceLocator();
        Image background = rc.getImage( "space.jpg" );

        g2d.drawImage( background, 0, 0, null );

        try
        {
            List<ParticleGroup> particleGroups = groupManager.getParticleGroups();
            
            Image light_red = rc.getImage( "light_red.png" );
            Image light_blue = rc.getImage( "light_blue.png" );


            for ( ParticleGroup pg : particleGroups )
            {
                List<Particle> particles = pg.getParticles();

                for ( Particle p : particles )
                {
                    if ( p.getSize() < 25 )
                    {
                        g2d.drawImage( light_red, (int)p.getCurrentPosition().x - p.getSize() / 2, (int)(p.getCurrentPosition().y - p.getSize() / 2), p.getSize(), p.getSize(), null );
                    }

                    else
                    {
                        g2d.drawImage( light_blue, (int)p.getCurrentPosition().x - p.getSize() / 2, (int)(p.getCurrentPosition().y - p.getSize() / 2), p.getSize(), p.getSize(), null );
                    }
                    
                }
            }
        }

        catch( java.util.ConcurrentModificationException e )
        {
            System.out.println( "ops" );
        }
    }
}