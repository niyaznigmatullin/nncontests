package lib.test.on2013_06.on2013_06_16_Russian_Code_Cup_ER.TaskB;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_06/on2013_06_16_Russian_Code_Cup_ER/TaskB/TaskB.task"))
			Assert.fail();
	}
}
