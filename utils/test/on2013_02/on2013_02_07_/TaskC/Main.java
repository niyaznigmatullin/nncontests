package lib.test.on2013_02.on2013_02_07_.TaskC;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_02/on2013_02_07_/TaskC/TaskC.task"))
			Assert.fail();
	}
}
