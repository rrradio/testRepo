package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        String directoryPath = "C:/Users/rrrad/Desktop/TestDidfffhcjh3у2";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        Random random = new Random();
        long t =  System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(33);
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            final int folderNumber = i;
            tasks.add(() -> {
                String folderName = "Folder" + folderNumber;
                String folderPath = directoryPath + "/" + folderName;
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdir();
                }

                for (int j = 1; j <= 100; j++) {
                    String fileName = "";
                    for (int k = 0; k < 10; k++) {
                        char c = (char) (random.nextInt(26) + 'a');
                        fileName += c;
                    }
                    String filePath = folderPath + "/" + fileName + ".txt";
                    File file = new File(filePath);
                    try {
                        FileWriter writer = new FileWriter(file);
                        writer.write("This is a test file.");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            });
            System.out.println(i+" таска добавлена к будущему исполнению");
        }

        try {
            System.out.println("приступаем к выполнению всех тасок..");
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println( System.currentTimeMillis() - t + "ms затрачено");

        // Ого давай те ка добавим первый комментарий сюда, чтобы проследить разные ветки в гите
        // ого, теперь второй
        // третий коммент затестить ветки
        //4
    }
}