package Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import SimErgyClient.CLI;

public class CLITest {

	@Test
	public void testPromptCommandLine() {
		CLI cli = new CLI();
		cli.promptCommandLine();
		
		System.out.println(cli.getCommandLine());
		
		ArrayList<String> tryList = new ArrayList<String>();
		tryList.add("command");
		tryList.add("arg1");
		tryList.add("arg2");
		assertTrue("1.0", cli.getCommandLine().equals(tryList));
	}

}
