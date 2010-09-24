package com.j2dparticles.panes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.j2dparticles.data.GroupManager;
import com.j2dparticles.particleEffects.AttractToTargetParticleEffect;
import com.j2dparticles.particleEffects.BigExplosionsParticleEffect;
import com.j2dparticles.particleEffects.CollisionParticleEffect;
import com.j2dparticles.particleEffects.DomainsParticleEffect;
import com.j2dparticles.particleEffects.ExplosionParticleEffect;
import com.j2dparticles.particleEffects.FireParticleEffect;
import com.j2dparticles.particleEffects.FountainParticleEffect;
import com.j2dparticles.particleEffects.LettersRandomParticleEffect;
import com.j2dparticles.particleEffects.MultiColorParticleEffect;
import com.j2dparticles.particleEffects.ParticleEffect;
import com.j2dparticles.particleEffects.RainParticleEffect;
import com.j2dparticles.particleEffects.ResilienceParticleEffect;
import com.j2dparticles.particleEffects.SmokeParticleEffect;
import com.j2dparticles.particleEffects.SnowParticleEffect;
import com.j2dparticles.particleEffects.SquaresParticleEffect;
import com.j2dparticles.particleEffects.StarsParticleEffect;
import com.j2dparticles.particleEffects.TextRotationParticleEffect;
import com.j2dparticles.particleEffects.TextParticleEffect;
import com.j2dparticles.particleEffects.WorldOfGooParticleEffect;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseMotionListener;

/**
 * ParticleEffectPane
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class ParticleEffectPane
        extends JPanel
        implements Runnable
{
    private static final int SCENE_EXPLOSION = 0;
    private static final int SCENE_LETTERS_RANDON = 1;
    private static final int SCENE_RAIN = 2;
    private static final int SCENE_RESILIENCE = 3;
    private static final int SCENE_TEXT_ROTATION = 4;
    private static final int SCENE_TEXT = 5;
    private static final int SCENE_BIG_EXPLOSIONS = 6;
    private static final int SCENE_MULTI_COLORS = 7;
    private static final int SCENE_WORLD_OF_GOO = 8;
    private static final int SCENE_SMOKE = 9;
    private static final int SCENE_FIRE = 10;
    private static final int SCENE_COLLISION = 11;
    private static final int SCENE_FOUNTAIN = 12;
    private static final int SCENE_DOMAINS = 13;
    private static final int SCENE_SNOW = 14;
    private static final int SCENE_SQUARES = 15;
    private static final int SCENE_STARS = 16;
    private static final int SCENE_ATTRACT_TO_TARGET = 17;

    private JPanel panel = new JPanel( new GridBagLayout() );

    private String[] scenes = { "Explosion",
                                "Matrix",
                                "Rain",
                                "Resilience",
                                "Text Rotation",
                                "Text",
                                "Big Explosions",
                                "Multi Colors",
                                "World of Goo",
                                "Smoke",
                                "Fire",
                                "Collision",
                                "Fountain",
                                "Domains",
                                "Snow",
                                "Squares",
                                "Stars",
                                "Attract to target" };

    private JComboBox combo = new JComboBox( scenes );

    private Thread animatorThread;

    private Graphics2D dbg;

    private Image dbImage = null;

    private GroupManager groupManager = new GroupManager();

    private ParticleEffect scene;

    private double fps = 1;

    private long previousMeasure = 1;
    private long startTime;

    private int frameNumber = -1;
    private int delay = 20;

    private boolean pause = false;

    private Point mousePoint = new Point();

    /**
     * ParticleEffectPane
     *
     */
    public ParticleEffectPane()
    {
        initComponent();
    }

    /**
     * paintComponent
     *
     * @param g Graphics
     */
    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        if ( dbImage != null )
        {
            g.drawImage( dbImage, 0, 0, null );
        }
    }

    /**
     * render
     *
     */
    public void render()
    {
        int w = getWidth();
        int h = getHeight();

        if ( w == 0 )
        {
            w = 1024;
            h = 768;
        }

        if ( dbImage == null )
        { // create the buffer
            dbImage = createImage( w, h );
            if ( dbImage == null )
            {
                return;
            }
            else
            {
                dbg = (Graphics2D) dbImage.getGraphics();
                dbg.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                                      RenderingHints.VALUE_ANTIALIAS_ON );
            }
        }
        
        scene.printScene( dbg, w, h, mousePoint );

        drawInfo();
    }

    /**
     * update
     *
     * @param g Graphics
     */
    @Override
    public void update( Graphics g )
    {
        paint( g );
    }

    /**
     * drawInfo
     *
     */
    private void drawInfo()
    {
        dbg.setFont( new Font( "Dialog", Font.BOLD, 16 ) );
        dbg.drawString( "FPS: ( " + fps + " )", 10, 140 );
        dbg.drawString( "Time: ( " + (System.currentTimeMillis() - startTime ) + " )", 10, 160 );
        dbg.drawString( "Particles: ( " + groupManager.getParticlesCount() + " )", 10, 180 );
    }

    /**
     * initComponent
     *
     */
    private void initComponent()
    {
        // canvas settings

        setBackground( Color.white);
        addMouseListener( mouseListener );

        // start animation

        animatorThread = new Thread( this );
        animatorThread.start();

        startTime = System.currentTimeMillis();

        combo.addActionListener( comboListener );

        scene = new RainParticleEffect();

        groupManager = scene.initParticles();

        combo.setSelectedIndex( SCENE_FOUNTAIN );

        panel.setOpaque( false );
        panel.add( combo,
                new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        panel.add( new JLabel(),
                new GridBagConstraints( 1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( new JLabel(),
                new GridBagConstraints( 0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.BOTH,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        add( panel,
                new GridBagConstraints( 0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets( 2, 2, 2, 2 ), 0, 0 ) );

        addMouseMotionListener( mouseMotionListener );
    }
    
    /**
     * run
     *
     */
    @Override
    public void run()
    {
        Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
        Thread currentThread = Thread.currentThread();

        while ( currentThread == animatorThread )
        {
            frameNumber++;

            // calculates fps
            if ( frameNumber % 10 == 0 )
            {
                double time = System.currentTimeMillis() - previousMeasure;

                fps = (int) (10 / (time / 1000));

                previousMeasure = System.currentTimeMillis();
            }

            // update, paint and repaint

            if ( !pause )
            {
                groupManager.update();
                render();
                repaint();
            }            

            try
            {
                Thread.sleep( delay );
            }
            catch ( InterruptedException e )
            {
                break;
            }
        }
    }

    /**
     * pause
     *
     */
    private void pause()
    {
        pause = !pause;
    }

    MouseListener mouseListener = new MouseAdapter()
    {
        @Override
        public void mousePressed( MouseEvent e )
        {
            if ( e.getButton() == MouseEvent.BUTTON1 )
            {
                pause();
            }
        }
    };

    ActionListener comboListener = new ActionListener()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            int index = ((JComboBox)e.getSource()).getSelectedIndex();

            startTime = System.currentTimeMillis();
            
            switch ( index )
            {
                case  SCENE_EXPLOSION :
                    scene = new ExplosionParticleEffect();
                    break;

                case  SCENE_LETTERS_RANDON :
                    scene = new LettersRandomParticleEffect();
                    break;

                case  SCENE_RAIN :
                    scene = new RainParticleEffect();
                    break;

                case  SCENE_RESILIENCE :
                    scene = new ResilienceParticleEffect();
                    break;

                case  SCENE_TEXT_ROTATION :
                    scene = new TextRotationParticleEffect();
                    break;

                case  SCENE_TEXT :
                    scene = new TextParticleEffect();
                    break;

                case  SCENE_BIG_EXPLOSIONS :
                    scene = new BigExplosionsParticleEffect();
                    break;

                case  SCENE_MULTI_COLORS :
                    scene = new MultiColorParticleEffect();
                    break;

                case  SCENE_WORLD_OF_GOO :
                    scene = new WorldOfGooParticleEffect();
                    break;

                case  SCENE_SMOKE :
                    scene = new SmokeParticleEffect();
                    break;

                case  SCENE_FIRE :
                    scene = new FireParticleEffect();
                    break;

                case  SCENE_COLLISION :
                    scene = new CollisionParticleEffect();
                    break;

                case  SCENE_FOUNTAIN :
                    scene = new FountainParticleEffect();
                    break;

                case  SCENE_DOMAINS :
                    scene = new DomainsParticleEffect();
                    break;

                case  SCENE_SNOW :
                    scene = new SnowParticleEffect();
                    break;

                case  SCENE_SQUARES :
                    scene = new SquaresParticleEffect();
                    break;

                case  SCENE_STARS :
                    scene = new StarsParticleEffect();
                    break;

                case  SCENE_ATTRACT_TO_TARGET :
                    scene = new AttractToTargetParticleEffect();
                    break;
            }

            groupManager = scene.initParticles();
        }
    };

    MouseMotionListener mouseMotionListener = new MouseMotionListener()
    {
        @Override
        public void mouseDragged( MouseEvent e )
        {
        }

        @Override
        public void mouseMoved( MouseEvent e )
        {
            mousePoint = e.getPoint();
        }
    };
}