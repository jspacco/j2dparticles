package com.j2dparticles.particlesActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import java.util.List;

/**
 * TextMovimentParticleAction
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class TextMovimentParticleAction implements ParticleAction
{
    public static final int TYPE_HORIZONTAL = 0;
    public static final int TYPE_VERTICAL = 1;

    private int iX;
    private int iY;
    private int iWidth;
    private int iHeight;
    private int fX;
    private int fY;
    private int fWidth;
    private int fHeight;

    private double duration;
    private double finalTime;

    private Position tempPosition = new Position();

    /**
     * TextMovimentParticleAction
     *
     * @param iX int
     * @param iY int
     * @param iWidth int
     * @param iHeight int
     * @param fX int
     * @param fY int
     * @param fWidth int
     * @param fHeight int
     * @param duration long
     */
    public TextMovimentParticleAction( int iX, int iY, int iWidth, int iHeight,
                                       int fX, int fY, int fWidth, int fHeight,
                                       long duration )
    {
        this.iX = iX;
        this.iY = iY;
        this.iWidth = iWidth;
        this.iHeight = iHeight;

        this.fX = fX;
        this.fY = fY;
        this.fWidth = fWidth;
        this.fHeight = fHeight;

        this.duration = duration;
        this.finalTime = System.currentTimeMillis() + duration;
    }
    
    /**
     * doAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void doAction( List<Particle> particles )
    {
        if ( particles.size() > 0 )
        {
            for ( int i = 0; i < particles.size(); i++ )
            {
                calculateInitialHorizontalText( particles, i );

                calculateFinalHorizontalText( particles, i );
            }

            // adjust finalTime
            if ( finalTime < System.currentTimeMillis() )
            {
                finalTime = System.currentTimeMillis() + duration;
            }
        }
    }

    /**
     * calculateInitialHorizontalText
     *
     * @param particles List<Particle>
     * @param index int
     */
    private void calculateInitialHorizontalText( List<Particle> particles, int index )
    {
        tempPosition.setPosition( iX + (iWidth / particles.size()) * index, iY + (iHeight / particles.size()) * index );
    }

    /**
     * calculateFinalHorizontalText
     *
     * @param particles List<Particle>
     * @param index int
     */
    private void calculateFinalHorizontalText( List<Particle> particles, int index )
    {        
        double timeDiff = duration - (finalTime - System.currentTimeMillis());

        double x_ = timeDiff * (fX - tempPosition.x) / duration;
        double y_ = timeDiff * (fY - tempPosition.y) / duration;

        double addx_ = ((fWidth / particles.size()) * index) * (timeDiff / duration);
        double addy_ = ((fHeight / particles.size()) * index) * (timeDiff / duration);

        particles.get( index ).setCurrentPosition( new Position( x_ + tempPosition.x + addx_,  
                                                                 y_ + tempPosition.y + addy_ ) );
    }    
}