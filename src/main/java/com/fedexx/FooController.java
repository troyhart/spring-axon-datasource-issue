package com.fedexx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

  private Repo fooRepo;

  @Autowired
  public FooController(Repo fooRepo) {
    this.fooRepo = fooRepo;
  }

  @GetMapping(path = "{id}")
  public Foo findFoo(@PathVariable String id) {
    return fooRepo.findById(id).get();
  }
}