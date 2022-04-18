# Calendar类

## 

```java
//生成Calendar类的对象
Calendar calendar=Calendar.getInstance();
//calendar接收一个Date类来设置时间
calendar.setTime(Date date);

//所以下面我们来说怎么得到Date类
//先格式化字符串，下面是一个格式化字符串的函数
SimpleDateFormat sFormat=new SimpleDateFormat("yyyy/MM/dd E HH:mm");
//这个函数接收一个字符串，定义了时间的格式，yyyy表示年，MM表示月，dd表示几号，E表示周几，HH表示小时，mm表示钟，其他的符号原封不动地保留。它有个函数，接收一个字符串
Date sFormat.parse(String s);//返回一个Date类
//若字符串不符合上面你定义的格式，那么会抛出异常
//所以设置calendar的方法为
calendar.setTime(sFormat.parse(String s));
//按你定义的sFormat的格式的字符串传进去就行


//还可以对某一个位加若干个数
int i=10
calendar.add(Calendar.SECOND,i);
//这里就对这个calendar的秒加了i

//还可以获取年份，月份等

int year=calendar.get(Calendar.YEAR);
int month=calendar.get(Calendar.MONTH)+1;//从0开始计数所以加一
int dow = cal.get(Calendar.DAY_OF_WEEK);//获取周几，星期日开始，从1开始

//还可以获得对应的Date类
Date calendar.getTime();

//还可以按另外的格式输出
SimpleDateFormat Format_other=new SimpleDateFormat("yyyy HH dd:mm");

String s=Format_other.format(calendar.getTime());//.format函数接收一个Date,所以要用calendar.getTime();


```

## 