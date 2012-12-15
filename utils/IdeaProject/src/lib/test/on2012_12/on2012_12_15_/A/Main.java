package lib.test.on2012_12.on2012_12_15_.A;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2012_12/on2012_12_15_/A/A.task"))
			Assert.fail();
	}
}
