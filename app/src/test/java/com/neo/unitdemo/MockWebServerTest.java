package com.neo.unitdemo;

import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith( RobolectricTestRunner.class )
@Config(sdk = 28,application = UnitApplication.class)
public class MockWebServerTest
{
	private UnitApi api;
	private MockWebServer server;
	
	@Rule
	public RxJavaRule rule = new RxJavaRule();
	
	@Before
	public void setUp(){
		ShadowLog.stream = System.out;
		
		server = new MockWebServer();
		
		MockResponse response = new MockResponse()
				.addHeader( "Content-Type", "application/json;charset=utf-8" )
				.addHeader( "Cache-Control", "no-cache")
				.setBody( "{\"name\":\"fx(neo)\"}" );
		MockResponse mockResponse1 = new MockResponse()
				.addHeader("Content-Type", "application/json;charset=utf-8")
				.setResponseCode(404)
				.throttleBody(5, 1, TimeUnit.SECONDS) //一秒传递5个字节，模拟网速慢的情况
				.setBody("{\"error\": \"网络异常\"}");
		server.enqueue( response );
		server.enqueue( mockResponse1 );
		
		OkHttpClient okHttpClient = new OkHttpClient.Builder(  )
				.addInterceptor( new HttpLoggingInterceptor(  ) )
				.build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://" + server.getHostName() + ":" + server.getPort() + "/")
				.client( okHttpClient )
				.addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
				.addConverterFactory( GsonConverterFactory.create() )
				.build();
		api = retrofit.create( UnitApi.class );
		
	}
	
	@Test
	public void getUserTest()  throws Exception{
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
		
		RecordedRequest request = server.takeRequest();
		assertEquals("GET /users/simplezhli HTTP/1.1", request.getRequestLine());
		assertEquals("okhttp/3.9.1", request.getHeader("User-Agent"));
		server.shutdown();
	}
}
