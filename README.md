# 第三方接口调用封装

## 1.背景
  基于公司项目情况，几乎每个项目都有大量的调用第三方数据接口的情况，因此急需一个公共的调用第三方接口的组件。同时也需要在调用过程中对于缓存、出错日志、重试等公共操作的封装。

  
## 2.组件设计说明
    1.代码简洁、清晰
    2.面向接口编程，支持扩展
    3.职责单一原则
    4.组件架构如下图：
![这里写图片描述](http://note.youdao.com/yws/public/resource/cd7aef06055279772d47aedeb8d6f5f7/xmlnote/9D1FE41C027442B1A1CD3DD32136DE21/8898)
    
## 3.代码说明
    1.代码库地址： https://github.com/kevin1987/apiinvoke.git
    2.采用技术包括 mybatis、jpa、swagger2、springboot
    


Create by Ian.Su   suyin@bbdservice.com 20170802





