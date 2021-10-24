package org.prgrms.kyu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stores")
public class Store extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 40)
  private String name;

  @Column(nullable = false, length = 11)
  private String telephone;

  @Lob
  private String description;

  @Column(nullable = false, length = 40)
  private String location;

  @Column(nullable = false)
  private Integer posX;

  @Column(nullable = false)
  private Integer posY;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  private boolean isDeleted;
}
