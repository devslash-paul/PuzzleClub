package servlet;

import puzzles.sortstring2.LongStringPuzzle;

public class TestLongString implements LongStringPuzzle {

    @Override
    public String toString( final Long s )
    {
        if ( s == null )
        {
            return "";
        }
        if ( s < 0 )
        {

            long big10 = (s >>> 1) / 5L;
            long small = s - big10 * 10L;
            final long v = -(s + small);
            String big = "" + v;
            return big.substring( 0, big.length() - 1 ) + small;
        }
        return "" + s;
    }

    public static void main(String... args)
    {
        TestLongString inst = new TestLongString();
        System.out.println( inst.toString( Long.MIN_VALUE ) );
        System.out.println( inst.toString( -123l ) );
        System.out.println( inst.toString( 123L ) );
    }
}