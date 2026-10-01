# 11. Container With Most Water

## Pattern: Two Pointers

---

## 1. Problem

You are given an array `height`.

Each value represents the height of a vertical line.

Choose **two lines** that can form a container with the x-axis.

The goal is to find the **maximum amount of water** the container can hold.

### Example

```text
height = [1,8,6,2,5,4,8,3,7]

Output = 49
```

The best container is formed by:

```text
height[1] = 8
height[8] = 7
```

---

## 2. Understand the Formula

The amount of water depends on:

```text
Area = Width × Height
```

### Width

```text
right - left
```

### Height

We must use the **shorter line**:

```text
min(height[left], height[right])
```

Therefore:

```text
Area = (right - left) × min(height[left], height[right])
```

---

## 3. Identify the Pattern

### Clues

Look for:

* Array
* Choose two elements
* Need maximum area
* Distance between two elements matters
* Need to avoid checking every pair

👉 This suggests **Two Pointers**.

---

## 4. Brute Force Approach

Try every possible pair.

```text
for i = 0 → n-1
    for j = i+1 → n-1

        width = j - i
        height = min(height[i], height[j])

        area = width × height

        take maximum
```

### Complexity

```text
Time: O(n²)
Space: O(1)
```

This is too slow when:

```text
n = 100000
```

---

# 5. Optimal Approach — Two Pointers

Use two pointers:

```text
left  = 0
right = n - 1
```

Start with the **widest possible container**.

At every step:

1. Calculate the current area.
2. Update the maximum.
3. Move the pointer with the **smaller height**.

### Why move the smaller one?

Suppose:

```text
left height  = 8
right height = 5
```

The water height is limited by `5`.

If we move the taller line:

```text
8 → another position
```

the width becomes smaller, but the limiting height can still be `5` or less.

So there is no reason to keep the taller line.

Instead:

```text
move the smaller height
```

This gives us a chance to find a taller boundary.

---

# 6. Java Code

```java
class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;

        while (left < right) {

            int width = right - left;

            int currentHeight = Math.min(height[left], height[right]);

            int area = width * currentHeight;

            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
```

---

# 7. Code Explanation

### Step 1: Create two pointers

```java
int left = 0;
int right = height.length - 1;
```

We start from both ends.

Why?

Because this gives us the **maximum possible width**.

---

### Step 2: Continue until pointers meet

```java
while (left < right)
```

We need two different lines.

When:

```text
left == right
```

there is only one line, so no container is possible.

---

### Step 3: Calculate width

```java
int width = right - left;
```

Example:

```text
left = 1
right = 8

width = 8 - 1
      = 7
```

---

### Step 4: Find limiting height

```java
int currentHeight = Math.min(height[left], height[right]);
```

The shorter line determines how much water we can hold.

Example:

```text
height[left]  = 8
height[right] = 7

currentHeight = 7
```

---

### Step 5: Calculate area

```java
int area = width * currentHeight;
```

Formula:

```text
Area = width × shorter height
```

---

### Step 6: Update maximum

```java
maxArea = Math.max(maxArea, area);
```

If the current container is bigger, store it.

---

### Step 7: Move the smaller pointer

```java
if (height[left] < height[right]) {
    left++;
} else {
    right--;
}
```

### Important interview rule:

> **Always move the pointer having the smaller height.**

---

# 8. Dry Run

Input:

```text
[1,8,6,2,5,4,8,3,7]
```

Start:

```text
left = 0
right = 8
```

| Left | Right | Heights | Width | Area | Move    |
| ---: | ----: | ------- | ----: | ---: | ------- |
|    0 |     8 | 1, 7    |     8 |    8 | left++  |
|    1 |     8 | 8, 7    |     7 |   49 | right-- |
|    1 |     7 | 8, 3    |     6 |   18 | right-- |
|    1 |     6 | 8, 8    |     5 |   40 | right-- |
|    1 |     5 | 8, 4    |     4 |   16 | right-- |
|    1 |     4 | 8, 5    |     3 |   15 | right-- |
|    1 |     3 | 8, 2    |     2 |    4 | right-- |
|    1 |     2 | 8, 6    |     1 |    6 | right-- |

Maximum:

```text
49
```

Therefore:

```text
Output = 49
```

---

# 9. Visual Thinking

Think of it like this:

```text
height = [1,8,6,2,5,4,8,3,7]

          8                   7
          │                   │
          │~~~~~~~~~~~~~~~~~~~│
          │                   │
          │                   │
          └───────────────────┘
             ←── width ─────→
```

Water level is controlled by:

```text
min(8, 7) = 7
```

So:

```text
Area = 7 × 7
     = 49
```

---

# 10. Why Two Pointers Works

Initially:

```text
left = 0
right = n-1
```

This gives the largest possible width.

After calculating the area:

* If left height is smaller → move `left`
* If right height is smaller → move `right`

We reduce the width while trying to find a taller limiting line.

This allows us to check the useful possibilities in **one pass** instead of checking every pair.

---

# 11. Complexity

```text
Time Complexity: O(n)
Space Complexity: O(1)
```

### Why O(n)?

Each pointer only moves toward the other pointer.

```text
left  → moves at most n times
right → moves at most n times
```

No nested loop.

---

# 12. Common Mistakes

### ❌ Mistake 1: Using the taller height

Wrong:

```java
Math.max(height[left], height[right])
```

Correct:

```java
Math.min(height[left], height[right])
```

The shorter line limits the water.

---

### ❌ Mistake 2: Moving the taller pointer

Wrong idea:

```text
if left is taller → move left
```

Correct:

```text
move the smaller-height pointer
```

---

### ❌ Mistake 3: Using width incorrectly

Wrong:

```java
right - left + 1
```

Correct:

```java
right - left
```

Because the distance between the x-coordinates is:

```text
right - left
```

---

### ❌ Mistake 4: Using O(n²) brute force

Two nested loops work logically, but they are too slow for:

```text
n = 100000
```

Use Two Pointers.

---

# 13. Interview Trigger

When you see:

> "Choose two elements/lines and maximize the area between them."

Think:

```text
TWO POINTERS
     ↓
left = 0
right = n - 1
     ↓
calculate area
     ↓
move smaller height
```

---

# 14. 30-Second Revision

```text
Pattern:
Two Pointers

Formula:
Area = (right - left) × min(height[left], height[right])

Start:
left = 0
right = n - 1

Loop:
while (left < right)

Calculate:
width = right - left
height = min(height[left], height[right])
area = width × height

Update:
maxArea = max(maxArea, area)

Move:
smaller height pointer

Complexity:
O(n) time
O(1) space
```

## ⭐ Interview One-Liner

> **Start from both ends, calculate the area, and always move the pointer with the smaller height because the shorter line limits the water.**
