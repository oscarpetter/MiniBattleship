
import java.util.Scanner;

public class MiniBattleship {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int boardSize = scan.nextInt();
        int numberOfShips = scan.nextInt();
        char[][] board = new char[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            String boardLine = scan.next();
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = boardLine.charAt(j);
            }
        }

        int[] shipSizes = new int[numberOfShips];
        for (int i = 0; i < numberOfShips; i++) {
            shipSizes[i] = scan.nextInt();
        }

        int placements = placements(board, shipSizes);
        System.out.println(placements);
    }
    private static int placements(char[][] board, int[] shipSizes) {
        int index = -1;
        return countValidPlacements(board, shipSizes, index);
    }

    private static void printBoard(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                System.out.print(board[r][c] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int countValidPlacements(char[][] board, int[] shipSizes, int index) {
        int length = board.length;
        index++;
        if (index == shipSizes.length) {
            if (findAnyX(board)) {
                return 0;
            }
            System.out.println();
            printBoard(board);
            return 1;
        }
        int shipSize = shipSizes[index];
        int possiblePlacements = 0;
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                // måste ha med shipSize till canAddShipHorizontal/Vertical

                if (canAddShipHorizontal(board, shipSize, r, c)) {
                    char[][] boardCopy = addShipHorizontal(board, shipSize, r, c);
                    // detta är en valid placement
                    possiblePlacements += countValidPlacements(boardCopy, shipSizes, index);
                }
                if (canAddShipVertical(board,shipSize, r, c)) {
                    char[][] boardCopy = addShipVertical(board, shipSize, r, c);
                    // detta är en valid placement
                    possiblePlacements += countValidPlacements(boardCopy, shipSizes, index);
                }

            }
        }
        return possiblePlacements;
    }

    private static boolean findAnyX(char[][] board) {
        int boardLenght = board.length;
        for (int r = 0; r < boardLenght; r++) {
            for (int c = 0; c < boardLenght; c++) {
                if (board[r][c] == 'X') {
                    // unused test placement found
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canAddShipVertical(char[][] board,int shipSize, int row, int col) {
        int boardLenght = board.length;
        if (row + shipSize > boardLenght) {
            return false;
        }
        for (int i = row; i < row + shipSize; i++) {
            if (board[i][col] != '.' && board[i][col] != 'X') {
                return false;
            }
        }
        return true;
    }

    private static char[][] addShipVertical(char[][] board, int shipSize, int row, int col) {
        int RADIX = 10;
        char[][] boardCopy = cloneArray(board);
        for (int i = row; i < row + shipSize; i++) {
            boardCopy[i][col] = Character.forDigit(shipSize, RADIX);
        }
        return boardCopy;
    }

    private static boolean canAddShipHorizontal(char[][] board, int shipSize, int row, int col) {
        int boardLenght = board.length;

        if (col + shipSize > boardLenght) {
            return false;
        }
        for (int i = col; i < col + shipSize; i++) {
            if (board[row][i] != '.' && board[row][i] != 'X') {
                return false;
            }
        }
        return true;
    }


    private static char[][] addShipHorizontal(char[][] board, int shipSize, int row, int col) {
        int RADIX = 10;
        char[][] boardCopy = cloneArray(board);
        for (int i = col; i < col + shipSize; i++) {
            boardCopy[row][i] = Character.forDigit(shipSize, RADIX); //(shipSize + 65); // int = test valid ship placement
        }
        return boardCopy;
    }

    private static char[][] cloneArray(char[][] array) {
        int arrayLength = array.length;
        int ship = array[0].length;
        char[][] copy = new char[arrayLength][ship];
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < ship; j++) {
                copy[i][j] = array[i][j];
            }
        }
        return copy;
    }
}
