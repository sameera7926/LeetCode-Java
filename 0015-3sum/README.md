# 15. 3Sum

## Pattern: Two Pointers

---

## 1. Problem

Given an integer array `nums`, find all **unique triplets**:

```text
nums[i] + nums[j] + nums[k] = 0
```

The three indices must be different.

The answer must **not contain duplicate triplets**.

### Example

```text
Input:
[-1,0,1,2,-1,-4]

Output:
[[-1,-1,2],[-1,0,1]]
```

---

# 2. Identify the Pattern

### Clues

Look for:

* Need to find **3 numbers**
* Their sum must equal a target (`0`)
* Need unique triplets
* Array can be sorted
* After fixing one number, we need to find a pair

👉 This suggests:

```text
Two Pointers
```

The main idea is:

```text
Fix one number
      ↓
Use two pointers for the remaining two numbers
```

---

# 3. Core Idea

First **sort the array**.

```text
[-1,0,1,2,-1,-4]

       ↓ sort

[-4,-1,-1,0,1,2]
```

Then:

```text
i = first number
left = i + 1
right = n - 1
```

Calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

### If:

```text
sum == 0
```

We found a valid triplet.

### If:

```text
sum < 0
```

We need a **larger sum**.

Move:

```text
left++
```

### If:

```text
sum > 0
```

We need a **smaller sum**.

Move:

```text
right--
```

---

# 4. Why Sorting Is Important

Sorting gives us two major advantages.

### Advantage 1 — Two Pointers

Because the array is sorted:

```text
left++  → increases the sum
right-- → decreases the sum
```

### Advantage 2 — Remove duplicates

For example:

```text
[-1,-1,0,1,2]
```

If we process both `-1`s as the starting number, we may produce the same triplet again.

So we skip duplicate values.

---

# 5. Java Code

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate starting values
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

                } 
                else if (sum < 0) {
                    left++;
                } 
                else {
                    right--;
                }
            }
        }

        return result;
    }
}
```

---

# 6. Code Explanation

## Step 1 — Create result

```java
List<List<Integer>> result = new ArrayList<>();
```

We need to store multiple triplets.

Example:

```text
[
 [-1,-1,2],
 [-1,0,1]
]
```

---

## Step 2 — Sort

```java
Arrays.sort(nums);
```

Example:

```text
[-1,0,1,2,-1,-4]

↓

[-4,-1,-1,0,1,2]
```

Sorting is necessary for the Two Pointer movement and duplicate handling.

---

## Step 3 — Fix the first number

```java
for (int i = 0; i < nums.length - 2; i++)
```

We fix:

```text
nums[i]
```

Then find two more numbers using `left` and `right`.

---

## Step 4 — Skip duplicate `i`

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

Example:

```text
[-4,-1,-1,0,1,2]
     ↑  ↑
```

Both `-1`s would produce the same set of triplets.

So after processing the first `-1`, skip the second one.

### Why `i > 0`?

Because:

```java
nums[i - 1]
```

must exist.

---

# 7. Set the Two Pointers

```java
int left = i + 1;
int right = nums.length - 1;
```

For:

```text
i = 1
```

we get:

```text
i     = 1
left  = 2
right = 5
```

So:

```text
nums[i] + nums[left] + nums[right]
```

---

# 8. Calculate Sum

```java
int sum = nums[i] + nums[left] + nums[right];
```

There are three possibilities.

### Case 1

```text
sum == 0
```

Found a valid triplet.

### Case 2

```text
sum < 0
```

Sum is too small.

Move:

```text
left++
```

### Case 3

```text
sum > 0
```

Sum is too large.

Move:

```text
right--
```

---

# 9. Handling Duplicates

This is the **most important part of 3Sum**.

After finding a valid triplet:

```java
while (left < right &&
       nums[left] == nums[left + 1]) {
    left++;
}
```

Skip duplicate values on the left.

Similarly:

```java
while (left < right &&
       nums[right] == nums[right - 1]) {
    right--;
}
```

Skip duplicate values on the right.

Then:

```java
left++;
right--;
```

Move both pointers to continue searching.

---

# 10. Dry Run

Input:

```text
[-1,0,1,2,-1,-4]
```

### Step 1 — Sort

```text
[-4,-1,-1,0,1,2]
```

---

### i = 0

```text
i = 0
nums[i] = -4

left = 1
right = 5
```

Calculate:

```text
-4 + (-1) + 2
= -3
```

Too small:

```text
sum < 0
```

So:

```text
left++
```

---

### i = 0, left = 2

```text
-4 + (-1) + 2
= -3
```

Still too small.

Move:

```text
left++
```

---

Eventually:

```text
-4 + 1 + 2
= -1
```

Still not zero.

No valid triplet starting with `-4`.

---

### i = 1

```text
nums[i] = -1

left = 2
right = 5
```

Values:

```text
-1, -1, 2
```

Sum:

```text
-1 + -1 + 2 = 0
```

Add:

```text
[-1,-1,2]
```

---

Move pointers:

```text
left++
right--
```

Now:

```text
left = 3
right = 4
```

Values:

```text
-1, 0, 1
```

Sum:

```text
-1 + 0 + 1 = 0
```

Add:

```text
[-1,0,1]
```

---

Final result:

```text
[[-1,-1,2],[-1,0,1]]
```

---

# 11. Important Duplicate Example

Input:

```text
[0,0,0,0]
```

Sorted:

```text
[0,0,0,0]
```

First:

```text
i = 0
left = 1
right = 3
```

Sum:

```text
0 + 0 + 0 = 0
```

Add:

```text
[0,0,0]
```

Now we skip duplicate values.

We should **not** add:

```text
[0,0,0]
[0,0,0]
[0,0,0]
```

The answer is only:

```text
[[0,0,0]]
```

---

# 12. Why `left++` and `right--` After Finding a Triplet?

After:

```java
sum == 0
```

we already used the current `left` and `right`.

We need to search for another pair.

So:

```java
left++;
right--;
```

If we don't move them, the same triplet would be found again.

---

# 13. Complexity

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
Time: O(n²)
Space: O(1)
```

Ignoring the space used to store the output.

---

# 14. Common Mistakes

### ❌ Mistake 1 — Forgetting to sort

Without:

```java
Arrays.sort(nums);
```

the Two Pointer movement doesn't work correctly.

---

### ❌ Mistake 2 — Not skipping duplicate `i`

Remember:

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

---

### ❌ Mistake 3 — Not skipping duplicates after finding a triplet

You also need:

```java
while (left < right &&
       nums[left] == nums[left + 1]) {
    left++;
}
```

and:

```java
while (left < right &&
       nums[right] == nums[right - 1]) {
    right--;
}
```

---

### ❌ Mistake 4 — Moving the wrong pointer

Remember:

```text
sum < 0 → left++

sum > 0 → right--
```

Because the array is sorted.

---

# 15. Interview Trigger

If the interviewer says:

> "Find three numbers whose sum equals a target."

Think:

```text
3 numbers
   ↓
Sort
   ↓
Fix one number
   ↓
Two Pointers for remaining two
```

For target `0`:

```text
nums[i] + nums[left] + nums[right] == 0
```

---

# 16. 3Sum Pattern Template

```text
Sort array

for each i:

    skip duplicate i

    left = i + 1
    right = n - 1

    while left < right:

        sum = nums[i] + nums[left] + nums[right]

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

# 17. 30-Second Revision

```text
Problem:
Find unique triplets whose sum is 0.

Pattern:
Two Pointers

Step 1:
Sort the array.

Step 2:
Fix nums[i].

Step 3:
left = i + 1
right = n - 1

Step 4:
Calculate:
sum = nums[i] + nums[left] + nums[right]

If sum == 0:
    save triplet
    skip duplicates
    left++
    right--

If sum < 0:
    left++

If sum > 0:
    right--

Complexity:
O(n²) time
O(1) extra space
```

## ⭐ Interview One-Liner

> **Sort the array, fix one number, and use two pointers to find the other two while skipping duplicates.**
