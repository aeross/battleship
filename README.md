# Hello, Battleship!

I've been wanting to do this project for a long time. And finally, as of 03/01/2024, I've finally gotten around to start doing it!

## Demo

https://drive.google.com/file/d/185EiRPgYC9q_BgpuR9LCrD5KKr9bxhJR/view?usp=sharing

## What's this about?

Well, this is a game where you play single-player battleship. Instead of the usual guess-where-your-opponents-ships-are, the ships will be placed randomly, hidden from you and you have to guess where the hidden ships are with the least number of guesses possible.

### Run it in your local machine

1. **Boot up the server**

Go to `/battleship/src/main/java/game/battleship/battleship` and run `BattleshipApplication.java`. Make sure you have Java 21 installed.

2. **Boot up the client**

Go to `/client`, run `npm install` and then `npm run dev`.

3. **Play the game**

Head over to `http://localhost:5173` to play the game.

## Tech Stack

- Server: REST API with Java and Spring Boot
- Client: JavaScript, HTML/CSS, Tailwind CSS, React
