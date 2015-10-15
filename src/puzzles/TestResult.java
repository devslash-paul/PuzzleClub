package puzzles;

import java.io.Serializable;

/**
 * User: pault Date: 15/10/2015 Time: 10:46
 */
public class TestResult implements Serializable
{
    public static long serialVersionUID = 1;
    private final String myTestName;
    public final boolean success;
    private final String myNotes;

    public TestResult( String testName, boolean success, String notes )
    {

        myTestName = testName;
        this.success = success;
        myNotes = notes;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getNotes()
    {
        return myNotes;
    }

    public String getTestName()
    {
        return myTestName;
    }
}
