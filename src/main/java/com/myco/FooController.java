package com.myco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

  private FooRepo fooRepo;

  @Autowired
  public FooController(FooRepo fooRepo) {
    this.fooRepo = fooRepo;
  }

  @GetMapping(path = "{id}")
  public Foo findFoo(@PathVariable String id) {
    return fooRepo.findById(id).get();
  }
}