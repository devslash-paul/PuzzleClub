package puzzles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * User: pault Date: 15/10/2015 Time: 09:22
 */
public class ExamMark implements Serializable, Comparator<ExamMark>
{
    public static final long serialVersionUID = 1;

    private final List<TestResult> results = new ArrayList<>();
    private double time;

    public String getName() {
        return name;
    }

    private String name;

    public ExamMark( String puzzleName )
    {

    }

    public void addResult( String testName, boolean success, String notes )
    {
        results.add(new TestResult( testName, success, notes ));
    }

    public List<TestResult> getTestResults()
    {
        return results;
    }


    public void setTime(double ave) {
        time = ave;
    }

    public double getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(ExamMark other) {
        return compare(this, other);
    }

    @Override
    public int compare(ExamMark o1, ExamMark o2) {
        long o1Wins = o1.getTestResults().stream().filter(TestResult::getSuccess).count();
        long o2Wins = o2.getTestResults().stream().filter(TestResult::getSuccess).count();

        if (o1Wins != o2Wins) {
            return Long.compare(o1Wins, o2Wins);
        } else {
            return -Double.compare(o1.getTime(), o2.getTime());
        }
    }

    public String toHTMLString() {
        return name + " solved " + getTestResults().stream().filter(TestResult::getSuccess).count() + " out of " +
                getTestResults().size() + " in " + time;
    }
}
