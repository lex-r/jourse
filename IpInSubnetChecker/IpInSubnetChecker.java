import java.util.Scanner;

public class IpInSubnetChecker {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    scanner.useDelimiter("\\p{Punct}|\\p{javaWhitespace}");

    System.out.print("input network address: ");
    short[] networkAddress = new short[4];
    networkAddress[0] = scanner.nextShort();
    networkAddress[1] = scanner.nextShort();
    networkAddress[2] = scanner.nextShort();
    networkAddress[3] = scanner.nextShort();

    int networkAddressLong =
        (networkAddress[0] << 24) +
        (networkAddress[1] << 16) +
        (networkAddress[2] << 8) +
        networkAddress[3];

    System.out.print("input host address in CIDR notation: ");

    short[] hostAddress = new short[5];
    scanner.useDelimiter("\\p{Punct}|\\p{javaWhitespace}|/");

    hostAddress[0] = scanner.nextShort();
    hostAddress[1] = scanner.nextShort();
    hostAddress[2] = scanner.nextShort();
    hostAddress[3] = scanner.nextShort();
    hostAddress[4] = scanner.nextShort();

    int hostAddressLong =
        (hostAddress[0] << 24) +
        (hostAddress[1] << 16) +
        (hostAddress[2] << 8) +
        hostAddress[3];

    for (byte i = 31; i > 31-hostAddress[4]; i--) {
      int bit = 1 << i;
      if ((bit & networkAddressLong) != (bit & hostAddressLong)) {
        System.out.println("false");
        return;
      }
    }
    System.out.println("true");
  }
}
