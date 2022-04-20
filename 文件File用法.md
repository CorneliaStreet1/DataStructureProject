# 文件File用法

## 路径

开头用 “C：” 或 “D：”就表示绝对路径，这个路径就是你在电脑看某个文件的路径

开头用 “.” 就表示相对路径，即从当前文件夹开始，那我们运行程序时 “.” 表示的当前文件夹是哪个文件夹呢？就是你整个项目的文件夹。举个例子

![image-20220420144113679](C:/Users/lenovo/AppData/Roaming/Typora/typora-user-images/image-20220420144113679.png)

看到DataStructureProject文件夹了吗？这个就是 "." 的意思

所以后面我们想访问我们项目下的某些文件时，路径就可以写为

```java
private static String record_path="./src/main/java/HYH/DailyRecord";
private static File record=new File(record_path+"/DailyRecord.txt");
```

那文件不存在的时候怎么办？

```java
//File有看是否存在的方法
record.exists();
//不存在就创一个呗
record.createNewFile();
//通常都会让你用try去包的
```

那怎么写这个文件呢？

```java
//File有FileWriter方法，但这个方法是写比特的（好像是，记不清了）
//所以我们对FileWriter外面再包一层函数，完成写文件的操作
BufferedWriter writer=new BufferedWriter(new FileWriter(record.getPath(),true));
//我们在FileWriter外面包了BufferedWriter函数，实例化了一个writer对象，然后用writer去写
writer.write("系统：进入“"+info_of_model+"”模块\n");
//writer可以看作缓存了字符串，write()就往writer里面添加字符串
//写完后记得close()，这样writer里面的字符串才会写到文件里面
writer.close();

//看到上面BufferedWriter里的FileWriter里的两个参数了吗，现在来解释它们
//getPath()是得到文件的路径，第一个参数锁死这个BufferWriter对应的文件是哪个，第二个参数Boolean为true时，写入的字符串是添加到文件里字符串后面，而不是把文件里原来的字符串全删了再写。可以不写第二个参数，这样表示把原来文件夹里的字符串全删了再写。
```

那怎么读文件呢？

```java
//和前面一样，reader缓存了读出的字符串
BufferedReader reader = new BufferedReader(new FileReader(time_init.getPath()));
//用BufferedReader的readline()来一行一行地读，这个函数返回文件下一行的字符串
reader.readLine();
//当然还有其他的读法，自己摸索吧，我觉得readline()已经够用了
```

