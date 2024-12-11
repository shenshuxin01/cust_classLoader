import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("----------");
        System.out.println("Main");
        System.out.println("classLoader:"+Main.class.getClassLoader());
        System.out.println("----------");

        URL[] webAppLibURL = new URL[]{new URL("file:demo1.jar"), new URL("file:demo2.jar")};
        URLClassLoader childClassLoader;
        if (args==null||args.length==0) {//默认使用双亲委派模式的加载器
            childClassLoader = new URLClassLoader(webAppLibURL);
        }else {
            //不使用双亲委派模式的加载器
            childClassLoader = new CustClassLoader(webAppLibURL);
        }
        Class<?> aClass = childClassLoader.loadClass("Demo1");
        aClass.getMethod("main",String[].class).invoke(null, new String[1] );

    }
}

class CustClassLoader extends URLClassLoader{
    public CustClassLoader(URL[] urls) {
        super(urls);
    }
    //重写loadClass方法 不使用双亲委派模式
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        try {
            //先查找自己url下的类
            return findClass(name);
        } catch (ClassNotFoundException e) {
            //没有找到，就使用双亲委派模式
           return super.loadClass(name);
        }
    }
}
