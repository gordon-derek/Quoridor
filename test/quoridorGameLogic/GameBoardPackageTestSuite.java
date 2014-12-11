package quoridorGameLogic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GameBoardTestPaths.class, GameBoardTestPawnMovment.class,
		WallplacementTest.class,LegalMovesTest.class })
public class GameBoardPackageTestSuite {
}
