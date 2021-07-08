package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class AlertRabbit {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try(FileInputStream in = new FileInputStream("C:\\projects\\job4j_design\\chapter_003\\src\\main\\resources\\rabbit.properties")) {
            properties.load(in);
        }
        String period = properties.getProperty("rabbit.interval");
        JobDetail job = JobBuilder.newJob(Rabbit.class).build();

        SimpleScheduleBuilder times = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(Integer.valueOf(period)).repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(times).build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }


}
