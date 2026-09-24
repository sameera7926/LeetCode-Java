# LeetCode 56 — Merge Intervals

## 🧩 Problem

Given an array of intervals:

```java
intervals[i] = [start, end]
```

Merge all **overlapping intervals** and return the resulting non-overlapping intervals.

---

## 🧠 Pattern

**Sorting + Intervals + Merging**

Main idea:

```text
Sort intervals by start
        ↓
Take current interval
        ↓
Compare with next interval
        ↓
Does next.start <= current.end?
       /              \
     YES              NO
      ↓                ↓
   Merge          Store current
      ↓                ↓
   Continue       Start next interval
```

---

# 🔑 Step 1 — Sort the Intervals

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
```

### Why?

The intervals can be given in any order.

We need them sorted according to their **starting value** so that we can process them from left to right.

### Why not simply:

```java
Arrays.sort(intervals);
```

Because `intervals` is a **2D array**, and we specifically want to tell Java:

> Sort the intervals based on `a[0]`, the starting value.

```java
a[0] → start of first interval
b[0] → start of second interval
```

---

# 🔑 Step 2 — Store the Current Interval

```java
int start1 = intervals[0][0];
int end1 = intervals[0][1];
```

`start1` and `end1` represent the **current interval** being processed.

---

# 🔑 Step 3 — Process Remaining Intervals

```java
for (int i = 1; i < intervals.length; i++)
```

Start from `1` because the first interval is already stored in `start1` and `end1`.

---

# 🔑 Step 4 — Get the Next Interval

```java
int start2 = intervals[i][0];
int end2 = intervals[i][1];
```

These represent the interval currently being compared with the current interval.

```text
start1, end1 → current interval
start2, end2 → next interval
```

---

# 🔑 Step 5 — Check Overlap

```java
if (start2 <= end1)
```

This checks whether the next interval overlaps the current interval.

### If TRUE → Merge

```java
end1 = Math.max(end1, end2);
```

Keep the current start and extend the end to the larger ending value.

---

# 🔑 Step 6 — No Overlap

If:

```java
start2 > end1
```

the intervals don't overlap.

Store the completed current interval:

```java
res.add(new int[]{start1, end1});
```

Then make the next interval the new current interval:

```java
start1 = start2;
end1 = end2;
```

---

# 🔑 Step 7 — Add the Last Interval

```java
res.add(new int[]{start1, end1});
```

The final current interval needs to be added after the loop finishes.

---

# 🔑 Step 8 — Convert List to Array

```java
return res.toArray(new int[res.size()][]);
```

`res` is:

```text
List<int[]>
```

But the method must return:

```text
int[][]
```

So `toArray()` converts the list into a 2D array.

---

# 💻 Final Java Code

```java
class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();

        int start1 = intervals[0][0];
        int end1 = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            int start2 = intervals[i][0];
            int end2 = intervals[i][1];

            if (start2 <= end1) {

                end1 = Math.max(end1, end2);

            } else {

                res.add(new int[]{start1, end1});

                start1 = start2;
                end1 = end2;
            }
        }

        res.add(new int[]{start1, end1});

        return res.toArray(new int[res.size()][]);
    }
}
```

---

# 🧠 Revision Formula

Remember only this:

```text
SORT
  ↓
CURRENT = [start1, end1]
  ↓
NEXT = [start2, end2]
  ↓
start2 <= end1 ?
   ↙          ↘
 YES           NO
  ↓             ↓
MERGE         SAVE
  ↓             ↓
end1 = max    CURRENT = NEXT
```

### ⭐ Most Important Lines

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
```

**→ Sort by starting value**

```java
if (start2 <= end1)
```

**→ Check overlap**

```java
end1 = Math.max(end1, end2);
```

**→ Merge**

```java
res.add(new int[]{start1, end1});
```

**→ Store completed interval**

```java
start1 = start2;
end1 = end2;
```

**→ Move to the next interval**

---

# ⏱️ Complexity

### Time

```text
O(n log n)
```

Sorting takes `O(n log n)` and processing takes `O(n)`.

### Space

```text
O(n)
```

For storing the result.

---

# 🎯 Interview Explanation

> First, I sort the intervals based on their starting values. Then I maintain a current interval and compare it with each next interval. If the next interval starts before or at the current end, they overlap, so I merge them using the maximum end value. Otherwise, I store the current interval and make the next interval the current one. Finally, I add the remaining interval.

### One-line Memory Trick

**Sort → Compare → Merge if overlap → Otherwise save and move.**
