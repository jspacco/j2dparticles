/*
 * ParticleSettings.java
 *
 * J2DParticles - Particles for Java
 *
 */
package com.j2dparticles.data;

import java.awt.Color;

/**
 * ParticleSettings is used to manage the settings of the particle
 * in the visual application of this library.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ParticleSettings
{
    private Position position = new Position();

    private Velocity velocity = new Velocity();

    private Color particleColor = Color.white;
    private Color sceneColor = Color.black;
    private Color startColor = Color.white;
    private Color endColor = Color.black;

    private int scatterX = 0;
    private int scatterY = 0;
    private int randomVelocityX = 0;
    private int randomVelocityY = 0;
    private int rate = 0;
    private int size = 0;
    private int random = 0;
    private int gravity = 1000;

    private long life = 10000;

    private boolean playing = false;
    private boolean moveEnable = true;
    private boolean gravityEnable = true;
    private boolean turnTransparentEnable = true;
    private boolean colorToColorEnable = true;

    /**
     * ParticleSettings
     *
     */
    public ParticleSettings()
    {
    }

    /**
     * getPosition
     *
     * @return Position
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * setPosition
     *
     * @param position Position
     */
    public void setPosition( Position position )
    {
        this.position = position;
    }

    /**
     * getVelocity
     *
     * @return Velocity
     */
    public Velocity getVelocity()
    {
        return velocity;
    }

    /**
     * setVelocity
     *
     * @param velocity Velocity
     */
    public void setVelocity( Velocity velocity )
    {
        this.velocity = velocity;
    }
    
    /**
     * getRate
     *
     * @return int
     */
    public int getRate()
    {
        return rate;
    }

    /**
     * setRate
     *
     * @param rate int
     */
    public void setRate( int rate )
    {
        this.rate = rate;
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
     * getRandom
     *
     * @return int
     */
    public int getRandom()
    {
        return random;
    }

    /**
     * setRandom
     *
     * @param random int
     */
    public void setRandom( int random )
    {
        this.random = random;
    }

    /**
     * getGravity
     *
     * @return int
     */
    public int getGravity()
    {
        return gravity;
    }

    /**
     * setGravity
     *
     * @param gravity int
     */
    public void setGravity( int gravity )
    {
        this.gravity = gravity;
    }

    /**
     * getLife
     *
     * @return long
     */
    public long getLife()
    {
        return life;
    }

    /**
     * setLife
     *
     * @param life long
     */
    public void setLife( long life )
    {
        this.life = life;
    }

    /**
     * getParticleColor
     *
     * @return Color
     */
    public Color getParticleColor()
    {
        return particleColor;
    }

    /**
     * setParticleColor
     *
     * @param particleColor Color
     */
    public void setParticleColor( Color particleColor )
    {
        this.particleColor = particleColor;
    }

    /**
     * getSceneColor
     *
     * @return Color
     */
    public Color getSceneColor()
    {
        return sceneColor;
    }

    /**
     * setSceneColor
     *
     * @param sceneColor Color
     */
    public void setSceneColor( Color sceneColor )
    {
        this.sceneColor = sceneColor;
    }

    /**
     * getScatterX
     *
     * @return int
     */
    public int getScatterX()
    {
        return scatterX;
    }

    /**
     * setScatterX
     *
     * @param scatterX int
     */
    public void setScatterX( int scatterX )
    {
        this.scatterX = scatterX;
    }

    /**
     * getScatterY
     *
     * @return int
     */
    public int getScatterY()
    {
        return scatterY;
    }

    /**
     * setScatterY
     *
     * @param scatterY int
     */
    public void setScatterY( int scatterY )
    {
        this.scatterY = scatterY;
    }

    /**
     * isGravityEnable
     *
     * @return boolean
     */
    public boolean isGravityEnable()
    {
        return gravityEnable;
    }

    /**
     * setGravityEnable
     *
     * @param gravityEnable boolean
     */
    public void setGravityEnable( boolean gravityEnable )
    {
        this.gravityEnable = gravityEnable;
    }

    /**
     * isMoveEnable
     *
     * @return boolean
     */
    public boolean isMoveEnable()
    {
        return moveEnable;
    }

    /**
     * setMoveEnable
     *
     * @param moveEnable boolean
     */
    public void setMoveEnable( boolean moveEnable )
    {
        this.moveEnable = moveEnable;
    }

    /**
     * isTurnTransparentEnable
     *
     * @return boolean
     */
    public boolean isTurnTransparentEnable()
    {
        return turnTransparentEnable;
    }

    /**
     * setTurnTransparentEnable
     *
     * @param turnTransparentEnable boolean
     */
    public void setTurnTransparentEnable( boolean turnTransparentEnable )
    {
        this.turnTransparentEnable = turnTransparentEnable;
    }

    /**
     * isPlaying
     *
     * @return boolean
     */
    public boolean isPlaying()
    {
        return playing;
    }

    /**
     * setPlaying
     *
     * @param playing boolean
     */
    public void setPlaying( boolean playing )
    {
        this.playing = playing;
    }

    /**
     * isColorToColorEnable
     *
     * @return boolean
     */
    public boolean isColorToColorEnable()
    {
        return colorToColorEnable;
    }

    /**
     * setColorToColorEnable
     *
     * @param colorToColorEnable boolean
     */
    public void setColorToColorEnable( boolean colorToColorEnable )
    {
        this.colorToColorEnable = colorToColorEnable;
    }

    /**
     * getStartColor
     *
     * @return Color
     */
    public Color getStartColor()
    {
        return startColor;
    }

    /**
     * setStartColor
     *
     * @param startColor Color
     */
    public void setStartColor( Color startColor )
    {
        this.startColor = startColor;
    }

    /**
     * getEndColor
     *
     * @return Color
     */
    public Color getEndColor()
    {
        return endColor;
    }

    /**
     * setEndColor
     *
     * @param endColor Color
     */
    public void setEndColor( Color endColor )
    {
        this.endColor = endColor;
    }

    /**
     * getRandomVelocityX
     *
     * @return int
     */
    public int getRandomVelocityX()
    {
        return randomVelocityX;
    }

    /**
     * setRandomVelocityX
     *
     * @param randomVelocityX int
     */
    public void setRandomVelocityX( int randomVelocityX )
    {
        this.randomVelocityX = randomVelocityX;
    }

    /**
     * getRandomVelocityY
     *
     * @return int
     */
    public int getRandomVelocityY()
    {
        return randomVelocityY;
    }

    /**
     * setRandomVelocityY
     *
     * @param randomVelocityY int
     */
    public void setRandomVelocityY( int randomVelocityY )
    {
        this.randomVelocityY = randomVelocityY;
    }
}