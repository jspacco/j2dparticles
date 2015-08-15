# Introduction #

J2DParticles is a library that can be used for both Java and JavaFX. The library was developed to facilitate the creation of particle effects in graphics applications such as games.


## Sample ##

The example below is a small demonstration of the usability of the library. With about 80 lines of code is possible to develop a simple particle effect.

```
import com.j2dparticles.data.*;
import com.j2dparticles.domains.*;
import com.j2dparticles.particlesActions.*;
import com.j2dparticles.sourceActions.*;
import java.awt.*;
import javax.swing.*;
public class TesteJ2DParticles{
    public static void main( String[] args ) {
        JFrame frame = new JFrame();
        frame.getContentPane().add( new Painel() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 200, 200 );
        frame.setVisible( true );
    }

    public static class Painel extends JPanel implements Runnable {
        public static Thread animatorThread;
        private static GroupManager groupManager = new GroupManager();

        public Painel() {
            animatorThread = new Thread( this );
            animatorThread.start();

            // create particle group
            ParticleGroup particleGroup = new ParticleGroup();
            // its necessary to define a domain
            particleGroup.setSourceDomain( new CircleDomain( 20, 150, 5 ) );
            // set initial velocity
            particleGroup.addSourceAction( new VelocityParticleSource( 60, -180, 80, -200 ) );
            // add actions: gravity and kill particles after 600 miliseconds
            particleGroup.addParticleAction( new GravityParticleAction( 0, 10 ) );
            particleGroup.addParticleAction( new KillParticleAction( 600 ) );
            groupManager.addParticleGroup( particleGroup );
        }

        // paint particles
        public void paint( Graphics g ) {
            g.setColor( Color.white );
            g.fillRect( 0, 0, getWidth(), getHeight() );
            g.setColor( Color.blue );
            for ( Particle p : groupManager.getParticleGroups().get( 0 ).getParticles() ){
                g.fillOval( (int) p.getCurrentPosition().x, (int) (p.getCurrentPosition().y - 5), 5, 5 );
                }
            }

            // control particles execution and rendering
            public void run() {
               Thread currentThread = Thread.currentThread();
               while ( currentThread == animatorThread ) {
                  groupManager.update(); // update particles
                  repaint(); // render particles
                  try {
                     Thread.sleep( 20 );
                  }
                  catch ( InterruptedException e ) {
                     break;
                }
            }
        }
    }
}
```

The result can be seen in the image below.

![http://lh6.ggpht.com/_ttDD7bj8BSo/TJ4LyTbODkI/AAAAAAAAABE/TGVmHjglAMI/exemplo.png](http://lh6.ggpht.com/_ttDD7bj8BSo/TJ4LyTbODkI/AAAAAAAAABE/TGVmHjglAMI/exemplo.png)

## Packages Description ##

At moment, the library contains the following packages.

### com.j2dparticles.data ###
Contains the basic data.
  * GroupManager
  * Position
  * Velocity
  * SymbolProperty

### com.j2dparticles.domains ###
Contains the classes that describe the geometric areas where particles are created or where are they undergo changes.

  * Domain
  * CircleDomain
  * PolygonDomain
  * RingDomain

### com.j2dparticles.particleSource ###
Classes define the initial values of the particles group.

  * ColorParticleSource
  * EndTimeParticleSource
  * ExplosionParticleSource
  * RandomCharacterParticleSource
  * SizeParticleSource
  * TextCreationParticleSource
  * VelocityParticleSource

### com.j2dparticles.particleActions ###
Classes that modify the values of the particles.

  * AttractToTarget
  * AvoidPointParticleAction
  * BounceCircleParticleAction
  * BounceParticleAction
  * BounceRectangleParticleAction
  * ColorToColorParticleAction
  * GravityParticleAction
  * KillParticleAction
  * ParticleAction
  * SinkParticleAction
  * TextMovimentParticleAction
  * TextTargetParticleAction
  * TurnTransparenteParticleAction