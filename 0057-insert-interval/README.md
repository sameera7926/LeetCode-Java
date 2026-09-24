# LeetCode 57 — Insert Interval

## 🧩 Problem

You are given:

```java
int[][] intervals
```

where the intervals are:

* sorted by their starting value
* non-overlapping

You are also given:

```java
int[] newInterval
```

Insert `newInterval` into the intervals while keeping the result:

* sorted
* non-overlapping

If `newInterval` overlaps with existing intervals, merge them.

---

# 🧠 Pattern

**Intervals + Two Pointer/Index + Merging**

Unlike LeetCode 56, the intervals are **already sorted**, so we do **not** need `Arrays.sort()`.

The main idea is to divide the intervals into 3 parts:

```text
BEFORE newInterval
        ↓
OVERLAPPING intervals
        ↓
AFTER newInterval
```

---

# 🔑 Three Main Cases

## 1. Interval is BEFORE `newInterval`

Condition:

```java
intervals[i][1] < newInterval[0]
```

Meaning:

> Current interval ends before `newInterval` starts.

There is no overlap.

So:

```java
res.add(intervals[i]);
```

Then move forward:

```java
i++;
```

---

## 2. Interval OVERLAPS `newInterval`

Condition:

```java
intervals[i][0] <= newInterval[1]
```

Meaning:

> Current interval starts before or exactly when `newInterval` ends.

So we merge.

### Update start

```java
newInterval[0] =
    Math.min(newInterval[0], intervals[i][0]);
```

Take the smaller starting value.

### Update end

```java
newInterval[1] =
    Math.max(newInterval[1], intervals[i][1]);
```

Take the larger ending value.

Then:

```java
i++;
```

Move to the next interval.

---

## 3. Interval is AFTER `newInterval`

After all overlapping intervals are merged:

```java
res.add(newInterval);
```

Then add all remaining intervals directly:

```java
while (i < intervals.length) {
    res.add(intervals[i]);
    i++;
}
```

---

# 💻 Java Code

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();

        int i = 0;

        // 1. Add intervals before newInterval
        while (i < intervals.length &&
               intervals[i][1] < newInterval[0]) {

            res.add(intervals[i]);
            i++;
        }

        // 2. Merge overlapping intervals
        while (i < intervals.length &&
               intervals[i][0] <= newInterval[1]) {

            newInterval[0] =
                Math.min(newInterval[0], intervals[i][0]);

            newInterval[1] =
                Math.max(newInterval[1], intervals[i][1]);

            i++;
        }

        // 3. Add merged newInterval
        res.add(newInterval);

        // 4. Add remaining intervals
        while (i < intervals.length) {

            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
```

---

# 🧠 Code Explanation

### Result list

```java
List<int[]> res = new ArrayList<>();
```

Stores the final intervals.

---

### Index

```java
int i = 0;
```

Keeps track of the current interval being processed.

---

### First `while`

```java
while (i < intervals.length &&
       intervals[i][1] < newInterval[0])
```

Finds intervals that are completely **before** `newInterval`.

They don't overlap, so add them directly.

---

### Second `while`

```java
while (i < intervals.length &&
       intervals[i][0] <= newInterval[1])
```

Finds intervals that **overlap** with `newInterval`.

Merge them by updating:

```java
newInterval[0] = Math.min(...);
```

and:

```java
newInterval[1] = Math.max(...);
```

---

### Add merged interval

```java
res.add(newInterval);
```

After merging all overlapping intervals, add the final merged interval.

---

### Third `while`

```java
while (i < intervals.length)
```

Adds all intervals that come after `newInterval`.

---

### Return

```java
return res.toArray(new int[res.size()][]);
```

Converts:

```text
List<int[]>
```

into:

```text
int[][]
```

because the method must return a 2D array.

---

# 🧠 Revision Formula

Remember:

```text
              INSERT INTERVAL
                    ↓
        ┌───────────┴───────────┐
        ↓                       ↓
     BEFORE                  OVERLAP
        ↓                       ↓
       ADD                    MERGE
                                ↓
                         update start/end
                                ↓
                         ADD newInterval
                                ↓
                         ADD remaining
```

### ⭐ Most Important Conditions

**Before:**

```java
intervals[i][1] < newInterval[0]
```

➡️ Add directly.

**Overlap:**

```java
intervals[i][0] <= newInterval[1]
```

➡️ Merge.

**Merge start:**

```java
newInterval[0] =
    Math.min(newInterval[0], intervals[i][0]);
```

**Merge end:**

```java
newInterval[1] =
    Math.max(newInterval[1], intervals[i][1]);
```

---

# ⚠️ Important Difference: 56 vs 57

| LeetCode 56               | LeetCode 57                          |
| ------------------------- | ------------------------------------ |
| Merge existing intervals  | Insert a new interval                |
| Input may not be sorted   | Input is already sorted              |
| Need `Arrays.sort()`      | No sorting needed                    |
| Compare current intervals | Compare intervals with `newInterval` |
| Merge overlaps            | Insert + merge overlaps              |

---

# 🎯 Interview Explanation

> The intervals are already sorted, so I use one index to process them in order. First, I add all intervals that end before the new interval starts. Then I merge all intervals that overlap with the new interval by taking the minimum start and maximum end. After that, I add the merged new interval and finally add all remaining intervals.

---

# ⏱️ Complexity

### Time

```text
O(n)
```

Each interval is processed once.

### Space

```text
O(n)
```

For the result list.

---

# 🔥 One-Line Memory Trick

**Before → Add | Overlap → Merge | After → Add**
