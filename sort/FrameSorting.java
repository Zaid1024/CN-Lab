import java.util.Scanner;

public class FrameSorting {
	static class Frame implements Comparable<Frame> {
		int seqNo;
		String data;

		public Frame(int seqNo) {
			this.seqNo = seqNo;
		}

		@Override
		public int compareTo(Frame otherFrame) {
			return Integer.compare(this.seqNo, otherFrame.seqNo);
		}
	}

	// Bubble sort function to sort frames based on their order
	static void bubbleSort(Frame[] frames) {
		int n = frames.length;
		boolean swapped;
		do {
			swapped = false;
			for (int i = 1; i < n; i++) {
				if (frames[i - 1].compareTo(frames[i]) > 0) {
					// Swap frames[i-1] and frames[i]
					Frame temp = frames[i - 1];
					frames[i - 1] = frames[i];
					frames[i] = temp;
					swapped = true;
				}
			}
		} while (swapped);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Input the number of frames
		System.out.print("Enter the number of frames: ");
		int numFrames = scanner.nextInt();

		// Input frame order and string data
		Frame[] frames = new Frame[numFrames];
		System.out.println("Enter the order and data of frames:");
		for (int i = 0; i < numFrames; i++) {
			System.out.print("Frame " + (i + 1) + " (seqNo): ");
			int seqNo = scanner.nextInt();
			frames[i] = new Frame(seqNo);

			System.out.print("Frame " + (i + 1) + " data: ");
			scanner.nextLine(); // consume the newline character
			frames[i].data = scanner.nextLine();
		}

		// Sorting frames using bubble sort
		bubbleSort(frames);

		// Displaying the sorted frames and string data
		System.out.println("\nSorted Frames:");
		for (Frame frame : frames) {
			System.out.println("Frame " + frame.seqNo + " (data): " + frame.data);
		}

		scanner.close();
	}
}
