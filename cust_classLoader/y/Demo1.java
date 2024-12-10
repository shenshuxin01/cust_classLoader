
public class Demo1 {
    public static void main(String[] args) {
        System.out.println("----------");
        System.out.println("Demo1");
        System.out.println("classLoader:"+Demo1.class.getClassLoader());
        System.out.println("----------");
        new Demo2().main(args);
    }
}
