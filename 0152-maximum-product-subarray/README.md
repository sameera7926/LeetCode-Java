# Maximum Product Subarray — Why We Need Max & Min

## Problem

Given an integer array `nums`, find the contiguous subarray with the largest product.

---

# Main Difference from Normal Kadane

In Maximum Subarray (Kadane), we track only:

```java
bestending
```

because we are dealing with **addition**.

For Maximum Product Subarray, we track:

```java
maxending
minending
```

because multiplication behaves differently when negative numbers are involved.

---

# Why Do We Need Both `maxending` and `minending`?

The key rule is:

```text
negative × negative = positive
```

A **minimum negative product can suddenly become the maximum product** when multiplied by another negative number.

### Example

Suppose:

```text
maxending = 4
minending = -10
nums[i] = -5
```

Multiply both:

```text
maxending × nums[i]
= 4 × -5
= -20
```

But:

```text
minending × nums[i]
= -10 × -5
= 50
```

So:

```text
Before              After × -5

MAX = 4       →     -20
MIN = -10     →      50
                       ↑
                  becomes MAX
```

Therefore, we must remember **both**.

---

# Three Possible Choices at Every Element

For every `nums[i]`, there are 3 possibilities:

```java
int v1 = nums[i];
int v2 = minending * nums[i];
int v3 = maxending * nums[i];
```

### `v1` — Start Fresh

```java
v1 = nums[i];
```

We start a new subarray from the current element.

Example:

```text
[-5]
```

---

### `v2` — Extend Previous Minimum

```java
v2 = minending * nums[i];
```

This is important when `nums[i]` is negative.

Example:

```text
minending = -10
nums[i] = -5

v2 = -10 × -5
   = 50
```

The previous minimum becomes a large positive value.

---

### `v3` — Extend Previous Maximum

```java
v3 = maxending * nums[i];
```

This is useful when the current number is positive.

Example:

```text
maxending = 10
nums[i] = 5

v3 = 10 × 5
   = 50
```

---

# Updating Maximum and Minimum

After calculating the three possibilities:

```java
maxending = Math.max(v1, Math.max(v2, v3));

minending = Math.min(v1, Math.min(v2, v3));
```

### `maxending`

Stores:

> The largest product of a subarray ending at the current index.

### `minending`

Stores:

> The smallest product of a subarray ending at the current index.

We need both because the minimum can later become the maximum.

---

# Example

```text
nums = [-2, 3, -4]
```

Initial:

```text
minending = -2
maxending = -2
res = -2
```

---

## At `3`

Three possibilities:

```text
v1 = 3
v2 = -2 × 3 = -6
v3 = -2 × 3 = -6
```

Therefore:

```text
maxending = 3
minending = -6
```

---

## At `-4`

Three possibilities:

```text
v1 = -4

v2 = minending × -4
   = -6 × -4
   = 24

v3 = maxending × -4
   = 3 × -4
   = -12
```

So:

```text
maxending = 24
minending = -12
```

And:

```text
res = 24
```

The complete subarray is:

```text
[-2, 3, -4]
```

Product:

```text
(-2) × 3 × (-4) = 24
```

---

# Visual Intuition

```text
                  nums[i]
                     ↓
          ┌──────────┼──────────┐
          ↓          ↓          ↓
       START      MIN × X     MAX × X
         v1          v2          v3
          │          │           │
          └──────────┼───────────┘
                     ↓
              ┌──────┴──────┐
              ↓             ↓
          MAXIMUM         MINIMUM
              ↓             ↓
         maxending      minending
              │
              ↓
             res
```

---

# Complete Code

```java
class Solution {
    public int maxProduct(int[] nums) {

        int minending = nums[0];
        int maxending = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int v1 = nums[i];
            int v2 = minending * nums[i];
            int v3 = maxending * nums[i];

            maxending = Math.max(v1, Math.max(v2, v3));
            minending = Math.min(v1, Math.min(v2, v3));

            res = Math.max(res, maxending);
        }

        return res;
    }
}
```

---

# Important Variables

| Variable    | Meaning                                 |
| ----------- | --------------------------------------- |
| `v1`        | Start a new subarray                    |
| `v2`        | Previous minimum × current              |
| `v3`        | Previous maximum × current              |
| `maxending` | Maximum product ending at current index |
| `minending` | Minimum product ending at current index |
| `res`       | Maximum product found anywhere          |

---

# Memory Trick

### Maximum Subarray — Sum

```text
Keep → best
```

### Maximum Product Subarray

```text
Keep → best + worst
```

Why?

```text
MINIMUM × NEGATIVE = MAXIMUM 🔥
```

> **For product, never throw away the minimum. It may become the maximum when a negative number arrives.**

---

# Complexity

```text
Time  → O(n)
Space → O(1)
```

We scan the array once and only store a few variables.
