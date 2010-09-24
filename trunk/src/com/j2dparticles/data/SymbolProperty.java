/*
 * SymbolProperty.java
 *
 * J2DParticles - Particles for Java
 *
 */

package com.j2dparticles.data;

import java.awt.Font;

/**
 * SymbolProperty is used to manage the properties of the particle that use
 * text actions.
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class SymbolProperty
{
    private Font font;

    private char character;

    private String text = "";

    private double rotation;
    private double rpm;
    
    private int size;

    private boolean inverse = false;

    /**
     * SymbolProperty
     *
     */
    public SymbolProperty()
    {
    }

    /**
     * getFont
     *
     * @return Font
     */
    public Font getFont()
    {
        return font;
    }

    /**
     * setFont
     *
     * @param value Font
     */
    public void setFont( Font value )
    {
        this.font = value;
    }

    /**
     * getCharacter
     *
     * @return char
     */
    public char getCharacter()
    {
        return character;
    }

    /**
     * setCharacter
     *
     * @param character char
     */
    public void setCharacter( char character )
    {
        this.character = character;
    }

    /**
     * getText
     *
     * @return String
     */
    public String getText()
    {
        return text;
    }

    /**
     * setText
     *
     * @param value String
     */
    public void setText( String value )
    {
        this.text = value;
    }

    /**
     * getRotation
     *
     * @return double
     */
    public double getRotation()
    {
        return rotation;
    }

    /**
     * setRotation
     *
     * @param value double
     */
    public void setRotation( double value )
    {
        this.rotation = value;
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
     * @param value int
     */
    public void setSize( int value )
    {
        this.size = value;
    }

    /**
     * isInverse
     *
     * @return boolean
     */
    public boolean isInverse()
    {
        return inverse;
    }

    /**
     * setInverse
     *
     * @param value boolean
     */
    public void setInverse( boolean value )
    {
        this.inverse = value;
    }
}