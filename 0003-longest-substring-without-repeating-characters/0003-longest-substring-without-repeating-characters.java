import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Add current character to the map
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // Shrink the window until there are no duplicates
            while (map.get(ch) > 1) {

                char leftChar = s.charAt(left);

                map.put(leftChar, map.get(leftChar) - 1);

                left++;
            }

            // Update the maximum length
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}