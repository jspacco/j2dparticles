package com.j2dparticles.panes;

import com.j2dparticles.data.ParticleSettings;
import com.j2dparticles.data.Position;
import com.j2dparticles.data.Velocity;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MenuPane
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class MenuPane extends JPanel
{
    private JPanel particlePane = new JPanel();
    private JPanel scenePane = new JPanel();
    private JPanel moveActionPane = new JPanel();
    private JPanel gravityActionPane = new JPanel();
    private JPanel turnTransparentActionPane = new JPanel();
    private JPanel colorToColorActionPane = new JPanel();

    private ParticleSettings currentConfig = new ParticleSettings();

    private JLabel lbRate = new JLabel();
    private JLabel lbPositionX = new JLabel();
    private JLabel lbPositionY = new JLabel();
    private JLabel lbSpeedX = new JLabel();
    private JLabel lbSpeedY = new JLabel();
    private JLabel lbScatterX = new JLabel();
    private JLabel lbScatterY = new JLabel();
    private JLabel lbSize = new JLabel();
    private JLabel lbRandom = new JLabel();
    private JLabel lbLife = new JLabel();
    private JLabel lbParticleColor = new JLabel();
    private JLabel lbStartColor = new JLabel();
    private JLabel lbEndColor = new JLabel();

    private JLabel lbGravity = new JLabel();
    private JLabel lbSceneColor = new JLabel();

    private JSlider slRate = new JSlider( SwingConstants.HORIZONTAL, 0, 100, 10 );

    private JSlider slPositionX = new JSlider( SwingConstants.HORIZONTAL, 0, 1280, 200 );
    private JSlider slPositionY = new JSlider( SwingConstants.HORIZONTAL, 0, 720, 200 );
    private JSlider slSpeedX = new JSlider( SwingConstants.HORIZONTAL, -500, 500, 100 );
    private JSlider slSpeedY = new JSlider( SwingConstants.HORIZONTAL, -500, 500, 100 );

    private JSlider slScatterX = new JSlider( SwingConstants.HORIZONTAL, 0, 500, 1 );
    private JSlider slScatterY = new JSlider( SwingConstants.HORIZONTAL, 0, 500, 1 );

    private JSlider slSize = new JSlider( SwingConstants.HORIZONTAL, 0, 30, 10 );
    private JSlider slRandom = new JSlider( SwingConstants.HORIZONTAL, 0, 500, 100 );
    private JSlider slLife = new JSlider( SwingConstants.HORIZONTAL, 0, 5000, 1000 );
    private JSlider slGravity = new JSlider( SwingConstants.HORIZONTAL, -500, 500, 100 );

    private JButton btPlayer = new JButton();
    private JButton btParticleColor = new JButton();
    private JButton btSceneColor = new JButton();
    private JButton btStartColor = new JButton();
    private JButton btEndColor = new JButton();

    private JCheckBox cbEnableGravity = new JCheckBox();
    private JCheckBox cbEnableMove = new JCheckBox();
    private JCheckBox cbEnableTurnTransparent = new JCheckBox();
    private JCheckBox cbEnableColorToColor = new JCheckBox();

    private List<ChangeListener> changeListeners = null;

    private Color selectedColor = Color.black;

    public MenuPane()
    {
        initComponents();
    }

    public ParticleSettings getConfig()
    {
        return currentConfig;
    }

    private Color getColor()
    {
        final JColorChooser chooser = new JColorChooser();

        JDialog dialog = JColorChooser.createDialog( MenuPane.this,
                                                     "Color Chooser", true, chooser, new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                selectedColor = chooser.getColor();
            }
        }, null );
        dialog.setVisible( true );

        return selectedColor;
    }

    private void initComponents()
    {
        Font defaultFont = new Font( Font.MONOSPACED, Font.PLAIN, 12 );

        lbPositionX.setText( "X: ( " + slPositionX.getValue() + " )" );
        lbPositionX.setFont( defaultFont );

        lbPositionY.setText( "Y: ( " + slPositionY.getValue() + " )" );
        lbPositionY.setFont( defaultFont );

        lbSpeedX.setText( "Speed X: ( " + slSpeedX.getValue() + " )" );
        lbSpeedX.setFont( defaultFont );

        lbSpeedY.setText( "Speed Y: ( " + slSpeedY.getValue() + " )" );
        lbSpeedY.setFont( defaultFont );

        lbScatterX.setText( "Scatter X: ( " + slScatterX.getValue() + " )" );
        lbScatterX.setFont( defaultFont );

        lbScatterY.setText( "Scatter Y: ( " + slScatterY.getValue() + " )" );
        lbScatterY.setFont( defaultFont );

        lbRate.setText( "Rate: ( " + slRate.getValue()+ " )" );
        lbRate.setFont( defaultFont );

        lbSize.setText( "Size: ( " + slSize.getValue() + " )" );
        lbSize.setFont( defaultFont );

        lbRandom.setText( "Randomness: ( " + slRandom.getValue() + " )" );
        lbRandom.setFont( defaultFont );

        lbLife.setText( "Life: ( " + slLife.getValue() + " )" );
        lbLife.setFont( defaultFont );

        lbParticleColor.setText( "Color: " );
        lbParticleColor.setFont( defaultFont );

        lbStartColor.setText( "Start Color: " );
        lbStartColor.setFont( defaultFont );

        lbEndColor.setText( "End Color: " );
        lbEndColor.setFont( defaultFont );

        lbGravity.setText( "Gravity: ( " + slGravity.getValue() + " )" );
        lbGravity.setFont( defaultFont );

        lbSceneColor.setText( "Color: " );
        lbSceneColor.setFont( defaultFont );

        cbEnableMove.setText( "Enable" );
        cbEnableMove.setFont( defaultFont );
        cbEnableMove.setSelected( true );

        cbEnableGravity.setText( "Enable" );
        cbEnableGravity.setFont( defaultFont );
        cbEnableGravity.setSelected( true );

        cbEnableTurnTransparent.setText( "Enable" );
        cbEnableTurnTransparent.setFont( defaultFont );
        cbEnableTurnTransparent.setSelected( false );

        cbEnableColorToColor.setText( "Enable" );
        cbEnableColorToColor.setFont( defaultFont );
        cbEnableColorToColor.setSelected( false );

        btParticleColor.addActionListener( actionParticleColor );
        btParticleColor.setBackground( Color.white );

        btSceneColor.addActionListener( actionSceneColor );
        btSceneColor.setBackground( Color.black );

        btStartColor.addActionListener( actionStartColor );
        btStartColor.setBackground( Color.white );

        btEndColor.addActionListener( actionEndColor );
        btEndColor.setBackground( Color.black );

        btPlayer.setText( "Play" );
        btPlayer.setFont( defaultFont );
        btPlayer.addActionListener( playerAction );
        
        slPositionX.addChangeListener( changeConfig );
        slPositionY.addChangeListener( changeConfig );
        slSpeedX.addChangeListener( changeConfig );
        slSpeedY.addChangeListener( changeConfig );
        slScatterX.addChangeListener( changeConfig );
        slScatterY.addChangeListener( changeConfig );
        slRate.addChangeListener( changeConfig );
        slSize.addChangeListener( changeConfig );
        slRandom.addChangeListener( changeConfig );
        slLife.addChangeListener( changeConfig );
        slGravity.addChangeListener( changeConfig );
        cbEnableMove.addChangeListener( changeConfig );
        cbEnableGravity.addChangeListener( changeConfig );
        cbEnableTurnTransparent.addChangeListener( changeConfig );
        cbEnableColorToColor.addChangeListener( changeConfig );

        particlePane.setBorder(javax.swing.BorderFactory.createTitledBorder( "Particle" ));
        scenePane.setBorder(javax.swing.BorderFactory.createTitledBorder( "Scene"));
        moveActionPane.setBorder(javax.swing.BorderFactory.createTitledBorder( "Move Action" ));
        gravityActionPane.setBorder(javax.swing.BorderFactory.createTitledBorder( "Gravity Action" ));
        turnTransparentActionPane.setBorder(javax.swing.BorderFactory.createTitledBorder( "Turn Transparent Action" ));
        colorToColorActionPane.setBorder(javax.swing.BorderFactory.createTitledBorder( "Turn Transparent Action" ));

        setLayout( new GridBagLayout() );

        particlePane.setLayout( new GridBagLayout() );
        scenePane.setLayout( new GridBagLayout() );
        moveActionPane.setLayout( new GridBagLayout() );
        gravityActionPane.setLayout( new GridBagLayout() );
        turnTransparentActionPane.setLayout( new GridBagLayout() );
        colorToColorActionPane.setLayout( new GridBagLayout() );

        int verticalPosition = 0;

        particlePane.add( lbPositionX,
                new GridBagConstraints( 0, verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slPositionX,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbPositionY,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slPositionY,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbSpeedX,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slSpeedX,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbSpeedY,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slSpeedY,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbScatterX,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slScatterX,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbScatterY,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slScatterY,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbRate,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slRate,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbSize,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slSize,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbRandom,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slRandom,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbLife,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( slLife,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( lbParticleColor,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        particlePane.add( btParticleColor,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        verticalPosition = 0;
        
        scenePane.add( lbSceneColor,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        scenePane.add( btSceneColor,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        scenePane.add( btPlayer,
                new GridBagConstraints( 0, ++verticalPosition, 2, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        verticalPosition = 0;

        gravityActionPane.add( cbEnableGravity,
                new GridBagConstraints( 0, verticalPosition, 2, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        gravityActionPane.add( lbGravity,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        gravityActionPane.add( slGravity,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        verticalPosition = 0;

        moveActionPane.add( cbEnableMove,
                new GridBagConstraints( 0, verticalPosition, 2, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        verticalPosition = 0;

        turnTransparentActionPane.add( cbEnableTurnTransparent,
                new GridBagConstraints( 0, verticalPosition, 2, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        verticalPosition = 0;

        colorToColorActionPane.add( cbEnableColorToColor,
                new GridBagConstraints( 0, verticalPosition, 2, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        colorToColorActionPane.add( lbStartColor,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        colorToColorActionPane.add( btStartColor,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        colorToColorActionPane.add( lbEndColor,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        colorToColorActionPane.add( btEndColor,
                new GridBagConstraints( 1, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        verticalPosition = 0;

        add( scenePane,
                new GridBagConstraints( 0, verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( particlePane,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( moveActionPane,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( gravityActionPane,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( turnTransparentActionPane,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( colorToColorActionPane,
                new GridBagConstraints( 0, ++verticalPosition, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );
        
    }

    private ActionListener playerAction = new ActionListener()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            currentConfig.setPlaying( !currentConfig.isPlaying() );
            btPlayer.setText( currentConfig.isPlaying() ? "Stop" : "Play" );

            fireChangeEvent();
        }
    };

    private ActionListener actionParticleColor = new ActionListener()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            btParticleColor.setBackground( getColor() );

            currentConfig.setParticleColor( btParticleColor.getBackground() );

            fireChangeEvent();
        }
    };

    private ActionListener actionSceneColor = new ActionListener()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            btSceneColor.setBackground( getColor() );

            currentConfig.setSceneColor( btSceneColor.getBackground() );

            fireChangeEvent();
        }
    };

    private ActionListener actionStartColor = new ActionListener()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            btStartColor.setBackground( getColor() );

            currentConfig.setStartColor( btStartColor.getBackground() );

            fireChangeEvent();
        }
    };

    private ActionListener actionEndColor = new ActionListener()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            btEndColor.setBackground( getColor() );

            currentConfig.setSceneColor( btEndColor.getBackground() );

            fireChangeEvent();
        }
    };

    private ChangeListener changeConfig = new ChangeListener()
    {
        @Override
        public void stateChanged( ChangeEvent e )
        {
            lbRate.setText( "Rate: ( " + slRate.getValue()+ " )" );
            lbPositionX.setText( "X: ( " + slPositionX.getValue() + " )" );
            lbPositionY.setText( "Y: ( " + slPositionY.getValue() + " )" );
            lbSpeedX.setText( "Speed X: ( " + slSpeedX.getValue() + " )" );
            lbSpeedY.setText( "Speed Y: ( " + slSpeedY.getValue() + " )" );
            lbScatterX.setText( "Scatter X: ( " + slScatterX.getValue() + " )" );
            lbScatterY.setText( "Scatter Y: ( " + slScatterY.getValue() + " )" );
            lbSize.setText( "Size: ( " + slSize.getValue() + " )" );
            lbRandom.setText( "Randomness: ( " + slRandom.getValue() + " )" );
            lbLife.setText( "Life: ( " + slLife.getValue() + " )" );
            lbGravity.setText( "Gravity: ( " + slGravity.getValue() + " )" );

            currentConfig.setRate( slRate.getValue() );
            currentConfig.setPosition( new Position( slPositionX.getValue(), slPositionY.getValue() ) );
            currentConfig.setVelocity( new Velocity( slSpeedX.getValue(), slSpeedY.getValue() ) );
            currentConfig.setScatterX( slScatterX.getValue() );
            currentConfig.setScatterY( slScatterY.getValue() );
            currentConfig.setSize( slSize.getValue() );
            currentConfig.setRandom( slRandom.getValue() );
            currentConfig.setLife( slLife.getValue() );
            currentConfig.setGravity( slGravity.getValue() );
            currentConfig.setMoveEnable( cbEnableMove.isSelected() );
            currentConfig.setGravityEnable( cbEnableGravity.isSelected() );
            currentConfig.setTurnTransparentEnable( cbEnableTurnTransparent.isSelected() );
            currentConfig.setColorToColorEnable( cbEnableColorToColor.isSelected() );

            fireChangeEvent();
        }
    };

    public void addChangeListener( ChangeListener l )
    {
        if ( changeListeners == null )
        {
            changeListeners = new ArrayList( 2 );
        }

        if ( ! changeListeners.contains( l ) )
        {
            changeListeners.add( l );
        }
    }

    public void removeChangeListener( ChangeListener l )
    {
        if ( changeListeners != null )
        {
            if ( ! changeListeners.contains( l ) )
            {
                changeListeners.remove( l );
            }
        }
    }

    protected void fireChangeEvent()
    {
        if ( changeListeners != null )
        {
            if ( changeListeners.size() > 0 )
            {
                ChangeEvent event = new ChangeEvent( this );

                for ( ChangeListener l : changeListeners )
                {
                    l.stateChanged( event );
                }
            }
        }
    }
}