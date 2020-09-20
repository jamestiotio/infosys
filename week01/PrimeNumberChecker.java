public class PrimeNumberChecker {

  public static int isPrime(int num) {
    for (int i = 2; i <= Math.sqrt((double)num); i++) {
      if (num % i == 0) {
        return 0;
      }
    }

    if (num <= 1) {
      return 0;
    }

    return 1;
  }

}