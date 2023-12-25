import java.util.Random;
import java.util.Scanner;

public class TokenBucket {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter the number of packets: ");
        int n = scanner.nextInt();

        System.out.print("Enter the bucket size: ");
        int bucketSize = scanner.nextInt();

        // Initialize the tokens
        int tokens = bucketSize;

        // Generate a random output rate
        int outputRate = random.nextInt(bucketSize - 1) + 1;

        // Create an array to store the packet sizes
        int[] packets = new int[n];

        // Get the packet sizes from the user
        System.out.println("Enter the packet sizes in order: ");
        for (int i = 0; i < n; i++) {
            packets[i] = scanner.nextInt();
        }

        // Initialize variables for the simulation
        int cycle = 0;
        int remains = 0;
        int sent = 0;
        boolean flag = false;

        // Print the header row for the simulation table
        System.out.println("Cycle\tPackets\tSent\tRemains");

        // Start the simulation loop
        while (true) {
            cycle++;

            // Compute the number of tokens available
            tokens = bucketSize - remains;

            // If the current packet size is less than or equal to the number of available tokens,
            // send the packet
            if (packets[0] <= tokens) {
                if (remains + packets[0] <= outputRate) {
                    sent = remains + packets[0];
                    remains = 0;
                } else {
                    remains += (packets[0] - outputRate);
                    sent = outputRate;
                }

                if (!flag) {
                    System.out.println(cycle + "\t" + packets[0] + "\t" + sent + "\t" + remains);
                    packets[0] = 0;
                } else {
                    System.out.println(cycle + "\t---\t" + sent + "\t" + remains);
                }
            } else {
                // Otherwise, discard the packet
                remains = bucketSize;
                if (remains <= outputRate) {
                    sent = remains;
                    remains = 0;
                } else {
                    remains -= outputRate;
                    sent = outputRate;
                }

                if (!flag) {
                    System.out.println(cycle + "\t" + packets[0] + "\t" + sent + "\t" + remains);
                    packets[0] -= tokens;
                } else {
                    System.out.println(cycle + "\t---\t" + sent + "\t" + remains);
                }
            }

            // If all packets have been sent, stop the simulation
            if (packets[0] != 0) {
                continue;	
            } else if (n == 1) {
                flag = true;
                if (remains == 0) {
                    break;
                }
            } else {
                n--;
                packets = Arrays.copyOfRange(packets, 1, packets.length);
            }
        }

        scanner.close();
    }
}
