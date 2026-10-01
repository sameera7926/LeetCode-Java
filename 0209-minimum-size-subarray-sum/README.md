# 209. Minimum Size Subarray Sum

## 🧩 Problem

Given an array of **positive integers** `nums` and an integer `target`, find the **minimum length of a contiguous subarray** whose sum is greater than or equal to `target`.

If no such subarray exists, return `0`.

### Example

```text
target = 7
nums = [2,3,1,2,4,3]

Answer = 2
```

Because:

```text
[4,3] → sum = 7 → length = 2
```

---

# 🔍 Pattern

**Variable Size Sliding Window**

### Why?

The window size is not fixed.

We:

```text
RIGHT → expand the window
LEFT  → shrink the window
```

We shrink whenever:

```text
sum >= target
```

Because we want the **minimum length**.

---

# 💡 Approach

Maintain:

* `left` → starting point of the window
* `right` → ending point of the window
* `sum` → sum of the current window
* `answer` → minimum window length found

### Flow

```text
right moves
    ↓
add nums[right] to sum
    ↓
sum >= target?
    ↓ YES
calculate window length
    ↓
remove nums[left] from sum
    ↓
left++
    ↓
check again
```

### Important Rule

For **minimum/shortest** Sliding Window problems:

```text
while (window is VALID)
    calculate answer
    shrink from LEFT
```

---

# 💻 Java Code

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                answer = Math.min(answer, right - left + 1);

                sum -= nums[left];
                left++;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}
```

---

# 🧠 Why `Integer.MAX_VALUE`?

We are looking for the **minimum** length.

So initially we need a very large value:

```java
int answer = Integer.MAX_VALUE;
```

Then:

```java
answer = Math.min(answer, windowLength);
```

Example:

```text
answer = 2147483647
window = 4

min(2147483647, 4) = 4
```

Later:

```text
min(4, 2) = 2
```

So the smallest length is stored.

---

# ❓ Why `while`, not `if`?

Because after finding a valid window, we want to keep shrinking it.

Example:

```text
[2,3,1,2] → sum = 8
```

Target:

```text
7
```

The window is valid.

Remove `2`:

```text
[3,1,2] → sum = 6
```

Now it becomes invalid.

But sometimes multiple elements can be removed while the window is still valid.

Therefore we use:

```java
while (sum >= target)
```

not just:

```java
if (sum >= target)
```

---

# 🔄 Dry Run

### Input

```text
target = 7
nums = [2,3,1,2,4,3]
```

### Step 1

```text
Window: [2]
sum = 2
```

Not valid.

```text
2 < 7
```

Expand.

---

### Step 2

```text
Window: [2,3]
sum = 5
```

Not valid.

```text
5 < 7
```

Expand.

---

### Step 3

```text
Window: [2,3,1]
sum = 6
```

Not valid.

Expand.

---

### Step 4

```text
Window: [2,3,1,2]
sum = 8
```

Valid:

```text
8 >= 7
```

Length:

```text
4
```

So:

```text
answer = 4
```

Now shrink.

Remove `2`:

```text
Window: [3,1,2]
sum = 6
```

Invalid.

Stop shrinking.

---

### Step 5

Add `4`:

```text
Window: [3,1,2,4]
sum = 10
```

Valid.

Length:

```text
4
```

Answer remains:

```text
4
```

Shrink.

Remove `3`:

```text
Window: [1,2,4]
sum = 7
```

Still valid.

Length:

```text
3
```

Update:

```text
answer = 3
```

Shrink again.

Remove `1`:

```text
Window: [2,4]
sum = 6
```

Invalid.

---

### Step 6

Add `3`:

```text
Window: [2,4,3]
sum = 9
```

Valid.

Length:

```text
3
```

Answer remains:

```text
3
```

Shrink.

Remove `2`:

```text
Window: [4,3]
sum = 7
```

Still valid.

Length:

```text
2
```

Update:

```text
answer = 2
```

Shrink again.

Remove `4`:

```text
Window: [3]
sum = 3
```

Invalid.

Final:

```text
answer = 2
```

---

# 🎯 Important Sliding Window Rules

### For LONGEST problems:

```text
Expand
 ↓
If INVALID → shrink
 ↓
Calculate MAX
```

### For SHORTEST problems:

```text
Expand
 ↓
If VALID → calculate MIN
 ↓
Keep shrinking
```

So for LC 209:

```text
Minimum
   ↓
while(valid)
   ↓
calculate MIN
   ↓
shrink
```

---

# ⚠️ Important Line

When `left` moves, the element leaving the window must be removed from `sum`.

```java
sum -= nums[left];
left++;
```

Think:

```text
RIGHT enters → ADD
LEFT leaves   → REMOVE
```

---

# 🧠 Why Sliding Window Works Here

The array contains **positive integers**.

Therefore:

```text
right moves → sum increases
left moves  → sum decreases
```

This predictable behavior allows us to expand and shrink the window efficiently.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Even though there is a `for` loop and a `while` loop, both `left` and `right` move forward through the array only once.

### Space Complexity

```text
O(1)
```

We only use a few variables.

---

# 🎤 Interview Explanation

> "I use a variable-size sliding window. I expand the window using the right pointer and maintain the current sum. Whenever the sum becomes greater than or equal to the target, the window is valid, so I update the minimum length and shrink the window from the left while it remains valid. Since all numbers are positive, removing elements always decreases the sum. The time complexity is O(n) and space complexity is O(1)."

---

# 🧠 One-Line Memory Trick

```text
Minimum Subarray Sum
        ↓
Variable Sliding Window
        ↓
sum >= target
        ↓
calculate MIN
        ↓
shrink LEFT
```

### Pattern

```text
RIGHT → ADD
LEFT  → REMOVE
VALID → SHRINK
MINIMUM → Math.min()
```
