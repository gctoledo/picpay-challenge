package dev.gabrieltoledo.picpaychallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PicpaychallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpaychallengeApplication.class, args);
	}

}
