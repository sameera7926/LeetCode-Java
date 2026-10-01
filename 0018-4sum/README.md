# 18. 4Sum

## Pattern: Two Pointers

---

## 1. Problem

Given an integer array `nums` and an integer `target`, find all **unique quadruplets**:

```text
nums[a] + nums[b] + nums[c] + nums[d] = target
```

All four indices must be different.

The answer must not contain duplicate quadruplets.

### Example

```text
Input:
nums = [1,0,-1,0,-2,2]
target = 0

Output:
[
    [-2,-1,1,2],
    [-2,0,0,2],
    [-1,0,0,1]
]
```

---

# 2. Identify the Pattern

### Clues

* Need **4 numbers**
* Their sum must equal a target
* Need unique combinations
* Array can be sorted
* Need better than checking every 4-number combination

Think:

```text
4Sum
 ↓
Fix 2 numbers
 ↓
Two Pointers for remaining 2
```

---

# 3. Core Idea

4Sum is basically an extension of **3Sum**.

### 3Sum

```text
Fix 1 number
+
Two Pointers
```

### 4Sum

```text
Fix 2 numbers
+
Two Pointers
```

So:

```text
for i
    for j
        left
        right
```

---

# 4. Sort the Array

First:

```java
Arrays.sort(nums);
```

Example:

```text
[1,0,-1,0,-2,2]

↓

[-2,-1,0,0,1,2]
```

Sorting helps us:

* Use Two Pointers
* Skip duplicates
* Decide which pointer to move

---

# 5. Approach

We use four positions:

```text
i
j
left
right
```

### Step 1

Fix `i`.

```text
i = 0
```

### Step 2

Fix `j`.

```text
j = i + 1
```

### Step 3

Set:

```text
left = j + 1
right = n - 1
```

### Step 4

Calculate:

```text
sum = nums[i] + nums[j] + nums[left] + nums[right]
```

---

# 6. Pointer Movement

### If:

```text
sum == target
```

We found a quadruplet.

Save it and move:

```text
left++
right--
```

---

### If:

```text
sum < target
```

The sum is too small.

Because the array is sorted:

```text
left++
```

makes the sum larger.

---

### If:

```text
sum > target
```

The sum is too large.

Move:

```text
right--
```

to make the sum smaller.

---

# 7. Java Code

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate first values
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate second values
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i]
                             + nums[j]
                             + nums[left]
                             + nums[right];

                    if (sum == target) {

                        result.add(Arrays.asList(
                            nums[i],
                            nums[j],
                            nums[left],
                            nums[right]
                        ));

                        // Skip duplicate left values
                        while (left < right &&
                               nums[left] == nums[left + 1]) {
                            left++;
                        }

                        // Skip duplicate right values
                        while (left < right &&
                               nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;

                    } else if (sum < target) {
                        left++;

                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
```

---

# 8. Code Explanation

## Step 1 — Result List

```java
List<List<Integer>> result = new ArrayList<>();
```

We need to store multiple quadruplets.

Example:

```text
[
 [-2,-1,1,2],
 [-2,0,0,2],
 [-1,0,0,1]
]
```

---

## Step 2 — Sort

```java
Arrays.sort(nums);
```

Without sorting, we cannot properly use the Two Pointer technique.

---

## Step 3 — First Loop

```java
for (int i = 0; i < n - 3; i++)
```

We fix the first number:

```text
nums[i]
```

Why `n - 3`?

Because after `i`, we still need **3 numbers**.

---

## Step 4 — Second Loop

```java
for (int j = i + 1; j < n - 2; j++)
```

Now we fix the second number:

```text
nums[j]
```

After `j`, we need:

```text
left
right
```

---

# 9. Why `j > i + 1`?

We skip duplicate second values:

```java
if (j > i + 1 && nums[j] == nums[j - 1]) {
    continue;
}
```

Important:

```text
j > i + 1
```

means:

> Don't skip the first `j` position for this particular `i`.

---

# 10. Set Two Pointers

```java
int left = j + 1;
int right = n - 1;
```

So our four positions are:

```text
i     j     left       right
↓     ↓       ↓          ↓
[-2,  -1,     0,    0,   1, 2]
```

---

# 11. Calculate Sum

```java
long sum = (long) nums[i]
         + nums[j]
         + nums[left]
         + nums[right];
```

### Why `long`?

The values can be large enough that adding four `int` values can overflow.

Casting the first value to `long` makes the whole addition happen using `long`.

---

# 12. Why `sum < target` → `left++`?

Example:

```text
sum = 3
target = 5
```

We need a bigger sum.

Because the array is sorted:

```text
left++
```

moves toward a larger value.

---

# 13. Why `sum > target` → `right--`?

Example:

```text
sum = 8
target = 5
```

We need a smaller sum.

Because the array is sorted:

```text
right--
```

moves toward a smaller value.

---

# 14. Dry Run

Input:

```text
nums = [1,0,-1,0,-2,2]
target = 0
```

After sorting:

```text
[-2,-1,0,0,1,2]
```

---

### i = 0

```text
nums[i] = -2
```

### j = 1

```text
nums[j] = -1
```

Pointers:

```text
left = 2
right = 5
```

Values:

```text
-2 + -1 + 0 + 2
= -1
```

Too small:

```text
sum < target
```

So:

```text
left++
```

---

### Now

```text
-2 + -1 + 0 + 2
```

Depending on pointer movement, we eventually find:

```text
-2 + -1 + 1 + 2 = 0
```

Add:

```text
[-2,-1,1,2]
```

---

### Another combination

With:

```text
-2 + 0 + 0 + 2
```

we get:

```text
0
```

Add:

```text
[-2,0,0,2]
```

---

### Later

With:

```text
-1 + 0 + 0 + 1
```

we get:

```text
0
```

Add:

```text
[-1,0,0,1]
```

Final answer:

```text
[
 [-2,-1,1,2],
 [-2,0,0,2],
 [-1,0,0,1]
]
```

---

# 15. Duplicate Handling

This is one of the most important parts.

There are **three places** where duplicates matter.

### 1. Duplicate `i`

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

---

### 2. Duplicate `j`

```java
if (j > i + 1 && nums[j] == nums[j - 1]) {
    continue;
}
```

---

### 3. Duplicate `left` and `right`

After finding a quadruplet:

```java
while (left < right &&
       nums[left] == nums[left + 1]) {
    left++;
}

while (left < right &&
       nums[right] == nums[right - 1]) {
    right--;
}
```

Then:

```java
left++;
right--;
```

---

# 16. 3Sum vs 4Sum

| Problem | Fixed Values | Two Pointers |
| ------- | ------------ | ------------ |
| 3Sum    | 1            | 2            |
| 4Sum    | 2            | 2            |

### 3Sum

```text
i
 ↓
[left -------- right]
```

### 4Sum

```text
i
 ↓
j
 ↓
[left -------- right]
```

So remember:

> **4Sum = 3Sum + one extra fixed loop.**

---

# 17. Why Not Four Loops?

You could theoretically do:

```text
i
 j
  k
   l
```

But that would take:

```text
O(n⁴)
```

which is far too slow.

Instead:

```text
i loop
   ↓
j loop
   ↓
Two Pointers
```

gives:

```text
O(n³)
```

---

# 18. Complexity

Sorting:

```text
O(n log n)
```

Main search:

```text
O(n³)
```

Overall:

```text
Time: O(n³)
Space: O(1)
```

Ignoring the space required for the output.

---

# 19. Common Mistakes

### ❌ Mistake 1 — Forgetting to sort

```java
Arrays.sort(nums);
```

is necessary.

---

### ❌ Mistake 2 — Not skipping duplicates

This can produce repeated quadruplets.

Remember:

```text
skip i duplicates
skip j duplicates
skip left/right duplicates
```

---

### ❌ Mistake 3 — Wrong pointer movement

```text
sum < target → left++

sum > target → right--
```

---

### ❌ Mistake 4 — Using `int sum`

For safety, use:

```java
long sum
```

because four values can overflow an `int`.

---

### ❌ Mistake 5 — Forgetting to move after finding an answer

After:

```text
sum == target
```

do:

```text
left++
right--
```

Otherwise the same pair can be processed again.

---

# 20. Interview Trigger

If you hear:

> "Find four numbers whose sum equals target."

Think immediately:

```text
4Sum
 ↓
Sort
 ↓
Fix first number
 ↓
Fix second number
 ↓
Two Pointers
 ↓
Skip duplicates
```

---

# 21. General Template

```text
Sort

for i:

    skip duplicate i

    for j:

        skip duplicate j

        left = j + 1
        right = n - 1

        while left < right:

            sum = 4 values

            if sum == target:
                save answer
                skip duplicates
                left++
                right--

            else if sum < target:
                left++

            else:
                right--
```

---

# 22. 30-Second Revision

```text
Problem:
Find unique quadruplets whose sum = target.

Pattern:
Two Pointers

Step 1:
Sort array.

Step 2:
Fix first number using i.

Step 3:
Fix second number using j.

Step 4:
left = j + 1
right = n - 1

Step 5:
Calculate 4-number sum.

If sum == target:
    save quadruplet
    skip duplicates
    left++
    right--

If sum < target:
    left++

If sum > target:
    right--

Complexity:
O(n³) time
O(1) extra space
```

## ⭐ Interview One-Liner

> **4Sum is an extension of 3Sum: sort the array, fix two numbers, then use Two Pointers to find the remaining two while skipping duplicates.**
