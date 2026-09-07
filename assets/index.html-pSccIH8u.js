import{a as n,c as a,b as e,o as i}from"./app-XcHn6pIB.js";const l={};function p(t,s){return i(),a("div",null,[...s[0]||(s[0]=[e(`<h2 id="constructor-props" tabindex="-1"><a class="header-anchor" href="#constructor-props"><span>constructor (props)</span></a></h2><p>The <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes/constructor" target="_blank" rel="noopener noreferrer">constructor</a> runs before your class component <em>mounts</em> (gets added to the screen). Typically, a constructor is only used for two purposes in React. It lets you declare state and <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_objects/Function/bind" target="_blank" rel="noopener noreferrer">bind</a> your class methods to the class instance:</p><div class="language-react line-numbers-mode" data-highlighter="shiki" data-ext="react" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-react"><span class="line"><span>class Counter extends Component {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  constructor(props) {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    super(props);</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    this.state = { counter: 0 };</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    this.handleClick = this.handleClick.bind(this);</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  }</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>  handleClick() {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    // ...</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  }</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>If you use modern JavaScript syntax, constructors are rarely needed. Instead, you can rewrite this code above using the <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes/Public_class_fields" target="_blank" rel="noopener noreferrer">public class field syntax</a> which is supported both by modern browsers and tools like <a href="https://babeljs.io/" target="_blank" rel="noopener noreferrer">Babel:</a></p><div class="language-react line-numbers-mode" data-highlighter="shiki" data-ext="react" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-react"><span class="line"><span>class Counter extends Component {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  state = { counter: 0 };</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>  handleClick = () =&gt; {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    // ...</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  }</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>A constructor should not contain any side effects or subscriptions.</p><h4 id="parameters" tabindex="-1"><a class="header-anchor" href="#parameters"><span>Parameters</span></a></h4><ul><li><code>props</code>: The component’s initial props.</li></ul><h4 id="returns" tabindex="-1"><a class="header-anchor" href="#returns"><span>Returns</span></a></h4><p><code>constructor</code> should not return anything.</p><h2 id="componentdidmount" tabindex="-1"><a class="header-anchor" href="#componentdidmount"><span><code>componentDidMount()</code></span></a></h2><p>If you define the <code>componentDidMount</code> method, React will call it when your component is added <em>(mounted)</em> to the screen. This is a common place to start data fetching, set up subscriptions, or manipulate the DOM nodes.</p><p>If you implement <code>componentDidMount</code>, you usually need to implement other lifecycle methods to avoid bugs. For example, if <code>componentDidMount</code> reads some state or props, you also have to implement <a href="https://react.dev/reference/react/Component#componentdidupdate" target="_blank" rel="noopener noreferrer"><code>componentDidUpdate</code></a> to handle their changes, and <a href="https://react.dev/reference/react/Component#componentwillunmount" target="_blank" rel="noopener noreferrer"><code>componentWillUnmount</code></a> to clean up whatever <code>componentDidMount</code> was doing.</p><div class="language-react line-numbers-mode" data-highlighter="shiki" data-ext="react" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-react"><span class="line"><span>class ChatRoom extends Component {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  state = {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    serverUrl: &#39;https://localhost:1234&#39;</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  };</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>  componentDidMount() {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    this.setupConnection();</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  }</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>  componentDidUpdate(prevProps, prevState) {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    if (</span></span>
<span class="line"><span></span></span>
<span class="line"><span>      this.props.roomId !== prevProps.roomId ||</span></span>
<span class="line"><span></span></span>
<span class="line"><span>      this.state.serverUrl !== prevState.serverUrl</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    ) {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>      this.destroyConnection();</span></span>
<span class="line"><span></span></span>
<span class="line"><span>      this.setupConnection();</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  }</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>  componentWillUnmount() {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    this.destroyConnection();</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  }</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>  // ...</span></span>
<span class="line"><span></span></span>
<span class="line"><span>}</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="structuring-state" tabindex="-1"><a class="header-anchor" href="#structuring-state"><span>structuring state</span></a></h2><p>When you write a component that holds some state, you’ll have to make choices about how many state variables to use and what the shape of their data should be.</p><h2 id="参考" tabindex="-1"><a class="header-anchor" href="#参考"><span>参考:</span></a></h2><p>https://react.dev/reference/react/Component#componentdidmount</p><p>https://react.dev/learn/choosing-the-state-structure</p>`,19)])])}const c=n(l,[["render",p]]),d=JSON.parse('{"path":"/npmDoc/uejbqqit/","title":"constructor (props)","lang":"en","frontmatter":{"title":"constructor (props)","createTime":"2025/03/13 09:15:35","permalink":"/npmDoc/uejbqqit/"},"readingTime":{"minutes":1.01,"words":303},"git":{"createdTime":1716094698000,"updatedTime":1769029816000,"contributors":[{"name":"testingforforwhat","username":"testingforforwhat","email":"2273664Z@student.gla.ac.uk","commits":4,"avatar":"https://avatars.githubusercontent.com/testingforforwhat?v=4","url":"https://github.com/testingforforwhat"},{"name":"QuantumBitstream","username":"QuantumBitstream","email":"2273664Z@student.gla.ac.uk","commits":3,"avatar":"https://avatars.githubusercontent.com/QuantumBitstream?v=4","url":"https://github.com/QuantumBitstream"}]},"filePathRelative":"npmDoc/wiki/front end/constructor (props).md","headers":[]}');export{c as comp,d as data};
