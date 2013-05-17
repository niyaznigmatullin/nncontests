package lib.test.on2013_04.on2013_04_13_Google_Code_Jam_2013_Qualification.TicTacToe;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_04/on2013_04_13_Google_Code_Jam_2013_Qualification/TicTacToe/TicTacToe.task"))
			Assert.fail();
	}
}
