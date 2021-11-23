package com.incomingtask.mycode.configuration;

import com.incomingtask.mycode.listener.FileListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;

import javax.annotation.PreDestroy;
import java.io.File;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {

    //   @Value("${monitoring-folder}")
    //  private String folderPath;

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(30000L), Duration.ofMillis(3000L));
        fileSystemWatcher.addSourceDirectory(new File("C:\\Users\\bozso\\OneDrive\\Desktop\\incoming"));
        fileSystemWatcher.addListener(new FileListener());
        fileSystemWatcher.start();
        System.out.println("started fileSystemWatcher");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        fileSystemWatcher().stop();
    }
}
