package lib.test.on2013_06.on2013_06_08_IPSC_2013.J;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_06/on2013_06_08_IPSC_2013/J/J.task"))
			Assert.fail();
	}
}
