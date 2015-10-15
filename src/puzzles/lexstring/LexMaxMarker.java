package puzzles.lexstring;

import puzzles.ExamMark;
import puzzles.Marker;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pault Date: 15/10/2015 Time: 09:18
 */
public class LexMaxMarker implements Marker<LexMaxPuzzle>
{

    @Override
    public ExamMark mark( final LexMaxPuzzle instance )
    {
        ExamMark result = new ExamMark( "Lexicographically large string" );

        long start = System.currentTimeMillis();
        result.addResult( "test1", instance.sortString( "aaa", "bbb" ).equals( "bbbaaa" ),
            "This is a simple base case. Can you sort 'aaa' and 'bbb'" );
        result.addResult("Empty Test", instance.sortString("", "").equals(""), "No strings provided");

        result.addResult("test2", instance.sortString("bbb", "bbb").equals("bbbbbb"), "All the same characters");
        result.addResult("test3", instance.sortString("", "cba").equals("cba"), "Secret");
        result.addResult("test4", instance.sortString("bbbz", "bbba").equals("bbbzbbba"), "Secret");

        List<Boolean> b = new ArrayList<>();

        for (int i = 0; i < 100_000; i++) {
            b.add(instance.sortString("aaa", "bbb").equals("bbbaaa"));
            b.add(instance.sortString("", "").equals(""));
            b.add(instance.sortString("bbb", "bbb").equals("bbbbbb"));
            b.add(instance.sortString("", "cba").equals("cba"));
            b.add(instance.sortString("bbbz", "bbba").equals("bbbzbbba"));
        }
        long end = System.currentTimeMillis();

        result.setTime(end - start);
        System.out.println(b.size());

        return result;
    }
}
