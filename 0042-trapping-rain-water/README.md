# 42. Trapping Rain Water

## Pattern: Two Pointers

---

## 1. Problem

You are given an array where each number represents the height of a vertical bar.

After raining, water can be trapped between the bars.

Return the **total amount of trapped water**.

### Example

```text
Input:
height = [0,1,0,2,1,0,1,3,2,1,2,1]

Output:
6
```

The total trapped water is:

```text
6
```

---

# 2. Identify the Pattern

### Clues

Look for:

* Array of heights
* Water trapped between bars
* Need total water
* Left and right boundaries matter
* Can solve using constant extra space

Think:

```text
Two Pointers
    ↓
left + right
    ↓
leftMax + rightMax
```

---

# 3. Core Idea

For every position:

```text
Water = min(leftMax, rightMax) - height[i]
```

The water level is controlled by the **smaller boundary**.

Instead of creating separate left and right arrays, maintain:

```text
leftMax
rightMax
```

and use two pointers:

```text
left = 0
right = n - 1
```

---

# 4. Java Code

```java
class Solution {
    public int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            if (height[left] <= height[right]) {

                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }

                left++;

            } else {

                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }

                right--;
            }
        }

        return water;
    }
}
```

---

# 5. Important Variables

### `left`

```java
int left = 0;
```

Starts from the beginning.

### `right`

```java
int right = height.length - 1;
```

Starts from the end.

### `leftMax`

Highest bar seen from the left.

### `rightMax`

Highest bar seen from the right.

### `water`

Stores the total trapped water.

---

# 6. Why Compare `height[left]` and `height[right]`?

```java
if (height[left] <= height[right])
```

If the left bar is smaller, then the **left side is the limiting side**.

So we can safely calculate water using:

```text
leftMax - height[left]
```

Otherwise, process the right side:

```text
rightMax - height[right]
```

### Remember:

```text
smaller boundary → process that side
```

---

# 7. Dry Run

Use a small example:

```text
height = [4,2,0,3,2,5]
```

Visual:

```text
5             █
4 █           █
3 █     █     █
2 █ █   █ █   █
1 █ █   █ █   █
  ─────────────
```

The trapped water is:

```text
9
```

### Main calculations

| Position | Height | Boundary | Water |
| -------: | -----: | -------: | ----: |
|        0 |      4 |        4 |     0 |
|        1 |      2 |        4 |     2 |
|        2 |      0 |        4 |     4 |
|        3 |      3 |        4 |     1 |
|        4 |      2 |        4 |     2 |
|        5 |      5 |        5 |     0 |

Total:

```text
2 + 4 + 1 + 2 = 9
```

---

# 8. How `leftMax` Works

Suppose:

```text
height[left] = 4
leftMax = 4
```

Then:

```text
water = leftMax - height[left]
```

If the current height is `2`:

```text
water = 4 - 2
      = 2
```

So 2 units of water are trapped above that bar.

---

# 9. Important Condition

```java
if (height[left] >= leftMax)
```

If the current bar is taller than the previous maximum:

```text
update leftMax
```

Otherwise:

```text
water += leftMax - height[left]
```

Same logic applies to the right side.

---

# 10. Why We Don't Need Two Arrays

A common approach is:

```text
leftMax[i]
rightMax[i]
```

Then calculate water for every position.

That takes:

```text
O(n) extra space
```

With Two Pointers, we only keep:

```text
leftMax
rightMax
```

So:

```text
Space = O(1)
```

---

# 11. Common Mistakes

### ❌ Mistake 1 — Using the taller boundary

Water depends on:

```text
min(leftMax, rightMax)
```

not the maximum.

---

### ❌ Mistake 2 — Forgetting to subtract bar height

Wrong:

```text
water = leftMax
```

Correct:

```text
water = leftMax - height[left]
```

---

### ❌ Mistake 3 — Moving both pointers every time

Don't blindly do:

```text
left++
right++
```

Process the side with the **smaller current boundary**.

---

# 12. Complexity

```text
Time: O(n)
Space: O(1)
```

Each pointer moves across the array only once.

---

# 13. Interview Trigger

If you hear:

> "Given heights of bars, calculate how much rainwater can be trapped."

Think:

```text
Trapping Rain Water
        ↓
Two Pointers
        ↓
leftMax + rightMax
        ↓
process smaller side
```

---

# 14. 30-Second Revision

```text
Pattern:
Two Pointers

left = 0
right = n - 1

leftMax = 0
rightMax = 0

while left < right:

    if height[left] <= height[right]:

        if height[left] >= leftMax:
            leftMax = height[left]
        else:
            water += leftMax - height[left]

        left++

    else:

        if height[right] >= rightMax:
            rightMax = height[right]
        else:
            water += rightMax - height[right]

        right--

Return water

Time: O(n)
Space: O(1)
```

## ⭐ Interview One-Liner

> **Use two pointers with `leftMax` and `rightMax`; process the side with the smaller current height because that side determines the possible water level.**
