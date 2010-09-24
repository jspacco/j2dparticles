package com.j2dparticles.panes;

import javax.swing.JPanel;

/**
 *
 * @author Michael
 */
public class CanvasPane
        extends JPanel
//        implements Runnable
{
//    private Thread animatorThread;
//
//    private List<Particle> particles = new ArrayList<Particle>();
//    private List<Particle> copyParticles = new ArrayList<Particle>();
//
//    private Graphics2D dbg;
//    private Image dbImage = null;
//
//    private ParticleSettings particleSettings = new ParticleSettings();
//
//    ActionMove actionMove = new ActionMove();
//    ActionGravity actionGravity = new ActionGravity();
//    ActionTurnTransparent actionTurnTransparent = new ActionTurnTransparent();
//
//    private int frameNumber = -1;
//    private int delay = 20;
//    private int numOfBalls = 0;
//
//    public CanvasPane()
//    {
//        init();
//    }
//
//    @Override
//    public void paintComponent( Graphics g )
//    {
//        super.paintComponent( g );
//
//        if ( dbImage != null )
//        {
//            g.drawImage( dbImage, 0, 0, null );
//        }
//    }
//
//    public void setConfig( ParticleSettings config)
//    {
//        particleSettings = config;
//    }
//
//    public void render()
//    {
//        int w = getWidth();
//        int h = getHeight();
//
//        if ( w == 0 )
//        {
//            w = 1280;
//            h = 720;
//            System.out.println( "here" );
//        }
//
//        if ( dbImage == null )
//        { // create the buffer
//            dbImage = createImage( w, h );
//            if ( dbImage == null )
//            {
//                System.out.println( "dbImage is null" );
//                return;
//            }
//            else
//            {
//                dbg = (Graphics2D) dbImage.getGraphics();
//                dbg.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
//                                      RenderingHints.VALUE_ANTIALIAS_ON );
//            }
//        }
//
//        dbg.setColor( particleSettings.getSceneColor() );
//        dbg.fillRect( 0, 0, w, h );
//
//        drawInfo();
//
//        try
//        {
//            for ( Particle b : copyParticles )
//            {
//                dbg.setColor( b.getColor());
//
//                dbg.fillOval( (int)b.getCurrentPosition().x, (int)(b.getCurrentPosition().y), b.getSize(), b.getSize());
//            }
//        }
//
//        catch( java.util.ConcurrentModificationException e )
//        {
//            System.out.println( "ops" );
//        }
//    }
//
//    @Override
//    public void update( Graphics g )
//    {
//        paint( g );
//    }
//
//    private void drawInfo()
//    {
//        if ( copyParticles.size() > 0 )
//        {
//            dbg.setColor( Color.black );
//            dbg.drawString( "Frames: " + frameNumber, 10, 20 );
//        }
//    }
//
//    private void applyActions()
//    {
//        for ( Particle p : copyParticles )
//        {
//            p.applyActions();
//        }
//    }
//
//    private void createParticles()
//    {
//        for ( int i = 0; i < particleSettings.getRate(); i++ )
//        {
//            Particle particle = new Particle();
//
//            actionGravity.setGravity( particleSettings.getGravity() );
//
//            if ( particleSettings.isMoveEnable() )
//            {
//                particle.addAction( actionMove );
//            }
//
//            if ( particleSettings.isGravityEnable() )
//            {
//                particle.addAction( actionGravity );
//            }
//
//            if ( particleSettings.isTurnTransparentEnable() )
//            {
//                particle.addAction( actionTurnTransparent );
//            }
//
//            particle.currentPosition.x = particleSettings.getPosition().x + ( getVariance( particleSettings.getScatterX() ) - particleSettings.getScatterX() / 2 );
//            particle.currentPosition.y = particleSettings.getPosition().y + ( getVariance( particleSettings.getScatterY() ) - particleSettings.getScatterY() / 2 );
//
//            particle.currentVelocity.x = particleSettings.getVelocity().x + getVariance( particleSettings.getRandom() );
//            particle.currentVelocity.y = particleSettings.getVelocity().y + getVariance( particleSettings.getRandom() );
//
//            particle.setSize( particleSettings.getSize() );
//
//            particle.impulsion = 10;
//
//            particle.setColor( particleSettings.getParticleColor() );
//
//            particle.setTimeLife( particleSettings.getLife() );
//
//            particles.add( particle );
//
//            particle.init();
//
//            numOfBalls++;
//
////            particle.addAction( new ActionSetTransparent() );
//
////            particle.currentPosition.x = p.x;
////            particle.currentPosition.y = groundHeight - p.y;
//
////            particle.currentVelocity.x = getVariance( 200 ) + 100; //getVariance( 200 ) - 100;
////            particle.currentVelocity.y = getVariance( 1000 );
//
////            int size = getVariance( 20 ) + 1;
////            particle.size = new Dimension( size, size );
//
////            ball.color = new Color( getVariance( 255 ), getVariance( 255 ), getVariance( 255 ), getVariance( 255 ) );
////            particle.setColor( new Color( 100, 100, 100, getVariance( 255 ) ) );
//
////            ball.color = new Color( 50, 50, 255);
//        }
//    }
//
//    private int getVariance( int n )
//    {
//        return (int)(Math.random() * n );
//    }
//
//    private void clearAll()
//    {
//        copyParticles.clear();
//        particles.clear();
//    }
//
//    private void init()
//    {
//        setBackground( Color.white );
//
//        // Start animation
//        animatorThread = new Thread( this );
//        animatorThread.start();
//
//        addMouseListener( new MouseAdapter()
//        {
//            @Override
//            public void mousePressed( MouseEvent e )
//            {
//                if ( e.getButton() == MouseEvent.BUTTON3 )
//                {
//                    clearAll();
//                }
//            }
//        });
//    }
//
//    public void run()
//    {
//        Thread.currentThread().setPriority( Thread.MAX_PRIORITY );
//
//        Thread currentThread = Thread.currentThread();
//
//        while ( currentThread == animatorThread )
//        {
//            if ( particleSettings.isPlaying() )
//            {
//                frameNumber++;
//
//                if ( frameNumber % 4 == 0 )
//                {
//                    createParticles();
//                }
//
//                copyParticles.clear();
//
//                for ( Particle b : particles )
//                {
//                    if ( b.getBirthTime() + b.getTimeLife() > System.currentTimeMillis() )
//                    {
//                        copyParticles.add( b );
//                    }
//                }
//
//                applyActions();
//                render();
//                repaint();
//            }
//
//            try
//            {
//                Thread.sleep( delay );
//            }
//            catch ( InterruptedException e )
//            {
//                break;
//            }
//        }
//    }
}