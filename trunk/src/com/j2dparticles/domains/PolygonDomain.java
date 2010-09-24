package com.j2dparticles.domains;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.Position;
import com.j2dparticles.data.Velocity;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 * PolygonDomain
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class PolygonDomain implements Domain
{
    private Polygon polygon = new Polygon();
    
    private Segment[] segments;

    /**
     * PolygonDomain
     *
     * @param polygon Polygon
     */
    public PolygonDomain( Polygon polygon )
    {
        this.polygon = polygon;

        segments = new Segment[polygon.npoints];

        int[] xs = polygon.xpoints;
        int[] ys = polygon.ypoints;

        for ( int i = 0; i < polygon.npoints - 1; i++ )
        {
            segments[i] = new Segment( xs[i],   ys[i],
                                       xs[i+1], ys[i+1] );
        }

        segments[polygon.npoints-1] = new Segment( xs[polygon.npoints-1],   ys[polygon.npoints-1],
                                                   xs[0], ys[0] );
    }
    
    /**
     * contains
     *
     * @param x int
     * @param y int
     * @return boolean
     */
    @Override
    public boolean contains( int x, int y )
    {
        return polygon.contains( x, y );
    }

    /**
     * createParticles
     *
     * @param rate int
     * @return List<Particle>
     */
    @Override
    public List<Particle> createParticles( int rate )
    {
        List<Particle> list = new ArrayList<Particle>();

        for ( int i = 0; i < rate; i++ )
        {
            Particle p = new Particle();

            Point point = getValidPoint();

            p.setCurrentPosition( new Position( point.getX(), point.getY() ) );
            p.setPreviousPosition( new Position( point.getX(), point.getY() ) );

            list.add( p );
        }

        return list;
    }

    /**
     * getValidPoint
     *
     * @return Point
     */
    private Point getValidPoint()
    {
        Point point = new Point( (int) (polygon.getBounds().x + polygon.getBounds().width * Math.random()),
                                 (int) (polygon.getBounds().y + polygon.getBounds().height * Math.random()) );

        while ( !polygon.contains( point ) )
        {
            point = new Point( (int) (polygon.getBounds().x + polygon.getBounds().width * Math.random()),
                               (int) (polygon.getBounds().y + polygon.getBounds().height * Math.random()) );
        }

        return point;
    }

    /**
     * bounce
     *
     * @param p Particle
     * @param resilience float
     */
    @Override
    public void bounce( Particle p, float resilience )
    {
         for ( int i = 0; i < segments.length; i++ )
         {
            checkCollision( segments[i], p, resilience );
         }
    }

    /**
     * checkCollision
     *
     * @param segment Segment
     * @param p Particle
     * @param resilience float
     */
    private void checkCollision( Segment segment, Particle p, float resilience )
    {
        // Get difference between orb and ground
        double deltaX = p.getCurrentPosition().x - segment.x;
        double deltaY = p.getCurrentPosition().y - segment.y;

        // Precalculate trig values
        double cosine = Math.cos( segment.rot );
        double sine = Math.sin( segment.rot );

        /* Rotate ground and velocity to allow
        orthogonal collision calculations */
        double groundXTemp = cosine * deltaX + sine * deltaY;
        double groundYTemp = cosine * deltaY - sine * deltaX;
        double velocityXTemp = cosine * p.getCurrentVelocity().dx + sine * p.getCurrentVelocity().dy;
        double velocityYTemp = cosine * p.getCurrentVelocity().dy - sine * p.getCurrentVelocity().dx;

        /* Ground collision - check for surface
        collision and also that orb is within
        left/rights bounds of ground segment */
        if ( groundYTemp > -p.getSize()
                && p.getCurrentPosition().x > segment.x1
                && p.getCurrentPosition().x < segment.x2 )
        {
            // keep orb from going into ground
            groundYTemp = -p.getSize();
            // bounce and slow down orb
            velocityYTemp *= -1.0;
            velocityYTemp *= resilience;
        }

        // Reset ground, velocity and orb
        deltaX = cosine * groundXTemp - sine * groundYTemp;
        deltaY = cosine * groundYTemp + sine * groundXTemp;

        p.setCurrentVelocity( new Velocity( cosine * velocityXTemp - sine * velocityYTemp,
                                            cosine * velocityYTemp + sine * velocityXTemp ) );
    }

    /**
     * Segment
     *
     * @return class
     */
    public class Segment
    {
        float x1, y1, x2, y2;
        float x, y, len, rot;

        /**
         * Segment
         *
         */
        Segment()
        {
        }

        /**
         * Segment
         *
         * @param x1 float
         * @param y1 float
         * @param x2 float
         * @param y2 float
         */
        Segment( float x1, float y1, float x2, float y2 )
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            x = (x1 + x2) / 2;
            y = (y1 + y2) / 2;

            len = (float) Point.distance( x1, y1, x2, y2 );
            rot = (float) Math.atan2( (y2 - y1), (x2 - x1) );
        }
    }
}