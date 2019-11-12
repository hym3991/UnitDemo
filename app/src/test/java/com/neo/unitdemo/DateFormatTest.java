package com.neo.unitdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 */
@RunWith( Parameterized.class )
public class DateFormatTest
{
	private String time;
	
	public DateFormatTest(String time){
		this.time = time;
	}
	
	@Parameterized.Parameters
	public static Collection primeNumbers(){
		return Arrays.asList(
				"2017-10-15",
				"2019-11-11 16:00:00",
				"2019年10月12日 17时22分01秒" );
	}
	
	@Test(expected = ParseException.class )
	public void dateToStampTest1() throws Exception{
		DateUtil.dateToStamp( time );
	}
}
