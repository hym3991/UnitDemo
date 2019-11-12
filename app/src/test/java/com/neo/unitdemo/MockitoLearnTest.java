package com.neo.unitdemo;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class MockitoLearnTest
{
	List mocked;
	@Before
	public void setUp(){
		//Mockito.mock(ArrayList.class);只是返回了一个属于ArrayList这个类的一个mock对象。
		mocked = mock(List.class);
		mocked.add( "one" );
		mocked.clear();
		
		//验证是否执行了add方法并增加"one"项
		verify( mocked ).add( "one" );
		
		//指定 Mock 对象的某些方法的行为
		when( mocked.add( "one" ) ).thenReturn( false );
		
		//spy 对象的方法默认调用真实的逻辑，mock 对象的方法默认什么都不做，或直接返回默认值。
		mocked = spy( List.class );
		when( mocked.add( "1" ) ).thenReturn( true );
	}
	
	@Test
	public void test1() throws Exception{
		assertEquals( 4 , 2 + 2 );
	}
}
