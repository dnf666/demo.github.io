---
layout:     post
title:      java内置断言和junit单元测试
subtitle:   java 源码
date:       2018-01-07
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - java
    - junit
---


### 使用java断言assert关键字

             String st = "Hello TestNG";  
                assert st.endsWith("1TestNG"): st + "是以TestNG结尾";  
               如果直接运行，程序会正常运行，断言没有起到作用。因为没有在jvm运行时添加-ea参数
               但默认也是没开的。Jvm输入参数-ea，再次运行结果会显示失败。
               
#### junit断言

         String st = "Hello TestNG";  
            assertEquals(st, "Hello");  


### 总结
        最后不推荐使用java assert关键字。因为jvm默认没有开启-ea参数
        