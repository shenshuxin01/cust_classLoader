import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("----------");
        System.out.println("Main");
        System.out.println("classLoader:"+Main.class.getClassLoader());
        System.out.println("----------");

        URL[] webAppLibURL = new URL[] {new URL("file:demo1.jar"),new URL("file:demo2.jar")};
        URLClassLoader childClassLoader = new URLClassLoader(webAppLibURL);

        Class<?> aClass = childClassLoader.loadClass("Demo1");
        aClass.getMethod("main",String[].class).invoke(null, new String[1] );

    }
}
