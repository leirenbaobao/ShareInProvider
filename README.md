# ShareInProvider
虽然使用多进程增加了程序的内存空间，但是也有些负面的影响。
比如：

* *使用很多第三方lib扩展额外进程功能时容易混乱*
* *单例模式在单进程中用的很爽，但在多进程就鸡肋了*
* *使用AIDL完成的稳定性和效率并不是很高，本人做的项目拉取AIDL传来的值一直存在不稳定的问题*

最后一个问题最蛋疼。造成最后一个问题的原因是多方面的，最主要的原因是AIDL传输的大小是有限制的。

针对最后一个问题，提出了一个折中的方法。使用ContentProvider传递一些简单的基础变量，重新开辟一条AIDL通道，经测试，确实稳定高效很多。
