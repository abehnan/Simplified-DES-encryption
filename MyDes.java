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

		char[] message = input.toCharArray();
		int firstDigit = key / 10;
		int row, col;
		int maxLen = (firstDigit + 1) * 1000;
		char[] A = new char[maxLen];
		char[] B = new char[maxLen];
		char[] C = new char[maxLen];
		char[] D = new char[maxLen];
		char[] E = new char[maxLen];
		char[][] T = new char[firstDigit][1000];
		char[][] T2 = new char[firstDigit + 1][1000];
		char[][] T3 = new char[firstDigit + 1][1000];
		char[][] T4 = new char[firstDigit][1000];
		int len = message.length;

		// debug
		System.out.println("firstDigit: " + firstDigit);
		System.out.print("messageArray: ");
		for (int i = 0; i < input.length(); i++) {
			System.out.print(message[i]);
		}
		System.out.println();

		row = 0;
		col = 0;
		// populate T with the message
		for (int i = 0; i < len; i++) {
			if (i > 0 && i % 1000 == 0)
				row++;
			T[row][i % 1000] = message[i];

		}

		// debug
		// System.out.print("T: " + Arrays.deepToString(T));
		// System.out.println();

		// copy transpose of 2D array T into array A
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit; i++) {
				A[(j * firstDigit) + i] = T[i][j];
			}
		}

		System.out.print("A is: ");
		for (int i =0; i < A.length; i++) {
			System.out.print(A[i]);
		}
		System.out.println();

		// apply Caesar cipher to A and store into B
		for (int i = 0; i < len; i++) {
			B[i] = (char) (A[i] + key);
		}

		System.out.print("B is: ");
		for (int i =0; i < B.length; i++) {
			System.out.print(B[i]);
		}
		System.out.println();

		// populate second 2D array
		row = 0;
		for (int i = 0; i < B.length; i++) {
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

		System.out.print("The encrypted string C is: ");
		for (int i =0; i < C.length; i++) {
			System.out.print(C[i]);
		}
		System.out.println();

		// now we have to decrypt it.

		// populate T3 with C
		row = 0;
		for (int i = 0; i < B.length; i++) {
			if (i > 0 && i % 1000 == 0)
				row++;
			T3[row][i % 1000] = C[i];
		}

		// copy transpose of 2D array T3 into array D
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit + 1; i++) {
				D[j * (firstDigit + 1) + i] = T3[i][j];
			}
		}
		
		// apply Caesar cipher to D and store into E
				for (int i = 0; i < len; i++) {
					B[i] = (char) (A[i] + key);
				}
		
		

		reader.close();
		System.exit(0);
	}
}