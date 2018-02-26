---
layout:     post
title:      springioc源码分析
subtitle:   spring
date:       2017-12-29
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - spring
    - ioc
---

####概念
    ioc是控制反转，也叫依赖注入。简单地说就是你需要自己去创建对象，这个事情就由spring替你做了。
    
####ioc容器系列
    ioc容器其实是一系列ioc容器，用户可以根据自己的需要选择合适的ioc容器。如同水桶，有各种各样的水桶
    我们常用beanfactory和applicationcontext都可以看成容器的具体表现形式
    beanfactory是最基本的接口定义 就相当于水桶装水的功能是每个桶都要具备的功能
    
####spring如何管理对象
    通过beandefinition来管理基于spring的各种对象和依赖关系 
    
#### 具体的ioc容器
    defaultlistablebeanfactory
    xmlbeanfactory
    applicationcontext
    
#### factory的常用方法
    getbean（）
    containsbean（）
    issingleton（）
    isprototype（）
    istypematch（）
    gettype（）
    getaliases（）
    
#### xmlbeanfactory的工作原理
        
        初始化一个xmlbeandefinitionreader。用来读取xml的beandefinition
        
#### 什么是spring
###使用spring的好处
### 什么是控制反转，什么是依赖注入
### beanfactory和applicationcontext的区别
### spring bean的生命周期
### spring的各种作用域范围
### spring的单例是线程安全的吗    
       
    
    