package com.neo.unitdemo;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
	@Rule
	public MyRule rule = new MyRule();
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	@Test
	public void addition_isCorrect()
	{
		//断言传入的预期值与实际值是相等的
		assertEquals( 4 , 2 + 2 );
		
		//断言传入的预期值与实际值是不相等的
		assertNotEquals( 3,1+1 );
		
		//断言传入的预期数组与实际数组是相等的
		String[] a = {"1","2","3"};
		String[] b = {"1","2","3"};
		assertArrayEquals( a,b );
		
		//断言传入的对象是为空
		String nullObject = null;
		assertNull(nullObject);
		
		//断言传入的对象是不为空
		nullObject = "123";
		assertNotNull(nullObject);
		
		//断言条件为真
		assertTrue( nullObject.equals( "123" ) );
		
		//断言条件为假
		assertFalse( nullObject.equals( "23" ) );
		
		//断言两个对象引用同一个对象，相当于“==”
		String string1 = nullObject;
		assertSame( string1,nullObject );
		
		//断言两个对象引用不同的对象，相当于“!=”
		//应该是值比较 使用2个值一样的字符串，比较结果2者想同
		String string2 = "1123";
		assertNotSame( nullObject,string2 );
		
		/**
		 * 断言实际值是否满足指定的条件
		 * assertThat(T actual, Matcher<? super T> matcher);
		 * assertThat(String reason, T actual, Matcher<? super T> matcher);
		 * 其中reason为断言失败时的输出信息，actual为断言的值，matcher为断言的匹配器。
		 *
		 * 常用的匹配器；
		 *
		 * is       断言参数等于后面给出的匹配表达式     assertThat(5, is (5));
		 * not	    断言参数不等于后面给出的匹配表达式	 assertThat(5, not(6));
		 * equalTo	断言参数相等	                     assertThat(30, equalTo(30));
		 * equalToIgnoringCase 断言字符串相等忽略大小写 assertThat(“Ab”, equalToIgnoringCase(“ab”));
		 * containsString	   断言字符串包含某字符串	 assertThat(“abc”, containsString(“bc”));
		 * startsWith	断言字符串以某字符串开始	     assertThat(“abc”, startsWith(“a”));
		 * endsWith	    断言字符串以某字符串结束	     assertThat(“abc”, endsWith(“c”));
		 * nullValue	断言参数的值为null	         assertThat(null, nullValue());
		 * notNullValue	断言参数的值不为null	         assertThat(“abc”, notNullValue());
		 * greaterThan	断言参数大于	                 assertThat(4, greaterThan(3));
		 * lessThan	    断言参数小于	                 assertThat(4, lessThan(6));
		 * greaterThanOrEqualTo	断言参数大于等于	     assertThat(4, greaterThanOrEqualTo(3));
		 * lessThanOrEqualTo	断言参数小于等于	     assertThat(4, lessThanOrEqualTo(6));
		 * closeTo	断言浮点型数在某一范围内	         assertThat(4.0, closeTo(2.6, 4.3));
		 * allOf	断言符合所有条件，相当于&&	         assertThat(4,allOf(greaterThan(3), lessThan(6)));
		 * anyOf	断言符合某一条件，相当于或	         assertThat(4,anyOf(greaterThan(9), lessThan(6)));
		 * hasKey	断言Map集合含有此键	             assertThat(map, hasKey(“key”));
		 * hasValue	断言Map集合含有此值	             assertThat(map, hasValue(value));
		 * hasItem	断言迭代对象含有此元素	             assertThat(list, hasItem(element));
		 */
		assertThat( 10 ,is(10) );
	}
	
	/**
	 * 常用注解
	 * @Test           表示此方法为测试方法
	 * @Before         在每个测试方法前执行，可做初始化操作
	 * @After          在每个测试方法后执行，可做释放资源操作
	 * @Ignore         忽略的测试方法
	 * @BeforeClass    在类中所有方法前运行。此注解修饰的方法必须是static void
	 * @AfterClass     在类中最后运行。此注解修饰的方法必须是static void
	 * @RunWith        指定该测试类使用某个运行器
	 * @Parameters     指定测试类的测试数据集合
	 * @Rule           重新制定测试类中方法的行为
	 * @FixMethodOrder 指定测试类中方法的执行顺序
	 *
	 * 执行顺序：
	 * @BeforeClass –> @Before –> @Test –> @After –> @AfterClass
	 */
	
	@Test
	public void testAssertThat() throws Exception{
		assertThat( "Hello Us",both(startsWith( "H" )).and( endsWith( "U" ) ) );
	}
	
	@Test
	public void testMobilePhone() throws Exception{
		assertThat( "13455855555",new IsMobilePhoneMatcher() );
	}
	
}