package com.neo.unitdemo;

import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 */
public class DateUtilTest
{
	private Date mDate;
	private long timeStamp = 1508054402000L;
	private String time = "2017-10-15 16:00:02";
	
	@Rule
	public MyRule rule = new MyRule();
	
//	@Before
//	public void setUp() throws Exception{
//		System.out.println( "测试开始～～" );
//		mDate = new Date(  );
//		mDate.setTime( timeStamp );
//	}
	
//	@After
//	public void testDown() throws Exception{
//		System.out.println( "测试结束～～" );
//	}
	
	@Test
	public void dateToStamp() throws Exception
	{
		assertNotEquals( 4,DateUtil.dateToStamp( time ) );
	}
	
	//验证一个方法是否抛出了异常
	@Test(expected = ParseException.class)
	public void dateToStamp1() throws Exception{
		DateUtil.dateToStamp( "2016" );
	}
	
	@Test
	public void stampToDate()
	{
		assertEquals( time,DateUtil.stampToDate( timeStamp ) );
	}
	
	
}