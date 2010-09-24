package com.j2dparticles.util;

/**
 * RandomValue
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class RandomValue
{
    /**
     * getFromRange
     *
     * @param n double
     * @return double
     */
    public static double getFromRange( double n )
    {
        return Math.random() * n;
    }

    /**
     * getFromRange
     *
     * @param n long
     * @return long
     */
    public static long getFromRange( long n )
    {
        return (long) (Math.random() * n);
    }

    /**
     * betweenInterval
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int betweenInterval( int a, int b )
    {
        return a + (int)(Math.random() * (b - a) );
    }

    /**
     * betweenInterval
     *
     * @param a long
     * @param b long
     * @return long
     */
    public static long betweenInterval( long a, long b )
    {
        return a + (long)(Math.random() * (b - a) );
    }

    /**
     * getRangeForColor
     *
     * @param c1 int
     * @param c2 int
     * @return int
     */
    public static int getRangeForColor( int c1, int c2 )
    {
        c1 = c1 < 0 ? 0 : c1;
        c2 = c2 < 0 ? 0 : c2;

        c1 = c1 > 255 ? 255 : c1;
        c2 = c2 > 255 ? 255 : c2;

        int value = (int)(Math.min( c1, c2 ) + RandomValue.getFromRange( Math.max( c1, c2 ) - Math.min( c1, c2 ) ) );

        return value;
    }
}