package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {
	@Test
	public void testIstErstesHalbjahr() {
		for(int i=1; i<=6; ++i) {
			assertTrue(Util.istErstesHalbjahr(i));
		}
		for(int i=7; i<=12; ++i) {
			assertFalse(Util.istErstesHalbjahr(i));
		}
	}
	
	@Test(expected=IllegalArgumentException.class, timeout=1000)
	public void testIstErstesHalbjahr2() {
		Util.istErstesHalbjahr(0);
	}
}
