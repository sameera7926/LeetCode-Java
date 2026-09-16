# 1. Two Sum

## Problem

Given an integer array `nums` and an integer `target`, return the **indices of the two numbers** whose sum equals `target`.

### Important Conditions

* There is **exactly one valid answer**.
* We cannot use the **same element twice**.
* The answer can be returned in any order.

---

# Example

```text
nums = [2, 7, 11, 15]
target = 9
```

We need two numbers whose sum is `9`.

```text
2 + 7 = 9
```

Therefore:

```text
Output = [0, 1]
```

Because:

```text
nums[0] = 2
nums[1] = 7
```

---

# Brute Force Approach

Check every possible pair.

```java
for (int i = 0; i < nums.length; i++) {

    for (int j = i + 1; j < nums.length; j++) {

        if (nums[i] + nums[j] == target) {
            return new int[]{i, j};
        }
    }
}
```

### Complexity

```text
Time  → O(n²)
Space → O(1)
```

This works, but the follow-up asks for something **less than O(n²)**.

---

# Better Approach — HashMap

We can solve Two Sum in:

```text
O(n)
```

using a `HashMap`.

## Main Idea

For every number:

```text
needed = target - current number
```

Then ask:

> Have I already seen the number I need?

If yes → we found the answer.

If no → store the current number and its index.

---

# Example

```text
nums = [2, 7, 11, 15]
target = 9
```

### Step 1

Current number:

```text
2
```

Calculate:

```text
needed = 9 - 2
       = 7
```

Have we seen `7`?

```text
NO
```

Store:

```text
Map:
2 → 0
```

---

### Step 2

Current number:

```text
7
```

Calculate:

```text
needed = 9 - 7
       = 2
```

Have we seen `2`?

```text
YES
```

Its index is:

```text
0
```

Current index:

```text
1
```

Therefore:

```text
[0, 1]
```

🔥 Done.

---

# Visual Flow

```text
Current number
      ↓
needed = target - current
      ↓
Is needed in HashMap?
      ↓
   ┌──YES───→ Return [map.get(needed), currentIndex]
   │
   NO
   ↓
Store current number + index
   ↓
Move to next element
```

---

# Java Code

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int needed = target - nums[i];

            if (map.containsKey(needed)) {
                return new int[]{map.get(needed), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }
}
```

---

# Why `needed = target - nums[i]`?

Suppose:

```text
target = 9
current = 2
```

We need:

```text
2 + ? = 9
```

Therefore:

```text
? = 9 - 2
? = 7
```

So:

```java
int needed = target - nums[i];
```

means:

> **What number do I need to complete the target?**

---

# Why HashMap?

A HashMap lets us quickly check whether a number already exists.

```java
map.containsKey(needed)
```

and get its index:

```java
map.get(needed)
```

Average time:

```text
O(1)
```

So instead of checking every pair, we check each element once.

---

# Test Cases

## Test Case 1 — Normal Case

```text
Input:
nums = [2,7,11,15]
target = 9

Output:
[0,1]
```

Because:

```text
2 + 7 = 9
```

---

## Test Case 2 — Answer Not at the Beginning

```text
Input:
nums = [3,2,4]
target = 6

Output:
[1,2]
```

Because:

```text
2 + 4 = 6
```

---

## Test Case 3 — Duplicate Numbers

```text
Input:
nums = [3,3]
target = 6

Output:
[0,1]
```

Important:

We must use **two different indices**.

```text
nums[0] = 3
nums[1] = 3
```

So this is valid.

---

## Test Case 4 — Negative Numbers

```text
Input:
nums = [-3,4,3,90]
target = 0

Output:
[0,2]
```

Because:

```text
-3 + 3 = 0
```

---

## Test Case 5 — Negative Target

```text
Input:
nums = [-1,-2,-3,-4]
target = -7

Output:
[2,3]
```

Because:

```text
-3 + (-4) = -7
```

---

# Dry Run

```text
nums = [2, 7, 11, 15]
target = 9
```

| i | nums[i] | needed | Map before | Action                     |
| - | ------: | -----: | ---------- | -------------------------- |
| 0 |       2 |      7 | `{}`       | Store `2 → 0`              |
| 1 |       7 |      2 | `{2 → 0}`  | Found `2` → return `[0,1]` |

---

# Important: Why Check Before `put()`?

We do:

```java
if (map.containsKey(needed)) {
    return new int[]{map.get(needed), i};
}

map.put(nums[i], i);
```

not:

```java
map.put(nums[i], i);

if (map.containsKey(needed)) {
    ...
}
```

Because we are not allowed to use the **same element twice**.

For example:

```text
nums = [3,3]
target = 6
```

When processing the first `3`, we don't want to use that same `3` as both numbers.

By checking first and storing afterward, the current element is only matched with an **earlier element**.

---

# Core Pattern

Two Sum teaches an important HashMap pattern:

```text
Current value
      ↓
Calculate what is needed
      ↓
Check if needed was seen before
      ↓
YES → Answer
NO  → Store current value
```

### Memory Trick

```text
needed = target - current
```

> **"What do I need to complete the target?"**

---

# Complexity

```text
Time  → O(n)
Space → O(n)
```

We traverse the array once.

The HashMap can contain up to `n` elements.

---

# Final Comparison

```text
Brute Force
O(n²) time
O(1) space

        ↓

HashMap
O(n) time
O(n) space
```

For the follow-up asking for **less than O(n²)**, the HashMap approach gives an **O(n)** solution.
