package org.prgrms.kyu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StoreFindResponse {
  Long id;
  String name;
  String telephone;
  String description;
  String location;
}
