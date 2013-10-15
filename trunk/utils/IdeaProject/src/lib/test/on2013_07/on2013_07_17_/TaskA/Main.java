package lib.test.on2013_07.on2013_07_17_.TaskA;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_07/on2013_07_17_/TaskA/TaskA.task"))
			Assert.fail();
	}
}
