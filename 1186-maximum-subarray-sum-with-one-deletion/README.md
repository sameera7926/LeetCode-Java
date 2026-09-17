# Maximum Subarray Sum with One Deletion

**LeetCode 1186 — Maximum Subarray Sum with One Deletion**

## 📌 Problem

Given an integer array `arr`, find the maximum sum of a **non-empty subarray** after deleting **at most one element**.

### Example

```text
Input:
[1, -2, 0, 3]

Delete -2:

[1, 0, 3]

Maximum Sum = 4
```

## 💡 Approach

This problem is an extension of **Kadane's Algorithm**.

We maintain two values:

* `noDelete` → maximum subarray sum ending at the current index **without deleting any element**
* `oneDelete` → maximum subarray sum ending at the current index **after deleting exactly one element**

### 1. Without deletion

For the current element `x`:

```java
newNoDelete = Math.max(x, noDelete + x);
```

We either:

* Start a new subarray from `x`
* Extend the previous subarray

### 2. With one deletion

```java
newOneDelete = Math.max(oneDelete + x, noDelete);
```

There are two possibilities:

* `oneDelete + x` → the deletion was already used earlier
* `noDelete` → delete the current element `x`

## 🔍 Dry Run

For:

```text
arr = [1, -2, 0, 3]
```

| Element | noDelete | oneDelete | Result |
| ------: | -------: | --------: | -----: |
|       1 |        1 |         — |      1 |
|      -2 |       -1 |         1 |      1 |
|       0 |        0 |         1 |      1 |
|       3 |        3 |         4 |  **4** |

At `-2`, we can delete it:

```text
[1, -2, 0, 3]
    ↓
[1, 0, 3]

Sum = 4
```

## 💻 Java Solution

```java
class Solution {
    public int maximumSum(int[] arr) {

        int noDelete = arr[0];
        int oneDelete = Integer.MIN_VALUE;
        int result = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int x = arr[i];

            int newNoDelete =
                Math.max(x, noDelete + x);

            int newOneDelete =
                Math.max(oneDelete + x, noDelete);

            noDelete = newNoDelete;
            oneDelete = newOneDelete;

            result = Math.max(
                result,
                Math.max(noDelete, oneDelete)
            );
        }

        return result;
    }
}
```

## 🧠 Key Insight

Normal Kadane's Algorithm tracks only:

```text
maximum sum without deletion
```

Here we track:

```text
noDelete
    ↓
No element deleted

oneDelete
    ↓
One element deleted
```

So the problem becomes:

> **Kadane's Algorithm + one extra state**

## ⏱️ Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```

## 🎯 Pattern

**Kadane's Algorithm → Modified Kadane → State Tracking → One Deletion**

## 🔑 What I Learned

* How Kadane's Algorithm can be extended
* Why two states are required
* How to handle deletion of the current element
* How to maintain `O(n)` time and `O(1)` space
