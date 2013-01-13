package lib.test.on2013_01.on2013_01_09_.TaskA;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_01/on2013_01_09_/TaskA/TaskA.task"))
			Assert.fail();
	}
}
