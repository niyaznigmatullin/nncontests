package lib.test.on2013_06.on2013_06_08_IPSC_2013.B2;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_06/on2013_06_08_IPSC_2013/B2/B2.task"))
			Assert.fail();
	}
}
