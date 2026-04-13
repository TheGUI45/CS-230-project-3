# Draw It or Lose It

## CS 230 Project Software Design Template

**Version:** 1.2  
**Author:** Nate Cowart  
**Company:** Creative Technology Solutions

---

## Table of Contents

1. Executive Summary
2. Document Revision History
3. Requirements
4. Design Constraints
5. System Architecture View
6. Domain Model
7. Evaluation
8. Recommendations

---

## Document Revision History

| Version | Date | Author | Comments |
|---|---|---|---|
| 1.0 | March 16, 2026 | CTS Technology Consultant | Initial software design document for the web-based version of Draw It or Lose It |
| 1.1 | March 30, 2026 | CTS Technology Consultant | Expanded platform evaluation and recommendations for Project Two rubric coverage |
| 1.2 | April 13, 2026 | Nate Cowart | Finalized Project Three recommendations, best practices, and submission formatting |

---

## Executive Summary

The Gaming Room is expanding *Draw It or Lose It* from a single-platform Android application into a web-based game that can support multiple platforms and a broader user base. This change creates a need for a design that is scalable, maintainable, and flexible enough to support distributed access while still preserving the original game experience. The immediate software challenge is to build a reliable domain model that can manage games, teams, and players while enforcing core business rules such as unique names, consistent object identifiers, and controlled access to shared game data.

The proposed solution is to implement the application in Java using object-oriented design principles and established software design patterns. Java is a strong fit because it is portable across operating systems, supports server-side application development, and provides the structure needed for long-term maintainability. The design centers on a shared `Entity` base class and specialized `Game`, `Team`, and `Player` classes, while a singleton `GameService` manages object creation and shared state. Iterator-based searches are used to verify uniqueness before creating new objects, which ensures that game and team names remain distinct and that the system behaves predictably as the application grows.

From a client perspective, the most important point is that this design provides a stable software foundation before infrastructure decisions are finalized. By separating domain logic from platform-specific concerns, The Gaming Room will be able to move forward with development in a structured way, reduce the risk of rework, and prepare the application for future deployment in a distributed web environment.

---

## Requirements

The client has defined several business and technical requirements that guide the software design for this project.

From a business standpoint, the application must allow a game to include one or more teams, and each team must include multiple players. The system must also ensure that game names are unique so users can tell whether a name is already in use. Team names must follow the same rule to avoid confusion during gameplay and account creation.

From a technical standpoint, the client requires that only one instance of the game service exist in memory at any given time. The application must also assign unique identifiers to every game, team, and player object. These identifiers support consistency, reduce duplication, and create a dependable way to distinguish objects throughout the application lifecycle. In addition, the application should be readable, modular, and easy to extend as the project transitions from a classroom prototype into a practical web-based product.

---

## Design Constraints

One of the main design constraints is that the application is being developed for a web-based distributed environment rather than for a single mobile device. That means the software must be designed with platform independence in mind. Java is a practical choice because it can run in multiple operating environments and supports the development of server-side systems that can communicate with different client platforms.

Another constraint is data consistency. In a distributed environment, multiple users may try to create or join games, teams, or player sessions at the same time. Even though this project uses an in-memory implementation, the software still needs to model controlled object creation carefully so that duplicate names and conflicting identifiers do not occur. This is why the design places responsibility for instance creation in specific methods rather than allowing objects to be created without validation.

The requirement that only one service instance exist in memory also introduces an architectural constraint. To satisfy that requirement, the `GameService` class uses the singleton pattern, which restricts construction and provides a single access point to the service. This is effective for the project scope, though in a production environment that responsibility would likely shift to a centralized persistence or application service layer.

Finally, the project must remain understandable to both technical reviewers and business stakeholders. The code and documentation therefore need to favor clear naming, straightforward logic, and a structure that can be explained without unnecessary complexity. This affects implementation choices by encouraging a clean class hierarchy and focused method responsibilities.

---

## System Architecture View

The current design represents the software architecture at the application layer and focuses on the domain subsystem that manages game state. The architecture is intentionally lightweight because the project scope is centered on the foundational software model rather than a full deployment environment. At this stage, the design consists of a Java application with a service layer and a set of domain classes that represent the core business objects.

The `ProgramDriver` class serves as the entry point for testing and demonstration. It interacts with `SingletonTester`, which confirms that the singleton pattern is functioning correctly, and with `GameService`, which acts as the central manager for game-related operations. `GameService` is responsible for maintaining the collection of game objects and distributing unique identifiers for games, teams, and players.

Below the service layer are the domain entities: `Game`, `Team`, and `Player`. These classes inherit from `Entity`, which stores shared attributes such as object ID and name. The relationships are hierarchical. A game contains teams, and each team contains players. This design maps directly to the business structure of the application and keeps the model intuitive.

In a future web deployment, this domain layer would sit behind a web or API layer and interact with persistent storage rather than using memory only. Even so, the present architecture is appropriate because it isolates the business rules cleanly and provides a strong base for later expansion.

---

## Domain Model

The UML diagram models the essential objects required to support *Draw It or Lose It*. At the top of the inheritance structure is the `Entity` class, which stores the common attributes `id` and `name` and provides shared behavior such as accessors and a standard string representation. This demonstrates inheritance because the `Game`, `Team`, and `Player` classes all derive from `Entity` rather than duplicating the same fields and methods independently.

The `Game` class represents a single game instance and contains a collection of `Team` objects. This reflects a one-to-many relationship because one game may include multiple teams. The `Team` class, in turn, contains a collection of `Player` objects, which is also a one-to-many relationship because each team may include multiple players. These relationships directly support the client requirement that a game can include one or more teams and that each team can include multiple players.

The `GameService` class manages the overall collection of games and tracks the next available identifiers for each type of domain object. This class demonstrates the singleton design pattern through a private constructor, a private static instance reference, and a public `getInstance()` method. As a result, only one service object can be created in memory at a time, which satisfies one of the client's core technical requirements.

The design also uses the iterator pattern in methods such as `addGame()`, `getGame()`, `addTeam()`, and `addPlayer()`. Each method checks an existing collection before creating a new object. This ensures that duplicate names are not introduced when a game, team, or player already exists. Encapsulation is present throughout the model because each class manages its own data and exposes behavior through controlled methods. Together, these principles make the system easier to understand, extend, and maintain.

---

## Evaluation

To determine an appropriate platform strategy for the web-based version of *Draw It or Lose It*, each major operating environment was evaluated in terms of server-side suitability, client-side compatibility, and development tooling. The goal of this review is to identify the strengths, weaknesses, and tradeoffs of each platform in a distributed environment where a central web application must support many simultaneous players.

| Development Requirements | Mac | Linux | Windows | Mobile Devices |
|---|---|---|---|---|
| Server Side | macOS can host Java-based web services because it is Unix-based and supports standard server software, but it is rarely chosen for large-scale production hosting. Hardware costs are typically higher, server hosting options are less common than Linux, and Apple licensing ties the operating system closely to Apple hardware. It is suitable for development and small internal hosting, but it is not the strongest production server choice for a game expected to scale to thousands of players. | Linux is the strongest server-side platform for this application. It supports Java application servers, containers, load balancing, cloud hosting, and database services very well. Linux distributions are commonly available with no operating system licensing fee, which lowers deployment cost, and they are widely used for high-availability web applications. Its main weakness is that the team must be comfortable with Linux administration and deployment practices, but its scalability, stability, and cost profile make it the best fit for hosting the game. | Windows Server supports Java web applications and can host the game successfully with IIS, Java runtime tools, and common enterprise infrastructure. Its advantage is familiarity in many business environments and strong vendor tooling. Its weakness is cost because Windows Server licenses and related server management overhead are typically higher than Linux. It is a valid hosting option, but for this specific application it is less cost-effective than Linux for web-scale deployment. | Mobile devices are not an appropriate primary server platform. They do not provide a practical server-based deployment method for a scalable web application, have limited processing and memory resources, and are not designed for reliable 24/7 hosting. They also offer no realistic enterprise hosting model for thousands of concurrent players. As a result, mobile platforms should be treated strictly as clients rather than as server hosts. |
| Client Side | macOS is a strong desktop client platform because users can access the game through modern browsers such as Safari, Chrome, and Firefox. To support macOS well, the application must follow web standards, use responsive HTML, CSS, and JavaScript, and be tested across browsers for rendering consistency. The main weakness is its smaller user base compared with Windows and mobile, but it remains important for browser compatibility and quality assurance. | Linux clients can also use the browser-based game effectively through Chrome, Firefox, and other standards-compliant browsers. Supporting Linux mainly requires adherence to web standards and cross-browser testing rather than Linux-specific application code. Its weakness is that consumer market share is smaller, so it is less of a business priority than Windows or mobile, but compatibility is still achievable with a single web codebase. | Windows is the most important traditional desktop client platform because it has the broadest desktop reach and works well with all major browsers. The development process must ensure that the user interface is responsive, keyboard and mouse interactions are consistent, and major browsers are tested regularly. Windows adds little platform-specific development burden in a browser-based approach, which makes it a practical target for wide user adoption. | Mobile platforms are essential because the client wants to move beyond Android and support both Android and iOS users through a browser-based interface. This requires responsive page layouts, touch-friendly controls, flexible image scaling, efficient network usage, and testing across mobile browsers and screen sizes. Supporting mobile browsers increases development time, QA effort, and UI design complexity, but it avoids the need to build separate native Android and iOS applications. |
| Development Tools | macOS supports Java development very well with IntelliJ IDEA, Eclipse, Visual Studio Code, Maven, Git, browser developer tools, and testing frameworks. It is an excellent workstation for developers, especially for teams that prefer Unix-based tooling. Tool licensing can remain low if the team uses open-source tools, although commercial IDE editions may add cost. macOS does not usually require a separate development team, but a team using Macs should still include web UI, back-end Java, and QA skills. | Linux supports the same Java, web, and testing toolchain and also matches common production hosting environments closely. This reduces environment mismatch between development and deployment. Most Linux development tools are free or open source, which keeps licensing costs low. The main impact on the team is the need for Linux command-line and deployment knowledge, but it usually does not require a separate team if the developers already have Java and web experience. | Windows provides strong support for Java and web development through IntelliJ IDEA, Eclipse, Visual Studio Code, Maven, Git, and browser testing tools. It is often the most accessible platform for classroom and enterprise teams because of familiarity and broad vendor support. Licensing costs may be higher if the organization uses paid Windows editions or commercial IDE licenses, but many capable tools are still available at no cost. A Windows-based team can build the application successfully, though the team must still have front-end, back-end, testing, and deployment skills. | Mobile devices are not the primary development platform for the Java server application, but they are important test targets. The team will need browser-based testing tools, device emulators, and access to real phones and tablets to verify responsive behavior. This does not usually require a fully separate mobile development team if the solution remains browser-based, but it does require additional QA time and user experience expertise to ensure the interface performs well on touch devices. |

Overall, the evaluation shows that Linux is the best server platform because it provides the most practical combination of scalability, stability, and low operating cost. Windows and macOS remain useful development and desktop client environments, while mobile platforms are critical client targets rather than hosting targets. From a development perspective, the most efficient strategy is to build a single Java-based back end with a responsive web front end, allowing one cross-functional team to support all client platforms instead of maintaining separate native application teams. This approach lowers total development cost and maintenance time, but it requires deliberate browser testing, responsive interface design, and team expertise in Java, web development, QA, and deployment.

---

## Recommendations

### Operating Platform

The strongest recommendation for *Draw It or Lose It* is a Linux-based server platform, preferably an enterprise-ready distribution such as Ubuntu Server or Red Hat Enterprise Linux running a Java web application stack. Linux is the best fit because it is stable under continuous server workloads, scales well in virtual machines and containers, is widely supported by cloud providers, and avoids the higher licensing cost normally associated with Windows Server. This choice also supports the client goal of reaching many computing environments because the server can expose the application through a browser-based interface while users connect from Windows, macOS, Linux, Android, or iOS devices without needing a separate native build for each platform.

### Operating System Architectures

Linux uses a multiuser, multitasking architecture with protected memory, process isolation, and a monolithic kernel design. In practical terms, that means the server can run the Java Virtual Machine, web server processes, monitoring tools, and database connectors at the same time while keeping each process isolated from others. Linux also supports standard TCP/IP networking, thread scheduling, virtual memory, user and group permissions, and container platforms such as Docker, all of which are valuable for a modern web-hosted game. This architecture pairs well with a three-tier application design: a presentation layer in the browser, an application layer that contains the Java game logic, and a data layer that stores persistent information. That separation follows a best-practice approach because it makes maintenance easier and allows CTS to scale the web and application layers independently from the database layer as the number of players grows.

### Storage Management

PostgreSQL is the recommended storage management system. It is a reliable relational database that provides structured tables, ACID transactions, indexing, backup support, and strong concurrent access controls. Those features match the data model of the game because games, teams, players, user accounts, and match history all have clear relationships. In the classroom version of the project, `GameService` stores data in memory, but that approach would lose all information when the application stops and would not work well once multiple server instances are introduced. A PostgreSQL database on Linux would provide persistent storage for account data, active game metadata, completed game results, audit records, and administrative reporting. Best practices for this environment include normalized tables to reduce duplication, indexes on frequently searched fields such as game and team names, automated backups, replication for resilience, and transaction controls to preserve consistency when many users are active at once.

### Memory Management

The Linux and Java combination offers several useful memory management techniques. Linux uses virtual memory to map each process into its own address space, which helps prevent one process from corrupting another. The operating system can also use paging and swap space to manage temporary memory pressure, although the preferred production practice is to size RAM so the application does not rely heavily on swapping. On the Java side, memory is divided into areas such as the heap, stack, and metaspace, and the Java runtime uses garbage collection to reclaim objects that are no longer needed. That is helpful for *Draw It or Lose It* because game sessions will create short-lived objects such as requests, session data, and temporary state during gameplay. Modern garbage collectors reduce pause times and improve responsiveness, while thread stacks and managed heap allocation make the application easier to maintain than one written in a manually managed language. Best practices also include setting JVM heap limits, monitoring memory usage, limiting object retention in shared collections, and using performance profiling to identify leaks or excessive allocation.

### Distributed Systems and Networks

The recommended distributed design is a centralized web application that exposes services over HTTPS to many different client platforms. A browser on a phone, tablet, laptop, or desktop would connect through the Internet to a load balancer or reverse proxy, which would then route traffic to one or more Linux application servers running the Java service. Those application servers would depend on shared services such as the PostgreSQL database, authentication services, and possibly a distributed cache if the workload grows. This model allows the same business logic to be reused for all supported clients, but it also introduces dependencies that must be managed carefully. If network connectivity is slow or interrupted, players may see lag, failed requests, or dropped sessions. If the database is unavailable, the game may not be able to create teams, save progress, or validate unique names. Best practices for this architecture include health checks, connection timeouts, safe retry logic, centralized logging, monitoring dashboards, redundant network paths, and graceful degradation so that one failed component does not bring down the entire service.

### Security

Linux provides a strong security foundation when paired with standard web security practices. User information should be protected in transit with TLS over HTTPS and protected at rest through encrypted database storage and secure backups. User authentication should store passwords as salted hashes rather than plain text, and the application should enforce secure session handling, role-based authorization, and server-side validation of all input. On the operating system side, Linux supports least-privilege service accounts, file permissions, firewall controls, package patching, and mandatory access controls such as SELinux or AppArmor. These capabilities help limit damage if a service is compromised. Additional protections should include input validation to prevent SQL injection and cross-site scripting, rate limiting for login endpoints, audit logging for account and gameplay changes, and routine updates to the Java runtime and server packages. Together, these controls protect personal data, preserve game integrity, and support safe communication between platforms.

Overall, Linux with a Java-based web architecture and PostgreSQL storage is the best recommendation for The Gaming Room. This combination gives the client a cost-effective server platform, a clear architectural model, dependable storage and memory management, cross-platform distributed access, and strong built-in security options. It also creates a practical path from the current classroom prototype to a production-ready environment that can support future growth.