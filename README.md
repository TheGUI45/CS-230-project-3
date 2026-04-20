# CS 230 Portfolio Submission

## Repository Link

https://github.com/TheGUI45/CS-230-project-3

## Included Artifact

This repository includes the completed software design document for The Gaming Room:

- [Software Design Document.md](./Software%20Design%20Document.md)

## Reflection

The Gaming Room was the client for this project. They wanted a web-based design for their game, *Draw It or Lose It*, so that the application could move beyond a single Android platform and support multiple users across different operating systems and devices. The software needed to manage games, teams, and players while enforcing business rules such as unique naming, controlled object creation, and a single shared service instance.

One of the strongest parts of my work on this documentation was the way I connected technical decisions to the client's needs. I clearly explained why Java, object-oriented design, and the singleton pattern were appropriate for the project, and I organized the document so both technical reviewers and business stakeholders could follow the reasoning. I also did a strong job comparing operating platforms and recommending a practical production direction based on cost, scalability, and maintainability.

Working through the design document was helpful because it forced me to think about the software before writing code. Defining the requirements, domain model, and architecture first made it easier to implement the classes and relationships in code. The document gave me a roadmap for how `Entity`, `Game`, `Team`, `Player`, and `GameService` should interact, which reduced guesswork during development and helped keep the implementation aligned with the original requirements.

If I could revise one part of this work, I would expand the design further around persistence and concurrency. The current document explains the in-memory service model well, but I would improve it by adding more detail about how a production system would handle database persistence, concurrent users, and API-level communication in a web environment. That would make the transition from prototype to deployment more complete.

I interpreted the user's needs by focusing on both the gameplay structure and the long-term goal of cross-platform access. The client needed a system that preserved the core game rules while also preparing for distributed use, so I designed the software around reusable domain objects, unique identifiers, and centralized service control. Considering user needs is important because software is only successful if it solves the real problem the client and end users have. A technically correct design that ignores usability, scalability, or business rules would not meet the client's expectations.

My approach to software design was to start with requirements analysis, translate those requirements into a domain model, and then evaluate technical options before making recommendations. I used object-oriented principles, design patterns, and platform comparison as the main strategies for making decisions. In the future, I would use the same approach for similar projects: define the requirements first, model the system clearly, evaluate tradeoffs early, and document design decisions so the implementation has a solid foundation.
