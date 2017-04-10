# webviewdemo
主要讲解WebView拦截请求的例子，主要是要重写WebViewClient的shouldInterceptRequest方法，在这个方法中返回想拦截的请求。
博客地址：[android WebView拦截请求详解](http://blog.csdn.net/qq_19431333/article/details/52351437)
# textspan
主要讲解TextView如何通过CharacterStyle、ParagraphStyle对文本进行格式化显示。
- [TextView显示丰富多彩的文字（一）——如何使用CharacterStyle格式化字符](http://blog.csdn.net/qq_19431333/article/details/52432667)主要解释SpannableString的用法
- [TextView显示丰富多彩的文字（二）——如何使用ParagraphStyle格式化段落](http://blog.csdn.net/qq_19431333/article/details/52495052)主要解释ParagraphStyle各种子类的具体用法，包括LeadingMarginSpanStandard、BulletSpan、QuoteSpan、DrawableMarginSpan等多种LeadingMariginSpan的用法
- [TextView显示丰富多彩的文字（三）——自定义CharacterStyle和ParagraphStyle显示效果](http://blog.csdn.net/qq_19431333/article/details/52503821)主要解释了如何自定义Span，主要包含了两个例子：自定义TextReplacementSpan（以本文替换文本）和自定义OrderSpan（在每个段落前面加上序号）
- [TextView显示丰富多彩的文字（四）——从源码角度解释格式化显示的原理](http://blog.csdn.net/qq_19431333/article/details/52577632)从TextView的布局、绘制出发查看其中的源码实现分析各种效果是如何实现的以及实现的顺序

# camera2
主要是说明camera2包下如何构建一个相机应用，例子是采用的官方的例子。
博客地址：[使用camera2构建相机应用](http://blog.csdn.net/qq_19431333/article/details/52743980)

# downloadmanager
主要是讲解DownloadManager如何进行下载并可以查询进度。
博客地址：[DownloadManager使用详解](http://blog.csdn.net/qq_19431333/article/details/52798105)

# filedemo
主要是翻译官方文档，其中加入了个人的理解。需要知道在android中获取文件的几种方式，尤其是缓存目录的获取，获取方式不同。
博客地址：[理解文件存储](http://blog.csdn.net/qq_19431333/article/details/52829996)

# handlerdemo
讲解Android的消息机制，Handler、Looper、MessageQueue三者之间的关系是怎么样的，并从源码的角度分析消息机制的工作原理。
博客地址：[深入理解Handler](http://blog.csdn.net/qq_19431333/article/details/53610465)

# okhttpdemo
主要讲解OkHttp的基本用法，如何发送同步、异步请求，如何实现拦截器,博客[使用OkHttp进行网络同步异步操作](http://blog.csdn.net/qq_19431333/article/details/53053367)。
分四个章节分别从提交请求、获取响应、网络操作和缓存四个方面对OkHttp进行了源码分析，如下：
1. [深入理解OkHttp源码（一）——提交请求](http://blog.csdn.net/qq_19431333/article/details/53141013)
2. [深入理解OkHttp源码（二）——获取响应](http://blog.csdn.net/qq_19431333/article/details/53207220)
3. [深入理解OkHttp源码（三）——网络操作](http://blog.csdn.net/qq_19431333/article/details/53419249)
4. [深入理解OkHttp源码（四）——缓存](http://blog.csdn.net/qq_19431333/article/details/53513734)

# snackbardemo
主要讲解SnackBar控件的使用以及从源码角度解释了其内部的线性消失原理。
博客地址：[Snackbar使用详解](http://blog.csdn.net/qq_19431333/article/details/52862348)

# servicedemo
主要讲解Service相关的知识点，包括生命周期、启动服务与绑定服务、AIDL与Binder、以及Service的两个特殊用法：IntentService和前台服务。
博客主要分为三篇，如下：
1. [深入理解Service（一）——服务生命周期](http://blog.csdn.net/qq_19431333/article/details/53784734)
2. [深入理解Service（二）——绑定服务](http://blog.csdn.net/qq_19431333/article/details/53905615)
3. [深入理解Service（三）——前台服务和IntentService](http://blog.csdn.net/qq_19431333/article/details/54015648)

# viewdemo
主要讲解View的各个知识点，包括View的位置、滑动、绘制过程、事件分发
博客分为几篇，如下：
1. [View的位置参数详解](http://blog.csdn.net/qq_19431333/article/details/54341047)
2.

# activitydemo
主要讲解Activity生命周期、启动模式、taskAffinity属性以及清理返回栈。
