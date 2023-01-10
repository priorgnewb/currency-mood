package org.faze.currencymood.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SingleImage {

  String id;
  String type;
  String mood;
  String imageUrl;
}
