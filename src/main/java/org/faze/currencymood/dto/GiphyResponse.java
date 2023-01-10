package org.faze.currencymood.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GiphyResponse {

  SingleImage[] data;
}
