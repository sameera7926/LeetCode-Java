class Solution {
    public boolean isHappy(int n) {

        int slow = n;
        int fast = n;

        while (true) {

            slow = getNext(slow);
            fast = getNext(getNext(fast));

            if (fast == 1 || slow == 1) {
                return true;
            }

            if (slow == fast) {
                return false;
            }
        }
    }

    public int getNext(int n) {

        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            n = n / 10;

            sum += digit * digit;
        }

        return sum;
    }
}