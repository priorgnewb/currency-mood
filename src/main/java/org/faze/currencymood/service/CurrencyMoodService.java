package org.faze.currencymood.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.faze.currencymood.dto.CurrencyResponse;
import org.faze.currencymood.dto.GiphyResponse;
import org.faze.currencymood.dto.SingleImage;
import org.faze.currencymood.exception.CurrencyNotFoundException;
import org.faze.currencymood.client.CurrencyFeignClient;
import org.faze.currencymood.client.GiphyFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyMoodService {

  private final CurrencyFeignClient currencyClient;
  private final GiphyFeignClient giphyClient;
  private static final String IMAGE_URL = "https://i.giphy.com/media/%s/giphy.%s";
  private static final Logger logger = LoggerFactory.getLogger(CurrencyMoodService.class);

  public SingleImage getMoodImage(String code) {
    Map<String, Double> rates = currencyClient.getLatestRates().getRates();

    if(!rates.containsKey(code)) throw new CurrencyNotFoundException();

    String yesterday = LocalDate.now().minusDays(1).toString();
    Double todayRate = currencyClient.getLatestRates().getRates().get(code);
    Double yesterdayRate = currencyClient.getRateYesterday(yesterday).getRates().get(code);

    String mood = todayRate >= yesterdayRate ? "broke" : "rich";

    SingleImage image = randomImageId(giphyClient.getMoodGif(mood));
    image.setMood(mood);
    image.setImageUrl(IMAGE_URL.formatted(image.getId(), image.getType()));

    logger.info("Today 1.0 USD = %s %s".formatted(todayRate,code));
    logger.info("Yesterday 1.0 USD = %s %s".formatted(yesterdayRate,code));
    logger.info("Mood is: %s".formatted(mood));
    logger.info("URL for your image: %s".formatted(image.getImageUrl()));

    return image;
  }

  public CurrencyResponse getLatestRates() {
    return currencyClient.getLatestRates();
  }

  public static SingleImage randomImageId(GiphyResponse giphyResponse) {
    Random random = new Random();
    return giphyResponse.getData()[random.nextInt(giphyResponse.getData().length)];
  }
}
