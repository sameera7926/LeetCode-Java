# 26. Remove Duplicates from Sorted Array

## Pattern: Two Pointers

---

## 1. Problem

You are given a **sorted** integer array.

Remove duplicate values **in-place** so that every unique number appears only once.

Return:

```text
k = number of unique elements
```

The first `k` positions of the array must contain the unique values.

### Example

```text
Input:
[1,1,2]

Output:
k = 2

nums:
[1,2,_,]
```

The values after index `k - 1` do not matter.

---

# 2. Identify the Pattern

### Clues

Look for:

* Array is **sorted**
* Remove duplicates
* Modify the same array
* No extra array should be used
* Need to keep the original order

Think:

```text
Sorted Array
     ↓
Two Pointers
     ↓
Slow + Fast
```

---

# 3. Core Idea

Because the array is sorted, duplicates are next to each other.

Example:

```text
[0,0,1,1,1,2,2,3,3,4]
```

We only need to compare:

```text
current element
      with
previous unique element
```

Use two pointers:

```text
slow → position where next unique value should go

fast → scans every element
```

---

# 4. Pointer Meaning

### `slow`

```text
slow = 0
```

It represents the position of the **last unique element**.

### `fast`

```text
fast = 1
```

It scans the array looking for a new value.

Think:

```text
slow = WRITE
fast = READ
```

---

# 5. Java Code

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {

            if (nums[fast] != nums[slow]) {

                slow++;

                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }
}
```

---

# 6. Code Explanation

## Step 1 — Start `slow`

```java
int slow = 0;
```

The first element is always unique.

Example:

```text
[1,1,2]
 ↑
slow
```

So we start from index `0`.

---

## Step 2 — Start `fast`

```java
for (int fast = 1; fast < nums.length; fast++)
```

`fast` starts from index `1` because we compare every element with the last unique element.

---

## Step 3 — Compare

```java
if (nums[fast] != nums[slow])
```

If they are different:

```text
New unique value found.
```

If they are the same:

```text
Duplicate → ignore it.
```

---

## Step 4 — Move `slow`

When we find a new unique value:

```java
slow++;
```

This creates the next position where the unique value should be written.

---

## Step 5 — Copy the unique value

```java
nums[slow] = nums[fast];
```

Move the unique value into the correct position.

---

## Step 6 — Return `slow + 1`

```java
return slow + 1;
```

Important:

`slow` is an **index**, but `k` is a **count**.

Example:

```text
slow = 4
```

means there are:

```text
5 unique elements
```

Therefore:

```text
k = slow + 1
```

---

# 7. Dry Run

Input:

```text
[0,0,1,1,1,2,2,3,3,4]
```

Initial:

```text
slow = 0
```

### Step-by-step

| fast | nums[fast] | nums[slow] | Action      | Array                   |
| ---: | ---------: | ---------: | ----------- | ----------------------- |
|    1 |          0 |          0 | Duplicate   | `[0,0,1,1,1,2,2,3,3,4]` |
|    2 |          1 |          0 | New → write | `[0,1,1,1,1,2,2,3,3,4]` |
|    3 |          1 |          1 | Duplicate   | `[0,1,1,1,1,2,2,3,3,4]` |
|    4 |          1 |          1 | Duplicate   | `[0,1,1,1,1,2,2,3,3,4]` |
|    5 |          2 |          1 | New → write | `[0,1,2,1,1,2,2,3,3,4]` |
|    6 |          2 |          2 | Duplicate   | `[0,1,2,1,1,2,2,3,3,4]` |
|    7 |          3 |          2 | New → write | `[0,1,2,3,1,2,2,3,3,4]` |
|    8 |          3 |          3 | Duplicate   | `[0,1,2,3,1,2,2,3,3,4]` |
|    9 |          4 |          3 | New → write | `[0,1,2,3,4,2,2,3,3,4]` |

At the end:

```text
slow = 4
```

Therefore:

```text
k = slow + 1
  = 5
```

First 5 elements:

```text
[0,1,2,3,4]
```

Everything after that can be ignored.

---

# 8. Visual Thinking

Think of the two pointers like this:

```text
[0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
 ↑  ↑
slow fast
```

`fast` keeps scanning:

```text
READ → READ → READ → READ
```

`slow` only moves when a new value is found:

```text
WRITE → WRITE → WRITE
```

So:

```text
fast = scanner
slow = writer
```

---

# 9. Why Does Sorting Matter?

Suppose:

```text
[1,1,2,2,3]
```

Duplicates are next to each other.

Therefore:

```java
nums[fast] != nums[slow]
```

is enough to determine whether we found a new unique value.

If the array were:

```text
[1,2,1,3,2]
```

this technique would not work directly because duplicates are separated.

---

# 10. Why No Extra Array?

The problem says:

```text
in-place
```

So instead of creating:

```java
int[] unique = new int[...];
```

we overwrite the beginning of the same array:

```text
Original:
[0,0,1,1,2]

After:
[0,1,2,1,2]
 ↑─────↑
 useful   ignored
```

Only the first `k` elements matter.

---

# 11. Important Difference: `slow` Is Not `k`

Remember:

```text
slow = index
k = count
```

Example:

```text
[1,2,3,4]
```

At the end:

```text
slow = 3
```

But:

```text
k = 4
```

Therefore:

```java
return slow + 1;
```

---

# 12. Common Mistakes

### ❌ Mistake 1 — Starting `slow` at 1

Wrong:

```java
int slow = 1;
```

We use:

```java
int slow = 0;
```

because the first element is already unique.

---

### ❌ Mistake 2 — Returning `slow`

Wrong:

```java
return slow;
```

Correct:

```java
return slow + 1;
```

Because `slow` represents an index.

---

### ❌ Mistake 3 — Comparing with `nums[fast - 1]`

You might think:

```java
nums[fast] != nums[fast - 1]
```

This can identify changes, but our `slow` pointer represents the position where the unique values are being stored.

The standard pattern is:

```java
nums[fast] != nums[slow]
```

---

### ❌ Mistake 4 — Creating another array

The problem requires **in-place** modification.

Use the same array.

---

# 13. Pattern Template

This is a useful template for **sorted-array in-place modification**:

```text
slow = 0

for fast = 1 → n-1:

    if current value is different:

        slow++

        nums[slow] = nums[fast]

return slow + 1
```

Think:

```text
FAST → READ
SLOW → WRITE
```

---

# 14. Complexity

```text
Time: O(n)
Space: O(1)
```

### Why O(n)?

`fast` scans the array only once.

### Why O(1)?

We don't create another array or data structure.

---

# 15. Interview Trigger

If the interviewer says:

> "Given a sorted array, remove duplicates in-place."

Immediately think:

```text
SORTED ARRAY
     ↓
DUPLICATES ARE ADJACENT
     ↓
TWO POINTERS
     ↓
slow = write
fast = scan
```

---

# 16. 30-Second Revision

```text
Problem:
Remove duplicates from a sorted array in-place.

Pattern:
Two Pointers

slow = 0
fast = 1

If:
nums[fast] != nums[slow]

Then:
slow++
nums[slow] = nums[fast]

Duplicate:
Do nothing.

At the end:
return slow + 1

Why?
slow is an index.
k is a count.

Complexity:
O(n) time
O(1) space
```

## ⭐ Interview One-Liner

> **Use a slow pointer to write unique values and a fast pointer to scan the sorted array; whenever a new value is found, move slow and copy it.**
