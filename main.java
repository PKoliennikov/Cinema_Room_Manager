
package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] cinema = new String[rows][seats];
        fillTheCinema(cinema,rows,seats);
        engine(scanner, cinema, rows, seats);
    }

    private static void buyATicket(Scanner scanner, String[][] cinema) {
        System.out.println();
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (cinema.length < rowNumber || cinema[0].length < seatNumber){
            System.out.println("Wrong input!");
            buyATicket(scanner, cinema);
        } else if (cinema[rowNumber - 1][seatNumber - 1].equals("B")){
            System.out.println("That ticket has already been purchased!");
            buyATicket(scanner, cinema);
        } else {
            changeSeatBusy(cinema, rowNumber, seatNumber);
            ticketPrice(cinema, rowNumber);
        }
    }

    private static void engine(Scanner scanner, String[][] cinema, int a, int b) {
        int x;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            x = scanner.nextInt();
            switch (x) {
                case 1 -> printCinema(cinema);
                case 2 -> buyATicket(scanner, cinema);
                case 3 -> statistics(a, b, cinema);
            }
        } while (x != 0);
    }

    private static void statistics(int rows, int seats, String[][] cinema) {
        System.out.println("Number of purchased tickets: " + checkSeatBusy(cinema));
        System.out.printf("Percentage: %.2f%%\n", countPercentage(rows, seats, cinema));
        System.out.println("Current income: $" + currentIncome(rows, seats, cinema));
        System.out.println("Total income: $" + totalIncome(rows, seats));
    }

    private static int currentIncome(int rows, int seats, String[][] cinema) {
        int income = 0;
        for (int i = 0; i < cinema.length; i++) {
            for ( int j = 0; j < cinema[i].length; j++) {
                if (cinema[i][j].equals("B")) {
                    if (rows * seats > 60) {
                        if (rows / 2 > i) {
                            income += 10;
                        } else {
                            income += 8;
                        }
                    }  else {
                        income += 10;
                    }
                }
            }
        }
        return income;
    }

    private static float countPercentage(int rows, int seats, String[][] cinema) {
        return (float) checkSeatBusy(cinema) * 100 / (rows * seats);
    }

    private static int checkSeatBusy(String[][] cinema) {
        int counter = 0;
        for (String[] strings : cinema) {
            for (String string : strings) {
                if (Objects.equals(string, "B")) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static void changeSeatBusy(String[][] cinema, int a, int b) {
        cinema[a - 1][b - 1] = "B";
    }

    private static void ticketPrice(String[][] cinema, int rowNumber) {
        if (cinema.length * cinema[0].length > 60) {
            if (cinema.length / 2 >= rowNumber) {
                System.out.print("Ticket price: $10");
            } else if (cinema.length / 2 < rowNumber) {
                System.out.print("Ticket price: $8");
            }
        }  else if (cinema.length * cinema[0].length < 60) {
            System.out.print("Ticket price: $10");
        }
        System.out.println();
    }


    private static int totalIncome(int rows, int seats) {
        if (rows * seats < 60) {
           return rows * seats * 10;
        } else {
            if (rows % 2 == 0) {
                return (rows / 2 * seats * 10 + rows / 2 * seats * 8);
            } else {
                return (rows / 2 * seats * 10 + (rows / 2 + 1) * seats * 8);
            }
        }
    }

    private static void fillTheCinema(String[][] cinema, int rows, int seats) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    private static void printCinema(String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(" " + (i));
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
    }
}
