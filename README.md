# ADV E-Shop

Name: Fahmi Ramadhan<br>
NPM: 2206026473<br>
Class: Advanced Programming A<br>

# Module 01: Coding Standards

<details open>

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

<details open>

During the exercise, I addressed several code quality issues, such as:

- Removing the public modifier from files related to testing, as the best practice in testing is to use the default modifier.

- Removing field injection (@Autowired) and use constructor injection instead.

Looking at the CI/CD workflows, I believe the current implementation has indeed achieved Continuous Integration and Continuous Deployment. Firstly, the CI pipeline triggers on every push to the repository, ensuring that changes are integrated frequently. Secondly, automated tests are run as part of the pipeline to validate the code changes. Lastly, the CD pipeline deploys the code to the PaaS environment automatically upon successful testing, enabling continuous deployment of new features and fixes. Overall, the process ensures that code changes are quickly validated, integrated, and deployed, meeting the principles of CI/CD.

</details>