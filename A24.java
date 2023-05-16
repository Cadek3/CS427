import java.util.*;

public class CoinChangeGreedyAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Enter coin denominations
        System.out.print("Enter the coin denominations (space-separated): ");
        String[] denominationsStr = scanner.nextLine().split(" ");
        int[] denominations = new int[denominationsStr.length];
        for (int i = 0; i < denominationsStr.length; i++) {
            denominations[i] = Integer.parseInt(denominationsStr[i]);
        }

        // Enter the amount owed
        System.out.print("Enter the amount owed: ");
        int amountOwed = scanner.nextInt();

        // Compute the change
        Map<Integer, Integer> change = computeChange(denominations, amountOwed);

        // Display the change
        System.out.println("Change:");
        for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
            int coinValue = entry.getKey();
            int coinCount = entry.getValue();
            System.out.println(coinCount + " coins of " + coinValue);
        }

        scanner.close();
    }

    private static Map<Integer, Integer> computeChange(int[] denominations, int amountOwed) {
        PriorityQueue<Integer> coinHeap = new PriorityQueue<>(new IntegerCompare());
        coinHeap.addAll(Arrays.asList(Arrays.stream(denominations).boxed().toArray(Integer[]::new)));

        Map<Integer, Integer> change = new TreeMap<>(Collections.reverseOrder());

        while (!coinHeap.isEmpty() && amountOwed > 0) {
            int largestCoin = coinHeap.peek();

            if (largestCoin <= amountOwed) {
                int coinCount = amountOwed / largestCoin;
                change.put(largestCoin, coinCount);
                amountOwed -= coinCount * largestCoin;
            }

            coinHeap.poll();
        }

        return change;
    }

    static class IntegerCompare implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }
}

