import java.util.*;

public class tb2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter the number of packets: ");
        int n = scanner.nextInt();
        int bsize = scanner.nextInt();
        int tokens = 0;
        int outrate = random.nextInt(bsize - 1) + 1;

        int[] packets = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter packet size " + (i + 1) + ": ");
            packets[i] = scanner.nextInt();
        }

        int cycle = 0, sent = 0, i = 0;

        System.out.println("Cycle\tPackets\tSent\tRemains");

        while (i < n || tokens > 0) {
            cycle++;

            if (i < n) {
                tokens = Math.min(tokens + outrate, bsize);

                while (i < n && tokens > 0) {
                    sent = Math.min(packets[i], tokens);
                    tokens -= sent;
                    packets[i] -= sent;

                    System.out.println(cycle + "\t" + (packets[i] + sent) + "\t" + sent + "\t" + (packets[i] > 0 ? packets[i] : 0));

                    if (packets[i] == 0) {
                        i++;
                    }
                }
            } else {
                System.out.println(cycle + "\t---\t0\t" + 0);
                tokens = Math.max(tokens - outrate, 0);
            }
        }

        scanner.close();
    }
}
