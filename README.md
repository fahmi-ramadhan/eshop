# ADV E-Shop

Name: Fahmi Ramadhan<br>
NPM: 2206026473<br>
Class: Advanced Programming A<br>

App link: https://eshop-fahmi-ramadhan.koyeb.app/product/list

# Module 01: Coding Standards

<details>

### Reflection 1

In this module, I've learned about coding standards and applied them in the tutorial and exercises. In the first exercise, I implemented edit and delete features and applied some clean code principles in my code, such as:

- **Meaningful names**: I use descriptive names that clearly indicate the purpose of a function or variable so I don't need to add comments.

- **Small functions**: I make my functions small and only do one thing.

- **Error handling**: I use exceptions (I define `ProductNotFoundException` class instead of returning null if the product not found).

For the secure coding practices, I haven't implemented them yet because in this module, there is no authentication and authorization for users to do the CRUD operations.

I think there are still so much more to improve in my code so that it follows the coding standards, such as implementing authentication, authorization, input data validation, and more error handling.

### Reflection 2

1. After writing the unit test, I feel more confident about the reliability of my code and I'm starting to understand about the importance of it. I think the number of unit tests should be made in a class depends on the complexity of the class and the functionality it covers. To make sure that our unit tests are enough to verify our program, I think each method should be tested with at least one test and I also think we should test different scenarios, including edge cases. Also, code coverage is good way to help us understand how much of our code is tested, but I think 100% code coverage doesn't guarantee that my code is free of bugs and errors because there may still be logical errors or unforeseen edge cases that are not covered by the tests.

2. I think creating a new Java class similar to the prior functional test suites with the same setup procedures and instance variables can reduce the code cleanliness because it will create unnecessary code duplication, making it harder to maintain and the efficiency reduced. One possible improvement to make the code cleaner is to refactor the existing test suites by extracting common setup procedures and instance variables, like instantiating the product, into separate methods. Then, each test method can simply call these shared setup methods before running the actual test logic.

</details>

# Module 02: CI/CD & DevOps

<details>

During the exercise, I addressed several code quality issues, such as:

- Removing the public modifier from files related to testing, as the best practice in testing is to use the default modifier.

- Removing field injection (@Autowired) and use constructor injection instead.

Looking at the CI/CD workflows, I believe the current implementation has indeed achieved Continuous Integration and Continuous Deployment. Firstly, the CI pipeline triggers on every push to the repository, ensuring that changes are integrated frequently. Secondly, automated tests are run as part of the pipeline to validate the code changes. Lastly, the CD pipeline deploys the code to the PaaS environment automatically upon successful testing, enabling continuous deployment of new features and fixes. Overall, the process ensures that code changes are quickly validated, integrated, and deployed, meeting the principles of CI/CD.

</details>

# Module 03: Maintainability & OO Principles

<details>

### 1. Explain what principles you apply to your project!

1. **Single Responsibility Principle (SRP)**: Each class or module in the project should have one and only one reason to change. This means that each class or module should have only one job.
My project follows this principle because it has separate controllers for different entities like `Car` and `Product` so that each controller class in my project has a single responsibility.


2. **Open-Closed Principle (OCP)**: Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification. This means that I should be able to add new functionality without changing the existing code.
My project follows this principle because I ensure that the classes and functions that I create can be extended or I can add the implementation without changing the existing code, such as using the `ProductService` interface that is implemented in `ProductServiceImpl`
and I can add other methods in `ProductServiceImpl`.


3. **Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types. This means that if a program is using a base class, it should be able to use any of its subclasses without the program knowing it.
My project already follows this principle since there is no inheritance because i remove `ProductController` extension in `CarController`.


4. **Interface Segregation Principle (ISP)**: Clients should not be forced to depend on interfaces they do not use. This means that a class should not have to implement methods it doesn’t use.
My project follows this principle because I separate the interface for Car and Product (`PoductService` only relates to `Product` and `CarService` only relates to `Car`).


5. **Dependency Inversions Principle (DIP)**: High-level modules should not depend on low-level modules. Both should depend on abstractions. This means that I should depend on abstractions, not on concrete implementations.
My project follows this principle because I make sure all dependency I use depends on abstraction, i.e., I changed the code in my `CarController` to use `CarService` instead of `CarServiceImpl`. 


### 2. Explain the advantages of applying SOLID principles to your project with examples.

1. **Maintainability**: SOLID principles make my code more maintainable. For example, since my project follows the SRP, when a change is required in a class, it is likely to be a small change because the class has only one responsibility.
2. **Testability**: SOLID principles make my code more testable. For example, since my project follows the DIP, I can easily swap out dependencies with mock objects when testing.
3. **Extensibility**: SOLID principles make my code more extensible. For example, since my project follows the OCP, I can add new functionality without changing the existing code.


### 3. Explain the disadvantages of not applying SOLID principles to your project with examples.

1. **Tight Coupling**: Without applying the DIP, my classes will become tightly coupled. This makes it hard to change one class without affecting others.
2. **Large Classes**: Without applying the SRP, my classes can become large and difficult to maintain. This can happen if I don't separate `CarController` from `ProductController` class.
3. **Difficulty in Testing**: Without applying the DIP and ISP, my code can become difficult to test and I might have to deal with unwanted dependencies when testing a class, or I might have to implement methods that I don't need.

</details>

# Module 04: Refactoring and TDD

<details open>

### Reflection

1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

- Correctness:
    - Do I have enough functional tests? In this tutorial, I only make unit tests.
    - Am I testing all the edge cases thoroughly? In this tutorial, I make sure to test different scenarios, including happy and unhappy paths.
    - Do I have tests that check whether all my components fit together properly? I'm not sure about this because I only make unit tests.
- Maintainability:
    - Are my tests giving me the confidence to refactor my code fearlessly and frequently? Yes, I feel more confident to refactor my code after writing the tests because I know that if I break something, the tests will catch it.
    - Are my tests helping me to drive out a good design? Yes, I think my tests are helping me to drive out a good design because I have to think about how to test my code before I write it.
- Productive workflow:
    - Are my feedback cycles as fast as I would like them? Yes, I think my feedback cycles are fast because I can run my tests quickly every time I make a change.
    - Is there some way that I could write faster integration tests that would give me feedback quicker? In this tutorial, I only make unit tests, so I'm not sure about this.
    - Can I run a subset of the full test suite when I need to? Yes, I can run a subset of the full test suite when I need to.
    - Am I spending too much time waiting on test to run? No, I don't think I'm spending too much time waiting on tests to run.

I think the TDD flow is useful enough for me because it helps me to write better code and I feel more confident about the reliability of my code after writing the tests. However, I think I need to make more integration tests to check whether all my components fit together properly and to give me feedback quicker.

2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

- Fast: Yes, my tests are fast because I can run my tests quickly every time I make a change.
- Independent: Yes, my tests are independent because they don't depend on each other.
- Repeatable: Yes, my tests are repeatable because they produce the same results every time they run.
- Self-Validating: Yes, my tests are self-validating because they either pass or fail.
- Thorough: Yes, my tests are thorough because I make sure to test different scenarios, including happy and unhappy paths.

</details>