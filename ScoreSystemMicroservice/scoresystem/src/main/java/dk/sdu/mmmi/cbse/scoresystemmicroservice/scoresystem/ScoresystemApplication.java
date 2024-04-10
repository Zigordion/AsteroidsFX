package dk.sdu.mmmi.cbse.scoresystemmicroservice.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("api")
@RestController
public class ScoresystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoresystemApplication.class, args);
	}
	int score = 0;
	@GetMapping("/score")
	public Integer notifyScore() {
		score++;
		return score;
	}

}
