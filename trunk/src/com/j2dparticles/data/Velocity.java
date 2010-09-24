/*
 * Velocity.java
 *
 * J2DParticles - Particles for Java
 *
 */
package com.j2dparticles.data;

/**
 * Specifies the velocity of the particle in the space
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class Velocity
{

    public double dx = 0;
    public double dy = 0;

    /**
     * Velocity
     *
     */
    public Velocity()
    {
    }

    /**
     * Velocity
     *
     * @param dx double
     * @param dy double
     */
    public Velocity( double dx, double dy )
    {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * setVelocity
     *
     * @param dx double
     * @param dy double
     */
    public void setVelocity( double dx, double dy )
    {
        this.dx = dx;
        this.dy = dy;
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return getClass().getName() + "[dx=" + dx + ",dy=" + dy + "]";
    }
}