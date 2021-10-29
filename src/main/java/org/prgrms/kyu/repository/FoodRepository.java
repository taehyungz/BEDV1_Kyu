package org.prgrms.kyu.repository;

import org.prgrms.kyu.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
