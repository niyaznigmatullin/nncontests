package lib.test.on2013_02.on2013_02_08_.TaskHyper;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_02/on2013_02_08_/TaskHyper/TaskHyper.task"))
			Assert.fail();
	}
}
