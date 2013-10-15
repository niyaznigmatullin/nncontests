package lib.test.on2013_02.on2013_02_10_BitWise_2013.FireAndBlood;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_02/on2013_02_10_BitWise_2013/FireAndBlood/FireAndBlood.task"))
			Assert.fail();
	}
}
