package org.faze.currencymood.exception;

public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException() {
    super("Currency not found!");
  }
}
