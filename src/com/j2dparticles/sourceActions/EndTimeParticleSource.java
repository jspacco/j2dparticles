package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.util.RandomValue;
import java.util.List;

/**
 * EndTimeParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class EndTimeParticleSource implements SourceAction
{
    private long endTime = 0;
    private long limitTime = 0;

    /**
     * EndTimeParticleSource
     *
     * @param endTime long
     */
    public EndTimeParticleSource( long endTime )
    {
        this.endTime = endTime;
    }

    /**
     * EndTimeParticleSource
     *
     * @param endTime long
     * @param limitEndTime long
     */
    public EndTimeParticleSource( long endTime, long limitEndTime )
    {
        this.endTime = Math.min( endTime, limitEndTime );
        this.limitTime = Math.max( endTime, limitEndTime );
    }

    /**
     * getEndTime
     *
     * @return long
     */
    public long getEndTime()
    {
        return endTime;
    }

    /**
     * setEndTime
     *
     * @param endTime long
     */
    public void setEndTime( long endTime )
    {
        this.endTime = endTime;
    }

    /**
     * getLimitTime
     *
     * @return long
     */
    public long getLimitTime()
    {
        return limitTime;
    }

    /**
     * setLimitTime
     *
     * @param limitTime long
     */
    public void setLimitTime( long limitTime )
    {
        this.limitTime = limitTime;
    }
        
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        if ( limitTime == 0 )
        {
            for ( Particle p : particles )
            {
                p.setEndTime( endTime );
            }
        }

        else
        {
            for ( Particle p : particles )
            {
                p.setEndTime( RandomValue.betweenInterval( endTime, limitTime ) );
            }
        }
    }    
}