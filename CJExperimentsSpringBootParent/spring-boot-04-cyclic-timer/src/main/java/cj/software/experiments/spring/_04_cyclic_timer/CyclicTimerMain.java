package cj.software.experiments.spring._04_cyclic_timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CyclicTimerMain
{
	public static void main(String[] args)
	{
		SpringApplication.run(CyclicTimerMain.class, args);
	}
}
