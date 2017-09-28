import java.util.Arrays;
import java.util.Scanner;

public class MyDes {

	public static char[][] transposeMatrix(char[][] m) {
		char[][] temp = new char[m[0].length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				temp[j][i] = m[i][j];
		return temp;
	}
	
	private static char[] encrypt(int key, String input) {
		
		char[] message = input.toCharArray();
		int firstDigit = key / 10;
		int row;
		int maxLen = (firstDigit + 1) * 1000;
		char[] A = new char[maxLen];
		char[] B = new char[maxLen];
		char[] C = new char[maxLen];
		char[][] T = new char[firstDigit][1000];
		char[][] T2 = new char[firstDigit + 1][1000];
		int len = message.length;

		// debug
		System.out.println("~~~~~~~~~~~~~~~~  Encryption Starting  ~~~~~~~~~~~~~~~~");
		System.out.println("key: " + key);
		System.out.println("string: " + String.valueOf(message));

		row = 0;
		
		// populate T with the message
		for (int i = 0; i < len; i++) {
			if (i > 0 && i % 1000 == 0)
				row++;
			T[row][i % 1000] = message[i];

		}

		// copy transpose of 2D array T into array A
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit; i++) {
				A[(j * firstDigit) + i] = T[i][j];
			}
		}
		System.out.println("A is: " + String.valueOf(A));


		// apply Caesar cipher to A and store into B
		for (int i = 0; i < maxLen; i++) {
			B[i] = (char) (A[i] + key);
		}
		System.out.println("B is: " + String.valueOf(B));

		// populate second 2D array
		row = 0;
		for (int i = 0; i < maxLen; i++) {
			if (i > 0 && i % 1000 == 0)
				row++;
			T2[row][i % 1000] = B[i];
		}

		// copy transpose of 2D array T2 into array C
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit + 1; i++) {
				C[j * (firstDigit + 1) + i] = T2[i][j];
			}
		}
		
		System.out.println("~~~~~~~~~~~~~~~~  Encryption Ending  ~~~~~~~~~~~~~~~~");
		return C;
	}
	
	public static char[] decrypt(int key, String input) {
		
		char[] C = input.toCharArray();
		int firstDigit = key / 10;
		int row, col;
		int maxLen = (firstDigit+1) * 1000;
		char[] B = new char[maxLen];
		char[] A = new char[maxLen];
		char[] result = new char[maxLen];
		char[][] T = new char[1000][firstDigit];
		char[][] T2 = new char[1000][firstDigit + 1];
		// char[][] T2 = new char[firstDigit + 1][1000];
		int len = C.length;

		// debug
		System.out.println("~~~~~~~~~~~~~~~~  Decryption Starting  ~~~~~~~~~~~~~~~~");
		System.out.println("key: " + key);
		System.out.println("C: " + String.valueOf(C));

		row = 0;
		// populate T2 with C
		for (int i = 0; i < maxLen; i++) {
			if (i > 0 && i % (firstDigit + 1) == 0)
				row++;
			T2[row][i % (firstDigit + 1)] = C[i];

		}

		// copy transpose of 2D array T2 into array B
		for (int j = 0; j < firstDigit + 1; j++) {
			for (int i = 0; i < 1000; i++) {
				B[j*1000 + i] = T2[i][j];
			}
		}
		System.out.println("B is: " + String.valueOf(B));


		// apply the reverse Caesar cipher to B and store into A
		for (int i = 0; i < maxLen; i++) {
			A[i] = (char) (B[i] - key);
		}
		System.out.println("A is: " + String.valueOf(A));

		// populate second T4 with E 
		row = 0;
		for (int i = 0; i < len; i++) {
			if (i > 0 && i % firstDigit == 0)
				row++;
			if (i == 1000)
				break;
			T[row][i % firstDigit] = A[i];
		}

		// copy transpose of 2D array T4 into result
		for (int j = 0; j < firstDigit; j++) {
			for (int i = 0; i < 1000; i++) {
				result[j*1000 + i] = T[i][j];
			}
		}
		
		System.out.println("~~~~~~~~~~~~~~~~  Decryption Ending  ~~~~~~~~~~~~~~~~");
		return result;
	}

	public static void main(String[] args) {

		// get a number
		Scanner reader = new Scanner(System.in);
		System.out.print("Please enter a two-digit positive integer number: ");
		int key = reader.nextInt();

		if (key <= 9 || key >= 100) {
			System.out.println("Invalid number. Exiting...");
			reader.close();
			System.exit(0);
		}

		// get a message
		System.out.print("Please enter a message: ");
		reader.nextLine();
		String input = reader.nextLine();
		if (input == null || input.isEmpty()) {
			reader.close();
			System.out.println("Invalid message. Exiting...");
			System.exit(0);
		}

		char[] encrypted = encrypt(key, input);
		String encryptedString = String.valueOf(encrypted);
		System.out.println("The encryped message C is: " + encryptedString);
		
		char[] decrypted = decrypt(key, encryptedString);
		String decryptedString = String.valueOf(decrypted);
		System.out.println("The decrypted message is:" + decryptedString);
		
		reader.close();
		System.exit(0);
	}
}