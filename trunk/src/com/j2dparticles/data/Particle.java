/*
 * Particle.java
 *
 * J2DParticles - Particles for Java
 *
 */

package com.j2dparticles.data;

import java.awt.Color;

/**
 * The particle
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class Particle
{
    private Position currentPosition = new Position();
    private Position previousPosition = new Position();
    private Position targetPosition = new Position();

    private Velocity currentVelocity = new Velocity();
    private Velocity previousVelocity = new Velocity();

    private int size = 0;

    private long startTime;
    private long endTime;

    private boolean alive = true;

    private Color color;

    private Object data;

    /**
     * Particle
     *
     */
    public Particle()
    {
        startTime = System.currentTimeMillis();
    }

    /**
     * getColor
     *
     * @return Color
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * setColor
     *
     * @param color Color
     */
    public void setColor( Color color )
    {
        this.color = color;
    }

    /**
     * getCurrentPosition
     *
     * @return Position
     */
    public Position getCurrentPosition()
    {
        return currentPosition;
    }

    /**
     * setCurrentPosition
     *
     * @param currentPosition Position
     */
    public void setCurrentPosition( Position currentPosition )
    {
        this.currentPosition = currentPosition;
    }

    /**
     * getPreviousPosition
     *
     * @return Position
     */
    public Position getPreviousPosition()
    {
        return previousPosition;
    }

    /**
     * setPreviousPosition
     *
     * @param previousPosition Position
     */
    public void setPreviousPosition( Position previousPosition )
    {
        this.previousPosition = previousPosition;
    }

    /**
     * getTargetPosition
     *
     * @return Position
     */
    public Position getTargetPosition() {
        return targetPosition;
    }

    /**
     * setTargetPosition
     *
     * @param targetPosition Position
     */
    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     * getCurrentVelocity
     *
     * @return Velocity
     */
    public Velocity getCurrentVelocity()
    {
        return currentVelocity;
    }

    /**
     * setCurrentVelocity
     *
     * @param currentVelocity Velocity
     */
    public void setCurrentVelocity( Velocity currentVelocity )
    {
        this.currentVelocity = currentVelocity;
    }

    /**
     * getPreviousVelocity
     *
     * @return Velocity
     */
    public Velocity getPreviousVelocity()
    {
        return previousVelocity;
    }

    /**
     * setPreviousVelocity
     *
     * @param previousVelocity Velocity
     */
    public void setPreviousVelocity( Velocity previousVelocity )
    {
        this.previousVelocity = previousVelocity;
    }

    /**
     * getData
     *
     * @return Object
     */
    public Object getData()
    {
        return data;
    }

    /**
     * setData
     *
     * @param value Object
     */
    public void setData( Object value )
    {
        this.data = value;
    }

    /**
     * getStartTime
     *
     * @return long
     */
    public long getStartTime()
    {
        return startTime;
    }

    /**
     * setStartTime
     *
     * @param startTime long
     */
    public void setStartTime( long startTime )
    {
        this.startTime = startTime;
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
     * isAlive
     *
     * @return boolean
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * setAlive
     *
     * @param alive boolean
     */
    public void setAlive( boolean alive )
    {
        this.alive = alive;
    }
}