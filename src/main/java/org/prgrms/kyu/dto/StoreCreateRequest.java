package org.prgrms.kyu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.prgrms.kyu.entity.Store;
import org.prgrms.kyu.entity.User;


@Getter
public class StoreCreateRequest {
  String name;
  String telephone;
  String description;
  String location;
  Long userId;
}
