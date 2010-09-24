package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import java.util.List;
import com.j2dparticles.util.RandomValue;

/**
 * SizeParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class SizeParticleSource implements SourceAction
{
    private int size;
    private int minSize;
    private int maxSize;

    private boolean applyToAll = false;

    /**
     * SizeParticleSource
     *
     * @param size int
     */
    public SizeParticleSource( int size )
    {
        this( size, 0 );
    }

    /**
     * SizeParticleSource
     *
     * @param min int
     * @param max int
     */
    public SizeParticleSource( int min, int max )
    {
        this( min, max, false );
    }

    /**
     * SizeParticleSource
     *
     * @param min int
     * @param max int
     * @param applyToAll boolean
     */
    public SizeParticleSource( int min, int max, boolean applyToAll )
    {
        this.minSize = min;
        this.maxSize = max;
        this.applyToAll = applyToAll;
    }
    
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        if ( maxSize == 0)
        {
            for ( Particle p : particles )
            {
                p.setSize( minSize );
            }
        }

        else
        {
            if ( applyToAll )
            {
                for ( Particle p : particles )
                {
                    createSize();
                    p.setSize( size );
                }
            }

            else
            {
                createSize();

                for ( Particle p : particles )
                {
                    p.setSize( size );
                }
            }
        }
    }

    /**
     * createSize
     *
     */
    private void createSize()
    {
        size = RandomValue.betweenInterval( minSize, maxSize );
    }

    /**
     * getSize
     *
     * @return int
     */
    public int getSize()
    {
        return size;
    }

    /**
     * setSize
     *
     * @param size int
     */
    public void setSize( int size )
    {
        this.size = size;
    }

    /**
     * getMinSize
     *
     * @return int
     */
    public int getMinSize()
    {
        return minSize;
    }

    /**
     * setMinSize
     *
     * @param minSize int
     */
    public void setMinSize( int minSize )
    {
        this.minSize = minSize;
    }

    /**
     * getMaxSize
     *
     * @return int
     */
    public int getMaxSize()
    {
        return maxSize;
    }

    /**
     * setMaxSize
     *
     * @param maxSize int
     */
    public void setMaxSize( int maxSize )
    {
        this.maxSize = maxSize;
    }

    /**
     * isApplyToAll
     *
     * @return boolean
     */
    public boolean isApplyToAll()
    {
        return applyToAll;
    }

    /**
     * setApplyToAll
     *
     * @param applyToAll boolean
     */
    public void setApplyToAll( boolean applyToAll )
    {
        this.applyToAll = applyToAll;
    }
}