package com.incomingtask.mycode;

import com.incomingtask.mycode.service.PairOfWordFinder;
import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCodeApplication.class, args);

		PairOfWordFinder pairOfWordFinder = new PairOfWordFinder();
		pairOfWordFinder.pairOfWordCounter(3);
	/*	Options options = new Options();

		Option sourcePath = Option.builder("i").longOpt("inputpath")
				.argName("sourcePath")
				.hasArg()
				.required(true)
				.desc("Set input file path").build();
		options.addOption(sourcePath);

		CommandLine cmd;
		CommandLineParser parser = new DefaultParser();
		HelpFormatter helper = new HelpFormatter();

		try {
			cmd = parser.parse(options, args);
			if(cmd.hasOption("a")) {
				System.out.println("Alpha activated");
			}

			if (cmd.hasOption("i")) {
				String opt_config = cmd.getOptionValue("sourcePath");
				SpringApplication.run(MyCodeApplication.class, opt_config);
				System.out.println("Config set to " + opt_config);
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			helper.printHelp("Usage:", options);
			System.exit(0);
		}
*/
	}

}
