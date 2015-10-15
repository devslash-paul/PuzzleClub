import java.util.Arrays;
import java.util.stream.Collectors;

import puzzles.lexstring.LexMaxPuzzle;
import puzzles.sortstring2.SortStringPuzzle;
import puzzles.faketest.TestOne;

public class testClass implements SortStringPuzzle, LexMaxPuzzle
{
    @Override
    public String sortString( String s)

    {
        if ( s != null )
        {
            return Arrays.stream( s.split( "" ) ).sorted().collect( Collectors.joining() );
        }
        else
        {
            return null;
        }
    }

    @Override
    public int returnOne() {
    	return 1;
    }
}