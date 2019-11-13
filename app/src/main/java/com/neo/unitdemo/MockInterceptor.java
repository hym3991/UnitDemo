package com.neo.unitdemo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 *
 */
public class MockInterceptor implements Interceptor
{
	@NotNull
	@Override
	public Response intercept( @NotNull Chain chain ) throws IOException
	{
		return null;
	}
}
