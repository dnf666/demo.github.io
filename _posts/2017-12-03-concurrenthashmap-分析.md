---
layout:     post
title:      concurrenthashmap分析和对比
subtitle:   java 源码
date:       2017-12-03
author:     戴林甫
header-img: img/post-bg-ios9-web.jpg
catalog: true
tags:
    - java
    - 源码
---

### 前言
concurrenthashmap（简称chm） 是java1.5新引入的java.util.concurrent包的成员，作为hashtable的替代。为什么呢，hashtable采用了同步整个方法的结构。虽然实现了线程安全但是性能也就大大降低了
而hashmap呢，在并发情况下会很容易出错。所以也促进了安全并且能在多线程中使用的concurrenthashmap

### java如何实现concurrenthashmap
    首先来看看构造方法吧
    这是最底层的构造方法
    public ConcurrentHashMap(int initialCapacity,
                                 float loadFactor, int concurrencyLevel) {
            if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
                throw new IllegalArgumentException();
            if (concurrencyLevel > MAX_SEGMENTS)
                concurrencyLevel = MAX_SEGMENTS;
            // Find power-of-two sizes best matching arguments
            int sshift = 0;
            int ssize = 1;
            while (ssize < concurrencyLevel) {
                ++sshift;//代表ssize转换的次数
                ssize <<= 1;
            }
            this.segmentShift = 32 - sshift;//目前不知道有什么用，是在后来的segment定位中使用
            this.segmentMask = ssize - 1;//segment定位使用
            if (initialCapacity > MAXIMUM_CAPACITY)
                initialCapacity = MAXIMUM_CAPACITY;
            int c = initialCapacity / ssize;
            if (c * ssize < initialCapacity)
                ++c;
            int cap = MIN_SEGMENT_TABLE_CAPACITY;
            while (cap < c)
                cap <<= 1;
            // create segments and segments[0]
            Segment<K,V> s0 =
                new Segment<K,V>(loadFactor, (int)(cap * loadFactor),
                                 (HashEntry<K,V>[])new HashEntry[cap]);
            Segment<K,V>[] ss = (Segment<K,V>[])new Segment[ssize];
            UNSAFE.putOrderedObject(ss, SBASE, s0); // ordered write of segments[0]
            this.segments = ss;
        }
        这里我想和hashmap对比来分析，因为他们长得很像
        hashmap是entry<K,v>[]，而concurrenthashmap就是segments<K,v>[].
        可以说每一个segment都是一个hashmap，想要进入segment还需要获取对应的锁。默认conccurrenthashmap的segment数是16.每个segment内的hashentry数组大小也是16个。threadshord是16*0.75
        

###concurrenthashmap又是如何定位的呢

        先看看chm的hash方法        
        private int hash(Object k) {
                int h = hashSeed;
        
                if ((0 != h) && (k instanceof String)) {
                    return sun.misc.Hashing.stringHash32((String) k);
                }
        
                h ^= k.hashCode();
        
                // Spread bits to regularize both segment and index locations,
                // using variant of single-word Wang/Jenkins hash.
                h += (h <<  15) ^ 0xffffcd7d;
                h ^= (h >>> 10);
                h += (h <<   3);
                h ^= (h >>>  6);
                h += (h <<   2) + (h << 14);
                return h ^ (h >>> 16);
            }
            
        这里对key的hash值再哈希了一次。使用的方法是wang/jenkins的哈希算法
        这里再hash是为了减少hash冲突。如果不这样做的话，会出现大多数值都在一个segment上，这样就失去了分段锁的意义。
        以上代码只是算出了key的新的hash值，但是怎么用这个hash值定位呢
        如果我们要取得一个值，首先我们肯定需要先知道哪个segment，然后再知道hashentry的index，最后一次循环遍历该index下的元素
        确定segment，：(h >>> segmentShift) & segmentMask。默认使用h的前4位，segmentMask为15
        确定index：(tab.length - 1) & h  hashentry的长度减1，用之前确定了sement的新h计算
        循环：for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                                (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
                            e != null; e = e.next)
                            
          比较：if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                                 return e.value;
     
### concurrenthashmap的 get操作
    public V get(Object key) {
            Segment<K,V> s; // manually integrate access methods to reduce overhead
            HashEntry<K,V>[] tab;
            int h = hash(key);
            long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
            if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
                (tab = s.table) != null) {
                for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                         (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
                     e != null; e = e.next) {
                    K k;
                    if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                        return e.value;
                }
            }
            return null;
        }
        
        如果我们要取得一个值，首先我们肯定需要先知道哪个segment，然后再知道hashentry的index，最后一次循环遍历该index下的元素
               确定segment，：(h >>> segmentShift) & segmentMask。默认使用h的前4位，segmentMask为15
               确定index：(tab.length - 1) & h  hashentry的长度减1，用之前确定了sement的新h计算
               循环：for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                                       (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
                                   e != null; e = e.next)
                                   
                 比较：if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                                        return e.value;
                 
### concurrenthashmap put操作
        public V put(K key, V value) {
                Segment<K,V> s;
                if (value == null)
                    throw new NullPointerException();
                int hash = hash(key);
                int j = (hash >>> segmentShift) & segmentMask;
                if ((s = (Segment<K,V>)UNSAFE.getObject          // nonvolatile; recheck
                     (segments, (j << SSHIFT) + SBASE)) == null) //  in ensureSegment
                    s = ensureSegment(j);
                return s.put(key, hash, value, false);
            }   
            在jdk中，native方法的实现是没办法看的，请下载openjdk来看。在put方法中实际是需要判断是否需要扩容的
            扩容的时机选在阀值（threadshold）装满时，而不像hashmap是在装入后，再判断是否装满并扩容
            这里就是concurrenthashmap的高明之处，有可能会出现扩容后就没有新数据的情况
            
#### concrrenthashmap size
    public int size() {
            // Try a few times to get accurate count. On failure due to
            // continuous async changes in table, resort to locking.
            final Segment<K,V>[] segments = this.segments;
            int size;
            boolean overflow; // true if size overflows 32 bits
            long sum;         // sum of modCounts
            long last = 0L;   // previous sum
            int retries = -1; // first iteration isn't retry
            try {
                for (;;) {
                    if (retries++ == RETRIES_BEFORE_LOCK) {
                        for (int j = 0; j < segments.length; ++j)
                            ensureSegment(j).lock(); // force creation
                    }
                    sum = 0L;
                    size = 0;
                    overflow = false;
                    for (int j = 0; j < segments.length; ++j) {
                        Segment<K,V> seg = segmentAt(segments, j);
                        if (seg != null) {
                            sum += seg.modCount;
                            int c = seg.count;
                            if (c < 0 || (size += c) < 0)
                                overflow = true;
                        }
                    }
                    if (sum == last)
                        break;
                    last = sum;
                }
            } finally {
                if (retries > RETRIES_BEFORE_LOCK) {
                    for (int j = 0; j < segments.length; ++j)
                        segmentAt(segments, j).unlock();
                }
            }
            return overflow ? Integer.MAX_VALUE : size;
        }
        
        这段代码写的真巧妙，在统计concurrenthashmap的数量时，有多线程情况，但是并不是一开始就锁住修改结构的方法，比如put，remove等
        先执行一次统计，然后在执行一次统计，如果两次统计结果都一样，则没问题。反之就锁修改结构的方法。这样做效率会高很多，在统计的时候查询依旧可以进行

#### isEmpty方法    
    public boolean isEmpty() {
            /*
             * Sum per-segment modCounts to avoid mis-reporting when
             * elements are concurrently added and removed in one segment
             * while checking another, in which case the table was never
             * actually empty at any point. (The sum ensures accuracy up
             * through at least 1<<31 per-segment modifications before
             * recheck.)  Methods size() and containsValue() use similar
             * constructions for stability checks.
             */
            long sum = 0L;
            final Segment<K,V>[] segments = this.segments;
            for (int j = 0; j < segments.length; ++j) {
                Segment<K,V> seg = segmentAt(segments, j);
                if (seg != null) {
                    if (seg.count != 0)
                        return false;
                    sum += seg.modCount;
                }
            }
            if (sum != 0L) { // recheck unless no modifications
                for (int j = 0; j < segments.length; ++j) {
                    Segment<K,V> seg = segmentAt(segments, j);
                    if (seg != null) {
                        if (seg.count != 0)
                            return false;
                        sum -= seg.modCount;
                    }
                }
                if (sum != 0L)
                    return false;
            }
            return true;
        }
        即使在空的情况下也不能仅仅只靠segment的计数器来判断，还是因为多线程，count的值随时在变，所以追加判断
        modcount前后是否一致，如果一致，说明期间没有修改。
        
####remove 方法

    final V remove(Object key, int hash, Object value) {
                if (!tryLock())
                    scanAndLock(key, hash);
                V oldValue = null;
                try {
                    HashEntry<K,V>[] tab = table;
                    int index = (tab.length - 1) & hash;
                    HashEntry<K,V> e = entryAt(tab, index);
                    HashEntry<K,V> pred = null;
                    while (e != null) {
                        K k;
                        HashEntry<K,V> next = e.next;
                        if ((k = e.key) == key ||
                            (e.hash == hash && key.equals(k))) {
                            V v = e.value;
                            if (value == null || value == v || value.equals(v)) {
                                if (pred == null)
                                    setEntryAt(tab, index, next);
                                else
                                    pred.setNext(next);
                                ++modCount;
                                --count;
                                oldValue = v;
                            }
                            break;
                        }
                        pred = e;
                        e = next;
                    }
                } finally {
                    unlock();
                }
                return oldValue;
            }   
    思考：
    1.hashmap的默认大小是1<<4,即16，但是concurrenthashmap却直接16.
    2.（k << SSHIFT) + SBASE 这段话我是真没懂，定位的时候会用
    3.get方法中直接写的定位方法，为什么不像remove一样调用segmentforhash呢
    4.concurrenthashmap和hashtable不能允许key或者value为null。因为在多线程情况下无法判断返回一个null值到底是key为null还是value为null
     hashmap是非多线程的，所以可以key为null何value为null
    