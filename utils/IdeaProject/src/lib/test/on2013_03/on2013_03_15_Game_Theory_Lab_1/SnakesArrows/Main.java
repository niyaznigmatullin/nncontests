package lib.test.on2013_03.on2013_03_15_Game_Theory_Lab_1.SnakesArrows;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_03/on2013_03_15_Game_Theory_Lab_1/SnakesArrows/SnakesArrows.task"))
			Assert.fail();
	}
}
