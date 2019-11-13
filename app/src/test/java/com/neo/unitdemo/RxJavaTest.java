package com.neo.unitdemo;

import org.junit.Test;

import java.util.Arrays;

import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

/**
 *
 */
public class RxJavaTest
{
	@Test
	public void testObserver(){
		TestObserver<Integer> testObserver = TestObserver.create();
		testObserver.onNext( 1 );
		testObserver.onNext( 2 );
		
		testObserver.assertValues( 1,2 );
		testObserver.onComplete();
		
		testObserver.assertComplete();
	}
	
	@Test
	public void testJust(){
		TestSubscriber<String> testSubscriber = new TestSubscriber<>();
		Flowable.just( "a","b","c" ).subscribe( testSubscriber );
		
		testSubscriber.assertNever( "D" );
		testSubscriber.assertValues( "a","b","c" );
		testSubscriber.assertValueCount( 3 );
		testSubscriber.assertTerminated();
	}
	
	@Test
	public void testFrom(){
		TestSubscriber<Integer> testSubscriber = new TestSubscriber<>(  );
		Flowable.fromIterable( Arrays.asList( 1,2 ) ).subscribe( testSubscriber );
		testSubscriber.assertValues( 1,2 );
		testSubscriber.assertValueCount( 2 );
		testSubscriber.assertTerminated();
	}
	
	@Test
	public void testRange(){
		TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
		Flowable.range( 3,3 ).subscribe( testSubscriber );
		
		testSubscriber.assertValues( 3,4,5 );
		testSubscriber.assertValueCount( 3 );
		testSubscriber.assertTerminated();
	}
	
	
}
