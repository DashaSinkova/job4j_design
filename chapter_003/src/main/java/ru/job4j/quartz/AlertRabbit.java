package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AlertRabbit {
    private static Connection initConnection(Properties properties) throws SQLException {
        String url = properties.getProperty("jdbc.url");
        String login = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        return DriverManager.getConnection(url, login, password);
    }
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            try(FileInputStream in = new FileInputStream("C:\\projects\\job4j_design\\chapter_003\\src\\main\\resources\\rabbit.properties")) {
                properties.load(in);
            }
            String period = properties.getProperty("rabbit.interval");
            try(Connection cn = initConnection(properties)) {
                JobDataMap data = new JobDataMap();
                data.put("Connection", cn);
                JobDetail job = JobBuilder.newJob(Rabbit.class).usingJobData(data).build();

                SimpleScheduleBuilder times = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(Integer.valueOf(period)).repeatForever();
                Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(times).build();

                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            insert((Connection) context.getJobDetail().getJobDataMap().get("Connection"));

        }
        private void insert(Connection cn) {
            try(PreparedStatement statement = cn.prepareStatement("insert into rabbit(created_date) values (?)")) {
                statement.setLong(1, System.currentTimeMillis());
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
