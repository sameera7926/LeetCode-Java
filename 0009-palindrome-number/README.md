...# 3Sum — LeetCode 15

## Pattern: Two Pointers

---

## 1. What is this pattern?

Two Pointers means using two indexes to move through an array instead of checking every possible pair.

For 3Sum, we first fix one number and then use two pointers to find the other two numbers.

---

## 2. When should I recognize this pattern?

Think **Two Pointers** when you see:

* Sorted array
* Pair or triplet
* Target sum
* Need to find combinations
* Need to avoid brute force
* Need unique pairs or triplets

### For 3Sum

> Array + triplet + target sum → Think Two Pointers

---

## 3. Core Idea

We need to find three numbers:

```text
a + b + c = 0
```

Example:

```text
[-1, 0, 1, 2, -1, -4]
```

### Step 1: Sort the array

```text
[-4, -1, -1, 0, 1, 2]
```

### Step 2: Fix one number

```text
i
↓
[-4, -1, -1, 0, 1, 2]
```

### Step 3: Use two pointers

```text
       left        right
         ↓           ↓
[-4, -1, -1, 0, 1, 2]
```

Calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

Then:

```text
sum < 0  → left++
sum > 0  → right--
sum == 0 → found triplet
```

---

# 4. General Template

```java
Arrays.sort(nums);

for (int i = 0; i < nums.length - 2; i++) {

    if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
    }

    int left = i + 1;
    int right = nums.length - 1;

    while (left < right) {

        int sum = nums[i] + nums[left] + nums[right];

        if (sum == 0) {

            // found answer

            left++;
            right--;

        } else if (sum < 0) {
            left++;

        } else {
            right--;
        }
    }
}
```

## Important Variables

| Variable | Meaning                  |
| -------- | ------------------------ |
| `i`      | First/fixed number       |
| `left`   | Second number            |
| `right`  | Third number             |
| `sum`    | Sum of the three numbers |

### Why do we need `i`?

`i` fixes the first number of the triplet.

Example:

```text
[-4, -1, -1, 0, 1, 2]
 ↑
 i
```

Then we search for the other two numbers.

### Why do we need `left`?

`left` searches for the second number.

It starts at:

```java
int left = i + 1;
```

### Why do we need `right`?

`right` searches for the third number.

It starts at:

```java
int right = nums.length - 1;
```

### Why `sum < 0 → left++`?

The array is sorted.

If the sum is too small, we need a bigger number.

So:

```java
left++;
```

### Why `sum > 0 → right--`?

If the sum is too large, we need a smaller number.

So:

```java
right--;
```

---

# 5. Important Problem

## 3Sum — LeetCode 15

### Problem in simple words

Given an integer array, find all unique triplets:

```text
[a, b, c]
```

such that:

```text
a + b + c = 0
```

Example:

```text
nums = [-1,0,1,2,-1,-4]
```

Output:

```text
[[-1,-1,2],[-1,0,1]]
```

---

## Pattern

**Two Pointers**

---

## How to recognize it

The problem asks for:

* 3 numbers
* A target sum
* All valid combinations
* Unique triplets

Therefore:

```text
Triplet + target sum → Two Pointers
```

---

## Approach

### Step 1: Sort the array

```java
Arrays.sort(nums);
```

Example:

```text
[-1,0,1,2,-1,-4]

↓

[-4,-1,-1,0,1,2]
```

Sorting is important because it allows us to decide which pointer to move.

---

### Step 2: Fix one number

```java
for (int i = 0; i < nums.length - 2; i++)
```

`i` represents the first number.

---

### Step 3: Set two pointers

```java
int left = i + 1;
int right = nums.length - 1;
```

Now we search for the other two numbers.

---

### Step 4: Calculate the sum

```java
int sum = nums[i] + nums[left] + nums[right];
```

---

### Step 5: Move the correct pointer

```text
sum == 0
→ Found a triplet

sum < 0
→ left++

sum > 0
→ right--
```

---

### Step 6: Skip duplicates

We need unique triplets.

For example, we don't want:

```text
[-1,-1,2]
[-1,-1,2]
```

So we skip duplicate values.

---

# Java Code

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate first numbers
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(
                        nums[i],
                        nums[left],
                        nums[right]
                    ));

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right &&
                           nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                           nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    left++;

                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
```

---

# Important Java Syntax

## `Arrays.sort(nums)`

```java
Arrays.sort(nums);
```

Sorts the array in ascending order.

Example:

```text
[3,-1,2]

↓

[-1,2,3]
```

---

## `List<List<Integer>>`

```java
List<List<Integer>> result;
```

The answer contains multiple triplets.

Example:

```text
[
    [-1,-1,2],
    [-1,0,1]
]
```

Therefore we need a list containing other lists.

---

## `result.add()`

```java
result.add(Arrays.asList(
    nums[i],
    nums[left],
    nums[right]
));
```

Adds the current triplet to the result.

---

# Dry Run

Input:

```text
[-1,0,1,2,-1,-4]
```

After sorting:

```text
[-4,-1,-1,0,1,2]
```

| `i` | `nums[i]` | `left` | `right` | Sum | Action   |
| --: | --------: | -----: | ------: | --: | -------- |
|   0 |        -4 |      1 |       5 |  -3 | `left++` |
|   0 |        -4 |      2 |       5 |  -3 | `left++` |
|   0 |        -4 |      3 |       5 |  -2 | `left++` |
|   0 |        -4 |      4 |       5 |  -1 | `left++` |
|   1 |        -1 |      2 |       5 |   0 | Found    |
|   1 |        -1 |      3 |       4 |   0 | Found    |

Triplets found:

```text
[-1,-1,2]
[-1,0,1]
```

---

# Why `i < nums.length - 2`?

We need three numbers.

For example:

```text
indices:  0  1  2  3  4  5
```

If:

```text
i = 3
```

we still have:

```text
left = 4
right = 5
```

So there are three positions available:

```text
i → left → right
```

Therefore:

```java
i < nums.length - 2
```

---

# Why `left < right`?

We need two different elements.

If:

```text
left == right
```

we would use the same array position twice.

Therefore:

```java
while (left < right)
```

---

# Why skip duplicates?

Suppose:

```text
[-1,-1,0,1]
```

Without duplicate handling, the same triplet could be added more than once.

So we skip duplicate values.

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

---

# Complexity

### Time Complexity

Sorting:

```text
O(n log n)
```

Two-pointer search:

```text
O(n²)
```

Overall:

```text
O(n²)
```

### Space Complexity

Ignoring the output:

```text
O(1)
```

---

# Key Interview Point

> **3Sum = Fix one element + solve 2Sum using Two Pointers.**

Remember:

```text
SORT
  ↓
FIX i
  ↓
left = i + 1
right = n - 1
  ↓
CHECK SUM
  ↓
┌───────────────┐
│ sum < 0       │ → left++
│ sum > 0       │ → right--
│ sum == 0      │ → save answer
└───────────────┘
  ↓
SKIP DUPLICATES
```

---

# 6. Pattern Variations

## Two Sum

```text
left ↔ right
```

## 3Sum

```text
Fix i
+
left/right
```

## 4Sum

```text
Fix i
+
Fix j
+
left/right
```

The main Two Pointer idea remains the same.

---

# 7. Common Mistakes

1. Forgetting `Arrays.sort(nums)`.
2. Using `left <= right` instead of `left < right`.
3. Forgetting to skip duplicates.
4. Moving the wrong pointer.
5. Starting `left` from `0` instead of `i + 1`.
6. Forgetting to move both pointers after finding a valid triplet.
7. Returning duplicate triplets.

### Most important pointer rule

```text
sum < 0 → left++

sum > 0 → right--
```

---

# 8. Interview Triggers

```text
Sorted array + pair target
→ Two Pointers
```

```text
Array + 3 numbers + target
→ 3Sum
→ Fix one + Two Pointers
```

```text
Array + 4 numbers + target
→ 4Sum
→ Fix two + Two Pointers
```

```text
Triplets + unique answers
→ Sort + Skip Duplicates
```

---

# 9. 30-Second Revision

**Pattern:** Two Pointers

**Problem:** 3Sum — LeetCode 15

**When to use:** Triplet + target sum

**Main idea:** Sort → Fix one number → Use `left` and `right`

**Pointer rules:**

```text
sum < 0 → left++

sum > 0 → right--

sum == 0 → save answer
```

**Important:** Skip duplicates.

**Time:** `O(n²)`

**Space:** `O(1)` excluding output.

**Most important mistake:** Forgetting duplicate handling.

### One-line memory:

> **SORT → FIX ONE → LEFT/RIGHT → CHECK SUM → SKIP DUPLICATES**
