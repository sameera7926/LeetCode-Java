# 3. Longest Substring Without Repeating Characters

## Problem

Given a string `s`, find the length of the **longest substring** that contains **no duplicate characters**.

---

# Important: What is a Substring?

A **substring must be continuous**.

Example:

```text
s = "pwwkew"
```

Valid substring:

```text
"wke"
```

because the characters are next to each other.

But:

```text
"pwke"
```

is NOT a substring because we skipped characters.

---

# Example 1

```text
Input:
s = "abcabcbb"

Output:
3
```

Possible longest substrings:

```text
"abc"
"bca"
"cab"
```

All have length `3`.

---

# Main Pattern — Sliding Window

We maintain a window:

```text
[left ........ right]
```

The window must always contain **unique characters**.

We use:

```java
HashSet<Character> set
```

to keep track of the characters currently inside the window.

---

# Core Idea

Move `right` through the string.

### If the character is NOT already in the window:

Add it.

```java
set.add(s.charAt(right));
```

Then update the answer.

### If the character IS already in the window:

We have a duplicate.

So move `left` forward and remove characters until the duplicate is removed.

```java
set.remove(s.charAt(left));
left++;
```

Then we can add the current character.

---

# Visual Flow

```text
                    right
                      ↓
        ┌─────────────────────────┐
        │  a   b   c   a           │
        └─────────────────────────┘
        ↑
       left

Duplicate 'a' found
       ↓
Move left
       ↓
Remove characters
       ↓
Window becomes valid again
```

---

# Java Code

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {

            char current = s.charAt(right);

            while (set.contains(current)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(current);

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
```

---

# Understand Every Variable

### `left`

```java
int left = 0;
```

The **starting position** of our current window.

---

### `right`

```java
for (int right = 0; right < s.length(); right++)
```

`right` moves forward through the string and expands the window.

---

### `set`

```java
HashSet<Character> set = new HashSet<>();
```

Stores the characters currently inside the window.

Its purpose:

> Quickly check whether a character already exists.

---

### `current`

```java
char current = s.charAt(right);
```

The character currently being processed.

---

# Why `while`, Not `if`?

This is VERY important.

```java
while (set.contains(current))
```

means:

> Keep removing from the left until the duplicate is gone.

For example:

```text
Window = "abc"
Current = "c"
```

Actually imagine:

```text
Window = "abca"
Current = "a"
```

We have duplicate `a`.

Remove from left:

```text
"abca"
 ↑
remove a
```

Now:

```text
"bca"
```

The duplicate is gone.

In more complicated windows, we may need to remove multiple characters.

So:

```java
while (...)
```

is safer because it continues until the window becomes valid.

---

# Dry Run — Example 1

```text
s = "abcabcbb"
```

We start:

```text
left = 0
ans = 0
set = {}
```

---

### Step 1 — `right = 0`

```text
current = 'a'
```

`a` is not in set.

```text
set = {a}
```

Window:

```text
[a]
```

Length:

```text
0 - 0 + 1 = 1
```

```text
ans = 1
```

---

### Step 2 — `right = 1`

```text
current = 'b'
```

Not duplicate.

```text
set = {a,b}
```

Window:

```text
[ab]
```

Length:

```text
1 - 0 + 1 = 2
```

```text
ans = 2
```

---

### Step 3 — `right = 2`

```text
current = 'c'
```

Not duplicate.

```text
set = {a,b,c}
```

Window:

```text
[abc]
```

Length:

```text
2 - 0 + 1 = 3
```

```text
ans = 3
```

🔥 Current best = `"abc"`

---

### Step 4 — `right = 3`

```text
current = 'a'
```

But:

```text
set = {a,b,c}
```

`a` already exists.

So we enter:

```java
while (set.contains(current))
```

Remove:

```text
'a'
```

Move `left`:

```text
left = 1
```

Now:

```text
set = {b,c}
```

Add current `a`:

```text
set = {b,c,a}
```

Window:

```text
[bca]
```

Length:

```text
3 - 1 + 1 = 3
```

`ans` remains:

```text
3
```

---

### Step 5 — `right = 4`

Current:

```text
'b'
```

Duplicate found.

Remove from left:

```text
set = {c,a}
left = 2
```

Add `b`:

```text
set = {c,a,b}
```

Window:

```text
[cab]
```

Length:

```text
4 - 2 + 1 = 3
```

---

### Continue

The longest valid window never becomes bigger than `3`.

Therefore:

```text
Output = 3
```

---

# Complete Dry Run Table

```text
s = "abcabcbb"
```

| right | char | Action                  | left | Window | ans |
| ----: | :--: | ----------------------- | ---: | ------ | --: |
|     0 |   a  | Add                     |    0 | `a`    |   1 |
|     1 |   b  | Add                     |    0 | `ab`   |   2 |
|     2 |   c  | Add                     |    0 | `abc`  |   3 |
|     3 |   a  | Remove `a`, add `a`     |    1 | `bca`  |   3 |
|     4 |   b  | Remove `b`, add `b`     |    2 | `cab`  |   3 |
|     5 |   c  | Remove `c`, add `c`     |    3 | `abc`  |   3 |
|     6 |   b  | Remove `a`,`b`, add `b` |    5 | `cb`   |   3 |
|     7 |   b  | Remove `c`,`b`, add `b` |    7 | `b`    |   3 |

Final:

```text
ans = 3
```

---

# Example 2 — All Same Characters

```text
s = "bbbbb"
```

First:

```text
b
```

Then another `b` appears.

We remove the previous `b` and move `left`.

The window always contains only one `b`.

Therefore:

```text
Output = 1
```

---

# Example 3

```text
s = "pwwkew"
```

The longest valid substring is:

```text
"wke"
```

Length:

```text
3
```

Therefore:

```text
Output = 3
```

Remember:

```text
"pwke"
```

is not valid because it is a **subsequence**, not a substring.

---

# Important Formula

Window length:

```java
right - left + 1
```

Why `+1`?

Suppose:

```text
left = 2
right = 4
```

Positions are:

```text
2, 3, 4
```

That's `3` characters.

```text
4 - 2 + 1 = 3
```

---

# Why Sliding Window Works

We don't restart the window every time we find a duplicate.

Instead:

```text
right → keeps moving forward
left  → moves forward only when needed
```

So both pointers move in one direction.

```text
left  → → → →
right → → → → →
```

This avoids checking every possible substring.

---

# Complexity

```text
Time  → O(n)
Space → O(n)
```

### Why O(n)?

`right` moves from beginning to end.

`left` also only moves forward.

Even though there is a `while` loop, `left` can move at most `n` times.

So total work is still:

```text
O(n)
```

---

# Core Pattern to Remember

```text
right expands the window
        ↓
Is current character already present?
        ↓
     NO ─────────→ Add character
        │
       YES
        ↓
Move left + remove characters
        ↓
Window becomes valid
        ↓
Calculate window length
        ↓
Update answer
```

## Memory Trick

> **Expand with `right`, shrink with `left`, keep the window unique.**

This is a classic **Sliding Window + HashSet** problem.
