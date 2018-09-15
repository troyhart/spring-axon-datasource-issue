package com.myco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FooDataLoader implements CommandLineRunner {

  private FooRepo fooRepo;

  @Autowired
  public FooDataLoader(FooRepo fooRepo) {
    this.fooRepo = fooRepo;
  }

  @Override
  public void run(String... args) throws Exception {
    Foo foo = new Foo();
    foo.setId("ID1");
    foo.setName("The Big Lebowski");
    fooRepo.save(foo);
    foo = new Foo();
    foo.setId("ID2");
    foo.setName("True Grit");
    fooRepo.save(foo);
    foo = new Foo();
    foo.setId("ID3");
    foo.setName("Tron");
    fooRepo.save(foo);
  }
}
