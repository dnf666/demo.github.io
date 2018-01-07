---
layout:     post
title:      exception分析
subtitle:   java 源码
date:       2017-12-03
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - java
    - 源码
---


###前言
        error类继承了throwable类。和exception是兄弟关系
        之前我还没弄懂，为什么runtimeexception和exception都是继承throwable，而且构造器都是
        调用父类的构造器。要分成两个类实现
        答：继承exception的异常类都是编译时异常，继承runtimeexception是运行时异常
        
### 编译时异常（继承exception）
    ClassNotFoundException	应用程序试图加载类时，找不到相应的类，抛出该异常。
    CloneNotSupportedException	当调用 Object 类中的 clone 方法克隆对象，但该对象的类无法实现 Cloneable 接口时，抛出该异常。
    IllegalAccessException	拒绝访问一个类的时候，抛出该异常。
    InstantiationException	当试图使用 Class 类中的 newInstance 方法创建一个类的实例，而指定的类对象因为是一个接口或是一个抽象类而无法实例化时，抛出该异常。
    InterruptedException	一个线程被另一个线程中断，抛出该异常。
    NoSuchFieldException	请求的变量不存在
    NoSuchMethodException	请求的方法不存在
    
    
###  运行时异常
    ArithmeticException	当出现异常的运算条件时，抛出此异常。例如，一个整数"除以零"时，抛出此类的一个实例。
    ArrayIndexOutOfBoundsException	用非法索引访问数组时抛出的异常。如果索引为负或大于等于数组大小，则该索引为非法索引。
    ArrayStoreException	试图将错误类型的对象存储到一个对象数组时抛出的异常。
    ClassCastException	当试图将对象强制转换为不是实例的子类时，抛出该异常。
    IllegalArgumentException	抛出的异常表明向方法传递了一个不合法或不正确的参数。
    IllegalMonitorStateException	抛出的异常表明某一线程已经试图等待对象的监视器，或者试图通知其他正在等待对象的监视器而本身没有指定监视器的线程。
    IllegalStateException	在非法或不适当的时间调用方法时产生的信号。换句话说，即 Java 环境或 Java 应用程序没有处于请求操作所要求的适当状态下。
    IllegalThreadStateException	线程没有处于请求操作所要求的适当状态时抛出的异常。
    IndexOutOfBoundsException	指示某排序索引（例如对数组、字符串或向量的排序）超出范围时抛出。
    NegativeArraySizeException	如果应用程序试图创建大小为负的数组，则抛出该异常。
    NullPointerException	当应用程序试图在需要对象的地方使用 null 时，抛出该异常
    NumberFormatException	当应用程序试图将字符串转换成一种数值类型，但该字符串不能转换为适当格式时，抛出该异常。
    SecurityException	由安全管理器抛出的异常，指示存在安全侵犯。
    StringIndexOutOfBoundsException	此异常由 String 方法抛出，指示索引或者为负，或者超出字符串的大小。
    UnsupportedOperationException	当不支持请求的操作时，抛出该异常。
