
class Solution {
    public int longestPalindrome(String s) {
        int[] arr = new int[128];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)]++;
        }

        int count = 0;
        boolean odd = false;

        for (int i = 0; i < 128; i++) {
            count += (arr[i] / 2) * 2;

            if (arr[i] % 2 == 1) {
                odd = true;
            }
        }

        if (odd) {
            count++;
        }

        return count;
    }
}
