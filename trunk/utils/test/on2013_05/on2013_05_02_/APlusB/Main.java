package lib.test.on2013_05.on2013_05_02_.APlusB;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_05/on2013_05_02_/APlusB/APlusB.task"))
			Assert.fail();
	}
}
