package com.j2dparticles.panes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * ControllerPane
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ControllerPane extends JPanel
{
    private ParticleEffectPane canvas = new ParticleEffectPane();

    private MenuPane menuPane = new MenuPane();

    /**
     * ControllerPane
     *
     */
    public ControllerPane()
    {
        initComponents();
    }

    /**
     * initComponents
     *
     */
    private void initComponents()
    {
        setLayout( new GridBagLayout() );

        menuPane.addChangeListener( menuListener );

        add( canvas,
                new GridBagConstraints( 0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );
    }

    private ChangeListener menuListener = new ChangeListener()
    {
        @Override
        public void stateChanged( ChangeEvent e )
        {
//            canvasPane.setConfig( menuPane.getConfig() );
        }
    };
}
