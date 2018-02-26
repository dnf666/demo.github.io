---
layout:     post
title:      synchronized关键字
subtitle:   java 源码
date:       2018-02-14
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - java
    - 源码
---

### 前言
    目前在Java中存在两种锁机制：synchronized和Lock
    synchronized是基于jvm实现，lock是依据硬件层面依赖的特殊cpu指令
    jvm中锁也叫做对象监视器
    
### synchronized是怎样实现的
       是基于monitor对象实现的，
       当没有获得该monitor的线程请求某个monitor时，首先检查monitor对象的计数是否为0，不为0，则阻塞
       计数为0则线程获得该对象所有权，并且monitor计数加1.
       当持有该monitor的线程再次请求。则计数加1；
       相对地，如果释放一个锁计数就减1；直到计数为0。
       
    1. 线程状态及状态转换
    
        当多个线程同时请求某个对象监视器时，对象监视器会设置几种状态用来区分请求的线程：
    
    Contention List：所有请求锁的线程将被首先放置到该竞争队列
    Entry List：Contention List中那些有资格成为候选人的线程被移到Entry List
    Wait Set：那些调用wait方法被阻塞的线程被放置到Wait Set
    OnDeck：任何时刻最多只能有一个线程正在竞争锁，该线程称为OnDeck
    Owner：获得锁的线程称为Owner
    !Owner：释放锁的线程
    
    
![image](http://img.my.csdn.net/uploads/201107/28/0_1311821841e55M.gif)

##  contentionlist 介绍

    contentionlist是个虚拟队列（而且是先进先出），目的是为了存放请求锁的线程。但是他并不是一个真正的队列。因为它只有node节点和next指针
    新增一个节点是在队头添加。取出是在队尾。
    
![image](http://img.my.csdn.net/uploads/201107/27/0_1311762773jLq3.gif)

### entrylist 介绍
    为什么这里需要一个entrylist，而不是直接在contentionlist竞争锁。我认为是为了减小对contentionlist队尾的争用。
    owner线程在unlock时会从contentionlist中迁移线程到entrylist。并且指定一个线程为ondeck线程。
    但是并不是给他锁，而是给他竞争锁的权利。如果能够成功，那他就是owner线程，失败就继续呆在entrylist中。如果owner线程被wait就进入waitset中
    待notify和notifyall唤醒他就加入entrylist。
    
### 自旋锁

    那些处于ContetionList、EntryList、WaitSet中的线程均处于阻塞状态，阻塞操作由操作系统完成（在Linxu下通过pthread_mutex_lock函数）。线程被阻塞后便进入内核（Linux）调度状态，这个会导致系统在用户态与内核态之间来回切换，严重影响锁的性能
     
    　　缓解上述问题的办法便是自旋，其原理是：当发生争用时，若Owner线程能在很短的时间内释放锁，则那些正在争用线程可以稍微等一等（自旋），在
    Owner线程释放锁后，争用线程可能会立即得到锁，从而避免了系统阻塞。但Owner运行的时间可能会超出了临界值，争用线程自旋一段时间后还是无法获得锁，
    这时争用线程则会停止自旋进入阻塞状态（后退）。基本思路就是自旋，不成功再阻塞，尽量降低阻塞的可能性，这对那些执行时间很短的代码块来说有非常重要的性能提高
    。自旋锁有个更贴切的名字：自旋-指数后退锁，也即复合锁。很显然，自旋在多处理器上才有意义。
    
    
### synchronized哪里用了自旋锁

      在contentlist的线程即将加入entrylist时，会先自旋尝试获得锁，失败后再进入entrylist。成功就直接获得锁
      我的理解是当第一个进入contentionlist的线程，entrylist是空的，第一个线程就不用经历阻塞再运行的阶段。而是直接运行
      
### 偏向锁
    
        几乎所有的锁都是可重入的，已经获得锁的线程可以多次锁住/解锁监视对象。每次加锁和释放锁都会有一个cas操作
        对于安全来说是好的，但是如果在无竞争条件下就影响性能了。
        所以jdk1.6加入了偏向锁。在无竞争情况下对于第一次获得锁的线程。该锁偏向它。免去后面的cas操作
        
###             
    

