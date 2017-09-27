import java.util.Arrays;
import java.util.Scanner;

public class MyDes {
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

		int firstDigit = key / 10;
		System.out.println("firstDigit: " + firstDigit);
		char[][] T = new char[firstDigit][1000];
		char[] A = new char[firstDigit * 1000];
		char[] B = new char[(firstDigit+1) * 1000];
		char[] C = new char[(firstDigit+1) * 1000];
		char[][] T2 = new char[firstDigit + 1][1000];
		
		// get a message
		System.out.print("Please enter a message: ");
		reader.nextLine();
		String message = reader.nextLine();
		char[] messageArray = message.toCharArray();
		
		System.out.print("messageArray: ");
		for (int i = 0; i < message.length(); i++) {
			System.out.print(messageArray[i]);
		}
		System.out.println();
		
		
		int row = 0;

		if (message == null || message.isEmpty()) {
			System.out.println("Invalid message. Exiting...");
			System.exit(0);
		}

		// populate first 2D array
		for (int i = 0; i < messageArray.length; i++) {
			if (i > 0 && i % 1000 == 0)
				row++;
			T[row][i % 1000] = messageArray[i];
		}

	
		System.out.print("T: " + Arrays.deepToString(T));
		System.out.println();

		// copy transpose of 2D array T into array A
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit; i++) {
				A[i + j] = T[i][j];
			}
		}

		System.out.println("A: " + A.toString());

		// apply Caesar cipher to A and store into B
		for (int i = 0; i < A.length; i++) {
			// if (A[i] != 0)
				B[i] = (char) (A[i] + key);
		}

		System.out.println("B: " + B.toString());

		// populate second 2D array
		for (int i = 0; i < B.length; i++) {
			if (i > 0 && i % 1000 == 0)
				row++;
			T2[row][i % 1000] = B[i];
		}
	
		
		// copy transpose of 2D array T2 into array C
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit + 1; i++) {
				C[i + j] = T2[i][j];
			}
		}
		
		System.out.println("The encrypted string is: " + C.toString());
		reader.close();
		System.exit(0);
	}
}