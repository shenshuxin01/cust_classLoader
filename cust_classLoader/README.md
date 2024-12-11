# 自定义类加载器双亲委派例子

当前代码中的new关键字创建实例类，会先加载类，这个加载器是`this.getClass().getClassLoader()`，也就是说当前代码中的类加载器是谁，那么new的时候就是谁加载类。

例如下面的第二个例子，`Demo2`类没有被加载，因为`Demo1`类加载器是`AppClassLoader`，而`Demo2`类不在`AppClassLoader`，所以`Demo2`类没有被加载。
又例如下面的第一个例子，`Demo1`类加载器是`URLClassLoader`，而`Demo2`类在`URLClassLoader`，所以`Demo2`类加载。

## 【使用双亲委派模型运行，正确示例】
```
----------
Main
classLoader:jdk.internal.loader.ClassLoaders$AppClassLoader@75b84c92
----------
----------
Demo1
classLoader:java.net.URLClassLoader@2f2c9b19
----------
----------
Demo2
classLoader:java.net.URLClassLoader@2f2c9b19
----------
```

## 【使用双亲委派模型运行，错误示例】
```
----------
Main
classLoader:jdk.internal.loader.ClassLoaders$AppClassLoader@75b84c92
----------
----------
Demo1
classLoader:jdk.internal.loader.ClassLoaders$AppClassLoader@75b84c92
----------
Exception in thread "main" java.lang.reflect.InvocationTargetException
at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:118)
at java.base/java.lang.reflect.Method.invoke(Method.java:580)
at Main.main(Main.java:20)
Caused by: java.lang.NoClassDefFoundError: Demo2
at Demo1.main(Demo1.java:8)
at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
... 2 more
Caused by: java.lang.ClassNotFoundException: Demo2
at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:526)
... 4 more
```

## 【不使用双亲委派模型运行，正确示例】
```
----------
Main
classLoader:jdk.internal.loader.ClassLoaders$AppClassLoader@75b84c92
----------
----------
Demo1
classLoader:CustClassLoader@31befd9f
----------
----------
Demo2
classLoader:CustClassLoader@31befd9f
----------
```

## 【不使用双亲委派模型运行，依然是正确示例】
```
----------
Main
classLoader:jdk.internal.loader.ClassLoaders$AppClassLoader@75b84c92
----------
----------
Demo1
classLoader:CustClassLoader@31befd9f
----------
----------
Demo2
classLoader:CustClassLoader@31befd9f
----------
```

