# WebPOS

The demo shows a simple POS system in MVC architecture, which replaces the shell interface in aw02 with a pos web ui (https://github.com/bshbsh404/simple-pos-ui
).

![](screenshot.png)

To run

```shell
mvn clean spring-boot:run
```

Currently, it just lists the products for sale with a cart with one item (just for demonstration). 

Please read the tutorial at  https://www.baeldung.com/spring-boot-crud-thymeleaf and make the POS system robust and fully functional. You can also refer to other articles, for instance https://www.baeldung.com/tag/thymeleaf/ .



And please elaborate your understanding in MVC architecture via this homework in your README.md.

<hr>

## Understanding in MVC architecture

Model, View, and Controller.

Model calculates and stores data which is retrieved by View. Thymeleaf can read the data and render it into template HTML files.

View is user interface, which shows the data to and receives requests from users. 

Controller gets requests from servlet, calls Model to process business logic, and selects appropriate View to render. In this project, Controller does the first two things, because we only have one available View.