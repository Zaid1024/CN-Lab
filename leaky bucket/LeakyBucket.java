import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class LeakyBucket {
		private int bucketSize;
		private int rate;
		private Queue<Integer> bucket;

		public LeakyBucket(int bucketSize, int rate) {
				this.bucketSize = bucketSize;
				this.rate = rate;
				this.bucket = new LinkedList<>();

				// Set up a timer to simulate time passing
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
								if (!bucket.isEmpty()) {
										int removed = bucket.poll();
										System.out.println("Dropping packet: " + removed);
								}
						}
				}, 0, 1000 / rate); // Timer runs every 1000 milliseconds / rate
		}

		public void addPacket(int packetSize) {
				if (bucket.size() < bucketSize) {
						bucket.add(packetSize);
						System.out.println("Packet added: " + packetSize);
				} else {
						System.out.println("Bucket full. Dropping packet: " + packetSize);
				}
		}

		public static void main(String[] args) {
				Scanner scanner = new Scanner(System.in);

				System.out.print("Enter the bucket size: ");
				int bucketSize = scanner.nextInt();

				System.out.print("Enter the rate (packets per second): ");
				int rate = scanner.nextInt();

				// Create a leaky bucket with user-input parameters
				LeakyBucket leakyBucket = new LeakyBucket(bucketSize, rate);

				// Take user input for packet sizes until the user decides to exit
				while (true) {
						System.out.print("Enter packet size (or -1 to exit): ");
						int packetSize = scanner.nextInt();

						if (packetSize == -1) {
								break;
						}

						// Add the packet to the bucket
						leakyBucket.addPacket(packetSize);
				}

				scanner.close();
		}
}
