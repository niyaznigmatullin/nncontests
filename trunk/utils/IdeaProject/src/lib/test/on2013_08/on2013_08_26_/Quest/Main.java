package lib.test.on2013_08.on2013_08_26_.Quest;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_08/on2013_08_26_/Quest/Quest.task"))
			Assert.fail();
	}
}
