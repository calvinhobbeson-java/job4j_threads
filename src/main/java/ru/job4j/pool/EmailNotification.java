package ru.job4j.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool;

    public EmailNotification(ExecutorService pool) {
        this.pool = pool;
    }

    public void send(String subject, String body, String email) {

    }

    public List<String> emailTo(User user) {
        ArrayList<String> message = new ArrayList<>();
        String username = user.getUsername();
        String email = user.getEmail();
        String subject = String.format("Notification %s to email %s", username, email);
        String body = String.format("Add a new event for user %s", username);
        message.add(subject);
        message.add(body);
        message.add(email);
        return message;
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        emailNotification.pool.submit(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setEmail("Oleg1234@gmail.com");
                user.setUsername("OlegBanzai");
                emailNotification.send(emailNotification.emailTo(user).get(0), emailNotification.emailTo(user).get(1), emailNotification.emailTo(user).get(2));
            }
        });
        emailNotification.pool.shutdown();
    }
}
