package com.neo.unitdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
@RunWith( RobolectricTestRunner.class)
@Config(sdk = 28,application = UnitApplication.class)
public class MainActivityTest
{
	private MainActivity activity;
	
	@Before
	public void setUp(){
		//输出日志
		ShadowLog.stream = System.out;
		activity = Robolectric.setupActivity( MainActivity.class );
	}
	@Test
	public void testMainActivity()
	{
		assertNotNull(activity);
	}
	
	@Test
	public void testToast() throws Exception{
		//Toast toast = ShadowToast.getLatestToast();
		//assertNull(toast);
		
		assertEquals("Hello UkkT!", ShadowToast.getTextOfLatestToast());
		
	}
	
	
}