package com.asb.todo.model.eo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "TODOS")
public class Todo {

  @Id
  @Column(name = "_id")
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @OneToMany
  @JoinColumn(name = "TODO_ID")
  private Collection<Tags> tags;

  public Todo() {
  }

  public Todo(String name, String description, Collection<Tags> tags) {
    super();
    this.name = name;
    this.description = description;
    this.tags = tags;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Collection<Tags> getTags() {
    return tags;
  }

  public void setTags(Collection<Tags> tags) {
    this.tags = tags;
  }

}
