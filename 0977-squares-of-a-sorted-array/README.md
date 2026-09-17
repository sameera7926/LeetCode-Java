# Squares of a Sorted Array

**LeetCode 977 — Squares of a Sorted Array**

## 📌 Problem

Given an integer array `nums` sorted in **non-decreasing order**, return an array containing the **squares of every number**, also sorted in non-decreasing order.

### Example

```text
Input:
[-4, -1, 0, 3, 10]

Output:
[0, 1, 9, 16, 100]
```

---

## 💡 Key Idea

If we simply square the numbers:

```text
[-4, -1, 0, 3, 10]
 ↓   ↓  ↓  ↓   ↓
[16,  1, 0, 9, 100]
```

The result is **not sorted**.

The important observation is:

> The largest square will come from either the **leftmost** or **rightmost** element.

Why?

Because the array is sorted, so the largest absolute value must be at one of the two ends.

Therefore, use **Two Pointers**.

```text
left →                    ← right
[-4, -1, 0, 3, 10]
```

Compare:

```text
abs(nums[left])
vs
abs(nums[right])
```

Put the larger square at the **end** of the result array.

---

## 🔍 Dry Run

```text
nums = [-4, -1, 0, 3, 10]
```

Start:

```text
left = 0
right = 4

result = [_, _, _, _, _]
```

### Step 1

```text
(-4)² = 16
(10)² = 100

100 is larger
```

Put `100` at the end:

```text
[_, _, _, _, 100]
```

Move `right`.

---

### Step 2

```text
(-4)² = 16
(3)² = 9

16 is larger
```

```text
[_, _, _, 16, 100]
```

Move `left`.

---

### Step 3

```text
(-1)² = 1
(3)² = 9

9 is larger
```

```text
[_, _, 9, 16, 100]
```

Move `right`.

---

### Step 4

```text
(-1)² = 1
(0)² = 0

1 is larger
```

```text
[_, 1, 9, 16, 100]
```

---

### Step 5

```text
[0, 1, 9, 16, 100]
```

Final answer:

```text
[0, 1, 9, 16, 100]
```

---

## 💻 Java Solution

```java
class Solution {
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;

        int[] result = new int[n];

        int left = 0;
        int right = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[i] = leftSquare;
                left++;
            } else {
                result[i] = rightSquare;
                right--;
            }
        }

        return result;
    }
}
```

---

## 🧠 Why Fill From Right to Left?

We are finding the **largest square first**.

So place it at the last available position:

```text
i = n - 1
```

Then:

```text
largest → end
second largest → before it
third largest → before it
...
```

This automatically gives sorted order.

---

## 🎯 Pattern

**Sorted Array → Two Pointers → Compare Both Ends**

### Remember:

```text
left square  vs  right square
       ↓
   larger one
       ↓
put it at result[i]
       ↓
move that pointer
```

---

## ⏱️ Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(n)
```

`O(n)` extra space is required for the result array.

---

## 🔑 What I Learned

* How to use two pointers on a sorted array
* Why squaring can destroy sorted order
* Why the largest square is always at one of the two ends
* Why the result is filled from right to left
* How to solve the problem in `O(n)` time
