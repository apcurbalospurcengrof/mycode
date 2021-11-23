package com.incomingtask.mycode.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PairOfWordFinder {

    public void findPairOfWord(int minimumNrOfPair, Path inputFilePath) {

        String stringFromFile = null;
        try {
            stringFromFile = Files.readString(inputFilePath, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stringFromFile.toLowerCase();
        String[] textParts = stringFromFile.split(" ");

        Map<String, Integer> pairOfWordsMap = new HashMap<>();
        for (int i = 0; i < textParts.length - 1; i++) {
            String temp = textParts[i] + " " + textParts[i + 1];
            if (stringFromFile.toLowerCase().contains(temp)) {
                if (pairOfWordsMap.containsKey(temp))
                    pairOfWordsMap.put(temp, pairOfWordsMap.get(temp) + 1);
                else
                    pairOfWordsMap.put(temp, 1);
            }
        }
        pairOfWordsMap.entrySet().removeIf(entry -> entry.getValue() <= minimumNrOfPair);
        createTxtFromMap(pairOfWordsMap);
    }

    private void createTxtFromMap(Map map) {
        String dir = "C:\\Users\\bozso\\OneDrive\\Desktop\\pair";
        File file = new File(dir, "pairresult" + System.currentTimeMillis() + ".txt");

        BufferedWriter bf = null;

        try {
            bf = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, Integer> pairs : (Iterable<Map.Entry<String, Integer>>) map.entrySet()) {
                bf.write(pairs.getKey() + " " + pairs.getValue() + "\n");
            }
            bf.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                assert bf != null;
                bf.close();
            } catch (Exception e) {

            }
        }
    }
}
