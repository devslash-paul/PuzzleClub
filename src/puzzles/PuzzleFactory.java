package puzzles;

import java.io.File;
import java.lang.Class;import java.lang.ClassNotFoundException;import java.lang.IllegalAccessException;import java.lang.InstantiationException;import java.lang.String;import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * User: pault Date: 9/10/2015 Time: 10:23
 */
public class PuzzleFactory
{
    public static Object newInstance( String nameInst, ClassLoader base )
        throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        URLClassLoader temp = new URLClassLoader( getClassPath() ){
            public Class loadClass(String name) throws ClassNotFoundException
            {
                if ( nameInst.equals( name ) )
                {
                    return findClass( name );
                }

                return base.loadClass( name );
            }
        };

        return temp.loadClass( nameInst ).newInstance();
    }

    public static URL[] getClassPath() throws MalformedURLException
    {
        return new URL[]{
            new File("uploads").toURI().toURL(),
            new File("/home/pault/Programming/Java/Marker").toURI().toURL()
        };
    }
}
