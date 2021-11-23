package com.incomingtask.mycode.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PairOfWordFinder {

    public void pairOfWordCounter(int minimumNrOfPair) {
        String message = "The Quick brown fox jumps over the lazy brown dog the quick over the quick the quick";
        String[] split = message.split(" ");
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < split.length - 1; i++) {
            String temp = split[i] + " " + split[i + 1];
            temp = temp.toLowerCase();
            if (message.toLowerCase().contains(temp)) {
                if (map.containsKey(temp))
                    map.put(temp, map.get(temp) + 1);
                else
                    map.put(temp, 1);
            }
        }
        map.entrySet().removeIf(entry -> entry.getValue() <= minimumNrOfPair);

        System.out.println(map);
        createTxtFromMap(map);
    }

    private void createTxtFromMap(Map map) {

        File file = new File("C:\\Users\\bozso\\OneDrive\\Desktop\\pair\\pairresult.txt");

        BufferedWriter bf = null;;

        try{
            //create new BufferedWriter for the output file
            bf = new BufferedWriter( new FileWriter(file) );

            Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> pairs = it.next();
                bf.write(pairs.getKey() + " " + pairs.getValue() + "\n");

            }
            bf.flush();

        } catch (IOException e){
            e.printStackTrace();
        } finally {

            try{
                //always close the writer
                bf.close();
            }catch(Exception e){}
        }
    }
}
