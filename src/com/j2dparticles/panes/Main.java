package com.j2dparticles.panes;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Main class
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
        }
        catch ( ClassNotFoundException ex )
        {
        }
        catch ( InstantiationException ex )
        {
        }
        catch ( IllegalAccessException ex )
        {
        }
        catch ( UnsupportedLookAndFeelException ex )
        {
        }

        ControllerPane pane = new ControllerPane();

        JFrame frame = new JFrame( "Particles Java" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add( pane );
        frame.pack();
        frame.setSize( 1024, 768 );
        frame.setVisible( true );
    }
}
