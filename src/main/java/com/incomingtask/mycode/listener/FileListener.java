package com.incomingtask.mycode.listener;

import com.incomingtask.mycode.service.PairOfWordFinder;
import org.springframework.boot.devtools.filewatch.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.Set;

@Component
public class FileListener implements FileChangeListener {
    public enum PictureFileTypeExtension {
        jpg,
        bmp,
        gif,
        png
    }

    private static final String TXT = "txt";

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles cfiles : changeSet) {
            for (ChangedFile cfile : cfiles.getFiles()) {
                if ( /* (cfile.getType().equals(Type.MODIFY)
                     || cfile.getType().equals(Type.ADD)
                     || cfile.getType().equals(Type.DELETE) ) && */
                        cfile.getType().equals(ChangedFile.Type.ADD) && !isLocked(cfile.getFile().toPath())) {
                    System.out.println("Operation: " + cfile.getType()
                            + " On file: " + cfile.getFile().getName() + " is done");
                    Optional<String> xxx = getFileExtension(cfile.getFile().getName());
                    System.out.println("THE FILE EXTENSION IS: " + xxx.get());
                    if (getFileExtension(cfile.getFile().getName()).get().equals(TXT)) {
                        // TODO process txt file
                        System.out.println("PROCESS START WITH TXT");
                        PairOfWordFinder pairOfWordFinder = new PairOfWordFinder();
                        System.out.println("THIS IS THE PASS?? " + cfile.getFile().toPath());
                        pairOfWordFinder.findPairOfWord(3, cfile.getFile().toPath());
                    } else if (getFileExtension(cfile.getFile().getName()).equals(PictureFileTypeExtension.jpg)) {
                        // TODO process jpg file
                    }
                }
            }
        }
    }

    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
            return lock == null;
        } catch (IOException e) {
            return true;
        }
    }

    private Optional<String> getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
    }
}
