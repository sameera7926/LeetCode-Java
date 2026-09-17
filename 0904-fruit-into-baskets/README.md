# Fruit Into Baskets

**LeetCode 904 — Fruit Into Baskets**

## 📌 Problem

You are given an integer array `fruits`.

Each number represents a type of fruit.

You have **2 baskets**, and each basket can hold only **one type of fruit**.

You must pick fruits from a **continuous subarray**.

Find the maximum number of fruits you can collect.

### Example

```text
Input:
[1, 2, 1]

Output:
3
```

We can collect:

```text
[1, 2, 1]

Basket 1 → type 1
Basket 2 → type 2

Total = 3
```

---

## 💡 Key Idea

This is a **Sliding Window** problem.

The window can contain **at most 2 different fruit types**.

```text
[1, 2, 1]
 ↑     ↑
left  right
```

If the window contains more than 2 types:

```text
[1, 2, 3]
```

We move `left` until the window becomes valid again.

### Condition

```text
number of distinct fruit types <= 2
```

---

## 🔍 Dry Run

```text
fruits = [1, 2, 3, 2, 2]
```

### Step 1

```text
[1]

Types = {1}
Length = 1
```

### Step 2

```text
[1, 2]

Types = {1, 2}
Length = 2
```

Still valid.

### Step 3

```text
[1, 2, 3]

Types = {1, 2, 3}
```

❌ 3 different types.

Move `left`:

```text
[2, 3]

Types = {2, 3}
```

Valid again.

### Step 4

```text
[2, 3, 2]

Types = {2, 3}
Length = 3
```

### Step 5

```text
[2, 3, 2, 2]

Types = {2, 3}
Length = 4
```

Final answer:

```text
4
```

---

## 💻 Java Solution

```java
class Solution {
    public int totalFruit(int[] fruits) {

        int left = 0;
        int maxLength = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {

            int fruit = fruits[right];

            map.put(fruit, map.getOrDefault(fruit, 0) + 1);

            while (map.size() > 2) {

                int leftFruit = fruits[left];

                map.put(
                    leftFruit,
                    map.get(leftFruit) - 1
                );

                if (map.get(leftFruit) == 0) {
                    map.remove(leftFruit);
                }

                left++;
            }

            maxLength = Math.max(
                maxLength,
                right - left + 1
            );
        }

        return maxLength;
    }
}
```

---

## 🧠 Why Do We Need a HashMap?

The window needs to know:

> **How many fruits of each type are currently inside it?**

For:

```text
[1, 2, 2]
```

The map contains:

```text
1 → 1
2 → 2
```

When `left` moves past the `1`:

```text
1 → 0
```

We remove it:

```java
map.remove(leftFruit);
```

Now only one fruit type remains.

---

## ⭐ Important Line

```java
right - left + 1
```

This gives the current window size.

Example:

```text
left = 2
right = 5

5 - 2 + 1 = 4
```

So the window contains **4 fruits**.

---

## 🎯 Pattern

**Sliding Window + HashMap + At Most K Distinct Elements**

Here:

```text
K = 2
```

Think:

```text
right →
[ 1  2  1  3 ]
  ↑        ↑
 left     right

Types = 3 ❌

Move left →
[ 2  1  3 ]

Still 3 ❌

Move left →
[ 1  3 ]

Types = 2 ✅
```

---

## ⏱️ Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```

There can be at most **2 fruit types** in the valid window, so the HashMap contains at most 2 keys.

---

## 🔑 What I Learned

* How to identify a Sliding Window problem
* How to maintain a window with **at most 2 distinct elements**
* How to use a HashMap to track frequencies
* Why `left` moves when the window becomes invalid
* How to calculate the current window length
* How to solve the problem in `O(n)` time
