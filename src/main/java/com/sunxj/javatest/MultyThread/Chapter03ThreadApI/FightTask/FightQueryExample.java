package com.sunxj.javatest.MultyThread.Chapter03ThreadApI.FightTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = search("SH","BJ");
        results.forEach(System.out::println);
    }
    private static FightQueryTask createSearch(String fight,
                                               String origin,
                                               String dest){
        return new FightQueryTask(fight,origin,dest);
    }
    public static List<String> search(String origin,String dest){
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> tasks = fightCompany
                .stream()
                .map(f->createSearch(f,origin,dest)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }
}
