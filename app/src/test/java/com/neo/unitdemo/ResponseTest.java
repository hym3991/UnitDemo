package com.neo.unitdemo;

import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
@RunWith( RobolectricTestRunner.class )
@Config(sdk = 28,application = UnitApplication.class)
public class ResponseTest
{
	
	@Rule
	public RxJavaRule rxJavaRule = new RxJavaRule();
	
	@Before
	public void setUp(){
		ShadowLog.stream = System.out;
		//initRxJava2();
	}
	
	private void initRxJava2()
	{
		//异步的网络请求无法拿到数据
		//需要通过这个转换成同步的
		RxJavaPlugins.reset();
		RxJavaPlugins.setIoSchedulerHandler( new Function<Scheduler, Scheduler>()
		{
			@Override
			public Scheduler apply( Scheduler scheduler ) throws Exception
			{
				return Schedulers.trampoline();
			}
		} );
		
		RxAndroidPlugins.reset();
		RxAndroidPlugins.setMainThreadSchedulerHandler( new Function<Scheduler, Scheduler>()
		{
			@Override
			public Scheduler apply( Scheduler scheduler ) throws Exception
			{
				return Schedulers.trampoline();
			}
		} );
	}
	
	@Test
	public void getUserTest(){
		UnitService.get().getUser( "hym3991" )
				.subscribeOn( Schedulers.io() )
				.observeOn( AndroidSchedulers.mainThread() )
				.subscribe( new Observer<User>()
				{
					@Override
					public void onSubscribe( Disposable d )
					{
					
					}
					
					@Override
					public void onNext( User user )
					{
						Log.d( "hym","name:"+user.getName() );
					}
					
					@Override
					public void onError( Throwable e )
					{
						Log.d( "hym","error:"+e.toString() );
					}
					
					@Override
					public void onComplete()
					{
					
					}
				} );
	}
}
