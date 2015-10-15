package puzzles.sortstring2;

import java.util.HashMap;
import java.util.Map;

import puzzles.ExamMark;
import puzzles.Marker;

/**
 * User: pault Date: 9/10/2015 Time: 14:21
 */
public class LongStringMarker implements Marker<LongStringPuzzle>
{
    public ExamMark mark( final LongStringPuzzle loaded )
    {
        Map<String, String> tests = new HashMap<>();
        tests.put( "norm", String.valueOf( "123".equals( loaded.toString( -123l ) ) ) );

        tests.put( "max",
            String.valueOf( "9223372036854775808".equals( loaded.toString( Long.MIN_VALUE ) ) ) );

        try
        {
            loaded.toString( null );
            tests.put( "null", "true" );
        }
        catch ( NullPointerException e )
        {
            tests.put( "null", "false" );
        }

        return null;
    }
}
