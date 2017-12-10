---
layout:     post
title:      java8新特性-lambda表达式
subtitle:    学习java8
date:       2017-12-07
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - java
    - lambda
    - 函数式表达式
---

#### lambda表达式
    本文要搞清楚以下几点
     是什么
     为什么要用
     是否有其他选择
     能干什么
     应用场景
     优点
     缺点
     怎么用（重点）
     
#### lambda表达式是什么
    lambda表达式是JAVA8中提供的一种新的特性，它支持JAVA也能进行简单的“函数式编程”。 
    它是一个匿名函数，Lambda表达式基于数学中的λ演算得名，直接对应于其中的lambda抽象(lambda abstraction)，是一个匿名函数，即没有函数名的函数

### 为什么要用lambda表达式
    1.未来的编程语言将逐渐融合各自的特性，而不存在单纯的声明式语言（如之前的Java）或者单纯的函数编程语言
    2.Java的OO(object-oriented)和命令式编程（imperative programming）特性不够强大
    
### 是否有其他选择
       目前java想要实现函数式函数式编程，只有用lambda表达式。lambda还可以替换内部匿名类
   
### 能干什么
    函数式编程
    简化代码
    装逼
    提高性能
    可读性更高
    
### 应用场景
    替代匿名类
    内循环替代外循环
    支持函数编程
    Stream API ，让集合中的数据处理起来更加方便，性能更高，可读性更好
    
    
### 优点
    提高性能（根据开发lambda组的ppt，内部类和lambda比较，lambda最差的情况是和内部类一样的，甚至更好）
    
    可读性更高
    
#### 缺点
    我们首先看看Java 6/7中的一个传统方法案例：
    
    // simple check against empty strings
    public static int check(String s) {
        if (s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.length();
    }
       
    //map names to lengths
       
    List lengths = new ArrayList();
       
    for (String name : Arrays.asList(args)) {
        lengths.add(check(name));
    }
    如果一个空的字符串传入，这段代码将抛出错误，堆栈跟踪如下：
    at LmbdaMain.check(LmbdaMain.java:19)
    at LmbdaMain.main(LmbdaMain.java:34)
    再看看Lambda的例子
    
    Stream lengths = names.stream().map(name -> check(name));
    出错栈：   
    at LmbdaMain.check(LmbdaMain.java:19)
    at LmbdaMain.lambda$0(LmbdaMain.java:37)
    at LmbdaMain$$Lambda$1/821270929.apply(Unknown Source)
    at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
    at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
    at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:512)
    at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:502)
    at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
    at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    at java.util.stream.LongPipeline.reduce(LongPipeline.java:438)
    at java.util.stream.LongPipeline.sum(LongPipeline.java:396)
    at java.util.stream.ReferencePipeline.count(ReferencePipeline.java:526)
    at LmbdaMain.main(LmbdaMain.java:39)
    这非常类似Scala，出错栈信息太长，我们为代码的精简付出力代价，更精确的代码意味着更复杂的调试。
    
      
#### 怎么用

    lambda的基本写法
    （参数）->{}
    一个lambda expression由三部分组成： 
    - 参数：(T args)是这个lambda expression的参数部分，包括参数类型和参数名，可为空，小括号不可缺少 
    - 箭头：->，不可缺少 
    - 代码块：就是用”{}”包含着的代码。当代码块内代码只有一行时，花括号可以省略，且代码块内分号省略
    
    以下是几个使用场景
### 1. 实现runnable接口
    
    private static void lambdaTestRunnable() {
            //正常使用
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start");
                }
            }).start();
            
            //lambda使用
            new Thread(()-> System.out.println("start")).start();
        }
        
        
#### 2.在集合中使用迭代
        
        public static void lambdaTestList(){
        
                List<String> list = new ArrayList<String>();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("4");
                //正常使用迭代
                for (String play: list){
                    System.out.println(play);
                }
                //lambda
                list.forEach((play)-> System.out.println(play));
            }
            
            
####3.在predicate接口使用

        private static void lambdaTestPredicate() {
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        List<String> list = Arrays.asList(s1,s2,s3,s4,s5);
        //正常情况
        for(String s:list){
            if (s.startsWith("1")){
                System.out.println(s);
            }
        }

        //lambda
        filter(list,(str)->str.startsWith("1"));

    }

    public  static void filter(List<String> names, Predicate<String> condition){
        for (String name : names){
            if(condition.test(name)){
                System.out.println(name);
            }
        }
    }


#### 4. lambda表达式的map和reduce示例
     /**
     * lambda表达式的map和reduce
     */
    private static void lambdaTestMapAndReduce() {
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        List<String> list = Arrays.asList(s1, s2, s3, s4, s5);
        //正常情况
        for (String s:list)
        {
            System.out.println(s+"2");
        }
        //lambda表达式
        list.stream().map((str)->str+"2").forEach((str)-> System.out.println(str));
        Integer i1 = 1;
         Integer i2 = 2;
         Integer i3 = 3;
         Integer i4 = 4;
         Integer i5 = 5;
         Integer i6 = 6;
        List<Integer> list1 = Arrays.asList(i1,i2,i3,i4,i5,i6);
        Integer i = list1.stream().map((str)->str+1).reduce((sum,cost)->sum+cost).get();//get方法是获取到reduce操作结果
        System.out.println(i);
    }
#### 5.lambda表达式过滤一个String列表
    /**
     * lambda表达式过滤一个String列表
     */
    private static void lambdaTestString() {
         String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        List<String> list = Arrays.asList(s1, s2, s3, s4, s5);
        //去除字符串为1的元素
        List<String> list1 = list.stream().filter((x)->!x.equals("1")).collect(Collectors.toList());//collect方法将stream对象转换成list或set或hashmap或currenthashmap
        list1.forEach((x)-> System.out.println(x));
    }

注：lambda表达式只针对函数式接口，也就是只有一个抽象方法（其余方法随意）的接口