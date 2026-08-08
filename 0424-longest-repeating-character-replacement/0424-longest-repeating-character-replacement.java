import java.util.HashMap;

class Solution {
    public int characterReplacement(String s, int k) {

        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;
        int ans = 0;
        int maxFrequency = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Add current character
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // Highest frequency in the window
            maxFrequency = Math.max(maxFrequency, map.get(ch));

            // Too many characters need to be replaced
            while (right - left + 1 - maxFrequency > k) {

                char leftChar = s.charAt(left);

                // Remove left character
                map.put(leftChar, map.get(leftChar) - 1);

                left++;
            }

            // Store largest valid window
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}