package org.faze.currencymood.client;

import org.faze.currencymood.config.FeignConfig;
import org.faze.currencymood.dto.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="giphy", url="${giphy.url}/v1/", configuration = FeignConfig.class)
public interface GiphyFeignClient {

  @GetMapping("gifs/search?api_key=${giphy.com.api-key}&q={mood}")
  GiphyResponse getMoodGif(@PathVariable String mood);
}
