class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        int left = 0;
        int right = 0;

        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];

        // Count characters of p
        for (char ch : p.toCharArray()) {
            pFreq[ch - 'a']++;
        }

        // Sliding window
        while (right < s.length()) {

            // Add right character
            windowFreq[s.charAt(right) - 'a']++;

            // If window becomes bigger than p
            if (right - left + 1 > p.length()) {
                windowFreq[s.charAt(left) - 'a']--;
                left++;
            }

            // If window size is exactly p.length()
            if (right - left + 1 == p.length()) {

                // Check whether window is an anagram
                if (Arrays.equals(pFreq, windowFreq)) {
                    result.add(left);
                }
            }

            right++;
        }

        return result;
    }
}