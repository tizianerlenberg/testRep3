package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class QueueTest {
	private Queue testQueue;
	
	@Before
	public void initializeQueue() {
		testQueue = new Queue(5);
	}
	
	@Test(expected=IllegalArgumentException.class, timeout=1000)
	public void initializeQueueTest() {
		testQueue = new Queue(0);
	}
	
	@Test
	public void normalOperationTest() {
		testQueue.enqueue(11);
		testQueue.enqueue(12);
		testQueue.enqueue(13);
		testQueue.enqueue(14);
		testQueue.enqueue(15);
		assertEquals(11, testQueue.dequeue());
		assertEquals(12, testQueue.dequeue());
		assertEquals(13, testQueue.dequeue());
		assertEquals(14, testQueue.dequeue());
		assertEquals(15, testQueue.dequeue());
		
		testQueue.enqueue(21);
		testQueue.enqueue(22);
		testQueue.enqueue(23);
		testQueue.enqueue(24);
		testQueue.enqueue(25);
		assertEquals(21, testQueue.dequeue());
		assertEquals(22, testQueue.dequeue());
		assertEquals(23, testQueue.dequeue());
		assertEquals(24, testQueue.dequeue());
		assertEquals(25, testQueue.dequeue());
	}
	
	@Test
	public void overFlowTest() {
		testQueue.enqueue(11);
		testQueue.enqueue(12);
		testQueue.enqueue(13);
		testQueue.enqueue(14);
		testQueue.enqueue(15);
		testQueue.enqueue(16);
		testQueue.enqueue(17);
		assertEquals(11, testQueue.dequeue());
		assertEquals(12, testQueue.dequeue());
		assertEquals(13, testQueue.dequeue());
		assertEquals(14, testQueue.dequeue());
		assertEquals(17, testQueue.dequeue());
	}

	@Test(expected=IllegalStateException.class, timeout=1000)
	public void emptyDequeueTest() {
		testQueue.dequeue();
	}
}
