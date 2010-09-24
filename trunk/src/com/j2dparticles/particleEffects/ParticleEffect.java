package com.j2dparticles.particleEffects;

import com.j2dparticles.data.GroupManager;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Controls the execution of all particles groups included on it.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public interface ParticleEffect
{
    public GroupManager initParticles( );
    public void printScene( Graphics2D g2d, int w, int h, Point mousePoint );
}
