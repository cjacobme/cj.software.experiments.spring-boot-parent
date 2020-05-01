package cj.software.experiments.spring._04_cyclic_timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Poller
{
	private Logger logger = LogManager.getFormatterLogger();

	private Random randomizer = new Random();

	@Scheduled(fixedDelayString = "PT15S")
	public void triggered() throws InterruptedException
	{
		long sleepSeconds = this.randomizer.nextInt(10);
		this.logger.info("I was triggered, will sleep %d seconds", sleepSeconds);
		TimeUnit.SECONDS.sleep(sleepSeconds);
		this.logger.info("woke up again");
	}
}
