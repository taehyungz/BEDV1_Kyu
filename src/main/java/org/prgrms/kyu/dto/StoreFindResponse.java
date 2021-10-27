package org.prgrms.kyu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.kyu.entity.Store;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreFindResponse {
  Long id;
  String name;
  String telephone;
  String description;
  String location;
}
