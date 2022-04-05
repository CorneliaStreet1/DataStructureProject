- 只有一个方法
  - 第一个参数：原始文件
  - 第二个参数：要比较的文件
  - 返回值：double，为查重率的百分值。
  - 这个方法返回返回值的同时还会直接打印出查重的结果。增加返回值是为了灵活运用。
  - 这个异常直接抛出去就是，后面怎么处理看你们调用者去处理了。
  - `Demo/DuplicateDemo`有一个Demo。

```java
public static double getAnalysisResult(String s1, String s2) throws IOException
```

