# Next Greater Node In Linked List

**LeetCode 1019 — Next Greater Node In Linked List**

## 📌 Problem

Given the head of a linked list, for each node find the value of the **next node whose value is greater** than the current node.

If no greater node exists, return `0`.

### Example

```text
Input:
[2, 1, 5]

Output:
[5, 5, 0]
```

Explanation:

```text
2 → 1 → 5
↓   ↓   ↓
5   5   0
```

* For `2`, the next greater value is `5`
* For `1`, the next greater value is `5`
* For `5`, there is no greater value → `0`

---

## 💡 Approach

We use a **Monotonic Stack**.

The stack stores the **indices of nodes whose next greater element has not been found yet**.

### Why a stack?

When we find a new value, we check whether it is greater than the values represented by indices in the stack.

If it is greater:

```text
current value > stack's value
```

Then the current value is the **next greater node** for that index.

---

## 🔍 Example

```text
[2, 1, 5]
```

### Step 1

```text
Current = 2

Stack = [0]
Answer = [0, 0, 0]
```

### Step 2

```text
Current = 1

1 > 2 ❌

Stack = [0, 1]
```

### Step 3

```text
Current = 5
```

Check stack:

```text
5 > 1 ✅
answer[1] = 5

5 > 2 ✅
answer[0] = 5
```

Nothing remains that needs an answer.

```text
Answer = [5, 5, 0]
```

---

## 💻 Java Solution

```java
class Solution {
    public int[] nextLargerNodes(ListNode head) {

        ArrayList<Integer> values = new ArrayList<>();

        ListNode current = head;

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int[] answer = new int[values.size()];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < values.size(); i++) {

            while (!stack.isEmpty()
                    && values.get(i) > values.get(stack.peek())) {

                int index = stack.pop();
                answer[index] = values.get(i);
            }

            stack.push(i);
        }

        return answer;
    }
}
```

---

## 🧠 Important Idea

The stack contains **indices**, not values.

For:

```text
[2, 1, 5]
```

The stack looks like:

```text
Before 5:

Stack
 ↓
[1]
[0]
```

When `5` arrives:

```text
5 > value at index 1
→ answer[1] = 5
→ pop 1

5 > value at index 0
→ answer[0] = 5
→ pop 0
```

Then:

```text
answer = [5, 5, 0]
```

---

## 🎯 Pattern

**Linked List → Convert to ArrayList → Monotonic Stack → Next Greater Element**

This is the same core pattern used in problems like:

* Next Greater Element I
* Next Greater Element II
* Daily Temperatures
* Stock Span

---

## ⏱️ Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(n)
```

Each index is pushed into the stack once and popped at most once.

---

## 🔑 What I Learned

* How to process a linked list sequentially
* Why storing indices is useful
* How a monotonic stack works
* How to find the next greater element efficiently
* Why the brute-force approach can be improved from `O(n²)` to `O(n)`
