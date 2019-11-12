package com.neo.unitdemo;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 */
public class MyRule implements TestRule
{
	@Override
	public Statement apply( final Statement base ,
			final Description description )
	{
		return new Statement()
		{
			@Override
			public void evaluate() throws Throwable
			{
				//获取测试方法的名字
				String methodName = description.getMethodName();
				System.out.println( methodName+"测试开始！" );
				//运行测试方法
				base.evaluate();
				System.out.println( methodName+"测试结束！" );
			}
		};
	}
}
