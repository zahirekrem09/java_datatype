package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class NumberType {
    public boolean isPrime(int number) {
        if (number >= 2) {
            int i = 2;
            while (i <= Math.sqrt(number)) {
                if (number % i == 0) {
                    return false;
                }
                i += 1;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * A function to check if a given number is a prime number.
     *
     * @param n the number to be checked
     * @return true if the number is prime, false otherwise
     */
    public static boolean _isPrime(
            int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * A function that generates a list of prime numbers based on user input
     * and calculates the time taken to find them.
     */
    public ArrayList<Integer> primeList(int desiredPrimes) {
        ArrayList<Integer> primeListArr = new ArrayList<>();
        int k = 2;
        while (primeListArr.size() < desiredPrimes) {
            if (isPrime(k)) {
                primeListArr.add(k);
            }
            k++;
        }
        return primeListArr;
    }


    public void primeListPrint() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.print("Enter the desired number of prime numbers: ");
            int desiredPrimes = scanner.nextInt();
            //!! Timer start
            Instant start = Instant.now();
            var primeListArr = primeList(desiredPrimes);
            //!! Timer end
            Instant end = Instant.now();
            System.out.println(primeListArr);
            System.out.printf("\nWe brought %d prime numbers  in %.5f seconds.",
                    primeListArr.size(), Duration.between(start, end).toMillis() / 1000.0);
            System.out.print("Enter 'yes' to continue or 'no' to exit the application: ");
            String output = scanner.next();
            if (output.equalsIgnoreCase("yes")) {
                continue;
            } else {
                break;
            }
        }
    }

    public boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

    public boolean isAmicableNumber(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        int sum2 = 0;
        for (int j = 1; j < sum; j++) {
            if (sum % j == 0) {
                sum2 += j;
            }
        }
        return sum2 == number && number != sum;
    }

    public boolean isNeither(int number) {
        return !isPerfectNumber(number) && !isAmicableNumber(number);
    }

    public String numType(int n) {
        int l = IntStream.range(1, n)
                .filter(i -> n % i == 0)
                .sum();
        int t = IntStream.range(1, l)
                .filter(i -> l % i == 0)
                .sum();
        if (l == n) {
            return "Perfect";
        } else if (t == n) {
            return "Amicable";
        } else {
            return "Neither";
        }
    }

    public Integer primorialNumber(int n) {

        var primeListArr = primeList(n);
        AtomicReference<Integer> primorial = new AtomicReference<>(1);
        primeListArr.forEach(i -> primorial.updateAndGet(v -> v * i));
        return primorial.get();
        //        return primeListArr.stream().reduce(1, (a, b) -> a * b);
    }



    }



/*import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PrimeNumberGenerator {
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> primeList = new ArrayList<>();
        while (true) {
            System.out.print("Enter the desired number of prime numbers: ");
            int desiredPrimes = scanner.nextInt();
            long startTime = System.currentTimeMillis();
            int k = 2;
            while (primeList.size() < desiredPrimes) {
                if (isPrime(k)) {
                    primeList.add(k);
                }
                k++;
            }
            System.out.println(primeList);
            System.out.printf("\nFound %d prime numbers out of %d numbers in %.5f seconds.\nPercentage of primes: %.2f%%\n",
                    primeList.size(), k, (System.currentTimeMillis() - startTime) / 1000.0, 100.0 * primeList.size() / k);
            System.out.print("Enter 'yes' to save the list to a 'primes.txt' file, or 'no' to generate a new list: ");
            String output = scanner.next();
            if (output.equalsIgnoreCase("yes")) {
                try {
                    FileWriter writer = new FileWriter("primes.txt");
                    writer.write(primeList.toString());
                    writer.close();
                    System.out.println("Prime list saved to 'primes.txt'.");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file.");
                    e.printStackTrace();
                }
            } else if (output.equalsIgnoreCase("no")) {
                primeList.clear();
            } else {
                System.out.println("Invalid input. Exiting...");
                break;
            }
        }
    }
}*/

