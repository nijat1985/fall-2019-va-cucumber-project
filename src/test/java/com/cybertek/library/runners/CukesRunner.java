package com.cybertek.library.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/cybertek/library/step_definitions",
        dryRun = false,
        tags = " @steps"
//        tags = "librarian" // run if librarian applies to it
//        tags = "@login and @staff" // run if @login and @staff both applies to it
//        tags = "@admin or @staff" // run if either @admin or @staff applies to it
//        tags = "@smoke and not @staff" // run if has @smoke and ignore @staff
//        tags = "not @staff // run everything which doesn't have staff
)
public class CukesRunner {

}
