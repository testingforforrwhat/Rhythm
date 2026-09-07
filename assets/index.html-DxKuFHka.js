import{a as i,c as n,b as a,o as e}from"./app-XcHn6pIB.js";const l={};function p(d,s){return e(),n("div",null,[...s[0]||(s[0]=[a(`<p>1.创建线程的实现方式有哪些？</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span>创建线程的实现方式有以下几种：</span></span>
<span class="line"><span></span></span>
<span class="line"><span>1. 使用线程类：可以通过继承Thread类或实现Runnable接口来创建线程。继承Thread类需要重写run()方法，实现Runnable接口需要实现run()方法，并将其作为参数传递给Thread类的构造函数。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>2. 使用Callable和Future：可以通过实现Callable接口来创建线程，并使用Future接口来获取线程的返回值。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>3. 使用线程池：可以使用Executor框架提供的线程池来创建线程。可以通过Executors类的静态方法创建不同类型的线程池，例如FixedThreadPool、CachedThreadPool等。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>4. 使用定时器：可以使用Timer类来创建定时线程，通过TimerTask类的实现来定义定时任务。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>5. 使用异步任务：可以使用AsyncTask类来创建异步任务，该类封装了创建线程的细节，使得创建和管理线程更加简单。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>注意：以上方式都是基于Java语言的线程创建方式，其他编程语言可能会有不同的实现方式。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>2.讲一下线程的状态？</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span>线程在生命周期中会经历不同的状态，常见的线程状态有以下几种：</span></span>
<span class="line"><span></span></span>
<span class="line"><span>1. 新建状态（New）：当线程对象被创建后，但还没有调用start()方法启动线程时，线程处于新建状态。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>2. 运行状态（Runnable）：当线程调用start()方法后，线程进入运行状态，表示线程正在执行任务。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>3. 阻塞状态（Blocked）：线程在某些情况下会进入阻塞状态，例如调用了sleep()方法、等待某个条件、等待输入/输出等。在阻塞状态下，线程暂时停止执行，并释放CPU资源。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>4. 等待状态（Waiting）：线程在某些情况下会进入等待状态，例如调用了wait()方法、join()方法等。在等待状态下，线程需要等待其他线程的通知或者满足某个条件才能继续执行。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>5. 计时等待状态（Timed Waiting）：线程在某些情况下会进入计时等待状态，例如调用了sleep()方法、wait()方法的带有超时参数的重载方法、join()方法的带有超时参数的重载方法等。在计时等待状态下，线程需要等待一段时间或者满足某个条件才能继续执行。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>6. 终止状态（Terminated）：线程执行完任务后，或者出现异常导致线程终止时，线程进入终止状态，表示线程已经结束执行。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>需要注意的是，线程的状态可能会在不同的操作之间切换，例如线程在运行状态下可能会进入阻塞状态或等待状态，而线程在阻塞状态或等待状态下也可以被唤醒继续执行。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>3.线程死锁产生的原因是什么？</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span>线程死锁是指两个或多个线程互相持有对方所需的资源，导致所有线程都无法继续执行的情况。线程死锁产生的原因主要有以下几个：</span></span>
<span class="line"><span></span></span>
<span class="line"><span>1. 互斥条件：多个线程竞争有限的资源，每次只能有一个线程访问资源。当一个线程持有一个资源时，其他线程无法访问该资源。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>2. 请求与保持条件：一个线程持有一个资源的同时，又请求其他线程持有的资源。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>3. 不可剥夺条件：资源只能由持有它的线程释放，其他线程无法强行抢夺。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>4. 循环等待条件：多个线程形成一个循环等待的关系，每个线程都在等待下一个线程所持有的资源。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>当这些条件同时满足时，就可能导致线程死锁的产生。如果没有及时解决死锁，所有的线程都将无法继续执行，造成系统的停滞。为避免线程死锁，可以采取一些预防措施，如避免循环等待、按照固定的顺序获取资源、使用超时机制等。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>4.为什么要创建线程池？线程池怎么创建？</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span>创建线程池的目的是为了提高线程的利用率和效率，并且能够更好地管理和控制线程的数量和执行。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>创建线程池的步骤如下：</span></span>
<span class="line"><span></span></span>
<span class="line"><span>1. 导入相关的类库：首先需要导入Java中与线程池相关的类库，主要是java.util.concurrent包下的ExecutorService和Executors类。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>2. 创建线程池对象：使用Executors类中的静态方法创建线程池对象，常用的方法有以下几种：</span></span>
<span class="line"><span>   - newFixedThreadPool(int nThreads)：创建固定大小的线程池，线程数量固定为nThreads。</span></span>
<span class="line"><span>   - newCachedThreadPool()：创建可缓存的线程池，线程数量根据需要动态调整。</span></span>
<span class="line"><span>   - newSingleThreadExecutor()：创建单线程的线程池，只有一个线程在执行任务。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>3. 提交任务：使用线程池对象的submit()方法或execute()方法来提交任务，将任务交给线程池来执行。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>4. 关闭线程池：当不再需要线程池时，需要调用线程池对象的shutdown()方法来关闭线程池，释放资源。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>创建线程池后，可以通过线程池对象来管理和控制线程的执行，例如设置线程池的最大线程数、获取线程池的任务执行状态、等待所有任务执行完毕等。线程池能够更好地管理线程，避免线程的频繁创建和销毁，提高了系统的性能和稳定性。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>5.线程start和run有什么区别？</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span></span></span>
<span class="line"><span>在Java中，线程的启动有两种方式：调用start()方法和直接调用run()方法。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>1. start()方法：调用start()方法会使线程进入就绪状态，等待系统调度执行。在start()方法被调用后，线程会在后台启动一个新的线程，并且会自动调用线程的run()方法来执行线程的任务。start()方法会立即返回，不会阻塞当前线程。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>2. run()方法：直接调用run()方法会在当前线程中执行run()方法中的代码，而不会启动新的线程。当run()方法执行完毕后，线程也会结束。直接调用run()方法不会创建新的线程，而是在当前线程中顺序执行。</span></span>
<span class="line"><span></span></span>
<span class="line"><span>总结：调用start()方法会启动一个新的线程，并且在新的线程中执行run()方法；而直接调用run()方法只是在当前线程中顺序执行run()方法的代码，不会启动新的线程。一般情况下，我们应该使用start()方法来启动线程，以充分利用多线程的并发执行能力。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>6.synchronized和Lock有什么区别？</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">synchronized和Lock都是Java中用来实现线程同步的机制，它们的主要区别如下：</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">1.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 使用方式：synchronized是Java中的关键字，可以直接在方法或代码块中使用。Lock是一个接口，需要通过Lock对象来进行加锁和解锁操作。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">2.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 可重入性：synchronized是可重入的，即同一个线程可以多次获得同一个锁。而Lock需要显式地进行加锁和解锁操作，没有自动的可重入性。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">3.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 锁的获取方式：synchronized在获取锁时是非公平的，即不保证等待时间最长的线程优先获取锁。而Lock可以选择公平锁或非公平锁，可以通过构造方法来指定。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">4.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 等待可中断：synchronized在获取锁时，如果线程无法获取到锁，会一直等待下去，直到获取到锁或被中断。而Lock可以通过lockInterruptibly()方法来实现等待可中断的效果。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">5.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 条件变量：Lock提供了Condition接口，可以通过Condition对象来实现线程的等待和唤醒操作，而synchronized没有直接的支持。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">6.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 性能：在低竞争的情况下，synchronized的性能通常比Lock要好。但在高竞争的情况下，Lock的性能可能更好，因为它提供了更细粒度的控制。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">总结：synchronized是Java中内置的线程同步机制，使用简单，适用于大部分的线程同步场景。而Lock是一个更灵活、可定制的线程同步工具，适用于一些特殊的线程同步需求，例如可重入性、公平性、等待可中断等。在选择使用synchronized还是Lock时，需要根据具体的需求和场景进行选择。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>7.线程在项目中使用的场景有哪些？(自己先去了解一下，后期慢慢就懂了)</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">线程在项目中的使用场景有很多，以下是一些常见的场景：</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">1.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 并发处理：当需要同时处理多个任务或请求时，可以使用多线程来实现并发处理，提高系统的吞吐量和响应速度。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">2.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 异步操作：当需要执行一些耗时的操作，但不希望阻塞主线程时，可以使用线程来执行这些操作，让主线程继续执行其他任务。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">3.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 定时任务：当需要定时执行某些任务时，可以使用线程来实现定时任务，例如定时发送邮件、定时备份数据等。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">4.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 数据库操作：当需要进行大量的数据库操作时，可以使用线程来并发执行这些操作，提高数据库的访问效率。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">5.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 图像处理：当需要对大量的图像进行处理时，可以使用线程来并发执行图像处理任务，提高处理速度。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">6.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 网络编程：当需要处理大量的网络请求时，可以使用线程来并发处理这些请求，提高网络服务的并发能力。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">7.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> GUI应用程序：当需要响应用户的操作或事件时，可以使用线程来处理这些操作或事件，保证界面的流畅性。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">总结：线程在项目中的使用场景非常广泛，可以用于并发处理、异步操作、定时任务、数据库操作、图像处理、网络编程、GUI应用程序等。通过合理地使用线程，可以提高系统的性能、响应速度和并发能力。但需要注意线程安全的问题，避免出现线程竞争和数据不一致的情况。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>8.并行是什么意思，与并发的区别是什么？</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">并行是指多个任务同时进行，每个任务都在不同的处理器核心或计算单元上执行，实现真正的同时执行。并行可以显著提高系统的处理能力和效率。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">并发是指多个任务在同一时间段内交替执行，每个任务在一段时间内执行一部分，然后切换到下一个任务，通过快速的切换使得多个任务同时进行。并发可以提高系统的响应速度和资源利用率。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">区别：</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">1.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 执行方式：并行是真正的同时执行多个任务，每个任务都在不同的处理器核心上执行。而并发是交替执行多个任务，每个任务在一段时间内执行一部分。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">2.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 执行效果：并行可以显著提高系统的处理能力和效率，因为多个任务可以同时进行。而并发主要是为了提高系统的响应速度和资源利用率，通过快速的切换使得多个任务交替执行。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">3.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 资源需求：并行需要多个处理器核心或计算单元来执行多个任务。而并发可以通过快速的切换来实现多个任务在单个处理器上交替执行。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">4.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 任务关系：并行的任务之间通常是独立的，彼此没有依赖关系。而并发的任务之间可能存在依赖关系，需要协调和同步。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">总结：并行是真正的同时执行多个任务，可以显著提高系统的处理能力和效率。并发是交替执行多个任务，主要为了提高系统的响应速度和资源利用率。并行需要多个处理器核心或计算单元，而并发可以在单个处理器上通过快速的切换来实现。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>9.什么是线程，什么是进程，为什么要有线程，有什么关系与区别？</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">线程（Thread）是操作系统能够进行运算调度的最小单位，它被包含在进程中，是进程中的实际运行单位。一个进程可以包含多个线程，它们共享进程的资源，例如内存空间、文件句柄等。线程是进程中的一个执行流，它可以独立执行，具有自己的程序计数器、栈、寄存器等。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">进程（Process）是操作系统中的一个程序执行实例，它是资源分配和调度的基本单位。进程是一个独立的运行环境，包括程序代码、数据、打开的文件、资源等。每个进程都有自己的地址空间，相互之间不能直接访问对方的资源，需要通过进程间通信（IPC）来进行数据交换。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">为了更好地利用计算机的资源和提高系统的并发能力，引入了线程的概念。线程可以在一个进程中并发执行多个任务，每个线程可以独立执行，共享进程的资源。相比于进程，线程的创建、销毁和切换开销更小，可以更高效地利用系统资源。线程的引入使得程序的并发性更高，能够更好地响应用户的操作和请求。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">线程和进程之间有以下关系和区别：</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">1.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 关系：一个进程可以包含多个线程，线程是进程中的实际执行单位。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">2.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 资源：进程有独立的地址空间和资源，线程共享进程的资源。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">3.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 创建和销毁：进程的创建和销毁比较耗费系统资源，线程的创建和销毁开销较小。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">4.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 切换开销：进程切换需要保存和恢复整个进程的上下文，线程切换只需要保存和恢复线程的上下文，开销较小。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">5.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 通信和同步：进程之间通信需要使用进程间通信（IPC）机制，线程之间通信可以直接读写共享变量，同步也更加方便。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">6.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 并发能力：引入线程可以提高系统的并发能力，多个线程可以同时执行不同的任务，提高系统的效率。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">总结：线程是进程中的执行流，可以独立执行，共享进程的资源。线程的引入可以提高系统的并发能力和效率，减少资源的浪费。线程和进程之间有关系和区别，线程是进程中的实际执行单位，具有更小的开销和更高的效率。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>10.什么是守护线程，如何设置守护线程？ 11.线程如何进行通信的？</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">1.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 守护线程（Daemon Thread）是在程序运行时在后台提供服务的线程，它的生命周期会随着主线程的结束而结束。守护线程通常用来执行一些辅助性的任务，例如垃圾回收、日志记录等。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">2.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 在Java中，可以通过设置线程的setDaemon(true)方法将线程设置为守护线程。需要注意的是，setDaemon方法必须在启动线程之前调用，否则会抛出IllegalThreadStateException异常。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">下面是设置守护线程的示例代码：</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">\`\`\`</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">java</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">Thread</span><span style="--shiki-light:#B07D48;--shiki-dark:#BD976A;"> daemonThread</span><span style="--shiki-light:#999999;--shiki-dark:#666666;"> =</span><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;"> new</span><span style="--shiki-light:#59873A;--shiki-dark:#80A665;"> Thread</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">(</span><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">new</span><span style="--shiki-light:#59873A;--shiki-dark:#80A665;"> Runnable</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">()</span><span style="--shiki-light:#999999;--shiki-dark:#666666;"> {</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">    @</span><span style="--shiki-light:#AB5959;--shiki-dark:#CB7676;">Override</span></span>
<span class="line"><span style="--shiki-light:#AB5959;--shiki-dark:#CB7676;">    public</span><span style="--shiki-light:#AB5959;--shiki-dark:#CB7676;"> void</span><span style="--shiki-light:#59873A;--shiki-dark:#80A665;"> run</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">()</span><span style="--shiki-light:#999999;--shiki-dark:#666666;"> {</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">        // 守护线程的任务逻辑</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">    }</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">});</span></span>
<span class="line"><span style="--shiki-light:#B07D48;--shiki-dark:#BD976A;">daemonThread</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">.</span><span style="--shiki-light:#59873A;--shiki-dark:#80A665;">setDaemon</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">(</span><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">true</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">);</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"> // 设置为守护线程</span></span>
<span class="line"><span style="--shiki-light:#B07D48;--shiki-dark:#BD976A;">daemonThread</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">.</span><span style="--shiki-light:#59873A;--shiki-dark:#80A665;">start</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">();</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"> // 启动线程</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">\`\`\`</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">3.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 线程之间的通信可以通过共享变量来实现。线程可以通过读写共享变量来进行数据交换和共享资源。然而，线程之间的共享变量可能存在竞态条件（Race Condition）的问题，需要使用同步机制来保证线程之间的安全访问。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">常用的线程通信方式有以下几种：</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 共享变量：多个线程通过读写共享变量来进行数据交换和共享资源。需要使用同步机制（如synchronized关键字、Lock）来保证线程之间的安全访问。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 等待/通知机制：通过Object类的wait()、notify()和notifyAll()方法来实现线程之间的等待和通知。等待线程调用wait()方法进入等待状态，其他线程调用notify()或notifyAll()方法唤醒等待线程继续执行。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 阻塞队列：通过使用阻塞队列（如ArrayBlockingQueue、LinkedBlockingQueue）来实现线程之间的数据交换和协作。一个线程可以将数据放入队列中，另一个线程从队列中取出数据，实现线程之间的同步和通信。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 线程间的信号量：通过使用Semaphore类来实现线程之间的信号量控制，可以控制同时访问某个资源的线程数量。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 线程间的倒计时门闩：通过使用CountDownLatch类来实现线程之间的倒计时等待，一个或多个线程等待其他线程执行完毕后再继续执行。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">以上是一些常用的线程通信方式，具体使用哪种方式取决于具体的需求和场景。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>11.notify和notifyAll有什么区别？</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">notify和notifyAll都是Object类中的方法，用于实现线程之间的等待和通知机制。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> notify方法用于唤醒在该对象上调用wait方法进入等待状态的单个线程。如果有多个线程在等待，只会唤醒其中一个线程，具体唤醒哪个线程是不确定的，取决于操作系统的调度。</span></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">-</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> notifyAll方法用于唤醒在该对象上调用wait方法进入等待状态的所有线程。如果有多个线程在等待，会唤醒所有等待的线程，让它们竞争执行。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">需要注意的是，notify和notifyAll方法必须在synchronized代码块或方法中调用，否则会抛出IllegalMonitorStateException异常。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">使用notify和notifyAll方法可以实现线程之间的等待和通知机制，典型的场景是生产者-消费者模型，其中生产者线程负责生产数据，消费者线程负责消费数据。当生产者线程生产了数据后，可以调用notify或notifyAll方法唤醒消费者线程来消费数据。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>12.线程池中的核心参数有？分别有什么作用？</p><div class="language-markdown line-numbers-mode" data-highlighter="shiki" data-ext="markdown" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-markdown"><span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">线程池中的核心参数包括以下几个：</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">1.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> corePoolSize（核心线程数）：指定线程池中保持活动状态的线程数量。即使线程处于空闲状态，也会被保留在线程池中。核心线程数始终保持不变，除非设置了allowCoreThreadTimeOut参数为true，才会在空闲时间超过keepAliveTime时被终止。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">2.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> maximumPoolSize（最大线程数）：指定线程池中允许存在的最大线程数量。当工作队列已满且当前运行的线程数小于最大线程数时，线程池会创建新的线程来处理任务。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">3.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> keepAliveTime（线程空闲时间）：指定非核心线程的空闲时间，即线程在空闲状态下的存活时间。当线程池中的线程数量超过核心线程数时，空闲线程在经过指定的时间后会被终止，直到线程池中的线程数量等于核心线程数。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">4.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> workQueue（工作队列）：用于存放等待执行的任务的阻塞队列。当线程池中的线程数达到核心线程数时，新提交的任务会被放入工作队列中等待执行。常用的工作队列有ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue等。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">5.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> threadFactory（线程工厂）：用于创建新线程的工厂对象。可以自定义线程工厂来创建具有特定属性的线程。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A65E2B;--shiki-dark:#D4976C;">6.</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> handler（拒绝策略）：当线程池已经达到最大线程数并且工作队列也已满时，新提交的任务无法被处理时，拒绝策略会决定如何处理这个任务。常用的拒绝策略有ThreadPoolExecutor.AbortPolicy（抛出RejectedExecutionException异常）、ThreadPoolExecutor.DiscardPolicy（直接丢弃任务）、ThreadPoolExecutor.DiscardOldestPolicy（丢弃最早的任务）和ThreadPoolExecutor.CallerRunsPolicy（由提交任务的线程来执行任务）。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">这些核心参数可以根据具体的需求和场景进行配置，以达到合理利用线程资源、提高系统性能的目的。</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,24)])])}const r=i(l,[["render",p]]),t=JSON.parse('{"path":"/npmDoc/hx729pl9/","title":"homework_14","lang":"en","frontmatter":{"title":"homework_14","createTime":"2025/03/13 09:15:35","permalink":"/npmDoc/hx729pl9/"},"readingTime":{"minutes":17.68,"words":5303},"git":{"createdTime":1720268599000,"updatedTime":1769029816000,"contributors":[{"name":"testingforforwhat","username":"testingforforwhat","email":"2273664Z@student.gla.ac.uk","commits":1,"avatar":"https://avatars.githubusercontent.com/testingforforwhat?v=4","url":"https://github.com/testingforforwhat"},{"name":"QuantumBitstream","username":"QuantumBitstream","email":"2273664Z@student.gla.ac.uk","commits":3,"avatar":"https://avatars.githubusercontent.com/QuantumBitstream?v=4","url":"https://github.com/QuantumBitstream"}]},"filePathRelative":"npmDoc/wiki/note/02/homework_14.md","headers":[]}');export{r as comp,t as data};
