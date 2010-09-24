package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import java.util.List;

/**
 * TextTargetParticleAction
 *
 * @author Michael
 */
public class TextTargetParticleAction implements ParticleAction
{
    public static final int TYPE_HORIZONTAL = 0;

//    private int x;
//    private int y;
//    private int width;
//    private int height;
//    private int type;
//
//    private long duration;
//    private long finalTime;
    private int iX;
    private int iY;
    private int iWidth;
    private int iHeight;
    private int type;

    private long duration;
    private long finalTime;

//    public TextTargetParticleAction( long duration, int x, int y, int width, int height, int type )
//    {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//        this.type = type;
//
//        this.duration = duration;
//        this.finalTime = System.currentTimeMillis() + duration;
//    }
    public TextTargetParticleAction( long duration, int x, int y, int width, int height, int type )
    {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
        this.type = type;

        this.duration = duration;
        this.finalTime = System.currentTimeMillis() + duration;
    }

    @Override
    public void doAction( List<Particle> particles )
    {
        if ( type == TYPE_HORIZONTAL )
        {
            createLinearText( particles );
        }

        if ( finalTime < System.currentTimeMillis() )
        {
            finalTime = System.currentTimeMillis() + duration;
        }
    }

    private void createLinearText( List<Particle> particles )
    {
        if ( particles.size() > 0 )
        {
            long timeDiff = duration - (finalTime - System.currentTimeMillis());

//            double x_ = timeDiff * x / duration;
//            double y_ = timeDiff * y / duration;
            
            for ( int i = 0; i < particles.size(); i++ )
            {
//                particles.get( i ).setCurrentPosition( new Position( x_ + x, y_ + y ) );
//                particles.get( i ).setCurrentPosition( new Position( x_ + 100,  y_ +100 ) );
            }
        }
    }

//    private void createLinearText( List<Particle> particles )
//    {
//        if ( particles.size() > 0 )
//        {
//            long time = finalTime - System.currentTimeMillis();
//
//            for ( int i = 0; i < particles.size(); i++ )
//            {
//                double pcx = particles.get( i ).getCurrentPosition().x;
//                double pcy = particles.get( i ).getCurrentPosition().y;
//
////                double vx = pcx < x ? (x + (i * ( width / particles.size() ))) : (x - (i * ( width / particles.size() )));
//                double vx = x + (i * ( width / particles.size() ));
//
//                if ( pcx > x )
//                {
//                    vx = x - (i * ( width / particles.size() ));
//                }
//
//                double x_ = vx > pcx ? ( vx - pcx ) : ( pcx - vx );
//                double y_ = y > pcy ? ( y - pcy ) : ( pcy - y );
////                double x_ = vx - pcx;
////                double y_ = y - pcy;
//
//                double difX =  x_ / time * 1000;
//                double difY =  y_ / time * 1000;
//
//                particles.get( i ).setCurrentVelocity( new Velocity( difX, difY ) );
//            }
//        }
//    }
}