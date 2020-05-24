package com.misern.keystroke.qualityTools;

import com.misern.keystroke.dao.SampleDAO;
import com.misern.keystroke.dao.impl.SampleDAOImpl;
import com.misern.keystroke.model.Distance;
import com.misern.keystroke.model.Sample;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QualityCounter {
    private static final SampleDAO sampleDAO = new SampleDAOImpl();
    private static List<Sample> samples;

    public static String countQuality(Integer kValue, String metric) throws SQLException, ClassNotFoundException {
        int possitive = 0;
        samples = sampleDAO.findAll();
        float quality = 0;
        for (Sample sample : samples) {
            if(verifyUser(metric, sample, kValue)) {
                possitive++;
            }
        }
        if(possitive != 0) {
            quality = (possitive * 100) / (float) samples.size()  ;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            return df.format(quality) + "%";
        }

        return "0%";
    }

    private static boolean verifyUser(String metric, Sample testedSample, Integer kValue) throws SQLException, ClassNotFoundException {
        ArrayList<Distance> distances = new ArrayList<>();
        for (Sample sample: samples) {
            if(!testedSample.getId().equals(sample.getId())){
                switch (metric) {
                    case "Euclidean":
                        euclideanMetric(sample, testedSample, distances);
                        break;
                    case "Manhattan":
                        manhattanMetric(sample, testedSample, distances);
                        break;
                    case "Chebyshev":
                        chebyshevMetric(sample, testedSample, distances);
                        break;
                }
            }
        }
        sortDistances(distances);

        String bestUser = "";
        int bestUserOccurrences = 0;
        long bestUserDistance = 0L;
        if(kValue < distances.size()) {
            List<Distance> topList = distances.stream().limit(kValue).collect(Collectors.toList());
            List<String> users = sampleDAO.findAllUsers();
            for (String user: users) {
                List<Distance> userList = topList.stream().filter(e -> e.getClazz().equals(user))
                        .collect(Collectors.toList());
                int occurrences = userList.size();
                long userDistance = userList.stream()
                        .mapToLong(Distance::getDistance)
                        .sum();

                if(bestUserOccurrences < occurrences) {
                    bestUser = user;
                    bestUserOccurrences = occurrences;
                } else if(bestUserOccurrences == occurrences && bestUserDistance < userDistance) {
                    bestUser = user;
                    bestUserDistance = userDistance;
                }
            }
            return bestUser.equals(testedSample.getUserName());
        } else return false;

    }

    private static void euclideanMetric(Sample sample, Sample testedSample, ArrayList<Distance> distancesList) {
        Long[] sampleTimes = sample.getSampleTimes();
        Long[] testedSampleTimes = testedSample.getSampleTimes();
        int size = Math.min(sampleTimes.length, testedSampleTimes.length);
        long distanceValue = 0L;
        for(int i = 0; i < size; i++) {
            distanceValue += (sampleTimes[i] - testedSampleTimes[i]) * (sampleTimes[i] - testedSampleTimes[i]);
        }
        Distance distance = new Distance(Math.round(Math.sqrt((double) distanceValue)), sample.getUserName());
        distancesList.add(distance);
    }

    private static void manhattanMetric(Sample sample, Sample testedSample, ArrayList<Distance> distancesList) {
        Long[] sampleTimes = sample.getSampleTimes();
        Long[] testedSampleTimes = testedSample.getSampleTimes();
        int size = Math.min(sampleTimes.length, testedSampleTimes.length);
        long distanceValue = 0L;
        for(int i = 0; i < size; i++) {
            distanceValue += Math.abs(sampleTimes[i] - testedSampleTimes[i]);
        }
        Distance distance = new Distance(distanceValue, sample.getUserName());
        distancesList.add(distance);
    }

    private static void chebyshevMetric(Sample sample, Sample testedSample, ArrayList<Distance> distancesList) {
        Long[] sampleTimes = sample.getSampleTimes();
        Long[] testedSampleTimes = testedSample.getSampleTimes();
        int size = Math.min(sampleTimes.length, testedSampleTimes.length);
        long distanceValue = 0L;
        for(int i = 0; i < size; i++) {
            distanceValue = Math.max(Math.abs(sampleTimes[i] - testedSampleTimes[i]), distanceValue);
        }
        Distance distance = new Distance(distanceValue, sample.getUserName());
        distancesList.add(distance);
    }

    private static void sortDistances(ArrayList<Distance> distances) {
        distances.sort(Comparator.comparing(Distance::getDistance));
    }
}
