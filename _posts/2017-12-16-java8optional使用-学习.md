---
layout:     post
title:      java8新特性-optional
subtitle:    学习java8
date:       2017-12-18
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - java
    - optional
    - 函数式
---
##### 前言
    optional是java8的一个工具类，主要用于安全优雅的解决空指针问题
    
#### 代码
    import java.util.Optional;
    
    /**
     * java8的optional 安全解决nullexception
     */
    public class Java8Optional {
        public static void main(String[] args) {
            testOptional();
        }
    
        /**
         * test optional的方法
         */
        private static void testOptional() {
            Integer test1 = null;
            Integer test2 = 1;
            /*ofnullable方法允许值为空*/
           Optional<Integer> a =  Optional.ofNullable(test1);
           //of方法不允许值为空，如果为空就抛异常
           Optional<Integer> b = Optional.of(test2);
            System.out.println(sum(a,b));
        }
    
        private static int sum(Optional<Integer> a, Optional<Integer> b) {
            System.out.println(a.isPresent());
            System.out.println(b.isPresent());
            //orelse方法是当值为空时，另外赋予新值
                Integer i = a.orElse(new Integer(0));
                //get方法是取得值，但是必须保证值不为空
                Integer i2 = b.get();
            return i+i2;
        }
    }
