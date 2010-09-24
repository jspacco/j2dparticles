package com.j2dparticles.util;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * ResourceLocator
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ResourceLocator
{
    public Image getImage( String name )
    {
        Image image = new ImageIcon( getClass().getResource( "/images/" + name ) ).getImage();

        return image;
    }
}