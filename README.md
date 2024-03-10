# PSK-Individual tasks
Task repository for Vilnius University course PSK (Programų sistemų kūrimas) with Java.
## How to run
This is a `Jakarta EE` project with `H2` database connection.
There needs to be an application server set up on your local machine (I used `WildFly 31.0.1.Final` with `IntelliJ IDEA`).
If you have these set up and ready to go, then you can safely launch the project.
## How to generate MyBatis files
In your Run configuration you need to add an addition Maven build step goal `mybatis-generator:generate`.