import java.net.MalformedURLException;
import java.util.Scanner;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * User: pault Date: 9/10/2015 Time: 09:37
 */
public class CompileTester
{
    public static void main(String... args)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        Scanner in = new Scanner( System.in );
        while(true)
        {
            String line = in.nextLine();

            String fileToCompile = "uploads/"+line+".java";
            int compResult = compiler.run( null, null, null, fileToCompile );

            if ( compResult == 0 )
            {
                System.out.println( "Successfuil" );
            }
        }
    }
}
