package CLUI;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CLUITentative {

    @Parameter(names={"--length", "-l"})
    int length;
    @Parameter(names={"--pattern", "-p"})
    int pattern;

    public static void main(String ...argv) {
    	
        CLUITentative main = new CLUITentative();
        JCommander.newBuilder().addObject(main);
        JCommander.newBuilder().build();
        main.run();
    }
}
