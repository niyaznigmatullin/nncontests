package lib.test.on2013_04.on2013_04_13_Croc_Champ_2013___Qualification_Round.B___Command_Line_Arguments;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_04/on2013_04_13_Croc_Champ_2013___Qualification_Round/B___Command_Line_Arguments/B - Command Line Arguments.task"))
			Assert.fail();
	}
}
