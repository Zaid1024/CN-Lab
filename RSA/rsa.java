import java.util.Scanner;

public class rsa {
	public static void main(String[] args) {
		int p = 17;
		int q = 23;

		int n = p * q;
		int phi = (p - 1) * (q - 1);

		int e = findCoprime(phi);
		int d = findPrivateKey(e, phi);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter plaintext: ");
		String plaintext = scanner.nextLine();

		int[] ciphertext = rsaEncrypt(plaintext, e, n);

		System.out.print("Ciphertext: ");
		for (int cipher : ciphertext) {
			System.out.print(cipher + " ");
		}
		System.out.println();

		String decryptedText = rsaDecrypt(ciphertext, d, n);
		System.out.println("Decrypted plaintext: " + decryptedText);
	}

	static int findCoprime(int phi) {
		int e = 2;
		while (e < phi) {
			if (gcd(e, phi) == 1) {
				return e;
			}
			e++;
		}
		return 0; // No coprime found.
	}

	static int findPrivateKey(int e, int phi) {
		int d = 0;
		int k = 1;

		while (true) {
			d = (1 + k * phi) / e;
			if ((d * e) % phi == 1) {
				return d;
			}
			k++;
		}
	}

	static int[] rsaEncrypt(String plaintext, int e, int n) {
		char[] chars = plaintext.toCharArray();
		int[] ciphertext = new int[chars.length];

		for (int i = 0; i < chars.length; i++) {
			int charValue = (int) chars[i];
			ciphertext[i] = modPow(charValue, e, n);
		}

		return ciphertext;
	}

	static String rsaDecrypt(int[] ciphertext, int d, int n) {
		StringBuilder decryptedText = new StringBuilder();

		for (int cipher : ciphertext) {
			int charValue = modPow(cipher, d, n);
			decryptedText.append((char) charValue);
		}

		return decryptedText.toString();
	}

	static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	static int modPow(int base, int exponent, int modulus) {
		int result = 1;
		base = base % modulus;

		while (exponent > 0) {
			if (exponent % 2 == 1) {
				result = (result * base) % modulus;
			}
			exponent = exponent >> 1; // Equivalent to exponent /= 2
			base = (base * base) % modulus;
		}
		return result;
	}
}
