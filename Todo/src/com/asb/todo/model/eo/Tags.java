package com.asb.todo.model.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TAGS")
public class Tags {

  @Id
  @Column(name = "_id")
  private long id;

  @Column(name = "NAME")
  private String name;

  public Tags() {
  }

  public Tags(String name) {
    super();
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
