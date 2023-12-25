import java.util.Random;

public class Red {

	private static final int QUEUE_SIZE = 10;
	private static final int MAX_PACKETS = 20;
	private static final double MAX_PROBABILITY = 0.7;
	private static final double MIN_PROBABILITY = 0.3;

	// Generates a random number between min and max
	private static double randDouble(double min, double max) {
		Random rand = new Random();
		return min + (max - min) * rand.nextDouble();
	}

	public static void main(String[] args) {
		int queueSize = 0;
		double dropProbability = MIN_PROBABILITY;
		for (int i = 0; i < MAX_PACKETS; i++) {
			if (queueSize == QUEUE_SIZE) {
				// Queue is full, drop the packet
				System.out.println("Packet dropped (queue full)");
				dropProbability = MIN_PROBABILITY;
			} else if (randDouble(0, 1) < dropProbability) {
				// Randomly drop the packet based on the current drop probability
				System.out.println("Packet dropped (random)");
				dropProbability += (MAX_PROBABILITY - MIN_PROBABILITY) / (MAX_PACKETS - 1);
			} else {
				// Accept the packet
				System.out.println("Packet accepted");
				queueSize++;
				dropProbability = MIN_PROBABILITY;
			}
		}
	}
}