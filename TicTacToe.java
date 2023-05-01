import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    // global array list for player position to be called in
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();

    // global array list for CPU position to be called in
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    // TicTacToe Game
    public static void main(String args[]) {
        // the game board
        char gameBoard[][] = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '|', '-', '|', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '|', '-', '|', '-' },
                { ' ', '|', ' ', '|', ' ' }
        };
        printGameBoard(gameBoard);

        // Loops the game with the While Loop
        while (true) {
            // User can select their position on the 2D array with the scanner input
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your pick: ");
            int playerPos = scan.nextInt();

            while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPosition)) {
                System.out.println("Position Taken: Try again");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "Player");

            // pass the result string to the function checkWinner
            String result = checkWinner();
            if (result.length() > 0) {
                // print out the results once either won, tied or if CPU has won
                System.out.println(result);
                break;
            }

            // The CPU will randomly pick a selection from the 2D array
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "CPU");

            printGameBoard(gameBoard);

            // pass the result string to the function checkWinner
            result = checkWinner();
            if (result.length() > 0) {
                // print out the results once either won, tied or if CPU has won
                System.out.println(result);
                break;
            }

        }
    }

    // Prints the game board parameter
    public static void printGameBoard(char gameBoard[][]) {
        for (char row[] : gameBoard) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public static void placePiece(char gameBoard[][], int pos, String user) {
        char symbol = ' ';
        if (user.equals("Player")) {
            symbol = 'X';
            playerPosition.add(pos);
        } else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPosition.add(pos);
        }

        switch (pos) { // row | col
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        // Top row
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List crossLeftDown = Arrays.asList(1, 5, 9);
        List croosLeftUp = Arrays.asList(7, 5, 3);

        // check the following list of winning cordinates
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(crossLeftDown);
        winningConditions.add(croosLeftUp);

        for (List l : winningConditions) {
            if (playerPosition.containsAll(l)) {
                return "Congrats you've won";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU has won, Try Again";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "It's a tie";
            }
        }

        return "";
    }

}