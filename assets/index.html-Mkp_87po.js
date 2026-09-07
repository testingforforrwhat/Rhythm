import{a as n,c as i,b as a,o as e}from"./app-XcHn6pIB.js";const l={};function p(t,s){return e(),i("div",null,[...s[0]||(s[0]=[a(`<p>1、final、finally、finalize的区别？</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span>​	final 是 修饰符</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​		修饰 成员属性、变量，该成员属性、变量 为 常量。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​		修饰 成员方法，该成员方法不能被 Override 重写。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​		修饰 类，该类不能被 extends 继承。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​	finally 是 异常处理中，无论是否捕获到异常，最终都要执行的指令段，不会收到return关键字影响。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​	finalize 是 Object 类中的析构方法，对象被销毁时会自动调用的方法。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>2、error 和 exception 有什么区别？CheckedException 和 RuntimeException 有什么区别</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>​	Error 无法通过编译。是指程序无法恢复的严重错误，通常由系统级问题或资源耗尽引起，无法通过代码处理。例如，OutOfMemoryError表示内存耗尽，StackOverflowError表示堆栈溢出。Error是无法通过代码处理的，一旦出现，程序将无法继续执行。通常由虚拟机抛出。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​	Exception 编译可以通过，程序运行时异常。是指程序在运行过程中遇到的非正常情况，可以通过代码进行处理和恢复。异常分为已检查异常和未检查异常。可以由程序捕获和处理。</span></span>
<span class="line"><span>      已检查异常是指在编译时就需要处理的异常，必须在代码中显式地进行捕获或者声明抛出。例如，IOException、SQLException等。如果不处理已检查异常，编译器将会报错。</span></span>
<span class="line"><span>      未检查异常是指在运行时才会出现的异常，不需要显式地进行捕获或者声明抛出。例如，NullPointerException、ArrayIndexOutOfBoundsException等。未检查异常可以通过try-catch语句进行捕获和处理，也可以不处理，由上层调用者处理。</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>​	RuntimeException 运行时异常 JVM会自动捕获并且处理</span></span>
<span class="line"><span></span></span>
<span class="line"><span>​	CheckedException 检查异常 JVM不会自动捕获并且处理</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>3、请列出 3 个运行时异常</p><ul><li>NumberFormatException 数值类型转换异常</li><li>ClassCastException 类型转换异常</li><li>IndexOutOfBoundsException 索引越界异常</li></ul><p>4、throw 与 throws 的区别</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">Java中的throw和throws是两个不同的关键字，它们的作用和用法也不同。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">throw关键字用于抛出一个异常对象，通常在方法内部使用。例如：</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">\`\`\`</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">public void divide(int a, int b) {</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    if (b == 0) {</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">        throw new ArithmeticException(&quot;除数不能为0&quot;);</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    }</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    int result = a / b;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    System.out.println(result);</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">}</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">\`\`\`</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">上面的代码中，如果除数为0，则会抛出一个ArithmeticException异常对象。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">throws关键字用于声明一个方法可能会抛出的异常类型，通常在方法声明时使用。例如：</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">\`\`\`</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">public void readFile() throws IOException {</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    // 读取文件操作</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">}</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">\`\`\`</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">上面的代码中，声明了readFile方法可能会抛出IOException异常，调用该方法时需要进行异常处理。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">总结：</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> throw用于抛出一个异常对象，通常在方法内部使用。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> throws用于声明一个方法可能会抛出的异常类型，通常在方法声明时使用。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,8)])])}const r=n(l,[["render",p]]),c=JSON.parse('{"path":"/zh/npmDoc/gbbegfoh/","title":"homework_11","lang":"zh","frontmatter":{"title":"homework_11","createTime":"2025/03/13 09:15:35","permalink":"/zh/npmDoc/gbbegfoh/"},"readingTime":{"minutes":2.48,"words":743},"git":{"createdTime":1720268599000,"updatedTime":1770144228000,"contributors":[{"name":"testingforforwhat","username":"testingforforwhat","email":"2273664Z@student.gla.ac.uk","commits":1,"avatar":"https://avatars.githubusercontent.com/testingforforwhat?v=4","url":"https://github.com/testingforforwhat"},{"name":"QuantumBitstream","username":"QuantumBitstream","email":"2273664Z@student.gla.ac.uk","commits":4,"avatar":"https://avatars.githubusercontent.com/QuantumBitstream?v=4","url":"https://github.com/QuantumBitstream"}]},"filePathRelative":"zh/npmDoc/wiki/note/02/homework_11.md","headers":[]}');export{r as comp,c as data};
