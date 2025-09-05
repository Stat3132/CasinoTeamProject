# Variables in Vegas - Casino Team Project

A Java-based casino simulation game featuring multiple popular casino games, player management, and AI functionality.

## 🎰 Overview

**Variables in Vegas** is a console-based casino application that allows players to experience various casino games including BlackJack, Slot Machine, Roulette, and Horse Racing. The application features a comprehensive player management system and AI opponents for an engaging gaming experience.

## 🎮 Available Games

- **BlackJack** - Classic card game where players try to get as close to 21 as possible without going over
- **Slot Machine** - Spin the reels and match symbols including fruits and lucky 7s
- **Roulette** - Bet on numbers and colors in this classic casino wheel game  
- **Horse Racing** - Place bets on horses and watch them race to victory

## 🏗️ Project Structure

The project follows the Model-View-Controller (MVC) architectural pattern:

```
src/main/java/org/example/
├── Controller/          # Game logic and flow control
│   ├── Casino.java     # Main casino interface
│   ├── Controller.java # Primary game controller
│   ├── BlackJack.java  # BlackJack game implementation
│   ├── SlotMachine.java# Slot machine game logic
│   ├── Roulette.java   # Roulette game implementation
│   └── HorseRace.java  # Horse racing game logic
├── Model/              # Data models and business logic
│   ├── Player.java     # Player data model
│   ├── CasinoMembers.java # Casino membership system
│   ├── CasinoAI.java   # AI opponent logic
│   └── Horse.java      # Horse racing entities
├── View/               # User interface components
│   └── UI.java         # Console-based user interface
├── UTIL/               # Utility classes and helpers
│   ├── Card.java       # Playing card implementation
│   ├── CardValue.java  # Card value enumeration
│   ├── Suit.java       # Card suit enumeration
│   ├── Console.java    # Console output utilities
│   ├── Name.java       # Name generation utilities
│   ├── HorseColor.java # Horse color enumeration
│   └── ProbabilityForValue.java # Probability calculations
└── Main.java           # Application entry point
```

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6 or higher

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Stat3132/CasinoTeamProject.git
   cd CasinoTeamProject
   ```

2. **Compile the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

### Alternative Running Method

You can also build a JAR file and run it directly:

```bash
mvn clean package
java -jar target/CasinoTeamProject-1.0-SNAPSHOT.jar
```

## 🎯 How to Play

1. **Starting the Game:**
   - Launch the application
   - Create your first user account by entering a username
   - Navigate through the casino menu system

2. **Game Features:**
   - Multiple casino games to choose from
   - Player statistics and money management
   - AI opponents for competitive gameplay
   - Betting system with virtual currency

3. **Navigation:**
   - Use console input to make selections
   - Follow on-screen prompts for each game
   - Manage your virtual casino account

## 🛠️ Development

### Building the Project

```bash
# Clean and compile
mvn clean compile

# Run tests (if available)
mvn test

# Package the application
mvn package
```

### Project Configuration

The project uses Maven for dependency management and build automation. Key configuration files:

- `pom.xml` - Maven project configuration
- Java source code located in `src/main/java/`

## 🎲 Game Details

### BlackJack
- Classic 21 card game
- Player vs. dealer gameplay
- Card counting and probability calculations
- Hit, stand, and bust mechanics

### Slot Machine
- 16 different symbols including fruits and lucky 7s
- 4-reel spinning mechanism
- Payout system based on symbol combinations
- Random number generation for fair play

### Roulette
- Traditional casino roulette wheel
- Multiple betting options
- Number and color betting
- Probability-based outcomes

### Horse Racing
- Multiple horses with different attributes
- Betting system on race outcomes
- Dynamic race simulation
- Various horse colors and characteristics

## 👥 Team Development

This project demonstrates:
- Object-oriented programming principles
- MVC architectural design
- Team collaboration and version control
- Java best practices and conventions

## 🤝 Contributing

This is a team project for educational purposes. When contributing:

1. Follow the existing code style and structure
2. Maintain the MVC architecture pattern
3. Add appropriate comments and documentation
4. Test your changes thoroughly

## Author(s)

- [**Ethan Townsend (snxethan)**](www.ethantownsend.dev)
- Diego Perez Benitez
- Antonio Bateman

**Enjoy playing Variables in Vegas! 🎰🃏🎲**
