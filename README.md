# TicTacToe

A networked 2-player Tic-Tac-Toe game built in Java with a client-server architecture, custom graphics, and sound effects.

## Features

- **Client-server networking** — Two players connect over a local network via TCP sockets on port 9900
- **Custom graphics** — X/O symbols, win lines (horizontal, vertical, diagonal), win and game-over screens drawn with SimpleGraphics
- **Sound effects** — Win sound played on victory
- **Keyboard controls** — Players move a cursor around the board and place their symbol
- **Game rules engine** — Detects wins (rows, columns, diagonals) and draws

## Tech Stack

| | |
|---|---|
| Language | Java |
| Networking | Java Sockets (TCP) |
| Graphics | SimpleGraphics library |
| Audio | `javax.sound.sampled` |
| Build | Ant (`build.xml`) |
| IDE | IntelliJ IDEA |

## Project Structure

```
tictactoe/
├── ClientApp/
│   ├── src/org/academiadecodigo/loopeytunes/tictactoe/
│   │   ├── Client.java        # Entry point — connects to server
│   │   ├── Connection.java    # TCP client socket handling
│   │   ├── Field.java         # Game board rendering
│   │   ├── Cursor.java        # Player cursor movement
│   │   ├── GameKeyboard.java  # Keyboard input handler
│   │   ├── GameRules.java     # Win/draw detection
│   │   ├── Position.java      # Board position model
│   │   └── Sound.java         # Sound effect playback
│   ├── resources/             # Images and audio assets
│   └── build.xml
└── ServerApp/
    ├── src/org/academiadecodigo/loopeytunes/tictactoe/
    │   ├── Server.java            # Entry point — listens on port 9900
    │   └── ServerConnection.java  # Manages player connections and game flow
    └── build.xml
```

## Setup

### Prerequisites

- Java 8+
- IntelliJ IDEA (or any IDE supporting Ant builds)
- Two machines on the same local network (or localhost)

### 1. Clone the repo

```bash
git clone git@github.com:jovbcorreia/tictactoe.git
cd tictactoe
```

### 2. Start the Server

Open `ServerApp` in IntelliJ and run `Server.java`, or build with Ant:

```bash
cd ServerApp && ant run
```

### 3. Start two Clients

Open `ClientApp` in IntelliJ and run `Client.java` twice (on two machines or terminals).
Pass the server IP as an argument if needed:

```bash
java Client 192.168.1.14
```

Default server address is `192.168.1.14` — update `Client.java` to match your server IP.

## How to Play

- Use **arrow keys** to move the cursor around the board
- Press **Enter** to place your symbol (X or O)
- First player to get 3 in a row (horizontal, vertical, or diagonal) wins
- If all 9 squares are filled with no winner, it's a draw

