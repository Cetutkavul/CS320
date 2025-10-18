# CS-320 Software Testing, Automation, and Quality Assurance

## Overview
This repository contains my work from **CS-320: Software Testing, Automation, and Quality Assurance** at **Southern New Hampshire University**.  
The included artifacts demonstrate my ability to apply rigorous testing strategies, analyze software requirements, and ensure code reliability and security through the use of **JUnit testing** within the **Eclipse IDE**.

This portfolio entry includes:
- **Contact Service** (Contact.java, ContactService.java, ContactTest.java, ContactServiceTest.java)
- **Task Service** (Task.java, TaskService.java, TaskTest.java, TaskServiceTest.java)
- **Appointment Service** (Appointment.java, AppointmentService.java, AppointmentTest.java, AppointmentServiceTest.java)
- **Summary and Reflections Report** (from Project Two)

Together, these files highlight my understanding of unit testing principles, validation techniques, and disciplined quality assurance practices.

---

## How can I ensure that my code, program, or software is functional and secure?
To ensure functionality and security, I rely on systematic testing and validation throughout the development process. In this course, I implemented **JUnit test suites** in Eclipse to verify that every method behaved as expected under both valid and invalid conditions.  
Each test case confirmed data integrity, validated boundary conditions, and ensured that exceptions were handled safely. For example, tests for phone numbers, task descriptions, and appointment dates confirmed that invalid inputs were rejected, protecting the system from inconsistent or unsafe data.

Security and stability are achieved by maintaining **strong input validation**, **data encapsulation**, and **consistent exception handling**. By testing every data constraint (length, null checks, and immutability), I prevented logical vulnerabilities that could lead to data corruption or application failure. The use of automated unit tests also ensured that any future code modifications can be verified quickly and reliably through regression testing.

---

## How do I interpret user needs and incorporate them into a program?
Interpreting user needs begins with analyzing requirements and converting them into clear, testable behaviors. During development, I reviewed the **functional requirements** that defined field limits, validation rules, and error-handling expectations.  
For example, the requirement that contact phone numbers must be exactly ten digits was translated into both code logic and corresponding JUnit tests. This ensured that the software aligned with user expectations from the start.

I also designed my tests to simulate realistic user interactions. When a user attempts to create an appointment in the past or with missing details, the system correctly rejects that input. By thinking from the end user’s perspective, I created software that feels intuitive and reliable, reducing the risk of unexpected application errors in production.

---

## How do I approach designing software?
My software design process begins with **modularization** and **clarity**. Each feature, such as Contact, Task, and Appointment management, is structured into its own class and service layer.  
This separation of concerns makes the code easier to maintain, test, and extend. I use **object-oriented design principles** such as encapsulation and immutability to protect data integrity and minimize dependencies between components.

After designing the structure, I apply **test-driven development (TDD)** principles, where test cases are written alongside or before implementation. This ensures that my design remains aligned with both functional and technical requirements. Using Eclipse’s integrated JUnit and EclEmma coverage tools, I verify coverage and efficiency while maintaining clean, reusable code.  

Overall, my approach balances planning, iterative testing, and reflection. By combining these practices, I ensure that my software is not only functional but also maintainable, efficient, and professional.

---

## Tools and Technologies
- **Programming Language:** Java  
- **Testing Framework:** JUnit 5  
- **IDE:** Eclipse  
- **Development Environment:** Windows 11  

---

## Reflection Summary
Through this course, I strengthened my understanding of how testing drives quality software development. By applying consistent unit testing, boundary testing, and validation, I learned how to identify and correct logic errors early in the process.  
This experience also reinforced the importance of designing software around clear requirements, applying defensive programming techniques, and maintaining a disciplined commitment to testing and documentation.

The skills I developed here—writing efficient tests, validating user requirements, and maintaining high code quality—will continue to support me as I grow as a software engineer focused on reliability, maintainability, and security.

---

## Author
**Misty Tutkavul**  
Date: October 2025  

