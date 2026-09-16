# Kadane's Algorithm — Maximum Subarray

## Problem

Given an integer array `nums`, find the **contiguous subarray** with the largest sum and return its sum.

---

## Key Idea

At every element, we have **2 choices**:

1. **Continue** the previous subarray.
2. **Start a new subarray** from the current element.

```java
int v1 = bestending + nums[i]; // Continue
int v2 = nums[i];              // Start fresh

bestending = Math.max(v1, v2);
ans = Math.max(ans, bestending);
```

### Meaning of `v1` and `v2`

```text
v1 = bestending + nums[i]
    → Continue the previous subarray

v2 = nums[i]
    → Start a new subarray from nums[i]
```

We choose whichever gives the larger sum.

---

# Test Cases

## Test Case 1 — Normal Case

### Input

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

### Best Subarray

```text
[4, -1, 2, 1]
```

### Sum

```text
4 + (-1) + 2 + 1 = 6
```

### Output

```text
6
```

---

## Test Case 2 — All Positive

### Input

```text
nums = [1, 2, 3, 4]
```

Every time, continuing gives a larger sum.

```text
1 → 3 → 6 → 10
```

### Output

```text
10
```

Best subarray:

```text
[1, 2, 3, 4]
```

---

## Test Case 3 — All Negative

### Input

```text
nums = [-5, -2, -8, -1]
```

The largest value is `-1`.

### Output

```text
-1
```

This is why we should NOT initialize `ans` and `bestending` to `0`.

Instead:

```java
int bestending = nums[0];
int ans = nums[0];
```

---

## Test Case 4 — Restart Is Better

### Input

```text
nums = [-5, 3]
```

At `3`:

```text
v1 = -5 + 3
   = -2

v2 = 3
```

Compare:

```text
max(-2, 3) = 3
```

So we **start a new subarray**:

```text
[3]
```

### Output

```text
3
```

---

## Test Case 5 — Continue Is Better

### Input

```text
nums = [4, -1, 2]
```

At `-1`:

```text
v1 = 4 + (-1) = 3
v2 = -1
```

Continue:

```text
[4, -1] → 3
```

At `2`:

```text
v1 = 3 + 2 = 5
v2 = 2
```

Continue:

```text
[4, -1, 2] → 5
```

### Output

```text
5
```

---

# Dry Run

For:

```text
[-2, 1, -3, 4, -1, 2, 1]
```

| Element | Continue (`v1`) | Start New (`v2`) | `bestending` | `ans` |
| ------: | --------------: | ---------------: | -----------: | ----: |
|      -2 |               — |                — |           -2 |    -2 |
|       1 |              -1 |                1 |            1 |     1 |
|      -3 |              -2 |               -3 |           -2 |     1 |
|       4 |               2 |                4 |            4 |     4 |
|      -1 |               3 |               -1 |            3 |     4 |
|       2 |               5 |                2 |            5 |     5 |
|       1 |               6 |                1 |            6 |     6 |

### Final Answer

```text
6
```

---

# Important Intuition

The question Kadane asks at every index is:

```text
Should I continue?
        OR
Should I restart?
```

```text
Continue → bestending + nums[i]

Restart  → nums[i]

Take     → Math.max(continue, restart)
```

### Memory Trick

```text
v1 = CONTINUE
v2 = RESTART
bestending = BEST subarray ending HERE
ans = BEST sum seen ANYWHERE
```

---

# Complexity

```text
Time  → O(n)
Space → O(1)
```

Kadane is essentially a **space-optimized DP approach** because we only keep the previous `bestending` value instead of storing a complete `dp[]` array.
