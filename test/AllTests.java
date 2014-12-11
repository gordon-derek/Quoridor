import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import quoridorClient.gui.QuoridorClientGUIPackageTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ quoridorClient.QuoridorClientPackageTestSuite.class,
		quoridorGameLogic.GameBoardPackageTestSuite.class,
		quoridorServer.QuoridorServerPackageTestSuite.class,
		QuoridorClientGUIPackageTestSuite.class })
public class AllTests {

}
