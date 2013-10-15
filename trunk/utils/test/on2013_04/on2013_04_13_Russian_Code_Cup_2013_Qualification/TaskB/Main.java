package lib.test.on2013_04.on2013_04_13_Russian_Code_Cup_2013_Qualification.TaskB;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_04/on2013_04_13_Russian_Code_Cup_2013_Qualification/TaskB/TaskB.task"))
			Assert.fail();
	}
}
