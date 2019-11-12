package com.neo.unitdemo.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 *
 */
@RunWith( PowerMockRunner.class )
public class BananaTest
{
	@Test
	@PrepareForTest({Banana.class})
	public void testStaticMethod(){
		PowerMockito.mockStatic( Banana.class );
		when( Banana.getCOLOR() ).thenReturn( "绿色" );
		assertEquals( "绿色",Banana.getCOLOR());
	}
	
	@Test
	@PrepareForTest({Banana.class})
	public void testChangeColor(){
		Whitebox.setInternalState( Banana.class,"COLOR","红色的" );
		assertEquals( "红色的",Banana.getCOLOR() );
	}
}