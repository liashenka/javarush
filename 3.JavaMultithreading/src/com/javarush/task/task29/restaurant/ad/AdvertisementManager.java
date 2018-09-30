package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private static final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private List<Advertisement> videos;
    private int timeSeconds;
    private long maxProfit = 0;
    private int minRemainingTime = timeSeconds; //min оставшееся время

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
        videos = storage.list();
    }

    public void processVideos() throws NoVideoAvailableException {

        List<Advertisement> bestVariant = new ArrayList<>();
        bestVariant = pickVideosToList(null, null, timeSeconds, 0, bestVariant);
        long totalAmount = 0;
        int totalDuration = 0;
        for (Advertisement ad : bestVariant) {
            ad.revalidate();

            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(),
                    ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            totalAmount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }
          StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestVariant, totalAmount, totalDuration));
    }

    private boolean checkHit(List<Advertisement> listVideosForCheckHit){
        boolean result = true;
        for (Advertisement videoForCheck : listVideosForCheckHit) {
            if(videoForCheck.getHits() > 0) continue;
            else return result = false;
        }
        return result;
    }

    private List<Advertisement> pickVideosToList(List<Advertisement> previousList, Advertisement previousAd, int remainingTime, long profit, List<Advertisement> bestResult) throws NoVideoAvailableException {

        List<Advertisement> newList = new ArrayList<>();
        if (previousList != null) {
            newList.addAll(previousList);
            remainingTime -= previousAd.getDuration();
            profit += previousAd.getAmountPerOneDisplaying();
            newList.add(previousAd);
        }
        for (Advertisement ad : videos) {
            if (remainingTime <= 0) break;
            if (newList.contains(ad)) continue; // если новый список содержит такое видео, переходим к следующему
            if (ad.getHits() <= 0) continue; // если меньше либо равно нулю то переходим к следующему видео в списке
            if (remainingTime >= ad.getDuration()) bestResult = pickVideosToList(newList, ad, remainingTime, profit, bestResult);
        }
        if (profit > maxProfit) {
            maxProfit = profit;
            minRemainingTime = remainingTime;
            if(checkHit(newList)) {
                bestResult = newList;
            }
        } else if (profit == maxProfit && remainingTime < minRemainingTime) {
            minRemainingTime = remainingTime;
            if(checkHit(newList)) {
                bestResult = newList;
            }
        } else if (profit == maxProfit && remainingTime == minRemainingTime && bestResult.size() > newList.size())
            if(checkHit(newList)) {
                bestResult = newList;
            }

        if (bestResult.isEmpty()) {
                 StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        Collections.sort(bestResult, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long pricePerVideoDiff = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (pricePerVideoDiff != 0)
                    return (int) pricePerVideoDiff;
                else
                    return (int) (o1.getAmountPerOneDisplaying() * 100 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 100 / o2.getDuration());
            }
        });
        return bestResult;
    }
}