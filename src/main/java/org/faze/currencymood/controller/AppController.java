package org.faze.currencymood.controller;

import java.util.Optional;
import org.faze.currencymood.dto.CurrencyResponse;
import org.faze.currencymood.dto.SingleImage;
import org.faze.currencymood.exception.CurrencyNotFoundException;
import org.faze.currencymood.service.CurrencyMoodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/currency-mood")
public class AppController {

  private final CurrencyMoodService currencyMoodService;
  private final String defaultCurrency;

  public AppController(CurrencyMoodService currencyMoodService,
      @Value("${exchange.currency}") String defaultCurrency) {
    this.currencyMoodService = currencyMoodService;
    this.defaultCurrency = defaultCurrency;
  }

  @GetMapping( {"/", ""} )
  public String startPage() {
    return "index";
  }

  @GetMapping("/all")
  @ResponseBody
  public CurrencyResponse getLatestRates() {
    return currencyMoodService.getLatestRates();
  }

  @GetMapping("/{inputCode}")
  public String getUrlCurrencyMood(@PathVariable(name = "inputCode", required = false) Optional<String> inputCode, Model model) {

    String code = inputCode.orElse(defaultCurrency);
    if(!code.toUpperCase().matches("[A-Z]{3}")) throw new CurrencyNotFoundException();
    SingleImage moodImage = currencyMoodService.getMoodImage(code.toUpperCase());
    model.addAttribute("moodImageUrl", moodImage.getImageUrl());
    model.addAttribute("moodStatus", moodImage.getMood());
    return "currency-mood";
  }

  @ExceptionHandler(CurrencyNotFoundException.class)
  public String currencyNotFound() {
    return "currency-not-found";
  }
}