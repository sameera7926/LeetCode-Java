# Maximum Sum Circular Subarray

**LeetCode 918 — Maximum Sum Circular Subarray**

## 📌 Problem

Given a **circular integer array**, find the maximum possible sum of a **non-empty subarray**.

In a circular array, the last element can connect back to the first element.

### Example

```text
Input:
[5, -3, 5]

Circular array:

5 → -3 → 5
↑         ↓
└─────────┘
```

The maximum circular subarray is:

```text
[5, 5]

Sum = 10
```

**Output: `10`**

---

## 💡 Key Idea

There are **two possible cases** for the maximum subarray.

### Case 1: Normal subarray

The maximum subarray does **not wrap around**.

Use normal **Kadane's Algorithm**:

```text
[5, -3, 5]

Maximum normal subarray = [5, -3, 5]
Sum = 7
```

---

### Case 2: Circular / Wrapping subarray

The maximum subarray **wraps from the end to the beginning**.

Instead of directly finding the wrapping subarray:

```text
Total Sum - Minimum Subarray Sum
```

Example:

```text
[5, -3, 5]

Total = 7
Minimum subarray = [-3]

7 - (-3) = 10
```

So:

```text
Maximum circular sum = max(maxSum, totalSum - minSum)
```

---

## ⚠️ Important Edge Case

If **all elements are negative**, we cannot use:

```text
totalSum - minSum
```

because it would represent an empty subarray.

Example:

```text
[-3, -2, -5]
```

The answer is:

```text
-2
```

So if:

```java
maxSum < 0
```

return `maxSum`.

---

## 🔍 Dry Run

```text
arr = [5, -3, 5]
```

### Normal Kadane

```text
5 → 5
-3 → 2
5 → 7
```

```text
maxSum = 7
```

### Minimum Kadane

```text
5 → 5
-3 → -3
5 → 2
```

```text
minSum = -3
```

### Total

```text
totalSum = 5 + (-3) + 5
         = 7
```

### Circular sum

```text
totalSum - minSum

7 - (-3)
= 10
```

### Final

```text
max(7, 10) = 10
```

---

## 💻 Java Solution

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currentMax = 0;
        int maxSum = nums[0];

        int currentMin = 0;
        int minSum = nums[0];

        for (int num : nums) {

            totalSum += num;

            currentMax = Math.max(
                num,
                currentMax + num
            );

            maxSum = Math.max(
                maxSum,
                currentMax
            );

            currentMin = Math.min(
                num,
                currentMin + num
            );

            minSum = Math.min(
                minSum,
                currentMin
            );
        }

        // All elements are negative
        if (maxSum < 0) {
            return maxSum;
        }

        int circularSum = totalSum - minSum;

        return Math.max(maxSum, circularSum);
    }
}
```

---

## 🧠 Remember This

```text
Maximum Circular Subarray
        ↓
   Two possibilities
      ↙       ↘
 Normal      Circular
  Kadane      ↓
             Total - Minimum
```

So the formula is:

```text
answer = max(
    maximum normal subarray,
    total sum - minimum subarray
)
```

Except when **all numbers are negative**.

---

## 🎯 Pattern

**Kadane's Algorithm → Maximum + Minimum Kadane → Circular Array**

### Key Formula

```text
Circular Maximum = Total Sum - Minimum Subarray Sum
```

---

## ⏱️ Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```

---

## 🔑 What I Learned

* How Kadane's Algorithm works on a circular array
* How to handle wrapping subarrays
* Why `totalSum - minSum` gives the circular maximum
* Why minimum Kadane is required
* How to handle the all-negative edge case
* How to solve the problem in `O(n)` time and `O(1)` space
