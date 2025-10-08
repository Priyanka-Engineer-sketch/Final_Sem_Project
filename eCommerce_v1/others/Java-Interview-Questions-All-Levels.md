# Comprehensive Java Interview Programming Questions (100+) - All Levels

This comprehensive guide covers 100+ Java programming interview questions organized by experience level, designed to help you prepare for technical interviews.

## Table of Contents
1. [Junior Level Questions (0-2 Years Experience)](#junior-level-questions-0-2-years-experience)
2. [Mid-Level Questions (2-5 Years Experience)](#mid-level-questions-2-5-years-experience) 
3. [Senior Level Questions (5+ Years Experience)](#senior-level-questions-5-years-experience)
4. [Data Structures & Algorithms](#data-structures--algorithms)
5. [Collections Framework](#collections-framework)
6. [Multithreading & Concurrency](#multithreading--concurrency)
7. [OOP Concepts](#oop-concepts)
8. [Exception Handling](#exception-handling)
9. [Hibernate & JPA](#hibernate--jpa)

---

## Junior Level Questions (0-2 Years Experience)

### Basic Java Fundamentals

1. **Write a Java program to check if a number is prime.**
```java
public class PrimeCheck {
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
```

2. **Write a Java program to reverse a string.**
```java
public class StringReverse {
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    // Alternative approach
    public static String reverseManual(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
}
```

3. **Write a program to check if a string is palindrome.**
```java
public class PalindromeCheck {
    public static boolean isPalindrome(String str) {
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
}
```

4. **Write a Java program to find the factorial of a number.**
```java
public class Factorial {
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
    
    // Iterative approach
    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
```

5. **Write a program to generate Fibonacci series.**
```java
public class FibonacciSeries {
    public static void fibonacci(int n) {
        int a = 0, b = 1;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < n; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }
}
```

6. **Write a program to find the largest element in an array.**
```java
public class ArrayLargest {
    public static int findLargest(int[] arr) {
        int largest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }
}
```

7. **Write a program to count vowels and consonants in a string.**
```java
public class VowelConsonantCount {
    public static void countVowelsConsonants(String str) {
        int vowels = 0, consonants = 0;
        str = str.toLowerCase();
        
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiou".indexOf(c) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        System.out.println("Vowels: " + vowels + ", Consonants: " + consonants);
    }
}
```

8. **Write a program to find sum of digits of a number.**
```java
public class SumOfDigits {
    public static int sumDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
```

9. **Write a program to swap two numbers without using third variable.**
```java
public class SwapNumbers {
    public static void swapWithoutThird(int a, int b) {
        System.out.println("Before: a = " + a + ", b = " + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("After: a = " + a + ", b = " + b);
    }
    
    // Using XOR
    public static void swapXOR(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }
}
```

10. **Write a program to find second largest element in array.**
```java
public class SecondLargest {
    public static int findSecondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        return secondLargest;
    }
}
```

### Basic Array & String Operations

11. **Remove duplicates from array**
12. **Find missing number in array of 1 to n**
13. **Check if two strings are anagrams**
14. **Count frequency of each character in string**
15. **Find first non-repeating character**
16. **Rotate array by k positions**
17. **Merge two sorted arrays**
18. **Find pair of elements with given sum**
19. **Check if array is sorted**
20. **Convert string to integer (implement atoi)**

---

## Mid-Level Questions (2-5 Years Experience)

### Advanced Programming Concepts

21. **Implement Stack using Array.**
```java
public class StackUsingArray {
    private int[] stack;
    private int top;
    
    public StackUsingArray(int capacity) {
        stack = new int[capacity];
        top = -1;
    }
    
    public void push(int value) {
        if (top == stack.length - 1) {
            System.out.println("Stack overflow");
        } else {
            stack[++top] = value;
        }
    }
    
    public int pop() {
        if (top == -1) {
            System.out.println("Stack underflow");
            return -1;
        }
        return stack[top--];
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
}
```

22. **Implement Queue using Array.**
```java
public class QueueUsingArray {
    private int[] queue;
    private int front, rear, size;
    
    public QueueUsingArray(int capacity) {
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    public void enqueue(int value) {
        if (size == queue.length) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = value;
        size++;
    }
    
    public int dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        int value = queue[front];
        front = (front + 1) % queue.length;
        size--;
        return value;
    }
}
```

23. **Maximum Subarray Sum (Kadane's Algorithm).**
```java
public class MaxSubarraySum {
    public static int maxSubArraySum(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
```

24. **Binary Search Implementation.**
```java
public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
```

25. **Merge Sort Implementation.**
```java
public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
```

### Additional Mid-Level Questions

26. **Quick Sort Implementation**
27. **Find intersection of two arrays**
28. **Selection Sort Implementation**
29. **Valid Parentheses checker**
30. **Implement HashTable/HashMap**
31. **Find duplicate number in array**
32. **Rotate matrix by 90 degrees**
33. **Longest Common Prefix**
34. **Two Sum Problem**
35. **Valid Palindrome (ignoring non-alphanumeric)**
36. **Remove duplicates from sorted array**
37. **Climbing Stairs (Dynamic Programming)**
38. **House Robber Problem**
39. **Best time to buy and sell stock**
40. **Container with most water**

---

## Senior Level Questions (5+ Years Experience)

### Complex Algorithm & Design Patterns

41. **Implement LRU (Least Recently Used) Cache.**
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

42. **Design Pattern Implementation (Singleton, Factory, Observer)**
```java
// Singleton Pattern
public class Singleton {
    private static volatile Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

// Factory Pattern
public abstract class Vehicle {
    public abstract void start();
}

public class Car extends Vehicle {
    public void start() {
        System.out.println("Car started");
    }
}

public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car": return new Car();
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}
```

43. **Producer-Consumer Problem**
```java
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 10;
    
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == capacity) {
                    wait();
                }
                buffer.offer(value++);
                System.out.println("Produced: " + (value - 1));
                notify();
                Thread.sleep(1000);
            }
        }
    }
    
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait();
                }
                int value = buffer.poll();
                System.out.println("Consumed: " + value);
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
```

### Additional Senior Level Questions

44. **Thread Pool Implementation**
45. **Custom Thread-Safe HashMap**
46. **Rate Limiter Implementation**
47. **Consistent Hashing Algorithm**
48. **Bloom Filter Implementation**
49. **Trie Data Structure**
50. **Graph algorithms (BFS, DFS)**
51. **Dijkstra's Algorithm**
52. **N-Queens Problem**
53. **Word Ladder Problem**
54. **Serialize and Deserialize Binary Tree**
55. **Design Twitter Timeline**
56. **Design URL Shortener**
57. **Design Chat System**
58. **Memory Pool Implementation**
59. **Custom Garbage Collector Logic**
60. **Distributed System Concepts**

---

## Data Structures & Algorithms

### Linked Lists

61. **Reverse a Linked List**
```java
public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class LinkedListOps {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
```

62. **Detect cycle in linked list**
63. **Find middle of linked list**
64. **Merge two sorted linked lists**
65. **Remove nth node from end**

### Trees

66. **Binary Tree Traversals (Inorder, Preorder, Postorder)**
```java
public class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeTraversal {
    public void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }
    
    public void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }
    
    public void postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.val + " ");
        }
    }
}
```

67. **Level order traversal**
68. **Maximum depth of binary tree**
69. **Validate binary search tree**
70. **Lowest common ancestor**

---

## Collections Framework

### ArrayList & LinkedList

71. **Custom ArrayList Implementation**
72. **ArrayList vs LinkedList performance comparison**
73. **Remove duplicates from ArrayList**
74. **Sort ArrayList of custom objects**
75. **Convert Array to ArrayList and vice versa**

### HashMap & HashSet

76. **Custom HashMap Implementation**
77. **HashMap collision handling**
78. **ConcurrentHashMap vs HashMap**
79. **HashSet internal working**
80. **WeakHashMap usage scenarios**

---

## Multithreading & Concurrency

### Thread Basics

81. **Create thread using Runnable and Thread class**
```java
// Using Runnable
public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }
}

// Using Thread class
public class MyThread extends Thread {
    public void run() {
        System.out.println("Thread: " + getName());
    }
}
```

82. **Thread lifecycle demonstration**
83. **ThreadLocal usage example**
84. **Volatile keyword demonstration**
85. **Synchronized methods and blocks**

### Advanced Concurrency

86. **CountDownLatch usage**
87. **CyclicBarrier implementation**
88. **Semaphore for resource management**
89. **ReadWriteLock implementation**
90. **CompletableFuture examples**

---

## OOP Concepts

### Inheritance & Polymorphism

91. **Method Overloading vs Overriding**
```java
public class Parent {
    public void display() {
        System.out.println("Parent display");
    }
    
    public void display(String msg) {
        System.out.println("Parent: " + msg);
    }
}

public class Child extends Parent {
    @Override
    public void display() {
        System.out.println("Child display");
    }
    
    // Method overloading
    public void display(int num) {
        System.out.println("Child: " + num);
    }
}
```

92. **Abstract class vs Interface**
93. **Multiple inheritance using interfaces**
94. **Constructor chaining**
95. **Static vs Instance methods**

---

## Exception Handling

### Try-Catch-Finally

96. **Custom Exception Creation**
```java
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    public void demonstrateException() throws CustomException {
        try {
            // Some risky operation
            if (someCondition()) {
                throw new CustomException("Custom error occurred");
            }
        } catch (CustomException e) {
            System.err.println("Caught: " + e.getMessage());
            throw e; // Re-throw if needed
        } finally {
            System.out.println("Cleanup code");
        }
    }
}
```

97. **Try-with-resources example**
98. **Exception propagation**
99. **Checked vs Unchecked exceptions**
100. **Finally block execution scenarios**

---

## Hibernate & JPA

### Basic Operations

101. **Entity mapping and annotations**
```java
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    // Getters and setters
}
```

102. **HQL vs Native SQL queries**
103. **Lazy vs Eager loading**
104. **First-level vs Second-level caching**
105. **Transaction management**
106. **Cascade types and operations**
107. **N+1 problem and solutions**
108. **Hibernate session lifecycle**
109. **Criteria API usage**
110. **Named queries implementation**

---

## Additional Advanced Topics

### Java 8+ Features

111. **Lambda expressions and functional interfaces**
112. **Stream API operations**
113. **Optional class usage**
114. **Method references**
115. **Default methods in interfaces**

### Spring Framework Integration

116. **Dependency injection examples**
117. **Spring Boot auto-configuration**
118. **AOP implementation**
119. **Spring Security basics**
120. **REST API development**

---

## Tips for Interview Preparation

### Technical Preparation
1. **Practice coding daily** - Implement at least 2-3 programs every day
2. **Understand time/space complexity** - Know Big O notation for all algorithms
3. **Master debugging** - Learn to trace through code execution
4. **System design basics** - Understand scalability concepts for senior roles

### Interview Strategy
1. **Ask clarifying questions** - Understand requirements before coding
2. **Think out loud** - Explain your approach step by step
3. **Test your code** - Walk through examples and edge cases
4. **Optimize when asked** - Show multiple approaches when possible

### Common Mistakes to Avoid
1. **Not handling edge cases** - Always consider null values, empty arrays, etc.
2. **Overcomplicating solutions** - Start with simple approach first
3. **Poor variable naming** - Use meaningful names for readability
4. **Not testing the code** - Always verify your solution works

---

This comprehensive guide covers the most frequently asked Java programming interview questions across all experience levels. Practice these questions regularly and understand the underlying concepts to excel in your technical interviews.

Remember: The key to success is not just memorizing solutions, but understanding the problem-solving approach and being able to adapt to variations of these questions.