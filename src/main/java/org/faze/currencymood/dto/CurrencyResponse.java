package org.faze.currencymood.dto;

import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class CurrencyResponse {

  String disclaimer;
  String license;
  Long timestamp;
  String base;
  Map<String, Double> rates;
}
