# 🤖 Agent-Based Simulation: Social vs. Antisocial Dynamics

This project simulates a grid-based world populated by two types of agents — **Social Agents** and **Anti-Social Agents** — that interact with each other based on proximity rules. Built in Java, the simulation explores emergent behavior, group formation, and system dynamics through local decision-making.

---

## 🎯 Project Objective

The goal of this simulation is to model **local interactions and behavioral tendencies** between agents within a bounded environment. The simulation explores:

- How agents move and interact based on nearby neighbors
- How differing behavior (social vs antisocial) leads to clustering, isolation, or balance
- The impact of randomized initialization and step-wise updates

---

## 🛠 Features

- ✅ Implementation of two agent types: `SocialAgent` and `AntiSocialAgent`  
- 🎨 Real-time visualization with Java Swing in `LandscapeDisplay`  
- 🔁 Time-step based updates using `AgentSimulation`  
- 🧱 Custom data structures: `LinkedList`, `Landscape`, `Cell`  
- 🧪 Unit tests for key behaviors and logic using JUnit  
- ⚙️ Modular and extensible OOP design

---

## 📁 File Structure

```
Project3/
├── Project3Report.pdf             # Detailed project write-up
├── src/                          # Primary source code folder
│   ├── Agent.java
│   ├── SocialAgent.java
│   ├── AntiSocialAgent.java
│   ├── Landscape.java
│   ├── LandscapeDisplay.java
│   ├── AgentSimulation.java
│   ├── LinkedList.java
│   ├── AgentTests.java
│   ├── LandscapeTests.java
│   ├── LinkedListTests.java
│   ├── UpdateStateTests.java
│   └── AgentSimulationTests.java
├── extension/                    # Alternative implementation or enhancements
    ├── (Same structure as src/)
```

---

## 🧪 How to Run the Simulation

### Step 1: Compile the source files

```bash
cd src
javac *.java
```

### Step 2: Run the simulation

```bash
java AgentSimulation
```

This will open a graphical window where agents interact in real-time.

---

## 🧪 Tests

This project includes unit tests for:

- Agent behavior and neighbor counting
- Agent movement and state transitions
- Landscape structure and agent placement
- LinkedList functionality
- Simulation loop and updates

To run the tests, use any Java IDE with JUnit support or compile and run the `*Tests.java` files manually.

---

## 🧠 Concepts Explored

- Agent-Based Modeling (ABM)
- Emergent behavior from local rules
- Object-Oriented Programming (OOP)
- GUI programming with Java Swing
- Simulation and iteration
- Custom data structures and iterators

---

## 📄 Project Report

The project includes a formal write-up of the experiment, modeling choices, behavioral observations, and conclusions.

📘 [Project3Report.pdf](Project3Report.pdf)

---

## 👨‍💻 Author

**Azeem Gbolahan**  
Student of Computer Science & Economics  
Colby College

---

## 🌐 Let’s Connect

Interested in simulation design, behavioral modeling, or educational tools? Feel free to fork the repo, reach out, or suggest improvements!
