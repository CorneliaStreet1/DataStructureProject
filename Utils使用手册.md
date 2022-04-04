# 持久化（概念性的）

目前为止我们写过的大部分程序都没有记住有关其先前执行的任何内容的能力。

但是现实世界中的许多程序都有能力保持程序的状态，这个状态能够横跨多次程序启动。

以git为例，当你使用`git add <files>`，git会记录下来你添加了哪些文件。如果你使用`git status`，git会正确的列出文件。

即使你在这两次操作之间，完全终止过git，git也可以正确的运行。也就是说如果你运行`git add`，然后将计算机关机114514年，然后再回来，`git status`仍然会以某种方式做正确的事情。Git 以某种方式表现出**持久性**。

使持久化成为可能的关键是使用计算机的文件系统。通过将信息存储到硬盘，程序能够留下信息以供以后执行使用。

# Java中的文件和目录

## 当前工作目录（CWD，current working directory）

当前工作目录，就是放源代码的，它不是你当前所在的目录。超，不会解释，反正知道怎么用就行了。

- 使用`System.getProperty("user.dir")`在Java程序中访问当前工作目录。

- 运行`java/JYQ/CDWDemo`，可以得到工作目录的绝对路径。

<img src="https://raw.githubusercontent.com/CorneliaStreet1/PictureBed/master/202204041340773.png" alt="image-20220404134007587" style="zoom:67%;" />



### IntelliJ

在IntelliJ中，CWD可以在运行 ===》编辑配置==》工作目录找到

<img src="C:/Users/jiangyiqing/AppData/Roaming/Typora/typora-user-images/image-20220404134317320.png" alt="image-20220404134317320" style="zoom:50%;" />

## Java中的文件和目录操纵



- `Java`的`File`类代表一个文件或者目录，并且使你可以对文件和目录进行操纵。

- 我们的文件都放在我们的工作目录之下。所以我们需要创建目录和文件。

### 文件

通过文件构造器建立一个文件对象，传入文件的路径作为构造器参数。

```java
File f = new File("dummy.txt");
```

- 上述路径我们使用的是相对路径，这个文件将会处于工作目录中，这个`dummy.txt`的绝对路径是`CWD/dummy.txt`。

- 应该把文件对象`f`看做是一个指向实际文件`dummy.txt`的指针，**当你创建一个文件对象时，你并没有实际创建那个文件dummy.txt本身。**

- 只是说，以后当我们对`f`做操作的时候，我实际上是想对`dummy.txt`做这些操作。

- 要实际创建一个文件，使用下列，然后你就可以在文件管理器里找到这个`dummy.txt`

  ```java
  f.createNewFile();
  ```

- 检查`dummy.txt`是否实际存在：

- ```java
  f.exists()
  ```

- Java中对文件读写实际上是比较丑陋的，所以提供了`Utils.java`。

- 比如你想写入一些字符串到文件里去，你可以：

- ```java
  Utils.writeContents(f, "Hello World");
  ```

  ![image-20220404135828465](https://raw.githubusercontent.com/CorneliaStreet1/PictureBed/master/202204041358521.png)

<img src="https://raw.githubusercontent.com/CorneliaStreet1/PictureBed/master/202204041358008.png" alt="image-20220404135847958"  />

### 目录

- Java中目录同样使用`File`对象来代表。比如你可以创建一个文件对象来代表一个目录：

- ```java
  File d = new File("dummy");
  ```

- 与文件相同，这个目录可能并没有实际存在于你的文件系统中（也可能存在是因为可能之前有人手动创建过一次了）

- 要实际创建这个目录：

- ```java
  d.mkdir();
  ```

  ![image-20220404140233633](https://raw.githubusercontent.com/CorneliaStreet1/PictureBed/master/202204041402700.png)



# Serializable(可序列化)

向文本文件写入内容是很好，但是如果我们想要存储更复杂的状态呢？比如我们想做到游戏存档。总不可能把所有东西都转化为字符串然后存入文本文件。

幸运的是Java有一个标签接口(tagging interface )`java.io.Serializable`[Serialization](Serialization)。

- `Serialization`是将一个对象转化为一串能够被存储在文件中的字节流的过程。

- 然后可以通过反序列化( *deserialize* )的方式在以后的程序调用中读回这个对象。

- Java的序列化比较复杂，所以提供了`Utils.java`来简化。

- ```java
  Model m;//要写入的那个对象，中间省略了各种建立对象巴拉巴拉巴拉
  File outFile = new File(saveFileName);//把m存入名为saveFileName的文件中
  // Serializing the Model object
  writeObject(outFile, m);
  ```

  ```java
  Model m;//用来保存被反序列化得到的对象的对象变量m
  File inFile = new File(saveFileName);//要从哪个文件中读取对象
  
  // Deserializing the Model object
  m = readObject(inFile, Model.class);//把对象从这个文件中读取出来，存到m中去。
  ```

  

# SHA-1 Hash

- SHA-1 Hash：一个160位的二进制值，一般写作16进制的40位的值。
- 在进行文件去重的时候我们可以通过计算两个文件的SHA-1 Hash来确定两个文件是否相同。

```java
/**
*返回由一系列对象的构成的东西的哈希值
*以40位字符串的形式返回
*要求传递给函数的对象必须是字节数组或者是字符串
*/
static String sha1(Object... vals)
```

# 读写文件内容

```java
/**
*读取文件的内容，将其作为字节数组返回
*/
static byte[] readContents(File file)
/**
*将文件的内容作为字符串返回
*/
static String readContentsAsString(File file)
/**
*将一个字符串或字节数组的内容写入文件中
*/
static void writeContents(File file, Object... contents)
/**
*将文件中的内容读取出来，强制转换为需要的类型并返回
*/
static <T extends Serializable> T readObject(File file,Class<T>)
/**
*将对象写入文件
*/
static void writeObject(File file, Serializable obj)
```

# 目录

```java
/**
*读取处于给定目录下的非文件夹文件，并按字典序放入一个List<String>
*/
static List<String> plainFilenamesIn(File dir)
static List<String> plainFilenamesIn(String dir)
```

```java
/**
*将给定的文件目录（字符串或者文件形式）First和others拼接起来，返回一个新的文件
*比如给定first 为CWD，第二个参数为main，则返回的是代表目录CWD/m
*/
static File join(String first, String... others)
static File join(File first, String... others)
```

