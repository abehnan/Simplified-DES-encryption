import java.util.Scanner;

public class MyDes {
	public static void main(String[] args) {

		// get a number
		Scanner reader = new Scanner(System.in);
		System.out.print("Please enter a two-digit positive integer number: ");
		int key = reader.nextInt();
//		System.out.println();
		
		if (key <= 9 || key >= 100) {
			System.out.println("Invalid number. Exiting...");
			reader.close();
			System.exit(0);
		}

		int firstDigit = key / 10;
		char[][] T = new char[firstDigit][1000];
		char[] A = new char[firstDigit * 1000];
		char[] B = new char[(firstDigit+1) * 1000];
		char[] C = new char[(firstDigit+1) * 1000];
		char[][] T2 = new char[firstDigit + 1][1000];
		
		// get a message
		System.out.print("Please enter a message: ");
		String message = reader.next();
		char[] messageArray = message.toCharArray();
		reader.close();
//		System.out.println();
		
		if (message == null || message.isEmpty()) {
			System.out.println("Invalid message. Exiting...");
			System.exit(0);
		}

		// populate the 2D array with the message
		for (int i = 0; i < firstDigit; i++) {
			for (int j = 0; j < 1000; j++) {
				if (i + j < message.length())
					T[i][j] = messageArray[i + j];
			}
		}

		// copy transpose of 2D array T into array A
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit; i++) {
				A[i + j] = T[i][j];
			}
		}

		// apply Caesar cipher to A and store into B
		for (int i = 0; i < A.length; i++) {
			B[i] = (char) (A[i] + key);
		}

		// populate the 2D array T2 with the contents of B
		for (int i = 0; i < firstDigit + 1; i++) {
			for (int j = 0; j < 1000; j++) {
				T2[i][j] = B[i + j];
			}
		}
		
		// copy transpose of 2D array T2 into array C
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < firstDigit + 1; i++) {
				C[i + j] = T2[i][j];
			}
		}
		
		System.out.println("The encrypted string is: " + C.toString());

		System.exit(0);
	}
}