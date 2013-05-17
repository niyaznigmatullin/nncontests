package lib.test.on2013_02.on2013_02_02_Facebook_Hacker_Cup_2013_Round_1.Security;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_02/on2013_02_02_Facebook_Hacker_Cup_2013_Round_1/Security/Security.task"))
			Assert.fail();
	}
}
