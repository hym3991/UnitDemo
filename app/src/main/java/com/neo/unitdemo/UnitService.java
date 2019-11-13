package com.neo.unitdemo;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class UnitService
{
	private static Retrofit retrofit = new Retrofit.Builder()
			.baseUrl( UnitApi.BASE_URL )
			.client( getOkHttpClient() )
			.addConverterFactory( GsonConverterFactory.create() )
			.addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
			.build();
	
	private static OkHttpClient getOkHttpClient()
	{
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
		{
			@Override
			public void log( @NotNull String s )
			{
				Log.d( "hym",s );
			}
		} );
		return new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.build();
	}
	
	public static UnitApi get(){
		return retrofit.create( UnitApi.class );
	}
	
}
