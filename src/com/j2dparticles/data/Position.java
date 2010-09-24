/*
 * Position.java
 *
 * J2DParticles - Particles for Java
 *
 */

package com.j2dparticles.data;

/**
 * Specifies the position of the particle in the space
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class Position
{

    public double x = 0;
    public double y = 0;

    /**
     * Position
     *
     */
    public Position()
    {
    }

    /**
     * Position
     *
     * @param x double
     * @param y double
     */
    public Position( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    /**
     * setPosition
     *
     * @param x double
     * @param y double
     */
    public void setPosition( double x, double y )
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }
}
