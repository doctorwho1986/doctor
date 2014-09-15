package com.github.algorithm;

import java.util.BitSet;

/**
 * 
 * @author see http://www.cnblogs.com/allensun/archive/2011/02/16/1956532.html
 * 
 *         布隆过滤器 (Bloom Filter)是由Burton Howard Bloom于1970年提出，它是一种space efficient的概率型数据结构，
 *         用于判断一个元素是否在集合中。在垃圾邮件过滤的黑白名单方法、爬虫(Crawler)的网址判重模块中等等经常被用到。哈希表也能用于判断元素是否在集合中，
 *         但是布隆过滤器只需要哈希表的1/8或1/4的空间复杂度就能完成同样的问题。布隆过滤器可以插入元素，但不可以删除已有元素。其中的元素越多，
 *         false positive rate(误报率)越大，但是false negative (漏报)是不可能的。
 * 
 *         本文将详解布隆过滤器的相关算法和参数设计，在此之前希望大家可以先通过谷歌黑板报的数学之美系列二十一 － 布隆过滤器（Bloom Filter）来得到些基础知识。
 * 
 *         一. 算法描述
 * 
 *         一个empty bloom filter是一个有m bits的bit array，每一个bit位都初始化为0。并且定义有k个不同的hash function，
 *         每个都以uniform random distribution将元素hash到m个不同位置中的一个。在下面的介绍中n为元素数，m为布隆过滤器或哈希表的slot数，
 *         k为布隆过滤器重hash function数。
 *         为了add一个元素，用k个hash function将它hash得到bloom filter中k个bit位，将这k个bit位置1。
 *         为了query一个元素，即判断它是否在集合中，用k个hash function将它hash得到k个bit位。若这k bits全为1，
 *         则此元素在集合中；若其中任一位不为1，则此元素比不在集合中（因为如果在，则在add时已经把对应的k个bits位置为1）。
 *         不允许remove元素，因为那样的话会把相应的k个bits位置为0，而其中很有可能有其他元素对应的位。
 *         因此remove会引入false negative，这是绝对不被允许的。
 *         当k很大时，设计k个独立的hash function是不现实并且困难的。对于一个输出范围很大的hash function（例如MD5产生的128 bits数），
 *         如果不同bit位的相关性很小，则可把此输出分割为k份。或者可将k个不同的初始值（例如0,1,2, … ,k-1）结合元素，feed给一个hash function从而产生k个不同的数。
 *         当add的元素过多时，即n/m过大时（n是元素数，m是bloom filter的bits数），会导致false positive过高，此时就需要重新组建filter，但这种情况相对少见。
 *         二. 时间和空间上的优势
 * 
 *         当可以承受一些误报时，布隆过滤器比其它表示集合的数据结构有着很大的空间优势。例如self-balance BST, tries, hash table或者array, chain，
 *         它们中大多数至少都要存储元素本身，对于小整数需要少量的bits，对于字符串则需要任意多的bits（tries是个例外，因为对于有相同prefixes的元素可以共享存储空间）；
 *         而chain结构还需要为存储指针付出额外的代价。对于一个有1%误报率和一个最优k值的布隆过滤器来说，无论元素的类型及大小，每个元素只需要9.6
 *         bits来存储。这个优点一部分继承自array的紧凑性，一部分来源于它的概率性。如果你认为1%的误报率太高，那么对每个元素每增加4.8 bits，
 *         我们就可将误报率降低为原来的1/10。add和query的时间复杂度都为O(k)，与集合中元素的多少无关，这是其他数据结构都不能完成的。
 *         如果可能元素范围不是很大，并且大多数都在集合中，则使用确定性的bit array远远胜过使用布隆过滤器。因为bit array对于每个可能的元素空间上只需要1 bit，
 *         add和query的时间复杂度只有O(1)。注意到这样一个哈希表（bit array）只有在忽略collision并且只存储元素是否在其中的二进制信息时，
 *         才会获得空间和时间上的优势，而在此情况下，它就有效地称为了k=1的布隆过滤器。
 *         而当考虑到collision时，对于有m个slot的bit array或者其他哈希表（即k=1的布隆过滤器），如果想要保证1%的误判率，则这个bit array只能存储m/100个元素，
 *         因而有大量的空间被浪费，同时也会使得空间复杂度急剧上升，这显然不是space efficient的。解决的方法很简单，使用k>1的布隆过滤器，
 *         即k个hash function将每个元素改为对应于k个bits，因为误判度会降低很多，并且如果参数k和m选取得好，一半的m可被置为为1，
 *         这充分说明了布隆过滤器的space efficient性。
 * 
 * 
 *         see http://blog.csdn.net/acceptedxukai/article/details/7031899
 *         布隆过滤器原理很简单：就是把一个字符串哈希成一个整数key，然后选取一个很长的比特序列，开始都是0，在key把此位置的0变为1；
 *         下次进来一个字符串，哈希之后的值key，如果在此比特位上的值也是1，那么就说明这个字符串存在了。
 * 
 *         如果按照上面的做法，那就和哈希算法没有什么区别了，哈希算法还有重复的呢。
 *         布隆过滤器是将一个字符串哈希成多个key，我还是按照书上的说吧。
 *         先建立一个16亿二进制常量，然后将这16亿个二进制位全部置0。对于每个字符串，用8个不同的随机产生器（F1,F2,.....,F8）产生8个信息指纹(f1,f2,....,f8).
 *         再用一个随机数产生器G把这八个信息指纹映射到1到16亿中的8个自然数g1,g2,...,g8。现在把这8个位置的二进制位全部变为1。这样一个布隆过滤器就建好了。
 * 
 *         那么如何检测一个字符串是否已经存在了呢？
 * 
 *         现在用8个随机数产生器（F1,F2,...,F8）对这个字符串产生8个信息指纹s1,s2,...,s8，然后将这8个信息指纹对应到布隆过滤器的8个二进制位，
 *         分别是T1,T2,...,T8.如果字符串存在，那么显然T1,T2,...,T8对应的二进制位都应该是1。就是这样来判断字符串是否已经存在的。
 * 
 *         其实布隆过滤器就是对哈希算法的一个扩展，既然本质是哈希，那么就肯定会有不足，也就是说，肯定会有误判，一个字符串明明没有出现过而布隆过滤器判断出现了，
 *         虽然可能性很小，但是确实存在。
 * 
 *         那么如何减少这种概率呢，首先可以想到的是如果将8个信息指纹扩展到16个错误的概率肯定会降低，但是也要考虑到，这样的话，
 *         那么一个布隆过滤器所能存储的字符串数量也降低了1倍；另外就是选取很好的哈希函数，对字符串的哈希方法有很多种，其中不乏很好的哈希函数。
 * 
 *         布隆过滤器主要运用在过滤恶意网址用的，将所有的恶意网址建立在一个布隆过滤器上，然后对用户的访问的网址进行检测，如果在恶意网址中那么就通知用户。
 *         这样的话，我们还可以对一些常出现判断错误的网址设定一个白名单，然后对出现判断存在的网址再和白名单中的网址进行匹配，如果在白名单中，那么就放行。
 *         当然这个白名单不能太大，也不会太大，布隆过滤器错误的概率是很小的。有兴趣的读者可以去查阅，布隆过滤器的错误率。
 *
 */

public class BloomFilter {

	private static final int DEFAULT_SIZE = 2 << 24;// 布隆过滤器的比特长度
	private static final int[] seeds = { 3, 5, 7, 11, 13, 31, 37, 61 };// 这里要选取质数，能很好的降低错误率
	private static BitSet bits = new BitSet(DEFAULT_SIZE);
	private static SimpleHash[] func = new SimpleHash[seeds.length];

	public static void addValue(String value)
	{
		for (SimpleHash f : func)
			// 将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1
			bits.set(f.hash(value), true);
	}

	public static void add(String value)
	{
		if (value != null)
			addValue(value);
	}

	public static boolean contains(String value)
	{
		if (value == null)
			return false;
		boolean ret = true;
		for (SimpleHash f : func)
			// 这里其实没必要全部跑完，只要一次ret==false那么就不包含这个字符串
			ret = ret && bits.get(f.hash(value));
		return ret;
	}

	public static void main(String[] args) {
		String value = "xkeyideal@gmail.com";
		for (int i = 0; i < seeds.length; i++) {
			func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
		}
		add(value);
		System.out.println(contains(value));
	}
}

class SimpleHash {

	private int cap;
	private int seed;

	public SimpleHash(int cap, int seed) {
		this.cap = cap;
		this.seed = seed;
	}

	public int hash(String value) {// 字符串哈希，选取好的哈希函数很重要
		int result = 0;
		int len = value.length();
		for (int i = 0; i < len; i++) {
			result = seed * result + value.charAt(i);
		}
		return (cap - 1) & result;
	}
}
