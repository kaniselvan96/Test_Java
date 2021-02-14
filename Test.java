import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// import org.apache.commons.lang.math.RandomUtils;

public class Test {
    public static void getSumOfNumbers(String s) {
        /*
         * Please implement this method to calculate the sum of all integers found in
         * the parameter String. You can assume that integers are separated from other
         * parts with one or more spaces (' ' symbol). For example,
         * s="12 some text 3  7", result: 22 (12+3+7=22)
         */
        s = s.replaceAll("[^0-9]+", " ");

        String[] numbersArray = s.split(" ");
        Integer total = 0;
        for (int i = 0; i < numbersArray.length; i++) {
            // trim() removes whitespace from both ends
            if (numbersArray[i].trim().length() != 0) {
                Integer value = Integer.valueOf(numbersArray[i].trim());
                total += value;
            }
        }
        System.out.println("Sum of numbers equals to " + total);
    }

    public static void sortIgnoringSpaces(String[] a) {
        /*
         * Please implement this method to sort a given array of Strings in alphabetical
         * order ignoring spaces (' ' symbols) within the strings.
         */
        Map<String, String> map = new HashMap<String, String>();
        String[] keys = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            String noSpace = a[i].replace(" ", "");
            map.put(noSpace, a[i]);
            keys[i] = noSpace;
        }
        Arrays.sort(keys);
        for (int k = 0; k < keys.length; k++) {
            a[k] = map.get(keys[k]);
        }
        System.out.println("Answer for sort ignoring space:");
        for (String s : a) {
            if (s != null && !s.trim().isEmpty())
                System.out.println(s);
        }
    }

    public static void reverseArray(String[] array) {
        /*
         * Please implement this method to reverse array where the order of elements has
         * been reversed from the original array. E.g. given {"a", "b", "c", "d"},
         * result is {"d", "c", "b", "a"}
         */
        int n = array.length;
        String[] arr = new String[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            arr[j - 1] = array[i];
            j -= 1;
        }

        System.out.println("Answer for reverse array:");
        for (String s : arr) {
            System.out.println(s);
        }
    }

    public static void sumOfTwoLargestNumbers(int[] arr) {
        /*
         * Please implement this method to calculate the sum of the two largest numbers
         * in a given array.
         */
        // Initialize
        int first = 0;
        int second = 0;

        // Find first and second largest
        for (int i = 0; i < arr.length; i++) {
            // If current element is greater than first then update both first and second
            if (arr[i] > first) {
                second = first;
                first = arr[i];
            }

            // If arr[i] is in between first and second then update second
            else if (arr[i] > second && arr[i] != first)
                second = arr[i];
        }
        int sum = first + second;
        System.out.println("Sum of the two largest numbers is " + sum);
    }

    // Please do not change this helper class
    public static class Node {
        int val;
        List<Node> children;

        int getValue() {
            return val;
        }

        void setValue(int val) {
            this.val = val;
        }

        List<Node> getChildren() {
            return children;
        }

        void setChildren(List<Node> children) {
            this.children = children;
        }
    }

    public static void getAverage(Node root) {
        /*
         * Please implement this method to calculate the average of all node values
         * (Node.getValue()) in the tree. root c1 c2 c3 c4 c5 c6
         *
         * The codes must able to support any tree structures even the orphan root which
         * doesn't have children. You can create any helper function as needed.
         */
        double avg = 0;

        int[] treeSum = getTreeSum(root);
        avg = (double) treeSum[0] / treeSum[1];
        System.out.println("Average of the node values equals to " + avg);
    }

    // Return a pair of numbers
    // -first one is sum of all nodes in tree
    // -second one is number of nodes in the tree
    public static int[] getTreeSum(Node root) {
        int[] res = new int[2];
        if (root == null) {
            res[0] = 0;
            res[1] = 0;
        } else {
            res[0] = root.getValue();
            res[1] = 1;
            if (root.getChildren() != null) {
                for (Node child : root.getChildren()) {
                    int[] childRes = getTreeSum(child);
                    res[0] += childRes[0];
                    res[1] += childRes[1];
                }
            }
        }
        return (res);
    }

    public static void main(String args[]) {
        try {
            // sum
            getSumOfNumbers("text mix with 112 and 222 with numbers 2 278 991");
            System.out.println("////////////////////////");
            System.out.println();

            // sort
            sortIgnoringSpaces(
                    new String[] { " ", "test", "ABC", "why", " ", "HLB", "webiste", "google", "1", "9", "-111" });
            System.out.println("////////////////////////");
            System.out.println();

            // reverse
            reverseArray(new String[] { "first", "second", "third", "fourth", "fifth", "sixth", "seventh" });
            System.out.println("////////////////////////");
            System.out.println();

            // sum two largest
            int[] array = { 43, 12, 12, 44, 47, 9, 34, 58, 3, 11, 4, 21 };
            sumOfTwoLargestNumbers(array);
            System.out.println("////////////////////////");
            System.out.println();

            // average
            Node root = new Node();
            root.setValue(ThreadLocalRandom.current().nextInt(100));
            Node c1 = new Node();
            c1.setValue(ThreadLocalRandom.current().nextInt(100));
            Node c2 = new Node();
            c2.setValue(ThreadLocalRandom.current().nextInt(100));
            List<Node> list = new ArrayList<>();
            list.add(c1);
            list.add(c2);
            root.setChildren(list);

            Node c3 = new Node();
            c3.setValue(ThreadLocalRandom.current().nextInt(100));
            list = new ArrayList<>();
            list.add(c3);

            c2.setChildren(list);

            Node c4 = new Node();
            c4.setValue(ThreadLocalRandom.current().nextInt(100));
            Node c5 = new Node();
            c5.setValue(ThreadLocalRandom.current().nextInt(100));
            Node c6 = new Node();
            c6.setValue(ThreadLocalRandom.current().nextInt(100));
            list = new ArrayList<>();
            list.add(c4);
            list.add(c5);
            list.add(c6);
            c3.setChildren(list);

            getAverage(root);

            int total = root.getValue() + c1.getValue() + c2.getValue() + c3.getValue() + c4.getValue() + c5.getValue()
                    + c6.getValue();
            double ans = (double) total / 7;
            System.out.println("Correct answer: " + total + " / 7 = " + ans);
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}