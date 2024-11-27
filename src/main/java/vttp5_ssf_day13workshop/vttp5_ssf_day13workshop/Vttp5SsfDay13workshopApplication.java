package vttp5_ssf_day13workshop.vttp5_ssf_day13workshop;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vttp5SsfDay13workshopApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(Vttp5SsfDay13workshopApplication.class);
		
		Path path = Paths.get("");
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if (cliOpts.containsOption("dataDir")){
			path = Paths.get(cliOpts.getOptionValues("dataDir").get(0));
			if (!(Files.exists(path))){
				Files.createDirectories(path);
			}
		}

		else{
			System.err.println("\n\n\n\n");
			System.err.println("dataDir argument not specified in command");
			System.err.println("\n\n\n\n");
			throw new IndexOutOfBoundsException();
		}

		app.setDefaultProperties(Collections.singletonMap("dataDir", path.toString())); //putting this value into main function for the beans to use. In this case the controller is using
		/* System.out.println("\n\n\n\n");
		System.out.println(System.getenv("dataDir"));
		System.out.println("\n\n\n\n"); */
		app.run(args);
	}

}
