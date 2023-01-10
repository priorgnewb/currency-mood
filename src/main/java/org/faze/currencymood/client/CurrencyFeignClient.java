package org.faze.currencymood.client;

import org.faze.currencymood.config.FeignConfig;
import org.faze.currencymood.dto.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-mood", url="${exchange.url}", configuration = FeignConfig.class)
public interface CurrencyFeignClient {

  @GetMapping("/api/latest.json?app_id=${exchange.app-id}")
  CurrencyResponse getLatestRates();

  @GetMapping("/api/historical/{yesterday}.json?app_id=${exchange.app-id}")
  CurrencyResponse getRateYesterday(@PathVariable String yesterday);
}
